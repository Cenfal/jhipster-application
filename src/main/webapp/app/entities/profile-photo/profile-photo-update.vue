<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.profilePhoto.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.profilePhoto.home.createOrEditLabel')">Create or edit a ProfilePhoto</h2>
                <div>
                    <div class="form-group" v-if="profilePhoto.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="profilePhoto.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.profilePhoto.userId')" for="profile-photo-userId">User Id</label>
                        <input type="text" class="form-control" name="userId" id="profile-photo-userId"
                            :class="{'valid': !$v.profilePhoto.userId.$invalid, 'invalid': $v.profilePhoto.userId.$invalid }" v-model="$v.profilePhoto.userId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.profilePhoto.path')" for="profile-photo-path">Path</label>
                        <input type="text" class="form-control" name="path" id="profile-photo-path"
                            :class="{'valid': !$v.profilePhoto.path.$invalid, 'invalid': $v.profilePhoto.path.$invalid }" v-model="$v.profilePhoto.path.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.profilePhoto.statusId')" for="profile-photo-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="profile-photo-statusId"
                            :class="{'valid': !$v.profilePhoto.statusId.$invalid, 'invalid': $v.profilePhoto.statusId.$invalid }" v-model.number="$v.profilePhoto.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.profilePhoto.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./profile-photo-update.component.ts">
</script>
