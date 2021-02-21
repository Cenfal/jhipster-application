package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ArticleTagService;
import com.mycompany.myapp.domain.ArticleTag;
import com.mycompany.myapp.repository.ArticleTagRepository;
import com.mycompany.myapp.service.dto.ArticleTagDTO;
import com.mycompany.myapp.service.mapper.ArticleTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ArticleTag}.
 */
@Service
@Transactional
public class ArticleTagServiceImpl implements ArticleTagService {

    private final Logger log = LoggerFactory.getLogger(ArticleTagServiceImpl.class);

    private final ArticleTagRepository articleTagRepository;

    private final ArticleTagMapper articleTagMapper;

    public ArticleTagServiceImpl(ArticleTagRepository articleTagRepository, ArticleTagMapper articleTagMapper) {
        this.articleTagRepository = articleTagRepository;
        this.articleTagMapper = articleTagMapper;
    }

    @Override
    public ArticleTagDTO save(ArticleTagDTO articleTagDTO) {
        log.debug("Request to save ArticleTag : {}", articleTagDTO);
        ArticleTag articleTag = articleTagMapper.toEntity(articleTagDTO);
        articleTag = articleTagRepository.save(articleTag);
        return articleTagMapper.toDto(articleTag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleTagDTO> findAll() {
        log.debug("Request to get all ArticleTags");
        return articleTagRepository.findAll().stream()
            .map(articleTagMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleTagDTO> findOne(Long id) {
        log.debug("Request to get ArticleTag : {}", id);
        return articleTagRepository.findById(id)
            .map(articleTagMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArticleTag : {}", id);
        articleTagRepository.deleteById(id);
    }
}
