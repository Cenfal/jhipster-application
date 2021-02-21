package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ArticleTopicService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ArticleTopicDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ArticleTopic}.
 */
@RestController
@RequestMapping("/api")
public class ArticleTopicResource {

    private final Logger log = LoggerFactory.getLogger(ArticleTopicResource.class);

    private static final String ENTITY_NAME = "articleTopic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArticleTopicService articleTopicService;

    public ArticleTopicResource(ArticleTopicService articleTopicService) {
        this.articleTopicService = articleTopicService;
    }

    /**
     * {@code POST  /article-topics} : Create a new articleTopic.
     *
     * @param articleTopicDTO the articleTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new articleTopicDTO, or with status {@code 400 (Bad Request)} if the articleTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/article-topics")
    public ResponseEntity<ArticleTopicDTO> createArticleTopic(@RequestBody ArticleTopicDTO articleTopicDTO) throws URISyntaxException {
        log.debug("REST request to save ArticleTopic : {}", articleTopicDTO);
        if (articleTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new articleTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArticleTopicDTO result = articleTopicService.save(articleTopicDTO);
        return ResponseEntity.created(new URI("/api/article-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /article-topics} : Updates an existing articleTopic.
     *
     * @param articleTopicDTO the articleTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated articleTopicDTO,
     * or with status {@code 400 (Bad Request)} if the articleTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the articleTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/article-topics")
    public ResponseEntity<ArticleTopicDTO> updateArticleTopic(@RequestBody ArticleTopicDTO articleTopicDTO) throws URISyntaxException {
        log.debug("REST request to update ArticleTopic : {}", articleTopicDTO);
        if (articleTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArticleTopicDTO result = articleTopicService.save(articleTopicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, articleTopicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /article-topics} : get all the articleTopics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of articleTopics in body.
     */
    @GetMapping("/article-topics")
    public List<ArticleTopicDTO> getAllArticleTopics() {
        log.debug("REST request to get all ArticleTopics");
        return articleTopicService.findAll();
    }

    /**
     * {@code GET  /article-topics/:id} : get the "id" articleTopic.
     *
     * @param id the id of the articleTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the articleTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/article-topics/{id}")
    public ResponseEntity<ArticleTopicDTO> getArticleTopic(@PathVariable Long id) {
        log.debug("REST request to get ArticleTopic : {}", id);
        Optional<ArticleTopicDTO> articleTopicDTO = articleTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(articleTopicDTO);
    }

    /**
     * {@code DELETE  /article-topics/:id} : delete the "id" articleTopic.
     *
     * @param id the id of the articleTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/article-topics/{id}")
    public ResponseEntity<Void> deleteArticleTopic(@PathVariable Long id) {
        log.debug("REST request to delete ArticleTopic : {}", id);
        articleTopicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
