package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Main.
 */
@Entity
@Table(name = "main")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Main implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToOne
    @JoinColumn(unique = true)
    private Contact contact;

    @OneToOne
    @JoinColumn(unique = true)
    private UserWebsite userWebsite;

    @OneToOne
    @JoinColumn(unique = true)
    private ProfilePhoto profilePhoto;

    @OneToOne
    @JoinColumn(unique = true)
    private Image image;

    @OneToOne
    @JoinColumn(unique = true)
    private ArticleHistory articleHistory;

    @OneToOne
    @JoinColumn(unique = true)
    private CommentHistory commentHistory;

    @OneToOne
    @JoinColumn(unique = true)
    private Article article;

    @OneToOne
    @JoinColumn(unique = true)
    private Comment comment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Main createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public Main updateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Main endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Main createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Main updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Contact getContact() {
        return contact;
    }

    public Main contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public UserWebsite getUserWebsite() {
        return userWebsite;
    }

    public Main userWebsite(UserWebsite userWebsite) {
        this.userWebsite = userWebsite;
        return this;
    }

    public void setUserWebsite(UserWebsite userWebsite) {
        this.userWebsite = userWebsite;
    }

    public ProfilePhoto getProfilePhoto() {
        return profilePhoto;
    }

    public Main profilePhoto(ProfilePhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
        return this;
    }

    public void setProfilePhoto(ProfilePhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Image getImage() {
        return image;
    }

    public Main image(Image image) {
        this.image = image;
        return this;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArticleHistory getArticleHistory() {
        return articleHistory;
    }

    public Main articleHistory(ArticleHistory articleHistory) {
        this.articleHistory = articleHistory;
        return this;
    }

    public void setArticleHistory(ArticleHistory articleHistory) {
        this.articleHistory = articleHistory;
    }

    public CommentHistory getCommentHistory() {
        return commentHistory;
    }

    public Main commentHistory(CommentHistory commentHistory) {
        this.commentHistory = commentHistory;
        return this;
    }

    public void setCommentHistory(CommentHistory commentHistory) {
        this.commentHistory = commentHistory;
    }

    public Article getArticle() {
        return article;
    }

    public Main article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment getComment() {
        return comment;
    }

    public Main comment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Main)) {
            return false;
        }
        return id != null && id.equals(((Main) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Main{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
