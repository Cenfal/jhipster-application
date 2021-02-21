package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class UserWebsiteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWebsite.class);
        UserWebsite userWebsite1 = new UserWebsite();
        userWebsite1.setId(1L);
        UserWebsite userWebsite2 = new UserWebsite();
        userWebsite2.setId(userWebsite1.getId());
        assertThat(userWebsite1).isEqualTo(userWebsite2);
        userWebsite2.setId(2L);
        assertThat(userWebsite1).isNotEqualTo(userWebsite2);
        userWebsite1.setId(null);
        assertThat(userWebsite1).isNotEqualTo(userWebsite2);
    }
}
