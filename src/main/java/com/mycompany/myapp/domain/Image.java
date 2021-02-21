package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Image.
 */
@Entity
@Table(name = "image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id")
    private String articleId;

    @Column(name = "path")
    private String path;

    @Column(name = "image_type")
    private Long imageType;

    @Column(name = "status_id")
    private Long statusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public Image articleId(String articleId) {
        this.articleId = articleId;
        return this;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getPath() {
        return path;
    }

    public Image path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getImageType() {
        return imageType;
    }

    public Image imageType(Long imageType) {
        this.imageType = imageType;
        return this;
    }

    public void setImageType(Long imageType) {
        this.imageType = imageType;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Image statusId(Long statusId) {
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
        if (!(o instanceof Image)) {
            return false;
        }
        return id != null && id.equals(((Image) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", path='" + getPath() + "'" +
            ", imageType=" + getImageType() +
            ", statusId=" + getStatusId() +
            "}";
    }
}
