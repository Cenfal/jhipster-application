package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Address} entity.
 */
public class AddressDTO implements Serializable {
    
    private Long id;

    private String countryCode;

    private String country;

    private String cityCode;

    private String city;

    private String districtCode;

    private String district;

    private String countyCode;

    private String county;

    private String neighborhoodCode;

    private String neighborhood;

    private String shipmentCode;

    private String shipment;

    private String villageCode;

    private String village;

    private String streetCode;

    private String street;

    private String buildingCode;

    private String buildingNumber;

    private String building;

    private String buildingFree;

    private String independentPartsCode;

    private String independentParts;

    private String independentPartsFree;

    private String postalCode;

    private Long statusId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getNeighborhoodCode() {
        return neighborhoodCode;
    }

    public void setNeighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getShipmentCode() {
        return shipmentCode;
    }

    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }

    public String getShipment() {
        return shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuildingFree() {
        return buildingFree;
    }

    public void setBuildingFree(String buildingFree) {
        this.buildingFree = buildingFree;
    }

    public String getIndependentPartsCode() {
        return independentPartsCode;
    }

    public void setIndependentPartsCode(String independentPartsCode) {
        this.independentPartsCode = independentPartsCode;
    }

    public String getIndependentParts() {
        return independentParts;
    }

    public void setIndependentParts(String independentParts) {
        this.independentParts = independentParts;
    }

    public String getIndependentPartsFree() {
        return independentPartsFree;
    }

    public void setIndependentPartsFree(String independentPartsFree) {
        this.independentPartsFree = independentPartsFree;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        return id != null && id.equals(((AddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
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
