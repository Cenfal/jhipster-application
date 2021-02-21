<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.imageType.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.imageType.home.createOrEditLabel')">Create or edit a ImageType</h2>
                <div>
                    <div class="form-group" v-if="imageType.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="imageType.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.imageType.typeId')" for="image-type-typeId">Type Id</label>
                        <input type="number" class="form-control" name="typeId" id="image-type-typeId"
                            :class="{'valid': !$v.imageType.typeId.$invalid, 'invalid': $v.imageType.typeId.$invalid }" v-model.number="$v.imageType.typeId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.imageType.type')" for="image-type-type">Type</label>
                        <input type="text" class="form-control" name="type" id="image-type-type"
                            :class="{'valid': !$v.imageType.type.$invalid, 'invalid': $v.imageType.type.$invalid }" v-model="$v.imageType.type.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.imageType.statusId')" for="image-type-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="image-type-statusId"
                            :class="{'valid': !$v.imageType.statusId.$invalid, 'invalid': $v.imageType.statusId.$invalid }" v-model.number="$v.imageType.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.imageType.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./image-type-update.component.ts">
</script>
