<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="commentHistory">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.commentHistory.detail.title')">CommentHistory</span> {{commentHistory.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.commentHistory.commentId')">Comment Id</span>
                    </dt>
                    <dd>
                        <span>{{commentHistory.commentId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.commentHistory.updateTime')">Update Time</span>
                    </dt>
                    <dd>
                        <span>{{commentHistory.updateTime}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="commentHistory.id" :to="{name: 'CommentHistoryEdit', params: {commentHistoryId: commentHistory.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./comment-history-details.component.ts">
</script>
