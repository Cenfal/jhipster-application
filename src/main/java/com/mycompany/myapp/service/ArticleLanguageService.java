package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ArticleLanguageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ArticleLanguage}.
 */
public interface ArticleLanguageService {

    /**
     * Save a articleLanguage.
     *
     * @param articleLanguageDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleLanguageDTO save(ArticleLanguageDTO articleLanguageDTO);

    /**
     * Get all the articleLanguages.
     *
     * @return the list of entities.
     */
    List<ArticleLanguageDTO> findAll();


    /**
     * Get the "id" articleLanguage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleLanguageDTO> findOne(Long id);

    /**
     * Delete the "id" articleLanguage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
