<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhipsterApplicationApp.address.home.createOrEditLabel" v-text="$t('jhipsterApplicationApp.address.home.createOrEditLabel')">Create or edit a Address</h2>
                <div>
                    <div class="form-group" v-if="address.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="address.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.countryCode')" for="address-countryCode">Country Code</label>
                        <input type="text" class="form-control" name="countryCode" id="address-countryCode"
                            :class="{'valid': !$v.address.countryCode.$invalid, 'invalid': $v.address.countryCode.$invalid }" v-model="$v.address.countryCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.country')" for="address-country">Country</label>
                        <input type="text" class="form-control" name="country" id="address-country"
                            :class="{'valid': !$v.address.country.$invalid, 'invalid': $v.address.country.$invalid }" v-model="$v.address.country.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.cityCode')" for="address-cityCode">City Code</label>
                        <input type="text" class="form-control" name="cityCode" id="address-cityCode"
                            :class="{'valid': !$v.address.cityCode.$invalid, 'invalid': $v.address.cityCode.$invalid }" v-model="$v.address.cityCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.city')" for="address-city">City</label>
                        <input type="text" class="form-control" name="city" id="address-city"
                            :class="{'valid': !$v.address.city.$invalid, 'invalid': $v.address.city.$invalid }" v-model="$v.address.city.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.districtCode')" for="address-districtCode">District Code</label>
                        <input type="text" class="form-control" name="districtCode" id="address-districtCode"
                            :class="{'valid': !$v.address.districtCode.$invalid, 'invalid': $v.address.districtCode.$invalid }" v-model="$v.address.districtCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.district')" for="address-district">District</label>
                        <input type="text" class="form-control" name="district" id="address-district"
                            :class="{'valid': !$v.address.district.$invalid, 'invalid': $v.address.district.$invalid }" v-model="$v.address.district.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.countyCode')" for="address-countyCode">County Code</label>
                        <input type="text" class="form-control" name="countyCode" id="address-countyCode"
                            :class="{'valid': !$v.address.countyCode.$invalid, 'invalid': $v.address.countyCode.$invalid }" v-model="$v.address.countyCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.county')" for="address-county">County</label>
                        <input type="text" class="form-control" name="county" id="address-county"
                            :class="{'valid': !$v.address.county.$invalid, 'invalid': $v.address.county.$invalid }" v-model="$v.address.county.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.neighborhoodCode')" for="address-neighborhoodCode">Neighborhood Code</label>
                        <input type="text" class="form-control" name="neighborhoodCode" id="address-neighborhoodCode"
                            :class="{'valid': !$v.address.neighborhoodCode.$invalid, 'invalid': $v.address.neighborhoodCode.$invalid }" v-model="$v.address.neighborhoodCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.neighborhood')" for="address-neighborhood">Neighborhood</label>
                        <input type="text" class="form-control" name="neighborhood" id="address-neighborhood"
                            :class="{'valid': !$v.address.neighborhood.$invalid, 'invalid': $v.address.neighborhood.$invalid }" v-model="$v.address.neighborhood.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.shipmentCode')" for="address-shipmentCode">Shipment Code</label>
                        <input type="text" class="form-control" name="shipmentCode" id="address-shipmentCode"
                            :class="{'valid': !$v.address.shipmentCode.$invalid, 'invalid': $v.address.shipmentCode.$invalid }" v-model="$v.address.shipmentCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.shipment')" for="address-shipment">Shipment</label>
                        <input type="text" class="form-control" name="shipment" id="address-shipment"
                            :class="{'valid': !$v.address.shipment.$invalid, 'invalid': $v.address.shipment.$invalid }" v-model="$v.address.shipment.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.villageCode')" for="address-villageCode">Village Code</label>
                        <input type="text" class="form-control" name="villageCode" id="address-villageCode"
                            :class="{'valid': !$v.address.villageCode.$invalid, 'invalid': $v.address.villageCode.$invalid }" v-model="$v.address.villageCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.village')" for="address-village">Village</label>
                        <input type="text" class="form-control" name="village" id="address-village"
                            :class="{'valid': !$v.address.village.$invalid, 'invalid': $v.address.village.$invalid }" v-model="$v.address.village.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.streetCode')" for="address-streetCode">Street Code</label>
                        <input type="text" class="form-control" name="streetCode" id="address-streetCode"
                            :class="{'valid': !$v.address.streetCode.$invalid, 'invalid': $v.address.streetCode.$invalid }" v-model="$v.address.streetCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.street')" for="address-street">Street</label>
                        <input type="text" class="form-control" name="street" id="address-street"
                            :class="{'valid': !$v.address.street.$invalid, 'invalid': $v.address.street.$invalid }" v-model="$v.address.street.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.buildingCode')" for="address-buildingCode">Building Code</label>
                        <input type="text" class="form-control" name="buildingCode" id="address-buildingCode"
                            :class="{'valid': !$v.address.buildingCode.$invalid, 'invalid': $v.address.buildingCode.$invalid }" v-model="$v.address.buildingCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.buildingNumber')" for="address-buildingNumber">Building Number</label>
                        <input type="text" class="form-control" name="buildingNumber" id="address-buildingNumber"
                            :class="{'valid': !$v.address.buildingNumber.$invalid, 'invalid': $v.address.buildingNumber.$invalid }" v-model="$v.address.buildingNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.building')" for="address-building">Building</label>
                        <input type="text" class="form-control" name="building" id="address-building"
                            :class="{'valid': !$v.address.building.$invalid, 'invalid': $v.address.building.$invalid }" v-model="$v.address.building.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.buildingFree')" for="address-buildingFree">Building Free</label>
                        <input type="text" class="form-control" name="buildingFree" id="address-buildingFree"
                            :class="{'valid': !$v.address.buildingFree.$invalid, 'invalid': $v.address.buildingFree.$invalid }" v-model="$v.address.buildingFree.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.independentPartsCode')" for="address-independentPartsCode">Independent Parts Code</label>
                        <input type="text" class="form-control" name="independentPartsCode" id="address-independentPartsCode"
                            :class="{'valid': !$v.address.independentPartsCode.$invalid, 'invalid': $v.address.independentPartsCode.$invalid }" v-model="$v.address.independentPartsCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.independentParts')" for="address-independentParts">Independent Parts</label>
                        <input type="text" class="form-control" name="independentParts" id="address-independentParts"
                            :class="{'valid': !$v.address.independentParts.$invalid, 'invalid': $v.address.independentParts.$invalid }" v-model="$v.address.independentParts.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.independentPartsFree')" for="address-independentPartsFree">Independent Parts Free</label>
                        <input type="text" class="form-control" name="independentPartsFree" id="address-independentPartsFree"
                            :class="{'valid': !$v.address.independentPartsFree.$invalid, 'invalid': $v.address.independentPartsFree.$invalid }" v-model="$v.address.independentPartsFree.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.postalCode')" for="address-postalCode">Postal Code</label>
                        <input type="text" class="form-control" name="postalCode" id="address-postalCode"
                            :class="{'valid': !$v.address.postalCode.$invalid, 'invalid': $v.address.postalCode.$invalid }" v-model="$v.address.postalCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhipsterApplicationApp.address.statusId')" for="address-statusId">Status Id</label>
                        <input type="number" class="form-control" name="statusId" id="address-statusId"
                            :class="{'valid': !$v.address.statusId.$invalid, 'invalid': $v.address.statusId.$invalid }" v-model.number="$v.address.statusId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.address.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./address-update.component.ts">
</script>
