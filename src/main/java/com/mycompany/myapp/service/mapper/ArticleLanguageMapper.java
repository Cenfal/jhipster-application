package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ArticleLanguageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArticleLanguage} and its DTO {@link ArticleLanguageDTO}.
 */
@Mapper(componentModel = "spring" )
public interface ArticleLanguageMapper extends EntityMapper<ArticleLanguageDTO, ArticleLanguage> {



    default ArticleLanguage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArticleLanguage articleLanguage = new ArticleLanguage();
        articleLanguage.setId(id);
        return articleLanguage;
    }
}
