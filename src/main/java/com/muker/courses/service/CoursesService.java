package com.muker.courses.service;

import com.muker.courses.entity.Courses;
import com.muker.vo.R;

/**
 * @author Wyy
 * @Date 2019-12-21
 */
public interface CoursesService {
    R add(Courses courses);

    R queryAll();

    R queryById(int id);
}
