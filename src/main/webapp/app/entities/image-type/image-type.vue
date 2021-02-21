<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.imageType.home.title')" id="image-type-heading">Image Types</span>
            <router-link :to="{name: 'ImageTypeCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-image-type">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.imageType.home.createLabel')">
                    Create a new Image Type
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
        <div class="alert alert-warning" v-if="!isFetching && imageTypes && imageTypes.length === 0">
            <span v-text="$t('jhipsterApplicationApp.imageType.home.notFound')">No imageTypes found</span>
        </div>
        <div class="table-responsive" v-if="imageTypes && imageTypes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.imageType.typeId')">Type Id</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.imageType.type')">Type</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.imageType.statusId')">Status Id</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="imageType in imageTypes"
                    :key="imageType.id">
                    <td>
                        <router-link :to="{name: 'ImageTypeView', params: {imageTypeId: imageType.id}}">{{imageType.id}}</router-link>
                    </td>
                    <td>{{imageType.typeId}}</td>
                    <td>{{imageType.type}}</td>
                    <td>{{imageType.statusId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ImageTypeView', params: {imageTypeId: imageType.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ImageTypeEdit', params: {imageTypeId: imageType.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(imageType)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.imageType.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-imageType-heading" v-text="$t('jhipsterApplicationApp.imageType.delete.question', {'id': removeId})">Are you sure you want to delete this Image Type?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-imageType" v-text="$t('entity.action.delete')" v-on:click="removeImageType()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./image-type.component.ts">
</script>
