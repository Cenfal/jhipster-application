<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="profilePhoto">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.profilePhoto.detail.title')">ProfilePhoto</span> {{profilePhoto.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.profilePhoto.userId')">User Id</span>
                    </dt>
                    <dd>
                        <span>{{profilePhoto.userId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.profilePhoto.path')">Path</span>
                    </dt>
                    <dd>
                        <span>{{profilePhoto.path}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.profilePhoto.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{profilePhoto.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="profilePhoto.id" :to="{name: 'ProfilePhotoEdit', params: {profilePhotoId: profilePhoto.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./profile-photo-details.component.ts">
</script>
