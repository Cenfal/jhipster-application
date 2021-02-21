package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city")
    private String city;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district")
    private String district;

    @Column(name = "county_code")
    private String countyCode;

    @Column(name = "county")
    private String county;

    @Column(name = "neighborhood_code")
    private String neighborhoodCode;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "shipment_code")
    private String shipmentCode;

    @Column(name = "shipment")
    private String shipment;

    @Column(name = "village_code")
    private String villageCode;

    @Column(name = "village")
    private String village;

    @Column(name = "street_code")
    private String streetCode;

    @Column(name = "street")
    private String street;

    @Column(name = "building_code")
    private String buildingCode;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "building")
    private String building;

    @Column(name = "building_free")
    private String buildingFree;

    @Column(name = "independent_parts_code")
    private String independentPartsCode;

    @Column(name = "independent_parts")
    private String independentParts;

    @Column(name = "independent_parts_free")
    private String independentPartsFree;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "status_id")
    private Long statusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Address countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public Address country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityCode() {
        return cityCode;
    }

    public Address cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public Address districtCode(String districtCode) {
        this.districtCode = districtCode;
        return this;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrict() {
        return district;
    }

    public Address district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public Address countyCode(String countyCode) {
        this.countyCode = countyCode;
        return this;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCounty() {
        return county;
    }

    public Address county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getNeighborhoodCode() {
        return neighborhoodCode;
    }

    public Address neighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
        return this;
    }

    public void setNeighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Address neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getShipmentCode() {
        return shipmentCode;
    }

    public Address shipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
        return this;
    }

    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }

    public String getShipment() {
        return shipment;
    }

    public Address shipment(String shipment) {
        this.shipment = shipment;
        return this;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public Address villageCode(String villageCode) {
        this.villageCode = villageCode;
        return this;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillage() {
        return village;
    }

    public Address village(String village) {
        this.village = village;
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public Address streetCode(String streetCode) {
        this.streetCode = streetCode;
        return this;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public Address buildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
        return this;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public Address buildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
        return this;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuilding() {
        return building;
    }

    public Address building(String building) {
        this.building = building;
        return this;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuildingFree() {
        return buildingFree;
    }

    public Address buildingFree(String buildingFree) {
        this.buildingFree = buildingFree;
        return this;
    }

    public void setBuildingFree(String buildingFree) {
        this.buildingFree = buildingFree;
    }

    public String getIndependentPartsCode() {
        return independentPartsCode;
    }

    public Address independentPartsCode(String independentPartsCode) {
        this.independentPartsCode = independentPartsCode;
        return this;
    }

    public void setIndependentPartsCode(String independentPartsCode) {
        this.independentPartsCode = independentPartsCode;
    }

    public String getIndependentParts() {
        return independentParts;
    }

    public Address independentParts(String independentParts) {
        this.independentParts = independentParts;
        return this;
    }

    public void setIndependentParts(String independentParts) {
        this.independentParts = independentParts;
    }

    public String getIndependentPartsFree() {
        return independentPartsFree;
    }

    public Address independentPartsFree(String independentPartsFree) {
        this.independentPartsFree = independentPartsFree;
        return this;
    }

    public void setIndependentPartsFree(String independentPartsFree) {
        this.independentPartsFree = independentPartsFree;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Address postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Address statusId(Long statusId) {
        this.statusId = statusId;
        return this;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", countryCode='" + getCountryCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", cityCode='" + getCityCode() + "'" +
            ", city='" + getCity() + "'" +
            ", districtCode='" + getDistrictCode() + "'" +
            ", district='" + getDistrict() + "'" +
            ", countyCode='" + getCountyCode() + "'" +
            ", county='" + getCounty() + "'" +
            ", neighborhoodCode='" + getNeighborhoodCode() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", shipmentCode='" + getShipmentCode() + "'" +
            ", shipment='" + getShipment() + "'" +
            ", villageCode='" + getVillageCode() + "'" +
            ", village='" + getVillage() + "'" +
            ", streetCode='" + getStreetCode() + "'" +
            ", street='" + getStreet() + "'" +
            ", buildingCode='" + getBuildingCode() + "'" +
            ", buildingNumber='" + getBuildingNumber() + "'" +
            ", building='" + getBuilding() + "'" +
            ", buildingFree='" + getBuildingFree() + "'" +
            ", independentPartsCode='" + getIndependentPartsCode() + "'" +
            ", independentParts='" + getIndependentParts() + "'" +
            ", independentPartsFree='" + getIndependentPartsFree() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", statusId=" + getStatusId() +
            "}";
    }
}
