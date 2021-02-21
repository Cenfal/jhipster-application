package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.ArticleTopic;
import com.mycompany.myapp.repository.ArticleTopicRepository;
import com.mycompany.myapp.service.ArticleTopicService;
import com.mycompany.myapp.service.dto.ArticleTopicDTO;
import com.mycompany.myapp.service.mapper.ArticleTopicMapper;

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
 * Integration tests for the {@link ArticleTopicResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArticleTopicResourceIT {

    private static final UUID DEFAULT_ARTICLE_ID = UUID.randomUUID();
    private static final UUID UPDATED_ARTICLE_ID = UUID.randomUUID();

    private static final Long DEFAULT_TOPIC_ID = 1L;
    private static final Long UPDATED_TOPIC_ID = 2L;

    @Autowired
    private ArticleTopicRepository articleTopicRepository;

    @Autowired
    private ArticleTopicMapper articleTopicMapper;

    @Autowired
    private ArticleTopicService articleTopicService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArticleTopicMockMvc;

    private ArticleTopic articleTopic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleTopic createEntity(EntityManager em) {
        ArticleTopic articleTopic = new ArticleTopic()
            .articleId(DEFAULT_ARTICLE_ID)
            .topicId(DEFAULT_TOPIC_ID);
        return articleTopic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArticleTopic createUpdatedEntity(EntityManager em) {
        ArticleTopic articleTopic = new ArticleTopic()
            .articleId(UPDATED_ARTICLE_ID)
            .topicId(UPDATED_TOPIC_ID);
        return articleTopic;
    }

    @BeforeEach
    public void initTest() {
        articleTopic = createEntity(em);
    }

    @Test
    @Transactional
    public void createArticleTopic() throws Exception {
        int databaseSizeBeforeCreate = articleTopicRepository.findAll().size();
        // Create the ArticleTopic
        ArticleTopicDTO articleTopicDTO = articleTopicMapper.toDto(articleTopic);
        restArticleTopicMockMvc.perform(post("/api/article-topics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTopicDTO)))
            .andExpect(status().isCreated());

        // Validate the ArticleTopic in the database
        List<ArticleTopic> articleTopicList = articleTopicRepository.findAll();
        assertThat(articleTopicList).hasSize(databaseSizeBeforeCreate + 1);
        ArticleTopic testArticleTopic = articleTopicList.get(articleTopicList.size() - 1);
        assertThat(testArticleTopic.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testArticleTopic.getTopicId()).isEqualTo(DEFAULT_TOPIC_ID);
    }

    @Test
    @Transactional
    public void createArticleTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = articleTopicRepository.findAll().size();

        // Create the ArticleTopic with an existing ID
        articleTopic.setId(1L);
        ArticleTopicDTO articleTopicDTO = articleTopicMapper.toDto(articleTopic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArticleTopicMockMvc.perform(post("/api/article-topics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleTopic in the database
        List<ArticleTopic> articleTopicList = articleTopicRepository.findAll();
        assertThat(articleTopicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArticleTopics() throws Exception {
        // Initialize the database
        articleTopicRepository.saveAndFlush(articleTopic);

        // Get all the articleTopicList
        restArticleTopicMockMvc.perform(get("/api/article-topics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(articleTopic.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID.toString())))
            .andExpect(jsonPath("$.[*].topicId").value(hasItem(DEFAULT_TOPIC_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getArticleTopic() throws Exception {
        // Initialize the database
        articleTopicRepository.saveAndFlush(articleTopic);

        // Get the articleTopic
        restArticleTopicMockMvc.perform(get("/api/article-topics/{id}", articleTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(articleTopic.getId().intValue()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID.toString()))
            .andExpect(jsonPath("$.topicId").value(DEFAULT_TOPIC_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingArticleTopic() throws Exception {
        // Get the articleTopic
        restArticleTopicMockMvc.perform(get("/api/article-topics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArticleTopic() throws Exception {
        // Initialize the database
        articleTopicRepository.saveAndFlush(articleTopic);

        int databaseSizeBeforeUpdate = articleTopicRepository.findAll().size();

        // Update the articleTopic
        ArticleTopic updatedArticleTopic = articleTopicRepository.findById(articleTopic.getId()).get();
        // Disconnect from session so that the updates on updatedArticleTopic are not directly saved in db
        em.detach(updatedArticleTopic);
        updatedArticleTopic
            .articleId(UPDATED_ARTICLE_ID)
            .topicId(UPDATED_TOPIC_ID);
        ArticleTopicDTO articleTopicDTO = articleTopicMapper.toDto(updatedArticleTopic);

        restArticleTopicMockMvc.perform(put("/api/article-topics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTopicDTO)))
            .andExpect(status().isOk());

        // Validate the ArticleTopic in the database
        List<ArticleTopic> articleTopicList = articleTopicRepository.findAll();
        assertThat(articleTopicList).hasSize(databaseSizeBeforeUpdate);
        ArticleTopic testArticleTopic = articleTopicList.get(articleTopicList.size() - 1);
        assertThat(testArticleTopic.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testArticleTopic.getTopicId()).isEqualTo(UPDATED_TOPIC_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingArticleTopic() throws Exception {
        int databaseSizeBeforeUpdate = articleTopicRepository.findAll().size();

        // Create the ArticleTopic
        ArticleTopicDTO articleTopicDTO = articleTopicMapper.toDto(articleTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArticleTopicMockMvc.perform(put("/api/article-topics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(articleTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArticleTopic in the database
        List<ArticleTopic> articleTopicList = articleTopicRepository.findAll();
        assertThat(articleTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArticleTopic() throws Exception {
        // Initialize the database
        articleTopicRepository.saveAndFlush(articleTopic);

        int databaseSizeBeforeDelete = articleTopicRepository.findAll().size();

        // Delete the articleTopic
        restArticleTopicMockMvc.perform(delete("/api/article-topics/{id}", articleTopic.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArticleTopic> articleTopicList = articleTopicRepository.findAll();
        assertThat(articleTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
