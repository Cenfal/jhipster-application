package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Topic} entity.
 */
public class TopicDTO implements Serializable {
    
    private Long id;

    private String name;

    private Long statusId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof TopicDTO)) {
            return false;
        }

        return id != null && id.equals(((TopicDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TopicDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
