package com.example.schoolmanagerrestresourceserver.models.mappers;

import com.example.schoolmanagerrestresourceserver.models.dtos.SchoolUserDTO;
import com.example.schoolmanagerrestresourceserver.models.entities.SchoolUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SchoolUserMapper {

    public static SchoolUserDTO fromSchoolUser(SchoolUser schoolUser) {
        if (schoolUser == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        SchoolUserDTO dto = new SchoolUserDTO();
        dto.setFirstName(schoolUser.getFirstName());
        dto.setLastName(schoolUser.getLastName());
        dto.setMobileNumber(schoolUser.getMobileNumber());
        dto.setAge(schoolUser.getAge());
        dto.setGender(schoolUser.getGender());
        return dto;
    }

    public static List<SchoolUserDTO> fromSchoolUsersList(List<SchoolUser> users) {
        return users.stream().map(SchoolUserMapper::fromSchoolUser).collect(Collectors.toList());
    }

}
