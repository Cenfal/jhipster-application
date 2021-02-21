package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.Main;
import com.mycompany.myapp.repository.MainRepository;
import com.mycompany.myapp.service.MainService;
import com.mycompany.myapp.service.dto.MainDTO;
import com.mycompany.myapp.service.mapper.MainMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MainResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MainResourceIT {

    private static final LocalDate DEFAULT_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private MainRepository mainRepository;

    @Autowired
    private MainMapper mainMapper;

    @Autowired
    private MainService mainService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMainMockMvc;

    private Main main;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Main createEntity(EntityManager em) {
        Main main = new Main()
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .endDate(DEFAULT_END_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return main;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Main createUpdatedEntity(EntityManager em) {
        Main main = new Main()
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return main;
    }

    @BeforeEach
    public void initTest() {
        main = createEntity(em);
    }

    @Test
    @Transactional
    public void createMain() throws Exception {
        int databaseSizeBeforeCreate = mainRepository.findAll().size();
        // Create the Main
        MainDTO mainDTO = mainMapper.toDto(main);
        restMainMockMvc.perform(post("/api/mains")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mainDTO)))
            .andExpect(status().isCreated());

        // Validate the Main in the database
        List<Main> mainList = mainRepository.findAll();
        assertThat(mainList).hasSize(databaseSizeBeforeCreate + 1);
        Main testMain = mainList.get(mainList.size() - 1);
        assertThat(testMain.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testMain.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testMain.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testMain.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMain.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createMainWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mainRepository.findAll().size();

        // Create the Main with an existing ID
        main.setId(1L);
        MainDTO mainDTO = mainMapper.toDto(main);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMainMockMvc.perform(post("/api/mains")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mainDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Main in the database
        List<Main> mainList = mainRepository.findAll();
        assertThat(mainList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMains() throws Exception {
        // Initialize the database
        mainRepository.saveAndFlush(main);

        // Get all the mainList
        restMainMockMvc.perform(get("/api/mains?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(main.getId().intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }
    
    @Test
    @Transactional
    public void getMain() throws Exception {
        // Initialize the database
        mainRepository.saveAndFlush(main);

        // Get the main
        restMainMockMvc.perform(get("/api/mains/{id}", main.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(main.getId().intValue()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingMain() throws Exception {
        // Get the main
        restMainMockMvc.perform(get("/api/mains/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMain() throws Exception {
        // Initialize the database
        mainRepository.saveAndFlush(main);

        int databaseSizeBeforeUpdate = mainRepository.findAll().size();

        // Update the main
        Main updatedMain = mainRepository.findById(main.getId()).get();
        // Disconnect from session so that the updates on updatedMain are not directly saved in db
        em.detach(updatedMain);
        updatedMain
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        MainDTO mainDTO = mainMapper.toDto(updatedMain);

        restMainMockMvc.perform(put("/api/mains")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mainDTO)))
            .andExpect(status().isOk());

        // Validate the Main in the database
        List<Main> mainList = mainRepository.findAll();
        assertThat(mainList).hasSize(databaseSizeBeforeUpdate);
        Main testMain = mainList.get(mainList.size() - 1);
        assertThat(testMain.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testMain.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testMain.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testMain.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMain.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingMain() throws Exception {
        int databaseSizeBeforeUpdate = mainRepository.findAll().size();

        // Create the Main
        MainDTO mainDTO = mainMapper.toDto(main);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMainMockMvc.perform(put("/api/mains")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mainDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Main in the database
        List<Main> mainList = mainRepository.findAll();
        assertThat(mainList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMain() throws Exception {
        // Initialize the database
        mainRepository.saveAndFlush(main);

        int databaseSizeBeforeDelete = mainRepository.findAll().size();

        // Delete the main
        restMainMockMvc.perform(delete("/api/mains/{id}", main.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Main> mainList = mainRepository.findAll();
        assertThat(mainList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
