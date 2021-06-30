package com.example.schoolmanagerrestresourceserver.models.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCourseParam {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}
