package com.example.schoolmanagerrestresourceserver.facades;

import com.example.schoolmanagerrestresourceserver.models.dtos.CourseDTO;
import com.example.schoolmanagerrestresourceserver.models.dtos.SchoolUserDTO;
import com.example.schoolmanagerrestresourceserver.models.entities.SchoolUser;
import com.example.schoolmanagerrestresourceserver.models.mappers.CourseMapper;
import com.example.schoolmanagerrestresourceserver.models.mappers.SchoolUserMapper;
import com.example.schoolmanagerrestresourceserver.services.SchoolUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional( propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class SchoolUserFacade {

    @Autowired
    private SchoolUserService schoolUserService;

    @Autowired
    private PasswordEncoder encoder;

    public List<SchoolUserDTO> getAllSchoolUsers(){
        return SchoolUserMapper.fromSchoolUsersList(schoolUserService.getAllSchoolUsers());
    }

    public SchoolUserDTO getSchoolUserByID(int id) throws Exception {
        return SchoolUserMapper.fromSchoolUser(schoolUserService.getSchoolUserByID(id));
    }

    public SchoolUserDTO createSchoolUser(UserRepresentation addUser) {
        ObjectMapper objectMapper = new ObjectMapper();
        SchoolUser user = new SchoolUser();
        user.setKeycloakId(addUser.getId());
        user.setFirstName(addUser.getFirstName());
        user.setLastName(addUser.getLastName());
        user.setUserName(addUser.getUsername());
        user.setDeleted(false);

        return SchoolUserMapper.fromSchoolUser(schoolUserService.createSchoolUser(user));
    }

    public SchoolUserDTO updateSchoolUser(int id , UserRepresentation newUser) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SchoolUser user = objectMapper.convertValue(newUser, SchoolUser.class);
        user.setId(id);
        return SchoolUserMapper.fromSchoolUser(schoolUserService.updateSchoolUser(user));
    }

    public void deleteSchoolUser(int id) throws Exception {
        schoolUserService.deleteSchoolUser(id);
    }

    public List<CourseDTO> getAllCourses(int id) throws Exception {
        return CourseMapper.fromCourseList(schoolUserService.getAllCourses(id));
    }

    public Long getUserId(String userName){
        return schoolUserService.getUserId(userName);
    }

    public void updateUserAge(Integer age , long id){
        schoolUserService.updateUserAge(age, id);
    }

    public void updateUserGender(String gender , long id){
        schoolUserService.updateUserGender(gender, id);
    }

    public void updateUserMobileNumber(String mobileNumber , long id){
        schoolUserService.updateUserMobileNumber(mobileNumber, id);
    }
}
