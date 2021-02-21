package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommentHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommentHistory} and its DTO {@link CommentHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommentHistoryMapper extends EntityMapper<CommentHistoryDTO, CommentHistory> {



    default CommentHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommentHistory commentHistory = new CommentHistory();
        commentHistory.setId(id);
        return commentHistory;
    }
}
