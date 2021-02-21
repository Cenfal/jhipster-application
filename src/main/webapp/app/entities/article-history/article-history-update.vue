<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.articleHistory.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.articleHistory.home.createOrEditLabel')">Create or edit a ArticleHistory</h2>
                <div>
                    <div class="form-group" v-if="articleHistory.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="articleHistory.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.articleHistory.articleId')" for="article-history-articleId">Article Id</label>
                        <input type="text" class="form-control" name="articleId" id="article-history-articleId"
                            :class="{'valid': !$v.articleHistory.articleId.$invalid, 'invalid': $v.articleHistory.articleId.$invalid }" v-model="$v.articleHistory.articleId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.articleHistory.updateTime')" for="article-history-updateTime">Update Time</label>
                        <input type="number" class="form-control" name="updateTime" id="article-history-updateTime"
                            :class="{'valid': !$v.articleHistory.updateTime.$invalid, 'invalid': $v.articleHistory.updateTime.$invalid }" v-model.number="$v.articleHistory.updateTime.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.articleHistory.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./article-history-update.component.ts">
</script>
