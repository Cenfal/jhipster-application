<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.comment.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.comment.home.createOrEditLabel')">Create or edit a Comment</h2>
                <div>
                    <div class="form-group" v-if="comment.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="comment.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.comment.comment')" for="comment-comment">Comment</label>
                        <input type="text" class="form-control" name="comment" id="comment-comment"
                            :class="{'valid': !$v.comment.comment.$invalid, 'invalid': $v.comment.comment.$invalid }" v-model="$v.comment.comment.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.comment.userID')" for="comment-userID">User ID</label>
                        <input type="text" class="form-control" name="userID" id="comment-userID"
                            :class="{'valid': !$v.comment.userID.$invalid, 'invalid': $v.comment.userID.$invalid }" v-model="$v.comment.userID.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.comment.articleId')" for="comment-articleId">Article Id</label>
                        <input type="text" class="form-control" name="articleId" id="comment-articleId"
                            :class="{'valid': !$v.comment.articleId.$invalid, 'invalid': $v.comment.articleId.$invalid }" v-model="$v.comment.articleId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.comment.statusId')" for="comment-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="comment-statusId"
                            :class="{'valid': !$v.comment.statusId.$invalid, 'invalid': $v.comment.statusId.$invalid }" v-model.number="$v.comment.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.comment.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./comment-update.component.ts">
</script>
