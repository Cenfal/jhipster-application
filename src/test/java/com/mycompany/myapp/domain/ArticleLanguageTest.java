package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleLanguageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleLanguage.class);
        ArticleLanguage articleLanguage1 = new ArticleLanguage();
        articleLanguage1.setId(1L);
        ArticleLanguage articleLanguage2 = new ArticleLanguage();
        articleLanguage2.setId(articleLanguage1.getId());
        assertThat(articleLanguage1).isEqualTo(articleLanguage2);
        articleLanguage2.setId(2L);
        assertThat(articleLanguage1).isNotEqualTo(articleLanguage2);
        articleLanguage1.setId(null);
        assertThat(articleLanguage1).isNotEqualTo(articleLanguage2);
    }
}
