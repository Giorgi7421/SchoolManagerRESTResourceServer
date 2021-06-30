package com.example.schoolmanagerrestresourceserver.repositories;

import com.example.schoolmanagerrestresourceserver.models.entities.Course;
import com.example.schoolmanagerrestresourceserver.models.entities.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select cou from SchoolUser sch join Connection con on sch.id = con.userId and sch.firstName = :first and sch.lastName = :last join Course cou on con.courseId = cou.id")
    List<Course> findEnrolledCoursesByFirstAndLastName(@Param("first") String firstName , @Param("last") String lastName);

    @Query("select cou from SchoolUser sch join Connection con on sch.id = con.userId and sch.id = :id join Course cou on con.courseId = cou.id")
    List<Course> findEnrolledCourses(@Param("id") long id);

    @Query("select cou from Course cou where cou.startDate >= :start and cou.endDate <= :end")
    List<Course> findCoursesOnInterval(@Param("start") Date start , @Param("end") Date end);

    @Transactional
    @Modifying
    @Query("update Course c set c.isDeleted = true where c.id = :id")
    void markDeleted(long id);

    @Query("select c from Course c where c.isDeleted = false")
    List<Course> findAllMarked();

    @Query("select c from Course c where c.isDeleted = false and c.id = :id")
    Optional<Course> findByIdMarked(long id);

}
