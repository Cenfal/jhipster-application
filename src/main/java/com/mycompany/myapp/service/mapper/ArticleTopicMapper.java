package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ArticleTopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArticleTopic} and its DTO {@link ArticleTopicDTO}.
 */
@Mapper(componentModel = "spring")
public interface ArticleTopicMapper extends EntityMapper<ArticleTopicDTO, ArticleTopic> {



    default ArticleTopic fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArticleTopic articleTopic = new ArticleTopic();
        articleTopic.setId(id);
        return articleTopic;
    }
}
