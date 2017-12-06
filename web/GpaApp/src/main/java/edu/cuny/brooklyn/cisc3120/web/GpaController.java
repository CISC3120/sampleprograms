package edu.cuny.brooklyn.cisc3120.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gpa")
public class GpaController {

    @RequestMapping("/{studentid}")
    public String getGpa(@PathVariable(name="studentid") String sId, Model model) {
        double gpa = 3.1415;
        model.addAttribute("gpa", gpa);
        model.addAttribute("sid", sId);
        return "dispgpa"; // return the name of the view. The name match a template html file.
    }
}
