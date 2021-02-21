package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class UserWebsiteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWebsiteDTO.class);
        UserWebsiteDTO userWebsiteDTO1 = new UserWebsiteDTO();
        userWebsiteDTO1.setId(1L);
        UserWebsiteDTO userWebsiteDTO2 = new UserWebsiteDTO();
        assertThat(userWebsiteDTO1).isNotEqualTo(userWebsiteDTO2);
        userWebsiteDTO2.setId(userWebsiteDTO1.getId());
        assertThat(userWebsiteDTO1).isEqualTo(userWebsiteDTO2);
        userWebsiteDTO2.setId(2L);
        assertThat(userWebsiteDTO1).isNotEqualTo(userWebsiteDTO2);
        userWebsiteDTO1.setId(null);
        assertThat(userWebsiteDTO1).isNotEqualTo(userWebsiteDTO2);
    }
}
