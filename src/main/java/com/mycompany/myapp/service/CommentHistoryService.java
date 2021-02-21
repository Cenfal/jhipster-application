package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CommentHistoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.CommentHistory}.
 */
public interface CommentHistoryService {

    /**
     * Save a commentHistory.
     *
     * @param commentHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    CommentHistoryDTO save(CommentHistoryDTO commentHistoryDTO);

    /**
     * Get all the commentHistories.
     *
     * @return the list of entities.
     */
    List<CommentHistoryDTO> findAll();


    /**
     * Get the "id" commentHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" commentHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
