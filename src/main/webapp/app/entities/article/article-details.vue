<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="article">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.article.detail.title')">Article</span> {{article.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.article.code')">Code</span>
                    </dt>
                    <dd>
                        <span>{{article.code}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.article.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{article.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.article.text')">Text</span>
                    </dt>
                    <dd>
                        <span>{{article.text}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.article.userId')">User Id</span>
                    </dt>
                    <dd>
                        <span>{{article.userId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.article.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{article.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="article.id" :to="{name: 'ArticleEdit', params: {articleId: article.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./article-details.component.ts">
</script>
