package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A CommentHistory.
 */
@Entity
@Table(name = "comment_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "comment_id", length = 36)
    private UUID commentId;

    @Column(name = "update_time")
    private Long updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public CommentHistory commentId(UUID commentId) {
        this.commentId = commentId;
        return this;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public CommentHistory updateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentHistory)) {
            return false;
        }
        return id != null && id.equals(((CommentHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentHistory{" +
            "id=" + getId() +
            ", commentId='" + getCommentId() + "'" +
            ", updateTime=" + getUpdateTime() +
            "}";
    }
}
