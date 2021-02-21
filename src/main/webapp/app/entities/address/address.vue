<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhipsterApplicationApp.address.home.title')" id="address-heading">Addresses</span>
            <router-link :to="{name: 'AddressCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-address">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhipsterApplicationApp.address.home.createLabel')">
                    Create a new Address
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
        <div class="alert alert-warning" v-if="!isFetching && addresses && addresses.length === 0">
            <span v-text="$t('jhipsterApplicationApp.address.home.notFound')">No addresses found</span>
        </div>
        <div class="table-responsive" v-if="addresses && addresses.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.countryCode')">Country Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.country')">Country</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.cityCode')">City Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.city')">City</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.districtCode')">District Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.district')">District</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.countyCode')">County Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.county')">County</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.neighborhoodCode')">Neighborhood Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.neighborhood')">Neighborhood</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.shipmentCode')">Shipment Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.shipment')">Shipment</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.villageCode')">Village Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.village')">Village</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.streetCode')">Street Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.street')">Street</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.buildingCode')">Building Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.buildingNumber')">Building Number</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.building')">Building</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.buildingFree')">Building Free</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.independentPartsCode')">Independent Parts Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.independentParts')">Independent Parts</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.independentPartsFree')">Independent Parts Free</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.postalCode')">Postal Code</span></th>
                    <th><span v-text="$t('jhipsterApplicationApp.address.statusId')">Status Id</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="address in addresses"
                    :key="address.id">
                    <td>
                        <router-link :to="{name: 'AddressView', params: {addressId: address.id}}">{{address.id}}</router-link>
                    </td>
                    <td>{{address.countryCode}}</td>
                    <td>{{address.country}}</td>
                    <td>{{address.cityCode}}</td>
                    <td>{{address.city}}</td>
                    <td>{{address.districtCode}}</td>
                    <td>{{address.district}}</td>
                    <td>{{address.countyCode}}</td>
                    <td>{{address.county}}</td>
                    <td>{{address.neighborhoodCode}}</td>
                    <td>{{address.neighborhood}}</td>
                    <td>{{address.shipmentCode}}</td>
                    <td>{{address.shipment}}</td>
                    <td>{{address.villageCode}}</td>
                    <td>{{address.village}}</td>
                    <td>{{address.streetCode}}</td>
                    <td>{{address.street}}</td>
                    <td>{{address.buildingCode}}</td>
                    <td>{{address.buildingNumber}}</td>
                    <td>{{address.building}}</td>
                    <td>{{address.buildingFree}}</td>
                    <td>{{address.independentPartsCode}}</td>
                    <td>{{address.independentParts}}</td>
                    <td>{{address.independentPartsFree}}</td>
                    <td>{{address.postalCode}}</td>
                    <td>{{address.statusId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AddressView', params: {addressId: address.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AddressEdit', params: {addressId: address.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(address)"
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
            <span slot="modal-title"><span id="jhipsterApplicationApp.address.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-address-heading" v-text="$t('jhipsterApplicationApp.address.delete.question', {'id': removeId})">Are you sure you want to delete this Address?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-address" v-text="$t('entity.action.delete')" v-on:click="removeAddress()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./address.component.ts">
</script>
