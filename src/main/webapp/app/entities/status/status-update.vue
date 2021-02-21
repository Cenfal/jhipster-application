<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.status.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.status.home.createOrEditLabel')">Create or edit a Status</h2>
                <div>
                    <div class="form-group" v-if="status.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="status.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.status.value')" for="status-value">Value</label>
                        <input type="text" class="form-control" name="value" id="status-value"
                            :class="{'valid': !$v.status.value.$invalid, 'invalid': $v.status.value.$invalid }" v-model="$v.status.value.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.status.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./status-update.component.ts">
</script>
