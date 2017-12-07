package edu.cuny.brooklyn.cisc3120.web.exception;

public class StudentNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2107558417686938695L;
    
    private String sid;

    public StudentNotFoundException(String sid) {
        super("Student " + sid + " not found.");
        this.sid = sid;
    }
    
    public String getSid() {
        return sid;
    }
}
