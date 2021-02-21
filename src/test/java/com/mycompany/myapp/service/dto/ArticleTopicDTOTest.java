package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleTopicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleTopicDTO.class);
        ArticleTopicDTO articleTopicDTO1 = new ArticleTopicDTO();
        articleTopicDTO1.setId(1L);
        ArticleTopicDTO articleTopicDTO2 = new ArticleTopicDTO();
        assertThat(articleTopicDTO1).isNotEqualTo(articleTopicDTO2);
        articleTopicDTO2.setId(articleTopicDTO1.getId());
        assertThat(articleTopicDTO1).isEqualTo(articleTopicDTO2);
        articleTopicDTO2.setId(2L);
        assertThat(articleTopicDTO1).isNotEqualTo(articleTopicDTO2);
        articleTopicDTO1.setId(null);
        assertThat(articleTopicDTO1).isNotEqualTo(articleTopicDTO2);
    }
}
