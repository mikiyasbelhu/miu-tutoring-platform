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

    @Override
    public boolean equals(Object obj) {
        boolean result;
        if((obj == null) || (getClass() != obj.getClass())){
            result = false;
        } // end if
        else{
            Faculty other = (Faculty) obj;
            result = this.getUsername().equals(other.getUsername()) &&
                    this.getMiddleName().equals(other.getMiddleName()) &&
                    this.getLastName().equals(other.getLastName())&&
                    this.getDepartment().equals(other.getDepartment())&&
                    this.getRoles().equals(other.getRoles());
        } // end else
        return result;
    }
}
