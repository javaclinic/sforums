
package com.marakana.sforums.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category extends IdentifiableEntity {

    private static final long serialVersionUID = 8723299921811314495L;

    private String name;

    private String description;

    @Column(length = 64, unique = true, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getName() == null) {
            return false;
        } else if (o instanceof Category) {
            Category that = (Category) o;
            return this.getName().equals(that.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName() == null ? System.identityHashCode(this) : 17 * this.getName()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
