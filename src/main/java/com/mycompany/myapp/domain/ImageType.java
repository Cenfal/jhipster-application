package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImageType.
 */
@Entity
@Table(name = "image_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImageType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type")
    private String type;

    @Column(name = "status_id")
    private Long statusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public ImageType typeId(Long typeId) {
        this.typeId = typeId;
        return this;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public ImageType type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStatusId() {
        return statusId;
    }

    public ImageType statusId(Long statusId) {
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
        if (!(o instanceof ImageType)) {
            return false;
        }
        return id != null && id.equals(((ImageType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageType{" +
            "id=" + getId() +
            ", typeId=" + getTypeId() +
            ", type='" + getType() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
