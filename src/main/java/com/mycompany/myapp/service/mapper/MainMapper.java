package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MainDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Main} and its DTO {@link MainDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContactMapper.class, UserWebsiteMapper.class, ProfilePhotoMapper.class, ImageMapper.class, ArticleHistoryMapper.class, CommentHistoryMapper.class, ArticleMapper.class, CommentMapper.class})
public interface MainMapper extends EntityMapper<MainDTO, Main> {

    @Mapping(source = "contact.id", target = "contactId")
    @Mapping(source = "userWebsite.id", target = "userWebsiteId")
    @Mapping(source = "profilePhoto.id", target = "profilePhotoId")
    @Mapping(source = "image.id", target = "imageId")
    @Mapping(source = "articleHistory.id", target = "articleHistoryId")
    @Mapping(source = "commentHistory.id", target = "commentHistoryId")
    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "comment.id", target = "commentId")
    MainDTO toDto(Main main);

    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "userWebsiteId", target = "userWebsite")
    @Mapping(source = "profilePhotoId", target = "profilePhoto")
    @Mapping(source = "imageId", target = "image")
    @Mapping(source = "articleHistoryId", target = "articleHistory")
    @Mapping(source = "commentHistoryId", target = "commentHistory")
    @Mapping(source = "articleId", target = "article")
    @Mapping(source = "commentId", target = "comment")
    Main toEntity(MainDTO mainDTO);

    default Main fromId(Long id) {
        if (id == null) {
            return null;
        }
        Main main = new Main();
        main.setId(id);
        return main;
    }
}
