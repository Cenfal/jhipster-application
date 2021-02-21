package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "user_id", length = 36)
    private UUID userId;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "preferred")
    private Boolean preferred;

    @Type(type = "uuid-char")
    @Column(name = "address_id", length = 36)
    private UUID addressId;

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

    public Contact userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public Contact mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public Contact phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isPreferred() {
        return preferred;
    }

    public Contact preferred(Boolean preferred) {
        this.preferred = preferred;
        return this;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public Contact addressId(UUID addressId) {
        this.addressId = addressId;
        return this;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Contact statusId(Long statusId) {
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
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", mail='" + getMail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", preferred='" + isPreferred() + "'" +
            ", addressId='" + getAddressId() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
