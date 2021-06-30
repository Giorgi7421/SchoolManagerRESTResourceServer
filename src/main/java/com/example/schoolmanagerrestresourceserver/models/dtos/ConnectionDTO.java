package com.example.schoolmanagerrestresourceserver.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConnectionDTO {
    private long userId;
    private long courseId;
}
