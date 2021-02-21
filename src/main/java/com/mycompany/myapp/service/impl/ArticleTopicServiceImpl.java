package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ArticleTopicService;
import com.mycompany.myapp.domain.ArticleTopic;
import com.mycompany.myapp.repository.ArticleTopicRepository;
import com.mycompany.myapp.service.dto.ArticleTopicDTO;
import com.mycompany.myapp.service.mapper.ArticleTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ArticleTopic}.
 */
@Service
@Transactional
public class ArticleTopicServiceImpl implements ArticleTopicService {

    private final Logger log = LoggerFactory.getLogger(ArticleTopicServiceImpl.class);

    private final ArticleTopicRepository articleTopicRepository;

    private final ArticleTopicMapper articleTopicMapper;

    public ArticleTopicServiceImpl(ArticleTopicRepository articleTopicRepository, ArticleTopicMapper articleTopicMapper) {
        this.articleTopicRepository = articleTopicRepository;
        this.articleTopicMapper = articleTopicMapper;
    }

    @Override
    public ArticleTopicDTO save(ArticleTopicDTO articleTopicDTO) {
        log.debug("Request to save ArticleTopic : {}", articleTopicDTO);
        ArticleTopic articleTopic = articleTopicMapper.toEntity(articleTopicDTO);
        articleTopic = articleTopicRepository.save(articleTopic);
        return articleTopicMapper.toDto(articleTopic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleTopicDTO> findAll() {
        log.debug("Request to get all ArticleTopics");
        return articleTopicRepository.findAll().stream()
            .map(articleTopicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleTopicDTO> findOne(Long id) {
        log.debug("Request to get ArticleTopic : {}", id);
        return articleTopicRepository.findById(id)
            .map(articleTopicMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArticleTopic : {}", id);
        articleTopicRepository.deleteById(id);
    }
}
