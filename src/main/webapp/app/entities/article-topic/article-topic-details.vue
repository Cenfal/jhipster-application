<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="articleTopic">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.articleTopic.detail.title')">ArticleTopic</span> {{articleTopic.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleTopic.articleId')">Article Id</span>
                    </dt>
                    <dd>
                        <span>{{articleTopic.articleId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleTopic.topicId')">Topic Id</span>
                    </dt>
                    <dd>
                        <span>{{articleTopic.topicId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="articleTopic.id" :to="{name: 'ArticleTopicEdit', params: {articleTopicId: articleTopic.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./article-topic-details.component.ts">
</script>
