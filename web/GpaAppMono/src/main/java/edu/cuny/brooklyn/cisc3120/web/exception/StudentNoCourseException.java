package edu.cuny.brooklyn.cisc3120.web.exception;

public class StudentNoCourseException extends RuntimeException {
    private static final long serialVersionUID = 4917383726997688691L;
    private String sid;
    
    public StudentNoCourseException(String sid) {
        super("Student " + sid + " has not taken any classes.");
        this.sid = sid;
    }
    
    
    public String getSid() {
        return sid;
    }

}
