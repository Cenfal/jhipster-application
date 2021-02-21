<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="imageType">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.imageType.detail.title')">ImageType</span> {{imageType.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.imageType.typeId')">Type Id</span>
                    </dt>
                    <dd>
                        <span>{{imageType.typeId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.imageType.type')">Type</span>
                    </dt>
                    <dd>
                        <span>{{imageType.type}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.imageType.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{imageType.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="imageType.id" :to="{name: 'ImageTypeEdit', params: {imageTypeId: imageType.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./image-type-details.component.ts">
</script>
