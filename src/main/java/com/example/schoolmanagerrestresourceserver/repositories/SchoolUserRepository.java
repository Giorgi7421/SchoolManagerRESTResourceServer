package com.example.schoolmanagerrestresourceserver.repositories;

import com.example.schoolmanagerrestresourceserver.models.entities.Course;
import com.example.schoolmanagerrestresourceserver.models.entities.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {

    @Query("select sch from SchoolUser sch where sch.userName = :userName")
    SchoolUser findSchoolUserByUserName(String userName);

    @Query("select usr from Course cour join Connection con on cour.id = :id and cour.id = con.courseId join SchoolUser usr on con.userId = usr.id")
    List<SchoolUser> findUsersOfTheCourse(long id);

    @Transactional
    @Modifying
    @Query("update SchoolUser su set su.isDeleted = true where su.id = :id")
    void markDeleted(long id);

    @Query("select s from SchoolUser s where s.isDeleted = false")
    List<SchoolUser> findAllMarked();

    @Query("select su from SchoolUser su where su.isDeleted = false and su.id = :id")
    Optional<SchoolUser> findByIdMarked(long id);

    @Query("select su.id from SchoolUser su where su.userName = :userName")
    Optional<Long> findIdOfUserName(String userName);

    @Query("update SchoolUser su set su.age = :age where su.id = :id")
    void updateUserAge(Integer age , long id);

    @Query("update SchoolUser su set su.gender = :gender where su.id = :id")
    void updateUserGender(String gender , long id);

    @Query("update SchoolUser su set su.mobileNumber = :mobileNumber where su.id = :id")
    void updateUserMobileNumber(String mobileNumber , long id);
}
