package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProfilePhotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProfilePhoto} and its DTO {@link ProfilePhotoDTO}.
 */
@Mapper(componentModel = "spring" )
public interface ProfilePhotoMapper extends EntityMapper<ProfilePhotoDTO, ProfilePhoto> {



    default ProfilePhoto fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setId(id);
        return profilePhoto;
    }
}
