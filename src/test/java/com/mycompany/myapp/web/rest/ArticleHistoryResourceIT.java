package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.ArticleHistory;
import com.mycompany.myapp.repository.ArticleHistoryRepository;
import com.mycompany.myapp.service.ArticleHistoryService;
import com.mycompany.myapp.service.dto.ArticleHistoryDTO;
import com.mycompany.myapp.service.mapper.ArticleHistoryMapper;

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
 * Integration tests for the {@link ArticleHistoryResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArticleHistoryResourceIT {

    private static final UUID DEFAULT_ARTICLE_ID = UUID.randomUUID();
    private static final UUID UPDATED_ARTICLE_ID = UUID.randomUUID();

    private static final Long DEFAULT_UPDATE_TIME = 1L;
    private static final Long UPDATED_UPDATE_TIME = 2L;

    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;

    @Autowired
    private ArticleHistoryMapper articleHistoryMapper;

    @Autowired
    private ArticleHistoryService articleHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArticleHistoryMockMvc;

    private ArticleHistory articleHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleHistory createEntity(EntityManager em) {
        ArticleHistory articleHistory = new ArticleHistory()
            .articleId(DEFAULT_ARTICLE_ID)
            .updateTime(DEFAULT_UPDATE_TIME);
        return articleHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleHistory createUpdatedEntity(EntityManager em) {
        ArticleHistory articleHistory = new ArticleHistory()
            .articleId(UPDATED_ARTICLE_ID)
            .updateTime(UPDATED_UPDATE_TIME);
        return articleHistory;
    }

    @BeforeEach
    public void initTest() {
        articleHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleHistory() throws Exception {
        int databaseSizeBeforeCreate = articleHistoryRepository.findAll().size();
        // Create the ArticleHistory
        ArticleHistoryDTO articleHistoryDTO = articleHistoryMapper.toDto(articleHistory);
        restArticleHistoryMockMvc.perform(post("/api/article-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ArticleHistory in the database
        List<ArticleHistory> articleHistoryList = articleHistoryRepository.findAll();
        assertThat(articleHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ArticleHistory testArticleHistory = articleHistoryList.get(articleHistoryList.size() - 1);
        assertThat(testArticleHistory.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testArticleHistory.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createArticleHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleHistoryRepository.findAll().size();

        // Create the ArticleHistory with an existing ID
        articleHistory.setId(1L);
        ArticleHistoryDTO articleHistoryDTO = articleHistoryMapper.toDto(articleHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleHistoryMockMvc.perform(post("/api/article-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleHistory in the database
        List<ArticleHistory> articleHistoryList = articleHistoryRepository.findAll();
        assertThat(articleHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArticleHistories() throws Exception {
        // Initialize the database
        articleHistoryRepository.saveAndFlush(articleHistory);

        // Get all the articleHistoryList
        restArticleHistoryMockMvc.perform(get("/api/article-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(articleHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.intValue())));
    }
    
    @Test
    @Transactional
    public void getArticleHistory() throws Exception {
        // Initialize the database
        articleHistoryRepository.saveAndFlush(articleHistory);

        // Get the articleHistory
        restArticleHistoryMockMvc.perform(get("/api/article-histories/{id}", articleHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(articleHistory.getId().intValue()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingArticleHistory() throws Exception {
        // Get the articleHistory
        restArticleHistoryMockMvc.perform(get("/api/article-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleHistory() throws Exception {
        // Initialize the database
        articleHistoryRepository.saveAndFlush(articleHistory);

        int databaseSizeBeforeUpdate = articleHistoryRepository.findAll().size();

        // Update the articleHistory
        ArticleHistory updatedArticleHistory = articleHistoryRepository.findById(articleHistory.getId()).get();
        // Disconnect from session so that the updates on updatedArticleHistory are not directly saved in db
        em.detach(updatedArticleHistory);
        updatedArticleHistory
            .articleId(UPDATED_ARTICLE_ID)
            .updateTime(UPDATED_UPDATE_TIME);
        ArticleHistoryDTO articleHistoryDTO = articleHistoryMapper.toDto(updatedArticleHistory);

        restArticleHistoryMockMvc.perform(put("/api/article-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the ArticleHistory in the database
        List<ArticleHistory> articleHistoryList = articleHistoryRepository.findAll();
        assertThat(articleHistoryList).hasSize(databaseSizeBeforeUpdate);
        ArticleHistory testArticleHistory = articleHistoryList.get(articleHistoryList.size() - 1);
        assertThat(testArticleHistory.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testArticleHistory.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingArticleHistory() throws Exception {
        int databaseSizeBeforeUpdate = articleHistoryRepository.findAll().size();

        // Create the ArticleHistory
        ArticleHistoryDTO articleHistoryDTO = articleHistoryMapper.toDto(articleHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArticleHistoryMockMvc.perform(put("/api/article-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleHistory in the database
        List<ArticleHistory> articleHistoryList = articleHistoryRepository.findAll();
        assertThat(articleHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticleHistory() throws Exception {
        // Initialize the database
        articleHistoryRepository.saveAndFlush(articleHistory);

        int databaseSizeBeforeDelete = articleHistoryRepository.findAll().size();

        // Delete the articleHistory
        restArticleHistoryMockMvc.perform(delete("/api/article-histories/{id}", articleHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArticleHistory> articleHistoryList = articleHistoryRepository.findAll();
        assertThat(articleHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
