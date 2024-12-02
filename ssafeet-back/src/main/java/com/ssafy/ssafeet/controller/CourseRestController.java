package com.ssafy.ssafeet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafeet.model.dto.Gps;
import com.ssafy.ssafeet.model.service.CourseService;

@RestController
@RequestMapping("/api-course")
@CrossOrigin("*")
public class CourseRestController {

    private final CourseService courseService;
    
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<Gps>> getCourseById(@PathVariable int id) {
        List<Gps> list = courseService.getCourseGps(id);
        return ResponseEntity.ok(list);
    }
    
}
