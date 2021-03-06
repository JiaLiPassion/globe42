package org.globe42.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Config class used to disable suffix matching, which prevents URLs to `/api/persons/1000/files/foo.png` to
 * be downloaded because Spring considers the path is `/api/persons/1000/files/foo`.
 * @author JB Nizet
 */
@Configuration
public class SuffixMatchingConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}
