package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserWebsite.
 */
@Entity
@Table(name = "user_website")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserWebsite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Long code;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "formatted_name")
    private String formattedName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "status_id")
    private Long statusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public UserWebsite code(Long code) {
        this.code = code;
        return this;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public UserWebsite name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public UserWebsite surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public UserWebsite formattedName(String formattedName) {
        this.formattedName = formattedName;
        return this;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getUserName() {
        return userName;
    }

    public UserWebsite userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public UserWebsite statusId(Long statusId) {
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
        if (!(o instanceof UserWebsite)) {
            return false;
        }
        return id != null && id.equals(((UserWebsite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserWebsite{" +
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
