package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.UserWebsiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserWebsite} and its DTO {@link UserWebsiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserWebsiteMapper extends EntityMapper<UserWebsiteDTO, UserWebsite> {



    default UserWebsite fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserWebsite userWebsite = new UserWebsite();
        userWebsite.setId(id);
        return userWebsite;
    }
}
