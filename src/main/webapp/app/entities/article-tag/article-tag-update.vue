<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.articleTag.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.articleTag.home.createOrEditLabel')">Create or edit a ArticleTag</h2>
                <div>
                    <div class="form-group" v-if="articleTag.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="articleTag.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.articleTag.articleId')" for="article-tag-articleId">Article Id</label>
                        <input type="text" class="form-control" name="articleId" id="article-tag-articleId"
                            :class="{'valid': !$v.articleTag.articleId.$invalid, 'invalid': $v.articleTag.articleId.$invalid }" v-model="$v.articleTag.articleId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.articleTag.tagId')" for="article-tag-tagId">Tag Id</label>
                        <input type="number" class="form-control" name="tagId" id="article-tag-tagId"
                            :class="{'valid': !$v.articleTag.tagId.$invalid, 'invalid': $v.articleTag.tagId.$invalid }" v-model.number="$v.articleTag.tagId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.articleTag.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./article-tag-update.component.ts">
</script>
