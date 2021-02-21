<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="contact">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.contact.detail.title')">Contact</span> {{contact.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.contact.userId')">User Id</span>
                    </dt>
                    <dd>
                        <span>{{contact.userId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.contact.mail')">Mail</span>
                    </dt>
                    <dd>
                        <span>{{contact.mail}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.contact.phone')">Phone</span>
                    </dt>
                    <dd>
                        <span>{{contact.phone}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.contact.preferred')">Preferred</span>
                    </dt>
                    <dd>
                        <span>{{contact.preferred}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.contact.addressId')">Address Id</span>
                    </dt>
                    <dd>
                        <span>{{contact.addressId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.contact.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{contact.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="contact.id" :to="{name: 'ContactEdit', params: {contactId: contact.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contact-details.component.ts">
</script>
