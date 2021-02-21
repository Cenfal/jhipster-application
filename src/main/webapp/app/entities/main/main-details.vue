<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="main">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.main.detail.title')">Main</span> {{main.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.createDate')">Create Date</span>
                    </dt>
                    <dd>
                        <span>{{main.createDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.updateDate')">Update Date</span>
                    </dt>
                    <dd>
                        <span>{{main.updateDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.endDate')">End Date</span>
                    </dt>
                    <dd>
                        <span>{{main.endDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.createdBy')">Created By</span>
                    </dt>
                    <dd>
                        <span>{{main.createdBy}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.updatedBy')">Updated By</span>
                    </dt>
                    <dd>
                        <span>{{main.updatedBy}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.contact')">Contact</span>
                    </dt>
                    <dd>
                        <div v-if="main.contactId">
                            <router-link :to="{name: 'ContactView', params: {contactId: main.contactId}}">{{main.contactId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.userWebsite')">User Website</span>
                    </dt>
                    <dd>
                        <div v-if="main.userWebsiteId">
                            <router-link :to="{name: 'UserWebsiteView', params: {userWebsiteId: main.userWebsiteId}}">{{main.userWebsiteId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.profilePhoto')">Profile Photo</span>
                    </dt>
                    <dd>
                        <div v-if="main.profilePhotoId">
                            <router-link :to="{name: 'ProfilePhotoView', params: {profilePhotoId: main.profilePhotoId}}">{{main.profilePhotoId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.image')">Image</span>
                    </dt>
                    <dd>
                        <div v-if="main.imageId">
                            <router-link :to="{name: 'ImageView', params: {imageId: main.imageId}}">{{main.imageId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.articleHistory')">Article History</span>
                    </dt>
                    <dd>
                        <div v-if="main.articleHistoryId">
                            <router-link :to="{name: 'ArticleHistoryView', params: {articleHistoryId: main.articleHistoryId}}">{{main.articleHistoryId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.commentHistory')">Comment History</span>
                    </dt>
                    <dd>
                        <div v-if="main.commentHistoryId">
                            <router-link :to="{name: 'CommentHistoryView', params: {commentHistoryId: main.commentHistoryId}}">{{main.commentHistoryId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.article')">Article</span>
                    </dt>
                    <dd>
                        <div v-if="main.articleId">
                            <router-link :to="{name: 'ArticleView', params: {articleId: main.articleId}}">{{main.articleId}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.main.comment')">Comment</span>
                    </dt>
                    <dd>
                        <div v-if="main.commentId">
                            <router-link :to="{name: 'CommentView', params: {commentId: main.commentId}}">{{main.commentId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="main.id" :to="{name: 'MainEdit', params: {mainId: main.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./main-details.component.ts">
</script>
