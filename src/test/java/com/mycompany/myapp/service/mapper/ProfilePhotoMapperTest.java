package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfilePhotoMapperTest {

    private ProfilePhotoMapper profilePhotoMapper;

    @BeforeEach
    public void setUp() {
        profilePhotoMapper = new ProfilePhotoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(profilePhotoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(profilePhotoMapper.fromId(null)).isNull();
    }
}
