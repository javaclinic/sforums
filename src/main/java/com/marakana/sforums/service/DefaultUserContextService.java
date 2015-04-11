package com.marakana.sforums.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.marakana.sforums.domain.User;
import com.marakana.sforums.security.DefaultUserDetails;

public class DefaultUserContextService implements UserContextService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserContextService.class);

    @Override
    public User getUserFromContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            logger.trace("Cannot get authenticated contact. Security context is not initialized.");
            return null;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            logger.trace("Cannot get authenticated contact. Authentication info is not present.");
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            logger.trace("Cannot get authenticated contact. Principal info is not present.");
            return null;
        }
        if (principal instanceof DefaultUserDetails) {
            return ((DefaultUserDetails)principal).getUser();
        } else if (principal instanceof String) {
            logger.trace("Not authenticated: {}", principal);
        } else {
            logger.debug("Cannot get authenticated contact from authenticated principal: {}", principal);
        }
        return null;
    }

    @Override
    public void addUserToContext(User user, String password) {
        User authenticatedUser = this.getUserFromContext();
        if (authenticatedUser == null) {
            if (user == null) {
                throw new NullPointerException("User must not be null");
            } else if (user.isEnabled()) {
                logger.trace("Logging in {}", user);
                DefaultUserDetails userDetails = new DefaultUserDetails(user);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities()));
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Not logging in disabled user: " + user);
                }
            }
        } else if (authenticatedUser.equals(user)) {
            logger.debug("User {} already logged in", user);
        } else {
            throw new IllegalStateException("Already logged in as another user: " + user);
        }
    }

}
