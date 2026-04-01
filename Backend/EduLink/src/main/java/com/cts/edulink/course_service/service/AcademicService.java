package com.cts.edulink.course_service.service;

import com.cts.edulink.course_service.entity.ClassRoom;
import com.cts.edulink.course_service.entity.Course;
import com.cts.edulink.course_service.repository.ClassRoomRepository;
import com.cts.edulink.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
    }

    public ClassRoom saveClass(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    public List<ClassRoom> getAllClasses() {
        return classRoomRepository.findAll();
    }

    public Optional<ClassRoom> getClassById(Long id) {
        return classRoomRepository.findById(id);
    }

    public void deleteClass(Long id) {
        if (classRoomRepository.existsById(id)) {
            classRoomRepository.deleteById(id);
        }
    }
}