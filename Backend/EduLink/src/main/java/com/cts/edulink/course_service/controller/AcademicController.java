package com.cts.edulink.course_service.controller;

import com.cts.edulink.course_service.entity.ClassRoom;
import com.cts.edulink.course_service.entity.Course;
import com.cts.edulink.course_service.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academics")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return academicService.saveCourse(course);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return academicService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return academicService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course details) {
        return academicService.getCourseById(id).map(course -> {
            course.setTitle(details.getTitle());
            course.setSubject(details.getSubject());
            course.setCredits(details.getCredits());
            return ResponseEntity.ok(academicService.saveCourse(course));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        academicService.deleteCourse(id);
        return ResponseEntity.ok("Course with ID " + id + " deleted successfully.");
    }

    @PostMapping("/classes")
    public ClassRoom createClass(@RequestBody ClassRoom classRoom) {
        return academicService.saveClass(classRoom);
    }

    @GetMapping("/classes")
    public List<ClassRoom> getAllClasses() {
        return academicService.getAllClasses();
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<ClassRoom> getClassById(@PathVariable Long id) {
        return academicService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/classes/{id}")
    public ResponseEntity<ClassRoom> updateClass(@PathVariable Long id, @RequestBody ClassRoom details) {
        return academicService.getClassById(id).map(room -> {
            room.setSchedule(details.getSchedule());
            room.setStatus(details.getStatus());
            room.setTeacherId(details.getTeacherId());
            // Optionally update courseId if your entity allows it
            return ResponseEntity.ok(academicService.saveClass(room));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        academicService.deleteClass(id);
        return ResponseEntity.ok("Classroom with ID " + id + " deleted successfully.");
    }
}