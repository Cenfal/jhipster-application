package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApplicationApp;
import com.mycompany.myapp.domain.CommentHistory;
import com.mycompany.myapp.repository.CommentHistoryRepository;
import com.mycompany.myapp.service.CommentHistoryService;
import com.mycompany.myapp.service.dto.CommentHistoryDTO;
import com.mycompany.myapp.service.mapper.CommentHistoryMapper;

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
 * Integration tests for the {@link CommentHistoryResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommentHistoryResourceIT {

    private static final UUID DEFAULT_COMMENT_ID = UUID.randomUUID();
    private static final UUID UPDATED_COMMENT_ID = UUID.randomUUID();

    private static final Long DEFAULT_UPDATE_TIME = 1L;
    private static final Long UPDATED_UPDATE_TIME = 2L;

    @Autowired
    private CommentHistoryRepository commentHistoryRepository;

    @Autowired
    private CommentHistoryMapper commentHistoryMapper;

    @Autowired
    private CommentHistoryService commentHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommentHistoryMockMvc;

    private CommentHistory commentHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommentHistory createEntity(EntityManager em) {
        CommentHistory commentHistory = new CommentHistory()
            .commentId(DEFAULT_COMMENT_ID)
            .updateTime(DEFAULT_UPDATE_TIME);
        return commentHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommentHistory createUpdatedEntity(EntityManager em) {
        CommentHistory commentHistory = new CommentHistory()
            .commentId(UPDATED_COMMENT_ID)
            .updateTime(UPDATED_UPDATE_TIME);
        return commentHistory;
    }

    @BeforeEach
    public void initTest() {
        commentHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommentHistory() throws Exception {
        int databaseSizeBeforeCreate = commentHistoryRepository.findAll().size();
        // Create the CommentHistory
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);
        restCommentHistoryMockMvc.perform(post("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        CommentHistory testCommentHistory = commentHistoryList.get(commentHistoryList.size() - 1);
        assertThat(testCommentHistory.getCommentId()).isEqualTo(DEFAULT_COMMENT_ID);
        assertThat(testCommentHistory.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createCommentHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentHistoryRepository.findAll().size();

        // Create the CommentHistory with an existing ID
        commentHistory.setId(1L);
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentHistoryMockMvc.perform(post("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommentHistories() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        // Get all the commentHistoryList
        restCommentHistoryMockMvc.perform(get("/api/comment-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commentHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].commentId").value(hasItem(DEFAULT_COMMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.intValue())));
    }
    
    @Test
    @Transactional
    public void getCommentHistory() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        // Get the commentHistory
        restCommentHistoryMockMvc.perform(get("/api/comment-histories/{id}", commentHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commentHistory.getId().intValue()))
            .andExpect(jsonPath("$.commentId").value(DEFAULT_COMMENT_ID.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCommentHistory() throws Exception {
        // Get the commentHistory
        restCommentHistoryMockMvc.perform(get("/api/comment-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommentHistory() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        int databaseSizeBeforeUpdate = commentHistoryRepository.findAll().size();

        // Update the commentHistory
        CommentHistory updatedCommentHistory = commentHistoryRepository.findById(commentHistory.getId()).get();
        // Disconnect from session so that the updates on updatedCommentHistory are not directly saved in db
        em.detach(updatedCommentHistory);
        updatedCommentHistory
            .commentId(UPDATED_COMMENT_ID)
            .updateTime(UPDATED_UPDATE_TIME);
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(updatedCommentHistory);

        restCommentHistoryMockMvc.perform(put("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeUpdate);
        CommentHistory testCommentHistory = commentHistoryList.get(commentHistoryList.size() - 1);
        assertThat(testCommentHistory.getCommentId()).isEqualTo(UPDATED_COMMENT_ID);
        assertThat(testCommentHistory.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingCommentHistory() throws Exception {
        int databaseSizeBeforeUpdate = commentHistoryRepository.findAll().size();

        // Create the CommentHistory
        CommentHistoryDTO commentHistoryDTO = commentHistoryMapper.toDto(commentHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommentHistoryMockMvc.perform(put("/api/comment-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommentHistory in the database
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommentHistory() throws Exception {
        // Initialize the database
        commentHistoryRepository.saveAndFlush(commentHistory);

        int databaseSizeBeforeDelete = commentHistoryRepository.findAll().size();

        // Delete the commentHistory
        restCommentHistoryMockMvc.perform(delete("/api/comment-histories/{id}", commentHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommentHistory> commentHistoryList = commentHistoryRepository.findAll();
        assertThat(commentHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
