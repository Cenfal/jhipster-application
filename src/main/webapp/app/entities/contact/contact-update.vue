<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.contact.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.contact.home.createOrEditLabel')">Create or edit a Contact</h2>
                <div>
                    <div class="form-group" v-if="contact.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="contact.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.contact.userId')" for="contact-userId">User Id</label>
                        <input type="text" class="form-control" name="userId" id="contact-userId"
                            :class="{'valid': !$v.contact.userId.$invalid, 'invalid': $v.contact.userId.$invalid }" v-model="$v.contact.userId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.contact.mail')" for="contact-mail">Mail</label>
                        <input type="text" class="form-control" name="mail" id="contact-mail"
                            :class="{'valid': !$v.contact.mail.$invalid, 'invalid': $v.contact.mail.$invalid }" v-model="$v.contact.mail.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.contact.phone')" for="contact-phone">Phone</label>
                        <input type="text" class="form-control" name="phone" id="contact-phone"
                            :class="{'valid': !$v.contact.phone.$invalid, 'invalid': $v.contact.phone.$invalid }" v-model="$v.contact.phone.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.contact.preferred')" for="contact-preferred">Preferred</label>
                        <input type="checkbox" class="form-check" name="preferred" id="contact-preferred"
                            :class="{'valid': !$v.contact.preferred.$invalid, 'invalid': $v.contact.preferred.$invalid }" v-model="$v.contact.preferred.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.contact.addressId')" for="contact-addressId">Address Id</label>
                        <input type="text" class="form-control" name="addressId" id="contact-addressId"
                            :class="{'valid': !$v.contact.addressId.$invalid, 'invalid': $v.contact.addressId.$invalid }" v-model="$v.contact.addressId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.contact.statusId')" for="contact-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="contact-statusId"
                            :class="{'valid': !$v.contact.statusId.$invalid, 'invalid': $v.contact.statusId.$invalid }" v-model.number="$v.contact.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contact.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contact-update.component.ts">
</script>
