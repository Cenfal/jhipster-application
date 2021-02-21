package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ArticleHistoryService;
import com.mycompany.myapp.domain.ArticleHistory;
import com.mycompany.myapp.repository.ArticleHistoryRepository;
import com.mycompany.myapp.service.dto.ArticleHistoryDTO;
import com.mycompany.myapp.service.mapper.ArticleHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ArticleHistory}.
 */
@Service
@Transactional
public class ArticleHistoryServiceImpl implements ArticleHistoryService {

    private final Logger log = LoggerFactory.getLogger(ArticleHistoryServiceImpl.class);

    private final ArticleHistoryRepository articleHistoryRepository;

    private final ArticleHistoryMapper articleHistoryMapper;

    public ArticleHistoryServiceImpl(ArticleHistoryRepository articleHistoryRepository, ArticleHistoryMapper articleHistoryMapper) {
        this.articleHistoryRepository = articleHistoryRepository;
        this.articleHistoryMapper = articleHistoryMapper;
    }

    @Override
    public ArticleHistoryDTO save(ArticleHistoryDTO articleHistoryDTO) {
        log.debug("Request to save ArticleHistory : {}", articleHistoryDTO);
        ArticleHistory articleHistory = articleHistoryMapper.toEntity(articleHistoryDTO);
        articleHistory = articleHistoryRepository.save(articleHistory);
        return articleHistoryMapper.toDto(articleHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleHistoryDTO> findAll() {
        log.debug("Request to get all ArticleHistories");
        return articleHistoryRepository.findAll().stream()
            .map(articleHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleHistoryDTO> findOne(Long id) {
        log.debug("Request to get ArticleHistory : {}", id);
        return articleHistoryRepository.findById(id)
            .map(articleHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArticleHistory : {}", id);
        articleHistoryRepository.deleteById(id);
    }
}
