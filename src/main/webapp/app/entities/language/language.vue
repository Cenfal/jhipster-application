<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.language.home.title')" id="language-heading">Languages</span>
            <router-link :to="{name: 'LanguageCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-language">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.language.home.createLabel')">
                    Create a new Language
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
        <div class="alert alert-warning" v-if="!isFetching && languages && languages.length === 0">
            <span v-text="$t('jhipsterApplicationApp.language.home.notFound')">No languages found</span>
        </div>
        <div class="table-responsive" v-if="languages && languages.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.language.language')">Language</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="language in languages"
                    :key="language.id">
                    <td>
                        <router-link :to="{name: 'LanguageView', params: {languageId: language.id}}">{{language.id}}</router-link>
                    </td>
                    <td>{{language.language}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LanguageView', params: {languageId: language.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'LanguageEdit', params: {languageId: language.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(language)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.language.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-language-heading" v-text="$t('jhipsterApplicationApp.language.delete.question', {'id': removeId})">Are you sure you want to delete this Language?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-language" v-text="$t('entity.action.delete')" v-on:click="removeLanguage()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./language.component.ts">
</script>
