package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.ProfilePhoto;
import com.mycompany.myapp.repository.ProfilePhotoRepository;
import com.mycompany.myapp.service.ProfilePhotoService;
import com.mycompany.myapp.service.dto.ProfilePhotoDTO;
import com.mycompany.myapp.service.mapper.ProfilePhotoMapper;

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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProfilePhotoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProfilePhotoResourceIT {

    private static final UUID DEFAULT_USER_ID = UUID.randomUUID();
    private static final UUID UPDATED_USER_ID = UUID.randomUUID();

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS_ID = 1L;
    private static final Long UPDATED_STATUS_ID = 2L;

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    @Autowired
    private ProfilePhotoMapper profilePhotoMapper;

    @Autowired
    private ProfilePhotoService profilePhotoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfilePhotoMockMvc;

    private ProfilePhoto profilePhoto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfilePhoto createEntity(EntityManager em) {
        ProfilePhoto profilePhoto = new ProfilePhoto()
            .userId(DEFAULT_USER_ID)
            .path(DEFAULT_PATH)
            .statusId(DEFAULT_STATUS_ID);
        return profilePhoto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfilePhoto createUpdatedEntity(EntityManager em) {
        ProfilePhoto profilePhoto = new ProfilePhoto()
            .userId(UPDATED_USER_ID)
            .path(UPDATED_PATH)
            .statusId(UPDATED_STATUS_ID);
        return profilePhoto;
    }

    @BeforeEach
    public void initTest() {
        profilePhoto = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilePhoto() throws Exception {
        int databaseSizeBeforeCreate = profilePhotoRepository.findAll().size();
        // Create the ProfilePhoto
        ProfilePhotoDTO profilePhotoDTO = profilePhotoMapper.toDto(profilePhoto);
        restProfilePhotoMockMvc.perform(post("/api/profile-photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilePhotoDTO)))
            .andExpect(status().isCreated());

        // Validate the ProfilePhoto in the database
        List<ProfilePhoto> profilePhotoList = profilePhotoRepository.findAll();
        assertThat(profilePhotoList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilePhoto testProfilePhoto = profilePhotoList.get(profilePhotoList.size() - 1);
        assertThat(testProfilePhoto.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testProfilePhoto.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testProfilePhoto.getStatusId()).isEqualTo(DEFAULT_STATUS_ID);
    }

    @Test
    @Transactional
    public void createProfilePhotoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilePhotoRepository.findAll().size();

        // Create the ProfilePhoto with an existing ID
        profilePhoto.setId(1L);
        ProfilePhotoDTO profilePhotoDTO = profilePhotoMapper.toDto(profilePhoto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilePhotoMockMvc.perform(post("/api/profile-photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilePhotoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilePhoto in the database
        List<ProfilePhoto> profilePhotoList = profilePhotoRepository.findAll();
        assertThat(profilePhotoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProfilePhotos() throws Exception {
        // Initialize the database
        profilePhotoRepository.saveAndFlush(profilePhoto);

        // Get all the profilePhotoList
        restProfilePhotoMockMvc.perform(get("/api/profile-photos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilePhoto.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].statusId").value(hasItem(DEFAULT_STATUS_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getProfilePhoto() throws Exception {
        // Initialize the database
        profilePhotoRepository.saveAndFlush(profilePhoto);

        // Get the profilePhoto
        restProfilePhotoMockMvc.perform(get("/api/profile-photos/{id}", profilePhoto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profilePhoto.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.statusId").value(DEFAULT_STATUS_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProfilePhoto() throws Exception {
        // Get the profilePhoto
        restProfilePhotoMockMvc.perform(get("/api/profile-photos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilePhoto() throws Exception {
        // Initialize the database
        profilePhotoRepository.saveAndFlush(profilePhoto);

        int databaseSizeBeforeUpdate = profilePhotoRepository.findAll().size();

        // Update the profilePhoto
        ProfilePhoto updatedProfilePhoto = profilePhotoRepository.findById(profilePhoto.getId()).get();
        // Disconnect from session so that the updates on updatedProfilePhoto are not directly saved in db
        em.detach(updatedProfilePhoto);
        updatedProfilePhoto
            .userId(UPDATED_USER_ID)
            .path(UPDATED_PATH)
            .statusId(UPDATED_STATUS_ID);
        ProfilePhotoDTO profilePhotoDTO = profilePhotoMapper.toDto(updatedProfilePhoto);

        restProfilePhotoMockMvc.perform(put("/api/profile-photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilePhotoDTO)))
            .andExpect(status().isOk());

        // Validate the ProfilePhoto in the database
        List<ProfilePhoto> profilePhotoList = profilePhotoRepository.findAll();
        assertThat(profilePhotoList).hasSize(databaseSizeBeforeUpdate);
        ProfilePhoto testProfilePhoto = profilePhotoList.get(profilePhotoList.size() - 1);
        assertThat(testProfilePhoto.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testProfilePhoto.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testProfilePhoto.getStatusId()).isEqualTo(UPDATED_STATUS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilePhoto() throws Exception {
        int databaseSizeBeforeUpdate = profilePhotoRepository.findAll().size();

        // Create the ProfilePhoto
        ProfilePhotoDTO profilePhotoDTO = profilePhotoMapper.toDto(profilePhoto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfilePhotoMockMvc.perform(put("/api/profile-photos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilePhotoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilePhoto in the database
        List<ProfilePhoto> profilePhotoList = profilePhotoRepository.findAll();
        assertThat(profilePhotoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfilePhoto() throws Exception {
        // Initialize the database
        profilePhotoRepository.saveAndFlush(profilePhoto);

        int databaseSizeBeforeDelete = profilePhotoRepository.findAll().size();

        // Delete the profilePhoto
        restProfilePhotoMockMvc.perform(delete("/api/profile-photos/{id}", profilePhoto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfilePhoto> profilePhotoList = profilePhotoRepository.findAll();
        assertThat(profilePhotoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
