<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.articleTopic.home.title')" id="article-topic-heading">Article Topics</span>
            <router-link :to="{name: 'ArticleTopicCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-article-topic">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.articleTopic.home.createLabel')">
                    Create a new Article Topic
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
        <div class="alert alert-warning" v-if="!isFetching && articleTopics && articleTopics.length === 0">
            <span v-text="$t('jhipsterApplicationApp.articleTopic.home.notFound')">No articleTopics found</span>
        </div>
        <div class="table-responsive" v-if="articleTopics && articleTopics.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.articleTopic.articleId')">Article Id</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.articleTopic.topicId')">Topic Id</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="articleTopic in articleTopics"
                    :key="articleTopic.id">
                    <td>
                        <router-link :to="{name: 'ArticleTopicView', params: {articleTopicId: articleTopic.id}}">{{articleTopic.id}}</router-link>
                    </td>
                    <td>{{articleTopic.articleId}}</td>
                    <td>{{articleTopic.topicId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ArticleTopicView', params: {articleTopicId: articleTopic.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ArticleTopicEdit', params: {articleTopicId: articleTopic.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(articleTopic)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.articleTopic.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-articleTopic-heading" v-text="$t('jhipsterApplicationApp.articleTopic.delete.question', {'id': removeId})">Are you sure you want to delete this Article Topic?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-articleTopic" v-text="$t('entity.action.delete')" v-on:click="removeArticleTopic()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./article-topic.component.ts">
</script>
