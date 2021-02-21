<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.articleLanguage.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.articleLanguage.home.createOrEditLabel')">Create or edit a ArticleLanguage</h2>
                <div>
                    <div class="form-group" v-if="articleLanguage.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="articleLanguage.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.articleLanguage.articleId')" for="article-language-articleId">Article Id</label>
                        <input type="text" class="form-control" name="articleId" id="article-language-articleId"
                            :class="{'valid': !$v.articleLanguage.articleId.$invalid, 'invalid': $v.articleLanguage.articleId.$invalid }" v-model="$v.articleLanguage.articleId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.articleLanguage.languageId')" for="article-language-languageId">Language Id</label>
                        <input type="number" class="form-control" name="languageId" id="article-language-languageId"
                            :class="{'valid': !$v.articleLanguage.languageId.$invalid, 'invalid': $v.articleLanguage.languageId.$invalid }" v-model.number="$v.articleLanguage.languageId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.articleLanguage.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./article-language-update.component.ts">
</script>
