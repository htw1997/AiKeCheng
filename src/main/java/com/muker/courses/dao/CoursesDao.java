package com.muker.courses.dao;

import com.muker.courses.entity.Courses;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wyy
 * @Date 2019-12-21
 */
@Repository
public interface CoursesDao {
    int insert(Courses courses);

    List<Courses> selectAll();

    Courses selectById(int id);
}
