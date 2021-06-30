package com.example.schoolmanagerrestresourceserver.controllers;

import com.example.schoolmanagerrestresourceserver.facades.CourseFacade;
import com.example.schoolmanagerrestresourceserver.facades.SchoolUserFacade;
import com.example.schoolmanagerrestresourceserver.models.dtos.SchoolUserDTO;
import com.example.schoolmanagerrestresourceserver.models.params.AddCourseParam;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    SchoolUserFacade schoolUserFacade;

    @Autowired
    CourseFacade courseFacade;

    @Autowired
    Keycloak keycloak;

    @PostMapping("/registerUser")
    public void registerUser(Principal user){
        UserRepresentation representation = keycloak.realm("Demo-Realm").users().get(user.getName()).toRepresentation();
        schoolUserFacade.createSchoolUser(representation);
        Long id = schoolUserFacade.getUserId(representation.getUsername());

        Map<String , List<String>> attributes = new HashMap<>();
        attributes.put("database-id" , Arrays.asList(Long.toString(id)));
        representation.setAttributes(attributes);
        keycloak.realm("Demo-Realm").users().get(user.getName()).update(representation);
    }

    @GetMapping("/getUserInfo")
    public SchoolUserDTO getUserInfo(Principal user){
        String id = user.getName();
        SchoolUserDTO dto = new SchoolUserDTO();
        UserResource user1 = keycloak.realm("Demo-Realm").users().get(id);
        dto.setFirstName(user1.toRepresentation().getFirstName());
        dto.setLastName(user1.toRepresentation().getLastName());
        return dto;
    }

    @PutMapping("/addInfoToUser")
    public void addInfoToUser(Principal user , @RequestBody Map<String , Object> body){
        UserRepresentation representation = keycloak.realm("Demo-Realm").users().get(user.getName()).toRepresentation();
        if(body.containsKey("age")){
            schoolUserFacade.updateUserAge((Integer)body.get("age") , Long.parseLong(representation.getAttributes().get("database-id").get(0)));
        }

        if(body.containsKey("gender")){
            schoolUserFacade.updateUserGender((String)body.get("gender") , Long.parseLong(representation.getAttributes().get("database-id").get(0)));
        }

        if(body.containsKey("mobileNumber")){
            schoolUserFacade.updateUserMobileNumber((String)body.get("mobileNumber") , Long.parseLong(representation.getAttributes().get("database-id").get(0)));
        }

    }

//    @PostMapping("/addCourse")
//    public void addCourse(Principal user , AddCourseParam addCourseParam){
//        courseFacade.createCourse(keycloak.realm("Demo-Realm").users().get(user.getName()).toRepresentation() , addCourseParam);
//    }
}
