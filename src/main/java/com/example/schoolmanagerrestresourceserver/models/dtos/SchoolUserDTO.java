package com.example.schoolmanagerrestresourceserver.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SchoolUserDTO {
    private String firstName;
    private String LastName;
    private Integer age;
    private String gender;
    private String mobileNumber;
}
