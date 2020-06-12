package edu.miu.cs.cs425.project.miututoring.api.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;


@Entity
public class Faculty extends User {

    @NotBlank(message = "* Department is required")
    private String department;

    public Faculty() {
    }

    public Faculty(@NotEmpty String username, @NotEmpty String password, @NotBlank String firstName, String middleName, @NotBlank String lastName, @NotBlank(message = "* Department is required") String department) {
        super(username, password, firstName, middleName, lastName, new ArrayList<>(Arrays.asList("ROLE_FACULTY")));
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
