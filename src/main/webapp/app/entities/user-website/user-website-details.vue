<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="userWebsite">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.userWebsite.detail.title')">UserWebsite</span> {{userWebsite.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.userWebsite.code')">Code</span>
                    </dt>
                    <dd>
                        <span>{{userWebsite.code}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.userWebsite.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{userWebsite.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.userWebsite.surname')">Surname</span>
                    </dt>
                    <dd>
                        <span>{{userWebsite.surname}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.userWebsite.formattedName')">Formatted Name</span>
                    </dt>
                    <dd>
                        <span>{{userWebsite.formattedName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.userWebsite.userName')">User Name</span>
                    </dt>
                    <dd>
                        <span>{{userWebsite.userName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.userWebsite.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{userWebsite.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="userWebsite.id" :to="{name: 'UserWebsiteEdit', params: {userWebsiteId: userWebsite.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./user-website-details.component.ts">
</script>
