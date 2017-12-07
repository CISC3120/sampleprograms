package edu.cuny.brooklyn.cisc3120.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cuny.brooklyn.cisc3120.web.exception.StudentNoCourseException;
import edu.cuny.brooklyn.cisc3120.web.exception.StudentNotFoundException;
import edu.cuny.brooklyn.cisc3120.web.service.Course;
import edu.cuny.brooklyn.cisc3120.web.service.GpaService;
import edu.cuny.brooklyn.cisc3120.web.service.Student;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/gpa")
public class GpaController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GpaController.class);
    
    private final GpaService gpaService;
    
    @Autowired
    GpaController(GpaService gpaService) {
        this.gpaService = gpaService;
    }

    @RequestMapping("/viewgpa/{studentid}")
    public String viewGpa(@PathVariable(name="studentid") String studentId, Model model) {
        LOGGER.info("Request /viewgpa/{studentid} as /viewgpa/" + studentId);
        Student student = gpaService.findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException(studentId);
        }
        List<Course> courses = gpaService.findCoursesByStudentId(studentId);
        if (courses == null || courses.isEmpty()) {
            throw new StudentNoCourseException(studentId);
        }
        double gpa = gpaService.computeGPA(courses);
        model.addAttribute("gpa", gpa);
        model.addAttribute("sid", student.getSid());
        return "viewgpa"; // return the name of the view. The name match a template html file.
    }
    
    @RequestMapping("/viewcourses/{studentid}")
    public String viewCourses(@PathVariable(name="studentid") String studentId, Model model) {
        LOGGER.info("Request /viewcourses/{studentid} as /viewcourses/" + studentId);
        Student student = gpaService.findStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException(studentId);
        }
        List<Course> courses = gpaService.findCoursesByStudentId(studentId);
        if (courses == null || courses.isEmpty()) {
            throw new StudentNoCourseException(studentId);
        }
        model.addAttribute("courseList", courses);
        model.addAttribute("student", student);
        return "viewcourses"; // return the name of the view. The name match a template html file.
    }
    
    @RequestMapping(value="/addcourse", method=RequestMethod.GET)
    String addCourseForm(Model model) {
        LOGGER.info("Request /gpa/addcourse: getting data");
        // the name of Student object must match the object in the template
        model.addAttribute("student", new Student()); 
        model.addAttribute("course", new Course());
        return "addcourse";
    }
    
    @RequestMapping(value="/addcourse", method=RequestMethod.POST)
    String addCourseSumit(@ModelAttribute("course") Course course, @ModelAttribute("student") Student student) {
        LOGGER.info("Request /gpa/addcourse: adding course.");
        LOGGER.info("Got student from request: " + student.toString());
        LOGGER.info("Got course from request: " + course.toString());
        Map<String, Double> gradePointMap = gpaService.getGradePointMap();
        course.setGradePoint(gradePointMap);
        course.setStudentId(student.getSid());
        gpaService.addCourse(course);
        LOGGER.info("Saved course: " + course.toString());
                
        double gpa = gpaService.computeGPA(student.getSid());
        student.setGpa(gpa);
        gpaService.addStudent(student);
        
        LOGGER.info("Saved student: " + student.toString());
        return "courseadded";
    }
}
