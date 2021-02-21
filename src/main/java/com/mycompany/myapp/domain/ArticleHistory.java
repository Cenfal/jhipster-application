package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A ArticleHistory.
 */
@Entity
@Table(name = "article_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArticleHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "article_id", length = 36)
    private UUID articleId;

    @Column(name = "update_time")
    private Long updateTime;

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

    public ArticleHistory articleId(UUID articleId) {
        this.articleId = articleId;
        return this;
    }

    public void setArticleId(UUID articleId) {
        this.articleId = articleId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public ArticleHistory updateTime(Long updateTime) {
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
        if (!(o instanceof ArticleHistory)) {
            return false;
        }
        return id != null && id.equals(((ArticleHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleHistory{" +
            "id=" + getId() +
            ", articleId='" + getArticleId() + "'" +
            ", updateTime=" + getUpdateTime() +
            "}";
    }
}
