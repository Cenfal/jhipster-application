package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A ArticleLanguage.
 */
@Entity
@Table(name = "article_language")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArticleLanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "article_id", length = 36)
    private UUID articleId;

    @Column(name = "language_id")
    private Long languageId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getArticleId() {
        return articleId;
    }

    public ArticleLanguage articleId(UUID articleId) {
        this.articleId = articleId;
        return this;
    }

    public void setArticleId(UUID articleId) {
        this.articleId = articleId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public ArticleLanguage languageId(Long languageId) {
        this.languageId = languageId;
        return this;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleLanguage)) {
            return false;
        }
        return id != null && id.equals(((ArticleLanguage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleLanguage{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", languageId=" + getLanguageId() +
            "}";
    }
}
