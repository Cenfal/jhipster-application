package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Type(type = "uuid-char")
    @Column(name = "user_id", length = 36)
    private UUID userID;

    @Type(type = "uuid-char")
    @Column(name = "article_id", length = 36)
    private UUID articleId;

    @Column(name = "status_id")
    private Long statusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public Comment comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UUID getUserID() {
        return userID;
    }

    public Comment userID(UUID userID) {
        this.userID = userID;
        return this;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getArticleId() {
        return articleId;
    }

    public Comment articleId(UUID articleId) {
        this.articleId = articleId;
        return this;
    }

    public void setArticleId(UUID articleId) {
        this.articleId = articleId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Comment statusId(Long statusId) {
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
        if (!(o instanceof Comment)) {
            return false;
        }
        return id != null && id.equals(((Comment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", userID='" + getUserID() + "'" +
            ", articleId='" + getArticleId() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
