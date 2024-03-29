package edu.cuny.brooklyn.cisc3120.web.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.cuny.brooklyn.cisc3120.web.service.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
    public Student findBySid(String studentId);
    public List<Student> findAll();
}
