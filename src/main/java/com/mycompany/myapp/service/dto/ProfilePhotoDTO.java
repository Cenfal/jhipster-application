package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ProfilePhoto} entity.
 */
public class ProfilePhotoDTO implements Serializable {
    
    private Long id;

    private UUID userId;

    private String path;

    private Long statusId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfilePhotoDTO)) {
            return false;
        }

        return id != null && id.equals(((ProfilePhotoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfilePhotoDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", path='" + getPath() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
