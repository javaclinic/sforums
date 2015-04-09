
package com.marakana.sforums.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class IdentifiableEntity implements Serializable {

    private static final long serialVersionUID = -1859673334768297518L;

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public boolean isIdSet() {
        return this.id != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.isIdSet() && o instanceof IdentifiableEntity) {
            IdentifiableEntity that = (IdentifiableEntity) o;
            return this.getId().equals(that.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.isIdSet() ? 17 * this.id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " with id " + this.getId();
    }
}
