<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.comment.home.title')" id="comment-heading">Comments</span>
            <router-link :to="{name: 'CommentCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-comment">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.comment.home.createLabel')">
                    Create a new Comment
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
        <div class="alert alert-warning" v-if="!isFetching && comments && comments.length === 0">
            <span v-text="$t('jhipsterApplicationApp.comment.home.notFound')">No comments found</span>
        </div>
        <div class="table-responsive" v-if="comments && comments.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('comment')"><span v-text="$t('jhipsterApplicationApp.comment.comment')">Comment</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'comment'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userID')"><span v-text="$t('jhipsterApplicationApp.comment.userID')">User ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userID'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('articleId')"><span v-text="$t('jhipsterApplicationApp.comment.articleId')">Article Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'articleId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('statusId')"><span v-text="$t('jhipsterApplicationApp.comment.statusId')">Status Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="comment in comments"
                    :key="comment.id">
                    <td>
                        <router-link :to="{name: 'CommentView', params: {commentId: comment.id}}">{{comment.id}}</router-link>
                    </td>
                    <td>{{comment.comment}}</td>
                    <td>{{comment.userID}}</td>
                    <td>{{comment.articleId}}</td>
                    <td>{{comment.statusId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CommentView', params: {commentId: comment.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'CommentEdit', params: {commentId: comment.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(comment)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.comment.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-comment-heading" v-text="$t('jhipsterApplicationApp.comment.delete.question', {'id': removeId})">Are you sure you want to delete this Comment?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-comment" v-text="$t('entity.action.delete')" v-on:click="removeComment()">Delete</button>
            </div>
        </b-modal>
        <div v-show="comments && comments.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./comment.component.ts">
</script>
