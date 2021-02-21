<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.topic.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.topic.home.createOrEditLabel')">Create or edit a Topic</h2>
                <div>
                    <div class="form-group" v-if="topic.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="topic.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.topic.name')" for="topic-name">Name</label>
                        <input type="text" class="form-control" name="name" id="topic-name"
                            :class="{'valid': !$v.topic.name.$invalid, 'invalid': $v.topic.name.$invalid }" v-model="$v.topic.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.topic.statusId')" for="topic-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="topic-statusId"
                            :class="{'valid': !$v.topic.statusId.$invalid, 'invalid': $v.topic.statusId.$invalid }" v-model.number="$v.topic.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.topic.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./topic-update.component.ts">
</script>
