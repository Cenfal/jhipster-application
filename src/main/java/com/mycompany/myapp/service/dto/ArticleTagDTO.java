package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ArticleTag} entity.
 */
public class ArticleTagDTO implements Serializable {
    
    private Long id;

    private UUID articleId;

    private Long tagId;

    
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

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleTagDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleTagDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleTagDTO{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", tagId=" + getTagId() +
            "}";
    }
}
