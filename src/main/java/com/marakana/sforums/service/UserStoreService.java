package com.marakana.sforums.service;

import com.marakana.sforums.domain.User;

public interface UserStoreService {
    public void store(User user, String password);
}
