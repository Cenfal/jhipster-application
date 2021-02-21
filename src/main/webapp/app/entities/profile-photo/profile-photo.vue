<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.profilePhoto.home.title')" id="profile-photo-heading">Profile Photos</span>
            <router-link :to="{name: 'ProfilePhotoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-profile-photo">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.profilePhoto.home.createLabel')">
                    Create a new Profile Photo
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
        <div class="alert alert-warning" v-if="!isFetching && profilePhotos && profilePhotos.length === 0">
            <span v-text="$t('jhipsterApplicationApp.profilePhoto.home.notFound')">No profilePhotos found</span>
        </div>
        <div class="table-responsive" v-if="profilePhotos && profilePhotos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userId')"><span v-text="$t('jhipsterApplicationApp.profilePhoto.userId')">User Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('path')"><span v-text="$t('jhipsterApplicationApp.profilePhoto.path')">Path</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'path'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('statusId')"><span v-text="$t('jhipsterApplicationApp.profilePhoto.statusId')">Status Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="profilePhoto in profilePhotos"
                    :key="profilePhoto.id">
                    <td>
                        <router-link :to="{name: 'ProfilePhotoView', params: {profilePhotoId: profilePhoto.id}}">{{profilePhoto.id}}</router-link>
                    </td>
                    <td>{{profilePhoto.userId}}</td>
                    <td>{{profilePhoto.path}}</td>
                    <td>{{profilePhoto.statusId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProfilePhotoView', params: {profilePhotoId: profilePhoto.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ProfilePhotoEdit', params: {profilePhotoId: profilePhoto.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(profilePhoto)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.profilePhoto.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-profilePhoto-heading" v-text="$t('jhipsterApplicationApp.profilePhoto.delete.question', {'id': removeId})">Are you sure you want to delete this Profile Photo?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-profilePhoto" v-text="$t('entity.action.delete')" v-on:click="removeProfilePhoto()">Delete</button>
            </div>
        </b-modal>
        <div v-show="profilePhotos && profilePhotos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./profile-photo.component.ts">
</script>
