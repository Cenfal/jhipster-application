<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.main.home.title')" id="main-heading">Mains</span>
            <router-link :to="{name: 'MainCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-main">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.main.home.createLabel')">
                    Create a new Main
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && mains && mains.length === 0">
            <span v-text="$t('jhipsterApplicationApp.main.home.notFound')">No mains found</span>
        </div>
        <div class="table-responsive" v-if="mains && mains.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.createDate')">Create Date</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.updateDate')">Update Date</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.endDate')">End Date</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.createdBy')">Created By</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.updatedBy')">Updated By</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.contact')">Contact</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.userWebsite')">User Website</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.profilePhoto')">Profile Photo</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.image')">Image</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.articleHistory')">Article History</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.commentHistory')">Comment History</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.article')">Article</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.main.comment')">Comment</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="main in mains"
                    :key="main.id">
                    <td>
                        <router-link :to="{name: 'MainView', params: {mainId: main.id}}">{{main.id}}</router-link>
                    </td>
                    <td>{{main.createDate}}</td>
                    <td>{{main.updateDate}}</td>
                    <td>{{main.endDate}}</td>
                    <td>{{main.createdBy}}</td>
                    <td>{{main.updatedBy}}</td>
                    <td>
                        <div v-if="main.contactId">
                            <router-link :to="{name: 'ContactView', params: {contactId: main.contactId}}">{{main.contactId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.userWebsiteId">
                            <router-link :to="{name: 'UserWebsiteView', params: {userWebsiteId: main.userWebsiteId}}">{{main.userWebsiteId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.profilePhotoId">
                            <router-link :to="{name: 'ProfilePhotoView', params: {profilePhotoId: main.profilePhotoId}}">{{main.profilePhotoId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.imageId">
                            <router-link :to="{name: 'ImageView', params: {imageId: main.imageId}}">{{main.imageId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.articleHistoryId">
                            <router-link :to="{name: 'ArticleHistoryView', params: {articleHistoryId: main.articleHistoryId}}">{{main.articleHistoryId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.commentHistoryId">
                            <router-link :to="{name: 'CommentHistoryView', params: {commentHistoryId: main.commentHistoryId}}">{{main.commentHistoryId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.articleId">
                            <router-link :to="{name: 'ArticleView', params: {articleId: main.articleId}}">{{main.articleId}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="main.commentId">
                            <router-link :to="{name: 'CommentView', params: {commentId: main.commentId}}">{{main.commentId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MainView', params: {mainId: main.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MainEdit', params: {mainId: main.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(main)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhipsterApplicationApp.main.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-main-heading" v-text="$t('jhipsterApplicationApp.main.delete.question', {'id': removeId})">Are you sure you want to delete this Main?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-main" v-text="$t('entity.action.delete')" v-on:click="removeMain()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./main.component.ts">
</script>
