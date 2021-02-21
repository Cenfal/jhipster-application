<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.article.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.article.home.createOrEditLabel')">Create or edit a Article</h2>
                <div>
                    <div class="form-group" v-if="article.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="article.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.article.code')" for="article-code">Code</label>
                        <input type="number" class="form-control" name="code" id="article-code"
                            :class="{'valid': !$v.article.code.$invalid, 'invalid': $v.article.code.$invalid }" v-model.number="$v.article.code.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.article.title')" for="article-title">Title</label>
                        <input type="text" class="form-control" name="title" id="article-title"
                            :class="{'valid': !$v.article.title.$invalid, 'invalid': $v.article.title.$invalid }" v-model="$v.article.title.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.article.text')" for="article-text">Text</label>
                        <input type="text" class="form-control" name="text" id="article-text"
                            :class="{'valid': !$v.article.text.$invalid, 'invalid': $v.article.text.$invalid }" v-model="$v.article.text.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.article.userId')" for="article-userId">User Id</label>
                        <input type="text" class="form-control" name="userId" id="article-userId"
                            :class="{'valid': !$v.article.userId.$invalid, 'invalid': $v.article.userId.$invalid }" v-model="$v.article.userId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.article.statusId')" for="article-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="article-statusId"
                            :class="{'valid': !$v.article.statusId.$invalid, 'invalid': $v.article.statusId.$invalid }" v-model.number="$v.article.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.article.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./article-update.component.ts">
</script>
