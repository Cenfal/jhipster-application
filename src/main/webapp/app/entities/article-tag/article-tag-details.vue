<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="articleTag">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.articleTag.detail.title')">ArticleTag</span> {{articleTag.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleTag.articleId')">Article Id</span>
                    </dt>
                    <dd>
                        <span>{{articleTag.articleId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleTag.tagId')">Tag Id</span>
                    </dt>
                    <dd>
                        <span>{{articleTag.tagId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="articleTag.id" :to="{name: 'ArticleTagEdit', params: {articleTagId: articleTag.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./article-tag-details.component.ts">
</script>
