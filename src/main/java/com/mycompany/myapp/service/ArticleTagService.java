package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ArticleTagDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ArticleTag}.
 */
public interface ArticleTagService {

    /**
     * Save a articleTag.
     *
     * @param articleTagDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleTagDTO save(ArticleTagDTO articleTagDTO);

    /**
     * Get all the articleTags.
     *
     * @return the list of entities.
     */
    List<ArticleTagDTO> findAll();


    /**
     * Get the "id" articleTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleTagDTO> findOne(Long id);

    /**
     * Delete the "id" articleTag.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
