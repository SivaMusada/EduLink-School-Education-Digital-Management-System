package com.cts.edulink.course_service.service;

import com.cts.edulink.course_service.entity.Course;
import com.cts.edulink.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Optional<Course> getById(Long id) {
        return repository.findById(id);
    }

    public Course save(Course course) {
        return repository.save(course);
    }
}