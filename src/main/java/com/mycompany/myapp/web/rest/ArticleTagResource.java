package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ArticleTagService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ArticleTagDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ArticleTag}.
 */
@RestController
@RequestMapping("/api")
public class ArticleTagResource {

    private final Logger log = LoggerFactory.getLogger(ArticleTagResource.class);

    private static final String ENTITY_NAME = "articleTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArticleTagService articleTagService;

    public ArticleTagResource(ArticleTagService articleTagService) {
        this.articleTagService = articleTagService;
    }

    /**
     * {@code POST  /article-tags} : Create a new articleTag.
     *
     * @param articleTagDTO the articleTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new articleTagDTO, or with status {@code 400 (Bad Request)} if the articleTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/article-tags")
    public ResponseEntity<ArticleTagDTO> createArticleTag(@RequestBody ArticleTagDTO articleTagDTO) throws URISyntaxException {
        log.debug("REST request to save ArticleTag : {}", articleTagDTO);
        if (articleTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new articleTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArticleTagDTO result = articleTagService.save(articleTagDTO);
        return ResponseEntity.created(new URI("/api/article-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /article-tags} : Updates an existing articleTag.
     *
     * @param articleTagDTO the articleTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated articleTagDTO,
     * or with status {@code 400 (Bad Request)} if the articleTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the articleTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/article-tags")
    public ResponseEntity<ArticleTagDTO> updateArticleTag(@RequestBody ArticleTagDTO articleTagDTO) throws URISyntaxException {
        log.debug("REST request to update ArticleTag : {}", articleTagDTO);
        if (articleTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArticleTagDTO result = articleTagService.save(articleTagDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, articleTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /article-tags} : get all the articleTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of articleTags in body.
     */
    @GetMapping("/article-tags")
    public List<ArticleTagDTO> getAllArticleTags() {
        log.debug("REST request to get all ArticleTags");
        return articleTagService.findAll();
    }

    /**
     * {@code GET  /article-tags/:id} : get the "id" articleTag.
     *
     * @param id the id of the articleTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the articleTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/article-tags/{id}")
    public ResponseEntity<ArticleTagDTO> getArticleTag(@PathVariable Long id) {
        log.debug("REST request to get ArticleTag : {}", id);
        Optional<ArticleTagDTO> articleTagDTO = articleTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(articleTagDTO);
    }

    /**
     * {@code DELETE  /article-tags/:id} : delete the "id" articleTag.
     *
     * @param id the id of the articleTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/article-tags/{id}")
    public ResponseEntity<Void> deleteArticleTag(@PathVariable Long id) {
        log.debug("REST request to delete ArticleTag : {}", id);
        articleTagService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
