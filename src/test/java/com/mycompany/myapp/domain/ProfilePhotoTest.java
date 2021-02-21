package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ProfilePhotoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilePhoto.class);
        ProfilePhoto profilePhoto1 = new ProfilePhoto();
        profilePhoto1.setId(1L);
        ProfilePhoto profilePhoto2 = new ProfilePhoto();
        profilePhoto2.setId(profilePhoto1.getId());
        assertThat(profilePhoto1).isEqualTo(profilePhoto2);
        profilePhoto2.setId(2L);
        assertThat(profilePhoto1).isNotEqualTo(profilePhoto2);
        profilePhoto1.setId(null);
        assertThat(profilePhoto1).isNotEqualTo(profilePhoto2);
    }
}
