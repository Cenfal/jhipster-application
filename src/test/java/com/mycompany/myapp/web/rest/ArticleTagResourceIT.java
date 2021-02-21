package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.ArticleTag;
import com.mycompany.myapp.repository.ArticleTagRepository;
import com.mycompany.myapp.service.ArticleTagService;
import com.mycompany.myapp.service.dto.ArticleTagDTO;
import com.mycompany.myapp.service.mapper.ArticleTagMapper;

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
 * Integration tests for the {@link ArticleTagResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArticleTagResourceIT {

    private static final UUID DEFAULT_ARTICLE_ID = UUID.randomUUID();
    private static final UUID UPDATED_ARTICLE_ID = UUID.randomUUID();

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    @Autowired
    private ArticleTagRepository articleTagRepository;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArticleTagMockMvc;

    private ArticleTag articleTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleTag createEntity(EntityManager em) {
        ArticleTag articleTag = new ArticleTag()
            .articleId(DEFAULT_ARTICLE_ID)
            .tagId(DEFAULT_TAG_ID);
        return articleTag;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleTag createUpdatedEntity(EntityManager em) {
        ArticleTag articleTag = new ArticleTag()
            .articleId(UPDATED_ARTICLE_ID)
            .tagId(UPDATED_TAG_ID);
        return articleTag;
    }

    @BeforeEach
    public void initTest() {
        articleTag = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleTag() throws Exception {
        int databaseSizeBeforeCreate = articleTagRepository.findAll().size();
        // Create the ArticleTag
        ArticleTagDTO articleTagDTO = articleTagMapper.toDto(articleTag);
        restArticleTagMockMvc.perform(post("/api/article-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTagDTO)))
            .andExpect(status().isCreated());

        // Validate the ArticleTag in the database
        List<ArticleTag> articleTagList = articleTagRepository.findAll();
        assertThat(articleTagList).hasSize(databaseSizeBeforeCreate + 1);
        ArticleTag testArticleTag = articleTagList.get(articleTagList.size() - 1);
        assertThat(testArticleTag.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testArticleTag.getTagId()).isEqualTo(DEFAULT_TAG_ID);
    }

    @Test
    @Transactional
    public void createArticleTagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleTagRepository.findAll().size();

        // Create the ArticleTag with an existing ID
        articleTag.setId(1L);
        ArticleTagDTO articleTagDTO = articleTagMapper.toDto(articleTag);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleTagMockMvc.perform(post("/api/article-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleTag in the database
        List<ArticleTag> articleTagList = articleTagRepository.findAll();
        assertThat(articleTagList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArticleTags() throws Exception {
        // Initialize the database
        articleTagRepository.saveAndFlush(articleTag);

        // Get all the articleTagList
        restArticleTagMockMvc.perform(get("/api/article-tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(articleTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID.toString())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getArticleTag() throws Exception {
        // Initialize the database
        articleTagRepository.saveAndFlush(articleTag);

        // Get the articleTag
        restArticleTagMockMvc.perform(get("/api/article-tags/{id}", articleTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(articleTag.getId().intValue()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID.toString()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingArticleTag() throws Exception {
        // Get the articleTag
        restArticleTagMockMvc.perform(get("/api/article-tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleTag() throws Exception {
        // Initialize the database
        articleTagRepository.saveAndFlush(articleTag);

        int databaseSizeBeforeUpdate = articleTagRepository.findAll().size();

        // Update the articleTag
        ArticleTag updatedArticleTag = articleTagRepository.findById(articleTag.getId()).get();
        // Disconnect from session so that the updates on updatedArticleTag are not directly saved in db
        em.detach(updatedArticleTag);
        updatedArticleTag
            .articleId(UPDATED_ARTICLE_ID)
            .tagId(UPDATED_TAG_ID);
        ArticleTagDTO articleTagDTO = articleTagMapper.toDto(updatedArticleTag);

        restArticleTagMockMvc.perform(put("/api/article-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTagDTO)))
            .andExpect(status().isOk());

        // Validate the ArticleTag in the database
        List<ArticleTag> articleTagList = articleTagRepository.findAll();
        assertThat(articleTagList).hasSize(databaseSizeBeforeUpdate);
        ArticleTag testArticleTag = articleTagList.get(articleTagList.size() - 1);
        assertThat(testArticleTag.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testArticleTag.getTagId()).isEqualTo(UPDATED_TAG_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingArticleTag() throws Exception {
        int databaseSizeBeforeUpdate = articleTagRepository.findAll().size();

        // Create the ArticleTag
        ArticleTagDTO articleTagDTO = articleTagMapper.toDto(articleTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArticleTagMockMvc.perform(put("/api/article-tags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleTag in the database
        List<ArticleTag> articleTagList = articleTagRepository.findAll();
        assertThat(articleTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticleTag() throws Exception {
        // Initialize the database
        articleTagRepository.saveAndFlush(articleTag);

        int databaseSizeBeforeDelete = articleTagRepository.findAll().size();

        // Delete the articleTag
        restArticleTagMockMvc.perform(delete("/api/article-tags/{id}", articleTag.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArticleTag> articleTagList = articleTagRepository.findAll();
        assertThat(articleTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
