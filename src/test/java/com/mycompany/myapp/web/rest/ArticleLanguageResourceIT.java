package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.ArticleLanguage;
import com.mycompany.myapp.repository.ArticleLanguageRepository;
import com.mycompany.myapp.service.ArticleLanguageService;
import com.mycompany.myapp.service.dto.ArticleLanguageDTO;
import com.mycompany.myapp.service.mapper.ArticleLanguageMapper;

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
 * Integration tests for the {@link ArticleLanguageResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArticleLanguageResourceIT {

    private static final UUID DEFAULT_ARTICLE_ID = UUID.randomUUID();
    private static final UUID UPDATED_ARTICLE_ID = UUID.randomUUID();

    private static final Long DEFAULT_LANGUAGE_ID = 1L;
    private static final Long UPDATED_LANGUAGE_ID = 2L;

    @Autowired
    private ArticleLanguageRepository articleLanguageRepository;

    @Autowired
    private ArticleLanguageMapper articleLanguageMapper;

    @Autowired
    private ArticleLanguageService articleLanguageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArticleLanguageMockMvc;

    private ArticleLanguage articleLanguage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleLanguage createEntity(EntityManager em) {
        ArticleLanguage articleLanguage = new ArticleLanguage()
            .articleId(DEFAULT_ARTICLE_ID)
            .languageId(DEFAULT_LANGUAGE_ID);
        return articleLanguage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleLanguage createUpdatedEntity(EntityManager em) {
        ArticleLanguage articleLanguage = new ArticleLanguage()
            .articleId(UPDATED_ARTICLE_ID)
            .languageId(UPDATED_LANGUAGE_ID);
        return articleLanguage;
    }

    @BeforeEach
    public void initTest() {
        articleLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleLanguage() throws Exception {
        int databaseSizeBeforeCreate = articleLanguageRepository.findAll().size();
        // Create the ArticleLanguage
        ArticleLanguageDTO articleLanguageDTO = articleLanguageMapper.toDto(articleLanguage);
        restArticleLanguageMockMvc.perform(post("/api/article-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleLanguageDTO)))
            .andExpect(status().isCreated());

        // Validate the ArticleLanguage in the database
        List<ArticleLanguage> articleLanguageList = articleLanguageRepository.findAll();
        assertThat(articleLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        ArticleLanguage testArticleLanguage = articleLanguageList.get(articleLanguageList.size() - 1);
        assertThat(testArticleLanguage.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testArticleLanguage.getLanguageId()).isEqualTo(DEFAULT_LANGUAGE_ID);
    }

    @Test
    @Transactional
    public void createArticleLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleLanguageRepository.findAll().size();

        // Create the ArticleLanguage with an existing ID
        articleLanguage.setId(1L);
        ArticleLanguageDTO articleLanguageDTO = articleLanguageMapper.toDto(articleLanguage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleLanguageMockMvc.perform(post("/api/article-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleLanguage in the database
        List<ArticleLanguage> articleLanguageList = articleLanguageRepository.findAll();
        assertThat(articleLanguageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArticleLanguages() throws Exception {
        // Initialize the database
        articleLanguageRepository.saveAndFlush(articleLanguage);

        // Get all the articleLanguageList
        restArticleLanguageMockMvc.perform(get("/api/article-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(articleLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID.toString())))
            .andExpect(jsonPath("$.[*].languageId").value(hasItem(DEFAULT_LANGUAGE_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getArticleLanguage() throws Exception {
        // Initialize the database
        articleLanguageRepository.saveAndFlush(articleLanguage);

        // Get the articleLanguage
        restArticleLanguageMockMvc.perform(get("/api/article-languages/{id}", articleLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(articleLanguage.getId().intValue()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID.toString()))
            .andExpect(jsonPath("$.languageId").value(DEFAULT_LANGUAGE_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingArticleLanguage() throws Exception {
        // Get the articleLanguage
        restArticleLanguageMockMvc.perform(get("/api/article-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleLanguage() throws Exception {
        // Initialize the database
        articleLanguageRepository.saveAndFlush(articleLanguage);

        int databaseSizeBeforeUpdate = articleLanguageRepository.findAll().size();

        // Update the articleLanguage
        ArticleLanguage updatedArticleLanguage = articleLanguageRepository.findById(articleLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedArticleLanguage are not directly saved in db
        em.detach(updatedArticleLanguage);
        updatedArticleLanguage
            .articleId(UPDATED_ARTICLE_ID)
            .languageId(UPDATED_LANGUAGE_ID);
        ArticleLanguageDTO articleLanguageDTO = articleLanguageMapper.toDto(updatedArticleLanguage);

        restArticleLanguageMockMvc.perform(put("/api/article-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleLanguageDTO)))
            .andExpect(status().isOk());

        // Validate the ArticleLanguage in the database
        List<ArticleLanguage> articleLanguageList = articleLanguageRepository.findAll();
        assertThat(articleLanguageList).hasSize(databaseSizeBeforeUpdate);
        ArticleLanguage testArticleLanguage = articleLanguageList.get(articleLanguageList.size() - 1);
        assertThat(testArticleLanguage.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testArticleLanguage.getLanguageId()).isEqualTo(UPDATED_LANGUAGE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingArticleLanguage() throws Exception {
        int databaseSizeBeforeUpdate = articleLanguageRepository.findAll().size();

        // Create the ArticleLanguage
        ArticleLanguageDTO articleLanguageDTO = articleLanguageMapper.toDto(articleLanguage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArticleLanguageMockMvc.perform(put("/api/article-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleLanguageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleLanguage in the database
        List<ArticleLanguage> articleLanguageList = articleLanguageRepository.findAll();
        assertThat(articleLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticleLanguage() throws Exception {
        // Initialize the database
        articleLanguageRepository.saveAndFlush(articleLanguage);

        int databaseSizeBeforeDelete = articleLanguageRepository.findAll().size();

        // Delete the articleLanguage
        restArticleLanguageMockMvc.perform(delete("/api/article-languages/{id}", articleLanguage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArticleLanguage> articleLanguageList = articleLanguageRepository.findAll();
        assertThat(articleLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
