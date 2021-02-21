package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ArticleLanguage} entity.
 */
public class ArticleLanguageDTO implements Serializable {
    
    private Long id;

    private UUID articleId;

    private Long languageId;

    
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

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleLanguageDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleLanguageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleLanguageDTO{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", languageId=" + getLanguageId() +
            "}";
    }
}
