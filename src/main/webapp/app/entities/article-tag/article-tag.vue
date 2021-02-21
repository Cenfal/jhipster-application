<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.articleTag.home.title')" id="article-tag-heading">Article Tags</span>
            <router-link :to="{name: 'ArticleTagCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-article-tag">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.articleTag.home.createLabel')">
                    Create a new Article Tag
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
        <div class="alert alert-warning" v-if="!isFetching && articleTags && articleTags.length === 0">
            <span v-text="$t('jhipsterApplicationApp.articleTag.home.notFound')">No articleTags found</span>
        </div>
        <div class="table-responsive" v-if="articleTags && articleTags.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.articleTag.articleId')">Article Id</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.articleTag.tagId')">Tag Id</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="articleTag in articleTags"
                    :key="articleTag.id">
                    <td>
                        <router-link :to="{name: 'ArticleTagView', params: {articleTagId: articleTag.id}}">{{articleTag.id}}</router-link>
                    </td>
                    <td>{{articleTag.articleId}}</td>
                    <td>{{articleTag.tagId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ArticleTagView', params: {articleTagId: articleTag.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ArticleTagEdit', params: {articleTagId: articleTag.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(articleTag)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.articleTag.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-articleTag-heading" v-text="$t('jhipsterApplicationApp.articleTag.delete.question', {'id': removeId})">Are you sure you want to delete this Article Tag?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-articleTag" v-text="$t('entity.action.delete')" v-on:click="removeArticleTag()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./article-tag.component.ts">
</script>
