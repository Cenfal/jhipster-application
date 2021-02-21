package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ArticleHistoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ArticleHistory}.
 */
public interface ArticleHistoryService {

    /**
     * Save a articleHistory.
     *
     * @param articleHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleHistoryDTO save(ArticleHistoryDTO articleHistoryDTO);

    /**
     * Get all the articleHistories.
     *
     * @return the list of entities.
     */
    List<ArticleHistoryDTO> findAll();


    /**
     * Get the "id" articleHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" articleHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
