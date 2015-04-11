
package com.marakana.sforums.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends TimestampedEntity {

    private static final long serialVersionUID = -6235390621369558140L;

    private Name name = new Name();

    private String organization;

    private String title;

    private String email;

    private String passwordDigest;

    private boolean admin = false;

    private boolean enabled = true;

    @Valid
    @NotNull
    @Embedded
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Size(max = 64)
    @Column(length = 64)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Email
    @Size(max = 64)
    @NotEmpty
    @NotNull
    @Column(length = 64, unique = true, nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 80, nullable = false)
    public String getPasswordDigest() {
        return this.passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    @Size(max = 64)
    @Column(length = 64)
    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Column(nullable = false, columnDefinition = "BIT")
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Column(nullable = false, columnDefinition = "BIT")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getEmail() == null) {
            return false;
        } else if (o instanceof User) {
            User that = (User) o;
            return this.getEmail().equals(that.getEmail());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getEmail() == null ? System.identityHashCode(this) : 17 * this.getEmail()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getName().toString();
    }
}
