package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ImageType} entity.
 */
public class ImageTypeDTO implements Serializable {
    
    private Long id;

    private Long typeId;

    private String type;

    private Long statusId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(o instanceof ImageTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((ImageTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageTypeDTO{" +
            "id=" + getId() +
            ", typeId=" + getTypeId() +
            ", type='" + getType() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
