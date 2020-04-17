package com.muker.courses.controller;

import com.muker.courses.entity.Courses;
import com.muker.courses.service.CoursesService;
import com.muker.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wyy
 * @Date 2019-12-21
 */
@RestController
public class CoursesController {
    @Autowired
    private CoursesService service;

    @PostMapping("/api/courses/add.do")
    public R add(Courses courses) {
        return service.add(courses);
    }

    @GetMapping("/api/courses/all.do")
    public R all() {
        return service.queryAll();
    }

    @GetMapping("/api/courses/find.do")
    public R find(int id) {
        return service.queryById(id);
    }
}
