package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ArticleTopic} entity.
 */
public class ArticleTopicDTO implements Serializable {
    
    private Long id;

    private UUID articleId;

    private Long topicId;

    
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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleTopicDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleTopicDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleTopicDTO{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", topicId=" + getTopicId() +
            "}";
    }
}
