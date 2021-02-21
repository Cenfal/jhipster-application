package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ArticleLanguageService;
import com.mycompany.myapp.domain.ArticleLanguage;
import com.mycompany.myapp.repository.ArticleLanguageRepository;
import com.mycompany.myapp.service.dto.ArticleLanguageDTO;
import com.mycompany.myapp.service.mapper.ArticleLanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ArticleLanguage}.
 */
@Service
@Transactional
public class ArticleLanguageServiceImpl implements ArticleLanguageService {

    private final Logger log = LoggerFactory.getLogger(ArticleLanguageServiceImpl.class);

    private final ArticleLanguageRepository articleLanguageRepository;

    private final ArticleLanguageMapper articleLanguageMapper;

    public ArticleLanguageServiceImpl(ArticleLanguageRepository articleLanguageRepository, ArticleLanguageMapper articleLanguageMapper) {
        this.articleLanguageRepository = articleLanguageRepository;
        this.articleLanguageMapper = articleLanguageMapper;
    }

    @Override
    public ArticleLanguageDTO save(ArticleLanguageDTO articleLanguageDTO) {
        log.debug("Request to save ArticleLanguage : {}", articleLanguageDTO);
        ArticleLanguage articleLanguage = articleLanguageMapper.toEntity(articleLanguageDTO);
        articleLanguage = articleLanguageRepository.save(articleLanguage);
        return articleLanguageMapper.toDto(articleLanguage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleLanguageDTO> findAll() {
        log.debug("Request to get all ArticleLanguages");
        return articleLanguageRepository.findAll().stream()
            .map(articleLanguageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleLanguageDTO> findOne(Long id) {
        log.debug("Request to get ArticleLanguage : {}", id);
        return articleLanguageRepository.findById(id)
            .map(articleLanguageMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArticleLanguage : {}", id);
        articleLanguageRepository.deleteById(id);
    }
}
