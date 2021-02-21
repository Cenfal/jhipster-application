<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="address">
                <h2 class="jh-entity-heading"><span v-text="$t('jhipsterApplicationApp.address.detail.title')">Address</span> {{address.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.countryCode')">Country Code</span>
                    </dt>
                    <dd>
                        <span>{{address.countryCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.country')">Country</span>
                    </dt>
                    <dd>
                        <span>{{address.country}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.cityCode')">City Code</span>
                    </dt>
                    <dd>
                        <span>{{address.cityCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.city')">City</span>
                    </dt>
                    <dd>
                        <span>{{address.city}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.districtCode')">District Code</span>
                    </dt>
                    <dd>
                        <span>{{address.districtCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.district')">District</span>
                    </dt>
                    <dd>
                        <span>{{address.district}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.countyCode')">County Code</span>
                    </dt>
                    <dd>
                        <span>{{address.countyCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.county')">County</span>
                    </dt>
                    <dd>
                        <span>{{address.county}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.neighborhoodCode')">Neighborhood Code</span>
                    </dt>
                    <dd>
                        <span>{{address.neighborhoodCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.neighborhood')">Neighborhood</span>
                    </dt>
                    <dd>
                        <span>{{address.neighborhood}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.shipmentCode')">Shipment Code</span>
                    </dt>
                    <dd>
                        <span>{{address.shipmentCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.shipment')">Shipment</span>
                    </dt>
                    <dd>
                        <span>{{address.shipment}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.villageCode')">Village Code</span>
                    </dt>
                    <dd>
                        <span>{{address.villageCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.village')">Village</span>
                    </dt>
                    <dd>
                        <span>{{address.village}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.streetCode')">Street Code</span>
                    </dt>
                    <dd>
                        <span>{{address.streetCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.street')">Street</span>
                    </dt>
                    <dd>
                        <span>{{address.street}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.buildingCode')">Building Code</span>
                    </dt>
                    <dd>
                        <span>{{address.buildingCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.buildingNumber')">Building Number</span>
                    </dt>
                    <dd>
                        <span>{{address.buildingNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.building')">Building</span>
                    </dt>
                    <dd>
                        <span>{{address.building}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.buildingFree')">Building Free</span>
                    </dt>
                    <dd>
                        <span>{{address.buildingFree}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.independentPartsCode')">Independent Parts Code</span>
                    </dt>
                    <dd>
                        <span>{{address.independentPartsCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.independentParts')">Independent Parts</span>
                    </dt>
                    <dd>
                        <span>{{address.independentParts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.independentPartsFree')">Independent Parts Free</span>
                    </dt>
                    <dd>
                        <span>{{address.independentPartsFree}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.postalCode')">Postal Code</span>
                    </dt>
                    <dd>
                        <span>{{address.postalCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('jhipsterApplicationApp.address.statusId')">Status Id</span>
                    </dt>
                    <dd>
                        <span>{{address.statusId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="address.id" :to="{name: 'AddressEdit', params: {addressId: address.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./address-details.component.ts">
</script>
