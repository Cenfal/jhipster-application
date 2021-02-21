package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ArticleLanguageService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ArticleLanguageDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ArticleLanguage}.
 */
@RestController
@RequestMapping("/api")
public class ArticleLanguageResource {

    private final Logger log = LoggerFactory.getLogger(ArticleLanguageResource.class);

    private static final String ENTITY_NAME = "articleLanguage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArticleLanguageService articleLanguageService;

    public ArticleLanguageResource(ArticleLanguageService articleLanguageService) {
        this.articleLanguageService = articleLanguageService;
    }

    /**
     * {@code POST  /article-languages} : Create a new articleLanguage.
     *
     * @param articleLanguageDTO the articleLanguageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new articleLanguageDTO, or with status {@code 400 (Bad Request)} if the articleLanguage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/article-languages")
    public ResponseEntity<ArticleLanguageDTO> createArticleLanguage(@RequestBody ArticleLanguageDTO articleLanguageDTO) throws URISyntaxException {
        log.debug("REST request to save ArticleLanguage : {}", articleLanguageDTO);
        if (articleLanguageDTO.getId() != null) {
            throw new BadRequestAlertException("A new articleLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArticleLanguageDTO result = articleLanguageService.save(articleLanguageDTO);
        return ResponseEntity.created(new URI("/api/article-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /article-languages} : Updates an existing articleLanguage.
     *
     * @param articleLanguageDTO the articleLanguageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated articleLanguageDTO,
     * or with status {@code 400 (Bad Request)} if the articleLanguageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the articleLanguageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/article-languages")
    public ResponseEntity<ArticleLanguageDTO> updateArticleLanguage(@RequestBody ArticleLanguageDTO articleLanguageDTO) throws URISyntaxException {
        log.debug("REST request to update ArticleLanguage : {}", articleLanguageDTO);
        if (articleLanguageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArticleLanguageDTO result = articleLanguageService.save(articleLanguageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, articleLanguageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /article-languages} : get all the articleLanguages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of articleLanguages in body.
     */
    @GetMapping("/article-languages")
    public List<ArticleLanguageDTO> getAllArticleLanguages() {
        log.debug("REST request to get all ArticleLanguages");
        return articleLanguageService.findAll();
    }

    /**
     * {@code GET  /article-languages/:id} : get the "id" articleLanguage.
     *
     * @param id the id of the articleLanguageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the articleLanguageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/article-languages/{id}")
    public ResponseEntity<ArticleLanguageDTO> getArticleLanguage(@PathVariable Long id) {
        log.debug("REST request to get ArticleLanguage : {}", id);
        Optional<ArticleLanguageDTO> articleLanguageDTO = articleLanguageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(articleLanguageDTO);
    }

    /**
     * {@code DELETE  /article-languages/:id} : delete the "id" articleLanguage.
     *
     * @param id the id of the articleLanguageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/article-languages/{id}")
    public ResponseEntity<Void> deleteArticleLanguage(@PathVariable Long id) {
        log.debug("REST request to delete ArticleLanguage : {}", id);
        articleLanguageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
