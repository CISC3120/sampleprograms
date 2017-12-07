package edu.cuny.brooklyn.cisc3120.web.service;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({
    @CompoundIndex(name = "course_idx"
            , def = "{'studentId': 1, 'number': 1}")
})
public class Course {
    @Id
    private String id;
    
    private String studentId;

    private String number;

    private String title;

    private String term;

    private int creditHours;

    private String grade;

    private double gradePoint;

    public Course() {
        this.studentId = "";
        this.number = "";
        this.title = "";
        this.term = "";
        this.creditHours = 0;
        this.grade = "";
        this.gradePoint = 0.0;
    }

    public Course(String studentId, String number, String title, String term, int creditHours) {
        this.studentId = studentId;
        this.number = number;
        this.title = title;
        this.term = term;
        this.creditHours = creditHours;
        this.grade = "";
        this.gradePoint = 0.0;
    }

    public int getCreditHours() {
        return creditHours;
    }
    
    public String getGrade() {
        return grade;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public String getNumber() {
        return number;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTerm() {
        return term;
    }

    public String getTitle() {
        return title;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGrade(String grade, Map<String, Double> gradePointMap) {
        this.grade = grade;
        setGradePoint(gradePointMap);
    }

    public void setGradePoint(Map<String, Double> gradePointMap) {
        double points = gradePointMap.get(grade);
        this.gradePoint = points; 
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public void setStudentId(String sid) {
        this.studentId = sid;
    }
    
    
    public void setTerm(String term) {
        this.term = term;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return String.format(
                "Course[studentId=%s, number=%s, title=%s, term=%s, creditHours=%d, grade=%s, gradePoint=%5.3f]"
                , studentId, number, title, term, creditHours, grade, gradePoint);
    }
    
    public void updateId() {
        this.id = studentId + "_" + number;
    }
}
