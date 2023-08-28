package com.ead.userauth.feignclient;

import com.ead.commonlib.dto.response.PageResponseDTO;
import com.ead.userauth.dto.response.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "EAD-API-GATEWAY", path = "/ead-course")
public interface CourseClient {

    @GetMapping("/courses")
    PageResponseDTO<CourseDTO> getAllCoursesByUserId(@RequestParam UUID userId, Pageable pageable);

}
