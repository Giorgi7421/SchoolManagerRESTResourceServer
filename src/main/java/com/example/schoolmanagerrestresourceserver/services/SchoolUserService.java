package com.example.schoolmanagerrestresourceserver.services;

import com.example.schoolmanagerrestresourceserver.models.entities.Course;
import com.example.schoolmanagerrestresourceserver.models.entities.SchoolUser;
import com.example.schoolmanagerrestresourceserver.repositories.ConnectionRepository;
import com.example.schoolmanagerrestresourceserver.repositories.CourseRepository;
import com.example.schoolmanagerrestresourceserver.repositories.SchoolUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolUserService {

    @Autowired
    private SchoolUserRepository schoolUserRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<SchoolUser> getAllSchoolUsers() {
        return schoolUserRepository.findAllMarked();
    }

    public SchoolUser getSchoolUserByID(long id) throws Exception {
        Optional<SchoolUser> optionalSchoolUser = schoolUserRepository.findByIdMarked(id);
        if (optionalSchoolUser.isPresent()){
            return optionalSchoolUser.get();
        }else{
            throw new Exception("SchoolUser not found");
        }
    }

    public SchoolUser createSchoolUser(SchoolUser schoolUser){
        return schoolUserRepository.save(schoolUser);
    }

    public SchoolUser updateSchoolUser(SchoolUser schoolUser){
        return schoolUserRepository.save(schoolUser);
    }

    public void deleteSchoolUser(int id){
        schoolUserRepository.markDeleted(id);
        connectionRepository.markDeletedByUserID(id);
    }

    public List<Course> getAllCourses(long schoolUserId) throws Exception {
        Optional<SchoolUser> optionalSchoolUser = schoolUserRepository.findByIdMarked(schoolUserId);
        if (optionalSchoolUser.isPresent()){
            return courseRepository.findEnrolledCourses(optionalSchoolUser.get().getId());
        }else{
            throw new Exception("SchoolUser not found");
        }
    }

    public Long getUserId(String userName){
        Optional<Long> optionalId = schoolUserRepository.findIdOfUserName(userName);
        if(optionalId.isPresent()){
            return optionalId.get();
        }else{
            return null;
        }
    }

    public void updateUserAge(Integer age , long id){
        schoolUserRepository.updateUserAge(age, id);
    }

    public void updateUserGender(String gender , long id){
        schoolUserRepository.updateUserGender(gender, id);
    }

    public void updateUserMobileNumber(String mobileNumber , long id){
        schoolUserRepository.updateUserMobileNumber(mobileNumber, id);
    }
}
