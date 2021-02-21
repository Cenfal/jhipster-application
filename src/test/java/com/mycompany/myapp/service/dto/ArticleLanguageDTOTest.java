package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleLanguageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleLanguageDTO.class);
        ArticleLanguageDTO articleLanguageDTO1 = new ArticleLanguageDTO();
        articleLanguageDTO1.setId(1L);
        ArticleLanguageDTO articleLanguageDTO2 = new ArticleLanguageDTO();
        assertThat(articleLanguageDTO1).isNotEqualTo(articleLanguageDTO2);
        articleLanguageDTO2.setId(articleLanguageDTO1.getId());
        assertThat(articleLanguageDTO1).isEqualTo(articleLanguageDTO2);
        articleLanguageDTO2.setId(2L);
        assertThat(articleLanguageDTO1).isNotEqualTo(articleLanguageDTO2);
        articleLanguageDTO1.setId(null);
        assertThat(articleLanguageDTO1).isNotEqualTo(articleLanguageDTO2);
    }
}
