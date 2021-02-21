package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.UserWebsite;
import com.mycompany.myapp.repository.UserWebsiteRepository;
import com.mycompany.myapp.service.UserWebsiteService;
import com.mycompany.myapp.service.dto.UserWebsiteDTO;
import com.mycompany.myapp.service.mapper.UserWebsiteMapper;

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
 * Integration tests for the {@link UserWebsiteResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserWebsiteResourceIT {

    private static final Long DEFAULT_CODE = 1L;
    private static final Long UPDATED_CODE = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATTED_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FORMATTED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS_ID = 1L;
    private static final Long UPDATED_STATUS_ID = 2L;

    @Autowired
    private UserWebsiteRepository userWebsiteRepository;

    @Autowired
    private UserWebsiteMapper userWebsiteMapper;

    @Autowired
    private UserWebsiteService userWebsiteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserWebsiteMockMvc;

    private UserWebsite userWebsite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWebsite createEntity(EntityManager em) {
        UserWebsite userWebsite = new UserWebsite()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .formattedName(DEFAULT_FORMATTED_NAME)
            .userName(DEFAULT_USER_NAME)
            .statusId(DEFAULT_STATUS_ID);
        return userWebsite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWebsite createUpdatedEntity(EntityManager em) {
        UserWebsite userWebsite = new UserWebsite()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .formattedName(UPDATED_FORMATTED_NAME)
            .userName(UPDATED_USER_NAME)
            .statusId(UPDATED_STATUS_ID);
        return userWebsite;
    }

    @BeforeEach
    public void initTest() {
        userWebsite = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserWebsite() throws Exception {
        int databaseSizeBeforeCreate = userWebsiteRepository.findAll().size();
        // Create the UserWebsite
        UserWebsiteDTO userWebsiteDTO = userWebsiteMapper.toDto(userWebsite);
        restUserWebsiteMockMvc.perform(post("/api/user-websites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWebsiteDTO)))
            .andExpect(status().isCreated());

        // Validate the UserWebsite in the database
        List<UserWebsite> userWebsiteList = userWebsiteRepository.findAll();
        assertThat(userWebsiteList).hasSize(databaseSizeBeforeCreate + 1);
        UserWebsite testUserWebsite = userWebsiteList.get(userWebsiteList.size() - 1);
        assertThat(testUserWebsite.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUserWebsite.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserWebsite.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testUserWebsite.getFormattedName()).isEqualTo(DEFAULT_FORMATTED_NAME);
        assertThat(testUserWebsite.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserWebsite.getStatusId()).isEqualTo(DEFAULT_STATUS_ID);
    }

    @Test
    @Transactional
    public void createUserWebsiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userWebsiteRepository.findAll().size();

        // Create the UserWebsite with an existing ID
        userWebsite.setId(1L);
        UserWebsiteDTO userWebsiteDTO = userWebsiteMapper.toDto(userWebsite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserWebsiteMockMvc.perform(post("/api/user-websites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWebsiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserWebsite in the database
        List<UserWebsite> userWebsiteList = userWebsiteRepository.findAll();
        assertThat(userWebsiteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserWebsites() throws Exception {
        // Initialize the database
        userWebsiteRepository.saveAndFlush(userWebsite);

        // Get all the userWebsiteList
        restUserWebsiteMockMvc.perform(get("/api/user-websites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userWebsite.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].formattedName").value(hasItem(DEFAULT_FORMATTED_NAME)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].statusId").value(hasItem(DEFAULT_STATUS_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getUserWebsite() throws Exception {
        // Initialize the database
        userWebsiteRepository.saveAndFlush(userWebsite);

        // Get the userWebsite
        restUserWebsiteMockMvc.perform(get("/api/user-websites/{id}", userWebsite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userWebsite.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.formattedName").value(DEFAULT_FORMATTED_NAME))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.statusId").value(DEFAULT_STATUS_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUserWebsite() throws Exception {
        // Get the userWebsite
        restUserWebsiteMockMvc.perform(get("/api/user-websites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserWebsite() throws Exception {
        // Initialize the database
        userWebsiteRepository.saveAndFlush(userWebsite);

        int databaseSizeBeforeUpdate = userWebsiteRepository.findAll().size();

        // Update the userWebsite
        UserWebsite updatedUserWebsite = userWebsiteRepository.findById(userWebsite.getId()).get();
        // Disconnect from session so that the updates on updatedUserWebsite are not directly saved in db
        em.detach(updatedUserWebsite);
        updatedUserWebsite
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .formattedName(UPDATED_FORMATTED_NAME)
            .userName(UPDATED_USER_NAME)
            .statusId(UPDATED_STATUS_ID);
        UserWebsiteDTO userWebsiteDTO = userWebsiteMapper.toDto(updatedUserWebsite);

        restUserWebsiteMockMvc.perform(put("/api/user-websites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWebsiteDTO)))
            .andExpect(status().isOk());

        // Validate the UserWebsite in the database
        List<UserWebsite> userWebsiteList = userWebsiteRepository.findAll();
        assertThat(userWebsiteList).hasSize(databaseSizeBeforeUpdate);
        UserWebsite testUserWebsite = userWebsiteList.get(userWebsiteList.size() - 1);
        assertThat(testUserWebsite.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUserWebsite.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserWebsite.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testUserWebsite.getFormattedName()).isEqualTo(UPDATED_FORMATTED_NAME);
        assertThat(testUserWebsite.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserWebsite.getStatusId()).isEqualTo(UPDATED_STATUS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingUserWebsite() throws Exception {
        int databaseSizeBeforeUpdate = userWebsiteRepository.findAll().size();

        // Create the UserWebsite
        UserWebsiteDTO userWebsiteDTO = userWebsiteMapper.toDto(userWebsite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserWebsiteMockMvc.perform(put("/api/user-websites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWebsiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserWebsite in the database
        List<UserWebsite> userWebsiteList = userWebsiteRepository.findAll();
        assertThat(userWebsiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserWebsite() throws Exception {
        // Initialize the database
        userWebsiteRepository.saveAndFlush(userWebsite);

        int databaseSizeBeforeDelete = userWebsiteRepository.findAll().size();

        // Delete the userWebsite
        restUserWebsiteMockMvc.perform(delete("/api/user-websites/{id}", userWebsite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserWebsite> userWebsiteList = userWebsiteRepository.findAll();
        assertThat(userWebsiteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
