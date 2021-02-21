package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ArticleHistoryService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ArticleHistoryDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ArticleHistory}.
 */
@RestController
@RequestMapping("/api")
public class ArticleHistoryResource {

    private final Logger log = LoggerFactory.getLogger(ArticleHistoryResource.class);

    private static final String ENTITY_NAME = "articleHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArticleHistoryService articleHistoryService;

    public ArticleHistoryResource(ArticleHistoryService articleHistoryService) {
        this.articleHistoryService = articleHistoryService;
    }

    /**
     * {@code POST  /article-histories} : Create a new articleHistory.
     *
     * @param articleHistoryDTO the articleHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new articleHistoryDTO, or with status {@code 400 (Bad Request)} if the articleHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/article-histories")
    public ResponseEntity<ArticleHistoryDTO> createArticleHistory(@RequestBody ArticleHistoryDTO articleHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save ArticleHistory : {}", articleHistoryDTO);
        if (articleHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new articleHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArticleHistoryDTO result = articleHistoryService.save(articleHistoryDTO);
        return ResponseEntity.created(new URI("/api/article-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /article-histories} : Updates an existing articleHistory.
     *
     * @param articleHistoryDTO the articleHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated articleHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the articleHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the articleHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/article-histories")
    public ResponseEntity<ArticleHistoryDTO> updateArticleHistory(@RequestBody ArticleHistoryDTO articleHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update ArticleHistory : {}", articleHistoryDTO);
        if (articleHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArticleHistoryDTO result = articleHistoryService.save(articleHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, articleHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /article-histories} : get all the articleHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of articleHistories in body.
     */
    @GetMapping("/article-histories")
    public List<ArticleHistoryDTO> getAllArticleHistories() {
        log.debug("REST request to get all ArticleHistories");
        return articleHistoryService.findAll();
    }

    /**
     * {@code GET  /article-histories/:id} : get the "id" articleHistory.
     *
     * @param id the id of the articleHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the articleHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/article-histories/{id}")
    public ResponseEntity<ArticleHistoryDTO> getArticleHistory(@PathVariable Long id) {
        log.debug("REST request to get ArticleHistory : {}", id);
        Optional<ArticleHistoryDTO> articleHistoryDTO = articleHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(articleHistoryDTO);
    }

    /**
     * {@code DELETE  /article-histories/:id} : delete the "id" articleHistory.
     *
     * @param id the id of the articleHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/article-histories/{id}")
    public ResponseEntity<Void> deleteArticleHistory(@PathVariable Long id) {
        log.debug("REST request to delete ArticleHistory : {}", id);
        articleHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
