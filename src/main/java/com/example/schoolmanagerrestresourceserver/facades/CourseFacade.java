package com.example.schoolmanagerrestresourceserver.facades;

import com.example.schoolmanagerrestresourceserver.models.dtos.CourseDTO;
import com.example.schoolmanagerrestresourceserver.models.dtos.SchoolUserDTO;
import com.example.schoolmanagerrestresourceserver.models.entities.Course;
import com.example.schoolmanagerrestresourceserver.models.mappers.CourseMapper;
import com.example.schoolmanagerrestresourceserver.models.mappers.SchoolUserMapper;
import com.example.schoolmanagerrestresourceserver.models.params.AddCourseParam;
import com.example.schoolmanagerrestresourceserver.models.params.UpdateCourseParam;
import com.example.schoolmanagerrestresourceserver.services.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional( propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class CourseFacade {

    @Autowired
    private CourseService courseService;

    public List<CourseDTO> getAllCourses(){
        return CourseMapper.fromCourseList(courseService.getAllCourses());
    }

    public CourseDTO getCourseByID(int id) throws Exception {
        return CourseMapper.fromCourse(courseService.getCourseByID(id));
    }

    public CourseDTO createCourse(AddCourseParam addCourse) {
        ObjectMapper objectMapper = new ObjectMapper();

        Course course = new Course();
        course.setName(addCourse.getName());
        course.setDescription(addCourse.getDescription());
        course.setDeleted(false);
        course.setStartDate(addCourse.getStartDate());
        course.setEndDate(addCourse.getEndDate());

        return CourseMapper.fromCourse(courseService.createCourse(course));
    }

    public CourseDTO updateCourse(int id , UpdateCourseParam newCourse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Course course = objectMapper.convertValue(newCourse, Course.class);
        course.setId(id);
        course.setDeleted(false);
        return CourseMapper.fromCourse(courseService.updateCourse(course));
    }

    public void deleteCourse(int id) throws Exception {
        courseService.deleteCourse(id);
    }

    public List<SchoolUserDTO> getAllUsers(int courseId) throws Exception {
        return SchoolUserMapper.fromSchoolUsersList(courseService.getAllUsers(courseId));
    }

    public List<CourseDTO> getCoursesOnInterval(Date startDate , Date endDate){
        return CourseMapper.fromCourseList(courseService.getCoursesOnInterval(startDate , endDate));
    }

}
