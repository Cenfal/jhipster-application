<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.image.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.image.home.createOrEditLabel')">Create or edit a Image</h2>
                <div>
                    <div class="form-group" v-if="image.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="image.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.image.articleId')" for="image-articleId">Article Id</label>
                        <input type="text" class="form-control" name="articleId" id="image-articleId"
                            :class="{'valid': !$v.image.articleId.$invalid, 'invalid': $v.image.articleId.$invalid }" v-model="$v.image.articleId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.image.path')" for="image-path">Path</label>
                        <input type="text" class="form-control" name="path" id="image-path"
                            :class="{'valid': !$v.image.path.$invalid, 'invalid': $v.image.path.$invalid }" v-model="$v.image.path.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.image.imageType')" for="image-imageType">Image Type</label>
                        <input type="number" class="form-control" name="imageType" id="image-imageType"
                            :class="{'valid': !$v.image.imageType.$invalid, 'invalid': $v.image.imageType.$invalid }" v-model.number="$v.image.imageType.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.image.statusId')" for="image-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="image-statusId"
                            :class="{'valid': !$v.image.statusId.$invalid, 'invalid': $v.image.statusId.$invalid }" v-model.number="$v.image.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.image.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./image-update.component.ts">
</script>
