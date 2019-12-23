package com.muker.courses.service.impl;

import com.muker.courses.dao.CoursesDao;
import com.muker.courses.entity.Courses;
import com.muker.courses.service.CoursesService;
import com.muker.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wyy
 * @Date 2019-12-21
 */
@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesDao coursesDao;


    @Override
    public R add(Courses courses) {
        if (coursesDao.insert(courses) > 0) {
            return R.Ok();
        } else {
            return R.fail();
        }
    }

    @Override
    public R queryAll() {
        if (coursesDao.selectAll() != null) {
            return R.Ok(coursesDao.selectAll());
        } else {
            return R.fail();
        }
    }

    @Override
    public R queryById(int id) {
        if (coursesDao.selectById(id) != null) {
            return R.Ok(coursesDao.selectById(id));
        } else {
            return R.fail();
        }
    }
}
