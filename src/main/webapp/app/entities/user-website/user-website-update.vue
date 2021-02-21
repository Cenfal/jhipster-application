<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.userWebsite.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.userWebsite.home.createOrEditLabel')">Create or edit a UserWebsite</h2>
                <div>
                    <div class="form-group" v-if="userWebsite.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="userWebsite.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.userWebsite.code')" for="user-website-code">Code</label>
                        <input type="number" class="form-control" name="code" id="user-website-code"
                            :class="{'valid': !$v.userWebsite.code.$invalid, 'invalid': $v.userWebsite.code.$invalid }" v-model.number="$v.userWebsite.code.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.userWebsite.name')" for="user-website-name">Name</label>
                        <input type="text" class="form-control" name="name" id="user-website-name"
                            :class="{'valid': !$v.userWebsite.name.$invalid, 'invalid': $v.userWebsite.name.$invalid }" v-model="$v.userWebsite.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.userWebsite.surname')" for="user-website-surname">Surname</label>
                        <input type="text" class="form-control" name="surname" id="user-website-surname"
                            :class="{'valid': !$v.userWebsite.surname.$invalid, 'invalid': $v.userWebsite.surname.$invalid }" v-model="$v.userWebsite.surname.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.userWebsite.formattedName')" for="user-website-formattedName">Formatted Name</label>
                        <input type="text" class="form-control" name="formattedName" id="user-website-formattedName"
                            :class="{'valid': !$v.userWebsite.formattedName.$invalid, 'invalid': $v.userWebsite.formattedName.$invalid }" v-model="$v.userWebsite.formattedName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.userWebsite.userName')" for="user-website-userName">User Name</label>
                        <input type="text" class="form-control" name="userName" id="user-website-userName"
                            :class="{'valid': !$v.userWebsite.userName.$invalid, 'invalid': $v.userWebsite.userName.$invalid }" v-model="$v.userWebsite.userName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.userWebsite.statusId')" for="user-website-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="user-website-statusId"
                            :class="{'valid': !$v.userWebsite.statusId.$invalid, 'invalid': $v.userWebsite.statusId.$invalid }" v-model.number="$v.userWebsite.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.userWebsite.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./user-website-update.component.ts">
</script>
