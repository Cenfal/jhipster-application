package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.ImageType;
import com.mycompany.myapp.repository.ImageTypeRepository;
import com.mycompany.myapp.service.ImageTypeService;
import com.mycompany.myapp.service.dto.ImageTypeDTO;
import com.mycompany.myapp.service.mapper.ImageTypeMapper;

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
 * Integration tests for the {@link ImageTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImageTypeResourceIT {

    private static final Long DEFAULT_TYPE_ID = 1L;
    private static final Long UPDATED_TYPE_ID = 2L;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS_ID = 1L;
    private static final Long UPDATED_STATUS_ID = 2L;

    @Autowired
    private ImageTypeRepository imageTypeRepository;

    @Autowired
    private ImageTypeMapper imageTypeMapper;

    @Autowired
    private ImageTypeService imageTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImageTypeMockMvc;

    private ImageType imageType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageType createEntity(EntityManager em) {
        ImageType imageType = new ImageType()
            .typeId(DEFAULT_TYPE_ID)
            .type(DEFAULT_TYPE)
            .statusId(DEFAULT_STATUS_ID);
        return imageType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageType createUpdatedEntity(EntityManager em) {
        ImageType imageType = new ImageType()
            .typeId(UPDATED_TYPE_ID)
            .type(UPDATED_TYPE)
            .statusId(UPDATED_STATUS_ID);
        return imageType;
    }

    @BeforeEach
    public void initTest() {
        imageType = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageType() throws Exception {
        int databaseSizeBeforeCreate = imageTypeRepository.findAll().size();
        // Create the ImageType
        ImageTypeDTO imageTypeDTO = imageTypeMapper.toDto(imageType);
        restImageTypeMockMvc.perform(post("/api/image-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageType in the database
        List<ImageType> imageTypeList = imageTypeRepository.findAll();
        assertThat(imageTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ImageType testImageType = imageTypeList.get(imageTypeList.size() - 1);
        assertThat(testImageType.getTypeId()).isEqualTo(DEFAULT_TYPE_ID);
        assertThat(testImageType.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testImageType.getStatusId()).isEqualTo(DEFAULT_STATUS_ID);
    }

    @Test
    @Transactional
    public void createImageTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageTypeRepository.findAll().size();

        // Create the ImageType with an existing ID
        imageType.setId(1L);
        ImageTypeDTO imageTypeDTO = imageTypeMapper.toDto(imageType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageTypeMockMvc.perform(post("/api/image-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageType in the database
        List<ImageType> imageTypeList = imageTypeRepository.findAll();
        assertThat(imageTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImageTypes() throws Exception {
        // Initialize the database
        imageTypeRepository.saveAndFlush(imageType);

        // Get all the imageTypeList
        restImageTypeMockMvc.perform(get("/api/image-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageType.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeId").value(hasItem(DEFAULT_TYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].statusId").value(hasItem(DEFAULT_STATUS_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getImageType() throws Exception {
        // Initialize the database
        imageTypeRepository.saveAndFlush(imageType);

        // Get the imageType
        restImageTypeMockMvc.perform(get("/api/image-types/{id}", imageType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(imageType.getId().intValue()))
            .andExpect(jsonPath("$.typeId").value(DEFAULT_TYPE_ID.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.statusId").value(DEFAULT_STATUS_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingImageType() throws Exception {
        // Get the imageType
        restImageTypeMockMvc.perform(get("/api/image-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageType() throws Exception {
        // Initialize the database
        imageTypeRepository.saveAndFlush(imageType);

        int databaseSizeBeforeUpdate = imageTypeRepository.findAll().size();

        // Update the imageType
        ImageType updatedImageType = imageTypeRepository.findById(imageType.getId()).get();
        // Disconnect from session so that the updates on updatedImageType are not directly saved in db
        em.detach(updatedImageType);
        updatedImageType
            .typeId(UPDATED_TYPE_ID)
            .type(UPDATED_TYPE)
            .statusId(UPDATED_STATUS_ID);
        ImageTypeDTO imageTypeDTO = imageTypeMapper.toDto(updatedImageType);

        restImageTypeMockMvc.perform(put("/api/image-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ImageType in the database
        List<ImageType> imageTypeList = imageTypeRepository.findAll();
        assertThat(imageTypeList).hasSize(databaseSizeBeforeUpdate);
        ImageType testImageType = imageTypeList.get(imageTypeList.size() - 1);
        assertThat(testImageType.getTypeId()).isEqualTo(UPDATED_TYPE_ID);
        assertThat(testImageType.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testImageType.getStatusId()).isEqualTo(UPDATED_STATUS_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingImageType() throws Exception {
        int databaseSizeBeforeUpdate = imageTypeRepository.findAll().size();

        // Create the ImageType
        ImageTypeDTO imageTypeDTO = imageTypeMapper.toDto(imageType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageTypeMockMvc.perform(put("/api/image-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(imageTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageType in the database
        List<ImageType> imageTypeList = imageTypeRepository.findAll();
        assertThat(imageTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageType() throws Exception {
        // Initialize the database
        imageTypeRepository.saveAndFlush(imageType);

        int databaseSizeBeforeDelete = imageTypeRepository.findAll().size();

        // Delete the imageType
        restImageTypeMockMvc.perform(delete("/api/image-types/{id}", imageType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImageType> imageTypeList = imageTypeRepository.findAll();
        assertThat(imageTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
