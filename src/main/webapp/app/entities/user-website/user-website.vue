<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.userWebsite.home.title')" id="user-website-heading">User Websites</span>
            <router-link :to="{name: 'UserWebsiteCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-user-website">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.userWebsite.home.createLabel')">
                    Create a new User Website
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
        <div class="alert alert-warning" v-if="!isFetching && userWebsites && userWebsites.length === 0">
            <span v-text="$t('jhipsterApplicationApp.userWebsite.home.notFound')">No userWebsites found</span>
        </div>
        <div class="table-responsive" v-if="userWebsites && userWebsites.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('code')"><span v-text="$t('jhipsterApplicationApp.userWebsite.code')">Code</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'code'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('jhipsterApplicationApp.userWebsite.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('surname')"><span v-text="$t('jhipsterApplicationApp.userWebsite.surname')">Surname</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'surname'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('formattedName')"><span v-text="$t('jhipsterApplicationApp.userWebsite.formattedName')">Formatted Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'formattedName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userName')"><span v-text="$t('jhipsterApplicationApp.userWebsite.userName')">User Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('statusId')"><span v-text="$t('jhipsterApplicationApp.userWebsite.statusId')">Status Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="userWebsite in userWebsites"
                    :key="userWebsite.id">
                    <td>
                        <router-link :to="{name: 'UserWebsiteView', params: {userWebsiteId: userWebsite.id}}">{{userWebsite.id}}</router-link>
                    </td>
                    <td>{{userWebsite.code}}</td>
                    <td>{{userWebsite.name}}</td>
                    <td>{{userWebsite.surname}}</td>
                    <td>{{userWebsite.formattedName}}</td>
                    <td>{{userWebsite.userName}}</td>
                    <td>{{userWebsite.statusId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'UserWebsiteView', params: {userWebsiteId: userWebsite.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'UserWebsiteEdit', params: {userWebsiteId: userWebsite.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(userWebsite)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.userWebsite.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-userWebsite-heading" v-text="$t('jhipsterApplicationApp.userWebsite.delete.question', {'id': removeId})">Are you sure you want to delete this User Website?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-userWebsite" v-text="$t('entity.action.delete')" v-on:click="removeUserWebsite()">Delete</button>
            </div>
        </b-modal>
        <div v-show="userWebsites && userWebsites.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./user-website.component.ts">
</script>
