<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="articleHistory">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.articleHistory.detail.title')">ArticleHistory</span> {{articleHistory.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleHistory.articleId')">Article Id</span>
                    </dt>
                    <dd>
                        <span>{{articleHistory.articleId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleHistory.updateTime')">Update Time</span>
                    </dt>
                    <dd>
                        <span>{{articleHistory.updateTime}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="articleHistory.id" :to="{name: 'ArticleHistoryEdit', params: {articleHistoryId: articleHistory.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./article-history-details.component.ts">
</script>
