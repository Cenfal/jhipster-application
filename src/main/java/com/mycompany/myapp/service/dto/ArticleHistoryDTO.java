package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ArticleHistory} entity.
 */
public class ArticleHistoryDTO implements Serializable {
    
    private Long id;

    private UUID articleId;

    private Long updateTime;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getArticleId() {
        return articleId;
    }

    public void setArticleId(UUID articleId) {
        this.articleId = articleId;
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
        if (!(o instanceof ArticleHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleHistoryDTO{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", updateTime=" + getUpdateTime() +
            "}";
    }
}
