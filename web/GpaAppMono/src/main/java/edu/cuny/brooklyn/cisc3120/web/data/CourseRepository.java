package edu.cuny.brooklyn.cisc3120.web.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.cuny.brooklyn.cisc3120.web.service.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
    public double findByNumber(String number);
    public List<Course> findAll();
    public List<Course> findAllByStudentId(String studentId);
    public List<Course> findAllByStudentId();
}
