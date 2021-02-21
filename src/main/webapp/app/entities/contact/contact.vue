<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.contact.home.title')" id="contact-heading">Contacts</span>
            <router-link :to="{name: 'ContactCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-contact">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.contact.home.createLabel')">
                    Create a new Contact
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && contacts && contacts.length === 0">
            <span v-text="$t('jhipsterApplicationApp.contact.home.notFound')">No contacts found</span>
        </div>
        <div class="table-responsive" v-if="contacts && contacts.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userId')"><span v-text="$t('jhipsterApplicationApp.contact.userId')">User Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mail')"><span v-text="$t('jhipsterApplicationApp.contact.mail')">Mail</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mail'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('phone')"><span v-text="$t('jhipsterApplicationApp.contact.phone')">Phone</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phone'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('preferred')"><span v-text="$t('jhipsterApplicationApp.contact.preferred')">Preferred</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'preferred'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('addressId')"><span v-text="$t('jhipsterApplicationApp.contact.addressId')">Address Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'addressId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('statusId')"><span v-text="$t('jhipsterApplicationApp.contact.statusId')">Status Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contact in contacts"
                    :key="contact.id">
                    <td>
                        <router-link :to="{name: 'ContactView', params: {contactId: contact.id}}">{{contact.id}}</router-link>
                    </td>
                    <td>{{contact.userId}}</td>
                    <td>{{contact.mail}}</td>
                    <td>{{contact.phone}}</td>
                    <td>{{contact.preferred}}</td>
                    <td>{{contact.addressId}}</td>
                    <td>{{contact.statusId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContactView', params: {contactId: contact.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ContactEdit', params: {contactId: contact.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contact)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhipsterApplicationApp.contact.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-contact-heading" v-text="$t('jhipsterApplicationApp.contact.delete.question', {'id': removeId})">Are you sure you want to delete this Contact?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-contact" v-text="$t('entity.action.delete')" v-on:click="removeContact()">Delete</button>
            </div>
        </b-modal>
        <div v-show="contacts && contacts.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contact.component.ts">
</script>
