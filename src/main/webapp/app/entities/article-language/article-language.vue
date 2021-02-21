<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.articleLanguage.home.title')" id="article-language-heading">Article Languages</span>
            <router-link :to="{name: 'ArticleLanguageCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-article-language">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.articleLanguage.home.createLabel')">
                    Create a new Article Language
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
        <div class="alert alert-warning" v-if="!isFetching && articleLanguages && articleLanguages.length === 0">
            <span v-text="$t('jhipsterApplicationApp.articleLanguage.home.notFound')">No articleLanguages found</span>
        </div>
        <div class="table-responsive" v-if="articleLanguages && articleLanguages.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.articleLanguage.articleId')">Article Id</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.articleLanguage.languageId')">Language Id</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="articleLanguage in articleLanguages"
                    :key="articleLanguage.id">
                    <td>
                        <router-link :to="{name: 'ArticleLanguageView', params: {articleLanguageId: articleLanguage.id}}">{{articleLanguage.id}}</router-link>
                    </td>
                    <td>{{articleLanguage.articleId}}</td>
                    <td>{{articleLanguage.languageId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ArticleLanguageView', params: {articleLanguageId: articleLanguage.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ArticleLanguageEdit', params: {articleLanguageId: articleLanguage.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(articleLanguage)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.articleLanguage.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-articleLanguage-heading" v-text="$t('jhipsterApplicationApp.articleLanguage.delete.question', {'id': removeId})">Are you sure you want to delete this Article Language?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-articleLanguage" v-text="$t('entity.action.delete')" v-on:click="removeArticleLanguage()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./article-language.component.ts">
</script>
