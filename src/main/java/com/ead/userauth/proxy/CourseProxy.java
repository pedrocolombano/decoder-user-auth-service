package com.ead.userauth.proxy;

import com.ead.commonlib.exception.ProxyException;
import com.ead.userauth.dto.response.CourseDTO;
import com.ead.userauth.dto.response.PageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class CourseProxy {

    private final RestTemplate restTemplate;
    private final Environment environment;

    public Page<CourseDTO> getAllByUser(final UUID userId, final Pageable pageable) {
        final String url = buildCourseUrl(userId, pageable).replace(": ", ",");
        log.info("Request URL: {}", url);
        try {
            ParameterizedTypeReference<PageResponseDTO<CourseDTO>> responseType = new ParameterizedTypeReference<>() {};
            ResponseEntity<PageResponseDTO<CourseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            return response.getBody();
        } catch (Exception e) {
            log.error("Error during request into: {}", url);
            throw new ProxyException("Could not fetch results from course service.");
        }
    }

    private String getCourseServiceHost() {
        return environment.getProperty("ead.course.service.host");
    }

    private String buildCourseUrl(final UUID userId, final Pageable pageable) {
        return getCourseServiceHost()
                + "/courses?userId="
                + userId
                + "&page="
                + pageable.getPageNumber()
                + "&size="
                + pageable.getPageSize();
    }

}
