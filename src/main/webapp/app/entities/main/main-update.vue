<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.main.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.main.home.createOrEditLabel')">Create or edit a Main</h2>
                <div>
                    <div class="form-group" v-if="main.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="main.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.createDate')" for="main-createDate">Create Date</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="main-createDate"
                                    v-model="$v.main.createDate.$model"
                                    name="createDate"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="main-createDate" type="text" class="form-control" name="createDate"  :class="{'valid': !$v.main.createDate.$invalid, 'invalid': $v.main.createDate.$invalid }"
                            v-model="$v.main.createDate.$model"  />
                        </b-input-group>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.updateDate')" for="main-updateDate">Update Date</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="main-updateDate"
                                    v-model="$v.main.updateDate.$model"
                                    name="updateDate"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="main-updateDate" type="text" class="form-control" name="updateDate"  :class="{'valid': !$v.main.updateDate.$invalid, 'invalid': $v.main.updateDate.$invalid }"
                            v-model="$v.main.updateDate.$model"  />
                        </b-input-group>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.endDate')" for="main-endDate">End Date</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="main-endDate"
                                    v-model="$v.main.endDate.$model"
                                    name="endDate"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="main-endDate" type="text" class="form-control" name="endDate"  :class="{'valid': !$v.main.endDate.$invalid, 'invalid': $v.main.endDate.$invalid }"
                            v-model="$v.main.endDate.$model"  />
                        </b-input-group>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.createdBy')" for="main-createdBy">Created By</label>
                        <input type="text" class="form-control" name="createdBy" id="main-createdBy"
                            :class="{'valid': !$v.main.createdBy.$invalid, 'invalid': $v.main.createdBy.$invalid }" v-model="$v.main.createdBy.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.updatedBy')" for="main-updatedBy">Updated By</label>
                        <input type="text" class="form-control" name="updatedBy" id="main-updatedBy"
                            :class="{'valid': !$v.main.updatedBy.$invalid, 'invalid': $v.main.updatedBy.$invalid }" v-model="$v.main.updatedBy.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.contact')" for="main-contact">Contact</label>
                        <select class="form-control" id="main-contact" name="contact" v-model="main.contactId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contactOption.id" v-for="contactOption in contacts" :key="contactOption.id">{{contactOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.userWebsite')" for="main-userWebsite">User Website</label>
                        <select class="form-control" id="main-userWebsite" name="userWebsite" v-model="main.userWebsiteId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="userWebsiteOption.id" v-for="userWebsiteOption in userWebsites" :key="userWebsiteOption.id">{{userWebsiteOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.profilePhoto')" for="main-profilePhoto">Profile Photo</label>
                        <select class="form-control" id="main-profilePhoto" name="profilePhoto" v-model="main.profilePhotoId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="profilePhotoOption.id" v-for="profilePhotoOption in profilePhotos" :key="profilePhotoOption.id">{{profilePhotoOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.image')" for="main-image">Image</label>
                        <select class="form-control" id="main-image" name="image" v-model="main.imageId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="imageOption.id" v-for="imageOption in images" :key="imageOption.id">{{imageOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.articleHistory')" for="main-articleHistory">Article History</label>
                        <select class="form-control" id="main-articleHistory" name="articleHistory" v-model="main.articleHistoryId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="articleHistoryOption.id" v-for="articleHistoryOption in articleHistories" :key="articleHistoryOption.id">{{articleHistoryOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.commentHistory')" for="main-commentHistory">Comment History</label>
                        <select class="form-control" id="main-commentHistory" name="commentHistory" v-model="main.commentHistoryId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="commentHistoryOption.id" v-for="commentHistoryOption in commentHistories" :key="commentHistoryOption.id">{{commentHistoryOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.article')" for="main-article">Article</label>
                        <select class="form-control" id="main-article" name="article" v-model="main.articleId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="articleOption.id" v-for="articleOption in articles" :key="articleOption.id">{{articleOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.main.comment')" for="main-comment">Comment</label>
                        <select class="form-control" id="main-comment" name="comment" v-model="main.commentId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="commentOption.id" v-for="commentOption in comments" :key="commentOption.id">{{commentOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.main.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./main-update.component.ts">
</script>
