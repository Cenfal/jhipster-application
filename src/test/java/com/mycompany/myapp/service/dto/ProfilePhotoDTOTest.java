package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ProfilePhotoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilePhotoDTO.class);
        ProfilePhotoDTO profilePhotoDTO1 = new ProfilePhotoDTO();
        profilePhotoDTO1.setId(1L);
        ProfilePhotoDTO profilePhotoDTO2 = new ProfilePhotoDTO();
        assertThat(profilePhotoDTO1).isNotEqualTo(profilePhotoDTO2);
        profilePhotoDTO2.setId(profilePhotoDTO1.getId());
        assertThat(profilePhotoDTO1).isEqualTo(profilePhotoDTO2);
        profilePhotoDTO2.setId(2L);
        assertThat(profilePhotoDTO1).isNotEqualTo(profilePhotoDTO2);
        profilePhotoDTO1.setId(null);
        assertThat(profilePhotoDTO1).isNotEqualTo(profilePhotoDTO2);
    }
}
