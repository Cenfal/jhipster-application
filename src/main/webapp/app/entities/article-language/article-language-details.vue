<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="articleLanguage">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.articleLanguage.detail.title')">ArticleLanguage</span> {{articleLanguage.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleLanguage.articleId')">Article Id</span>
                    </dt>
                    <dd>
                        <span>{{articleLanguage.articleId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.articleLanguage.languageId')">Language Id</span>
                    </dt>
                    <dd>
                        <span>{{articleLanguage.languageId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="articleLanguage.id" :to="{name: 'ArticleLanguageEdit', params: {articleLanguageId: articleLanguage.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./article-language-details.component.ts">
</script>
