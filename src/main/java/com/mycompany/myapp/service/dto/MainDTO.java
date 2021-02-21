package com.mycompany.myapp.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Main} entity.
 */
public class MainDTO implements Serializable {
    
    private Long id;

    private LocalDate createDate;

    private LocalDate updateDate;

    private LocalDate endDate;

    private String createdBy;

    private String updatedBy;


    private Long contactId;

    private Long userWebsiteId;

    private Long profilePhotoId;

    private Long imageId;

    private Long articleHistoryId;

    private Long commentHistoryId;

    private Long articleId;

    private Long commentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getUserWebsiteId() {
        return userWebsiteId;
    }

    public void setUserWebsiteId(Long userWebsiteId) {
        this.userWebsiteId = userWebsiteId;
    }

    public Long getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(Long profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getArticleHistoryId() {
        return articleHistoryId;
    }

    public void setArticleHistoryId(Long articleHistoryId) {
        this.articleHistoryId = articleHistoryId;
    }

    public Long getCommentHistoryId() {
        return commentHistoryId;
    }

    public void setCommentHistoryId(Long commentHistoryId) {
        this.commentHistoryId = commentHistoryId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MainDTO)) {
            return false;
        }

        return id != null && id.equals(((MainDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MainDTO{" +
            "id=" + getId() +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", contactId=" + getContactId() +
            ", userWebsiteId=" + getUserWebsiteId() +
            ", profilePhotoId=" + getProfilePhotoId() +
            ", imageId=" + getImageId() +
            ", articleHistoryId=" + getArticleHistoryId() +
            ", commentHistoryId=" + getCommentHistoryId() +
            ", articleId=" + getArticleId() +
            ", commentId=" + getCommentId() +
            "}";
    }
}
