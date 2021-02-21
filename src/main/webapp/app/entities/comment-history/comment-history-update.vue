<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.commentHistory.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.commentHistory.home.createOrEditLabel')">Create or edit a CommentHistory</h2>
                <div>
                    <div class="form-group" v-if="commentHistory.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="commentHistory.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.commentHistory.commentId')" for="comment-history-commentId">Comment Id</label>
                        <input type="text" class="form-control" name="commentId" id="comment-history-commentId"
                            :class="{'valid': !$v.commentHistory.commentId.$invalid, 'invalid': $v.commentHistory.commentId.$invalid }" v-model="$v.commentHistory.commentId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.commentHistory.updateTime')" for="comment-history-updateTime">Update Time</label>
                        <input type="number" class="form-control" name="updateTime" id="comment-history-updateTime"
                            :class="{'valid': !$v.commentHistory.updateTime.$invalid, 'invalid': $v.commentHistory.updateTime.$invalid }" v-model.number="$v.commentHistory.updateTime.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.commentHistory.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./comment-history-update.component.ts">
</script>
