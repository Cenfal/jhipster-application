package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.Address;
import com.mycompany.myapp.repository.AddressRepository;
import com.mycompany.myapp.service.AddressService;
import com.mycompany.myapp.service.dto.AddressDTO;
import com.mycompany.myapp.service.mapper.AddressMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AddressResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AddressResourceIT {

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPMENT = "AAAAAAAAAA";
    private static final String UPDATED_SHIPMENT = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STREET_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING_FREE = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING_FREE = "BBBBBBBBBB";

    private static final String DEFAULT_INDEPENDENT_PARTS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INDEPENDENT_PARTS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_INDEPENDENT_PARTS = "AAAAAAAAAA";
    private static final String UPDATED_INDEPENDENT_PARTS = "BBBBBBBBBB";

    private static final String DEFAULT_INDEPENDENT_PARTS_FREE = "AAAAAAAAAA";
    private static final String UPDATED_INDEPENDENT_PARTS_FREE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS_ID = 1L;
    private static final Long UPDATED_STATUS_ID = 2L;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddressMockMvc;

    private Address address;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createEntity(EntityManager em) {
        Address address = new Address()
            .countryCode(DEFAULT_COUNTRY_CODE)
            .country(DEFAULT_COUNTRY)
            .cityCode(DEFAULT_CITY_CODE)
            .city(DEFAULT_CITY)
            .districtCode(DEFAULT_DISTRICT_CODE)
            .district(DEFAULT_DISTRICT)
            .countyCode(DEFAULT_COUNTY_CODE)
            .county(DEFAULT_COUNTY)
            .neighborhoodCode(DEFAULT_NEIGHBORHOOD_CODE)
            .neighborhood(DEFAULT_NEIGHBORHOOD)
            .shipmentCode(DEFAULT_SHIPMENT_CODE)
            .shipment(DEFAULT_SHIPMENT)
            .villageCode(DEFAULT_VILLAGE_CODE)
            .village(DEFAULT_VILLAGE)
            .streetCode(DEFAULT_STREET_CODE)
            .street(DEFAULT_STREET)
            .buildingCode(DEFAULT_BUILDING_CODE)
            .buildingNumber(DEFAULT_BUILDING_NUMBER)
            .building(DEFAULT_BUILDING)
            .buildingFree(DEFAULT_BUILDING_FREE)
            .independentPartsCode(DEFAULT_INDEPENDENT_PARTS_CODE)
            .independentParts(DEFAULT_INDEPENDENT_PARTS)
            .independentPartsFree(DEFAULT_INDEPENDENT_PARTS_FREE)
            .postalCode(DEFAULT_POSTAL_CODE)
            .statusId(DEFAULT_STATUS_ID);
        return address;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createUpdatedEntity(EntityManager em) {
        Address address = new Address()
            .countryCode(UPDATED_COUNTRY_CODE)
            .country(UPDATED_COUNTRY)
            .cityCode(UPDATED_CITY_CODE)
            .city(UPDATED_CITY)
            .districtCode(UPDATED_DISTRICT_CODE)
            .district(UPDATED_DISTRICT)
            .countyCode(UPDATED_COUNTY_CODE)
            .county(UPDATED_COUNTY)
            .neighborhoodCode(UPDATED_NEIGHBORHOOD_CODE)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .shipmentCode(UPDATED_SHIPMENT_CODE)
            .shipment(UPDATED_SHIPMENT)
            .villageCode(UPDATED_VILLAGE_CODE)
            .village(UPDATED_VILLAGE)
            .streetCode(UPDATED_STREET_CODE)
            .street(UPDATED_STREET)
            .buildingCode(UPDATED_BUILDING_CODE)
            .buildingNumber(UPDATED_BUILDING_NUMBER)
            .building(UPDATED_BUILDING)
            .buildingFree(UPDATED_BUILDING_FREE)
            .independentPartsCode(UPDATED_INDEPENDENT_PARTS_CODE)
            .independentParts(UPDATED_INDEPENDENT_PARTS)
            .independentPartsFree(UPDATED_INDEPENDENT_PARTS_FREE)
            .postalCode(UPDATED_POSTAL_CODE)
            .statusId(UPDATED_STATUS_ID);
        return address;
    }

    @BeforeEach
    public void initTest() {
        address = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddress() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();
        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);
        restAddressMockMvc.perform(post("/api/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isCreated());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate + 1);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testAddress.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testAddress.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
        assertThat(testAddress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAddress.getDistrictCode()).isEqualTo(DEFAULT_DISTRICT_CODE);
        assertThat(testAddress.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testAddress.getCountyCode()).isEqualTo(DEFAULT_COUNTY_CODE);
        assertThat(testAddress.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testAddress.getNeighborhoodCode()).isEqualTo(DEFAULT_NEIGHBORHOOD_CODE);
        assertThat(testAddress.getNeighborhood()).isEqualTo(DEFAULT_NEIGHBORHOOD);
        assertThat(testAddress.getShipmentCode()).isEqualTo(DEFAULT_SHIPMENT_CODE);
        assertThat(testAddress.getShipment()).isEqualTo(DEFAULT_SHIPMENT);
        assertThat(testAddress.getVillageCode()).isEqualTo(DEFAULT_VILLAGE_CODE);
        assertThat(testAddress.getVillage()).isEqualTo(DEFAULT_VILLAGE);
        assertThat(testAddress.getStreetCode()).isEqualTo(DEFAULT_STREET_CODE);
        assertThat(testAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testAddress.getBuildingCode()).isEqualTo(DEFAULT_BUILDING_CODE);
        assertThat(testAddress.getBuildingNumber()).isEqualTo(DEFAULT_BUILDING_NUMBER);
        assertThat(testAddress.getBuilding()).isEqualTo(DEFAULT_BUILDING);
        assertThat(testAddress.getBuildingFree()).isEqualTo(DEFAULT_BUILDING_FREE);
        assertThat(testAddress.getIndependentPartsCode()).isEqualTo(DEFAULT_INDEPENDENT_PARTS_CODE);
        assertThat(testAddress.getIndependentParts()).isEqualTo(DEFAULT_INDEPENDENT_PARTS);
        assertThat(testAddress.getIndependentPartsFree()).isEqualTo(DEFAULT_INDEPENDENT_PARTS_FREE);
        assertThat(testAddress.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testAddress.getStatusId()).isEqualTo(DEFAULT_STATUS_ID);
    }

    @Test
    @Transactional
    public void createAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();

        // Create the Address with an existing ID
        address.setId(1L);
        AddressDTO addressDTO = addressMapper.toDto(address);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressMockMvc.perform(post("/api/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAddresses() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get all the addressList
        restAddressMockMvc.perform(get("/api/addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(address.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].districtCode").value(hasItem(DEFAULT_DISTRICT_CODE)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].countyCode").value(hasItem(DEFAULT_COUNTY_CODE)))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY)))
            .andExpect(jsonPath("$.[*].neighborhoodCode").value(hasItem(DEFAULT_NEIGHBORHOOD_CODE)))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD)))
            .andExpect(jsonPath("$.[*].shipmentCode").value(hasItem(DEFAULT_SHIPMENT_CODE)))
            .andExpect(jsonPath("$.[*].shipment").value(hasItem(DEFAULT_SHIPMENT)))
            .andExpect(jsonPath("$.[*].villageCode").value(hasItem(DEFAULT_VILLAGE_CODE)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].streetCode").value(hasItem(DEFAULT_STREET_CODE)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].buildingCode").value(hasItem(DEFAULT_BUILDING_CODE)))
            .andExpect(jsonPath("$.[*].buildingNumber").value(hasItem(DEFAULT_BUILDING_NUMBER)))
            .andExpect(jsonPath("$.[*].building").value(hasItem(DEFAULT_BUILDING)))
            .andExpect(jsonPath("$.[*].buildingFree").value(hasItem(DEFAULT_BUILDING_FREE)))
            .andExpect(jsonPath("$.[*].independentPartsCode").value(hasItem(DEFAULT_INDEPENDENT_PARTS_CODE)))
            .andExpect(jsonPath("$.[*].independentParts").value(hasItem(DEFAULT_INDEPENDENT_PARTS)))
            .andExpect(jsonPath("$.[*].independentPartsFree").value(hasItem(DEFAULT_INDEPENDENT_PARTS_FREE)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].statusId").value(hasItem(DEFAULT_STATUS_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", address.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(address.getId().intValue()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.districtCode").value(DEFAULT_DISTRICT_CODE))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.countyCode").value(DEFAULT_COUNTY_CODE))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY))
            .andExpect(jsonPath("$.neighborhoodCode").value(DEFAULT_NEIGHBORHOOD_CODE))
            .andExpect(jsonPath("$.neighborhood").value(DEFAULT_NEIGHBORHOOD))
            .andExpect(jsonPath("$.shipmentCode").value(DEFAULT_SHIPMENT_CODE))
            .andExpect(jsonPath("$.shipment").value(DEFAULT_SHIPMENT))
            .andExpect(jsonPath("$.villageCode").value(DEFAULT_VILLAGE_CODE))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE))
            .andExpect(jsonPath("$.streetCode").value(DEFAULT_STREET_CODE))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.buildingCode").value(DEFAULT_BUILDING_CODE))
            .andExpect(jsonPath("$.buildingNumber").value(DEFAULT_BUILDING_NUMBER))
            .andExpect(jsonPath("$.building").value(DEFAULT_BUILDING))
            .andExpect(jsonPath("$.buildingFree").value(DEFAULT_BUILDING_FREE))
            .andExpect(jsonPath("$.independentPartsCode").value(DEFAULT_INDEPENDENT_PARTS_CODE))
            .andExpect(jsonPath("$.independentParts").value(DEFAULT_INDEPENDENT_PARTS))
            .andExpect(jsonPath("$.independentPartsFree").value(DEFAULT_INDEPENDENT_PARTS_FREE))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.statusId").value(DEFAULT_STATUS_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAddress() throws Exception {
        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Update the address
        Address updatedAddress = addressRepository.findById(address.getId()).get();
        // Disconnect from session so that the updates on updatedAddress are not directly saved in db
        em.detach(updatedAddress);
        updatedAddress
            .countryCode(UPDATED_COUNTRY_CODE)
            .country(UPDATED_COUNTRY)
            .cityCode(UPDATED_CITY_CODE)
            .city(UPDATED_CITY)
            .districtCode(UPDATED_DISTRICT_CODE)
            .district(UPDATED_DISTRICT)
            .countyCode(UPDATED_COUNTY_CODE)
            .county(UPDATED_COUNTY)
            .neighborhoodCode(UPDATED_NEIGHBORHOOD_CODE)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .shipmentCode(UPDATED_SHIPMENT_CODE)
            .shipment(UPDATED_SHIPMENT)
            .villageCode(UPDATED_VILLAGE_CODE)
            .village(UPDATED_VILLAGE)
            .streetCode(UPDATED_STREET_CODE)
            .street(UPDATED_STREET)
            .buildingCode(UPDATED_BUILDING_CODE)
            .buildingNumber(UPDATED_BUILDING_NUMBER)
            .building(UPDATED_BUILDING)
            .buildingFree(UPDATED_BUILDING_FREE)
            .independentPartsCode(UPDATED_INDEPENDENT_PARTS_CODE)
            .independentParts(UPDATED_INDEPENDENT_PARTS)
            .independentPartsFree(UPDATED_INDEPENDENT_PARTS_FREE)
            .postalCode(UPDATED_POSTAL_CODE)
            .statusId(UPDATED_STATUS_ID);
        AddressDTO addressDTO = addressMapper.toDto(updatedAddress);

        restAddressMockMvc.perform(put("/api/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isOk());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testAddress.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testAddress.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAddress.getDistrictCode()).isEqualTo(UPDATED_DISTRICT_CODE);
        assertThat(testAddress.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testAddress.getCountyCode()).isEqualTo(UPDATED_COUNTY_CODE);
        assertThat(testAddress.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testAddress.getNeighborhoodCode()).isEqualTo(UPDATED_NEIGHBORHOOD_CODE);
        assertThat(testAddress.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testAddress.getShipmentCode()).isEqualTo(UPDATED_SHIPMENT_CODE);
        assertThat(testAddress.getShipment()).isEqualTo(UPDATED_SHIPMENT);
        assertThat(testAddress.getVillageCode()).isEqualTo(UPDATED_VILLAGE_CODE);
        assertThat(testAddress.getVillage()).isEqualTo(UPDATED_VILLAGE);
        assertThat(testAddress.getStreetCode()).isEqualTo(UPDATED_STREET_CODE);
        assertThat(testAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testAddress.getBuildingCode()).isEqualTo(UPDATED_BUILDING_CODE);
        assertThat(testAddress.getBuildingNumber()).isEqualTo(UPDATED_BUILDING_NUMBER);
        assertThat(testAddress.getBuilding()).isEqualTo(UPDATED_BUILDING);
        assertThat(testAddress.getBuildingFree()).isEqualTo(UPDATED_BUILDING_FREE);
        assertThat(testAddress.getIndependentPartsCode()).isEqualTo(UPDATED_INDEPENDENT_PARTS_CODE);
        assertThat(testAddress.getIndependentParts()).isEqualTo(UPDATED_INDEPENDENT_PARTS);
        assertThat(testAddress.getIndependentPartsFree()).isEqualTo(UPDATED_INDEPENDENT_PARTS_FREE);
        assertThat(testAddress.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testAddress.getStatusId()).isEqualTo(UPDATED_STATUS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAddress() throws Exception {
        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressMockMvc.perform(put("/api/addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        int databaseSizeBeforeDelete = addressRepository.findAll().size();

        // Delete the address
        restAddressMockMvc.perform(delete("/api/addresses/{id}", address.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
