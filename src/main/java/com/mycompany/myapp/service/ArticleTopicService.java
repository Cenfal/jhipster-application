package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ArticleTopicDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ArticleTopic}.
 */
public interface ArticleTopicService {

    /**
     * Save a articleTopic.
     *
     * @param articleTopicDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleTopicDTO save(ArticleTopicDTO articleTopicDTO);

    /**
     * Get all the articleTopics.
     *
     * @return the list of entities.
     */
    List<ArticleTopicDTO> findAll();


    /**
     * Get the "id" articleTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleTopicDTO> findOne(Long id);

    /**
     * Delete the "id" articleTopic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
