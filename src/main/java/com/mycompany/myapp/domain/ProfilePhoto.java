package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A ProfilePhoto.
 */
@Entity
@Table(name = "profile_photo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfilePhoto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "user_id", length = 36)
    private UUID userId;

    @Column(name = "path")
    private String path;

    @Column(name = "status_id")
    private Long statusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public ProfilePhoto userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public ProfilePhoto path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getStatusId() {
        return statusId;
    }

    public ProfilePhoto statusId(Long statusId) {
        this.statusId = statusId;
        return this;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfilePhoto)) {
            return false;
        }
        return id != null && id.equals(((ProfilePhoto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfilePhoto{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", path='" + getPath() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
