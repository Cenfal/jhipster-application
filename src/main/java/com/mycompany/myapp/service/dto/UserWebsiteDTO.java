package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.UserWebsite} entity.
 */
public class UserWebsiteDTO implements Serializable {
    
    private Long id;

    private Long code;

    private String name;

    private String surname;

    private String formattedName;

    private String userName;

    private Long statusId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (!(o instanceof UserWebsiteDTO)) {
            return false;
        }

        return id != null && id.equals(((UserWebsiteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserWebsiteDTO{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", formattedName='" + getFormattedName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
