package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ArticleTagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArticleTag} and its DTO {@link ArticleTagDTO}.
 */
@Mapper(componentModel = "spring" )
public interface ArticleTagMapper extends EntityMapper<ArticleTagDTO, ArticleTag> {



    default ArticleTag fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArticleTag articleTag = new ArticleTag();
        articleTag.setId(id);
        return articleTag;
    }
}
