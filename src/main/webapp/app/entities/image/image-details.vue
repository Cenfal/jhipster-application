<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="image">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.image.detail.title')">Image</span> {{image.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.image.articleId')">Article Id</span>
                    </dt>
                    <dd>
                        <span>{{image.articleId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.image.path')">Path</span>
                    </dt>
                    <dd>
                        <span>{{image.path}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.image.imageType')">Image Type</span>
                    </dt>
                    <dd>
                        <span>{{image.imageType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.image.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{image.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="image.id" :to="{name: 'ImageEdit', params: {imageId: image.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./image-details.component.ts">
</script>
