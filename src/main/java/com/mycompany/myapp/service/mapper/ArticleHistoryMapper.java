package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ArticleHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArticleHistory} and its DTO {@link ArticleHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArticleHistoryMapper extends EntityMapper<ArticleHistoryDTO, ArticleHistory> {



    default ArticleHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArticleHistory articleHistory = new ArticleHistory();
        articleHistory.setId(id);
        return articleHistory;
    }
}
