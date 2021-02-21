package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CommentHistory} entity.
 */
public class CommentHistoryDTO implements Serializable {
    
    private Long id;

    private UUID commentId;

    private Long updateTime;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((CommentHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentHistoryDTO{" +
            "id=" + getId() +
            ", commentId='" + getCommentId() + "'" +
            ", updateTime=" + getUpdateTime() +
            "}";
    }
}
