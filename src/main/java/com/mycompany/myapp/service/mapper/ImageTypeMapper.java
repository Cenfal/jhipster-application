package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ImageTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ImageType} and its DTO {@link ImageTypeDTO}.
 */
@Mapper(componentModel = "spring" )
public interface ImageTypeMapper extends EntityMapper<ImageTypeDTO, ImageType> {



    default ImageType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageType imageType = new ImageType();
        imageType.setId(id);
        return imageType;
    }
}
