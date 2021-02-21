package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleTagDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleTagDTO.class);
        ArticleTagDTO articleTagDTO1 = new ArticleTagDTO();
        articleTagDTO1.setId(1L);
        ArticleTagDTO articleTagDTO2 = new ArticleTagDTO();
        assertThat(articleTagDTO1).isNotEqualTo(articleTagDTO2);
        articleTagDTO2.setId(articleTagDTO1.getId());
        assertThat(articleTagDTO1).isEqualTo(articleTagDTO2);
        articleTagDTO2.setId(2L);
        assertThat(articleTagDTO1).isNotEqualTo(articleTagDTO2);
        articleTagDTO1.setId(null);
        assertThat(articleTagDTO1).isNotEqualTo(articleTagDTO2);
    }
}
