package com.example.schoolmanagerrestresourceserver.repositories;

import com.example.schoolmanagerrestresourceserver.models.entities.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    @Transactional
    @Modifying
    @Query("update Connection co set co.isDeleted = true where co.id = :id")
    void markDeleted(long id);

    @Transactional
    @Modifying
    @Query("update Connection co set co.isDeleted = true where co.userId = :userId")
    void markDeletedByUserID(long userId);

    @Transactional
    @Modifying
    @Query("update Connection co set co.isDeleted = true where co.courseId = :courseId")
    void markDeletedByCourseID(long courseId);

    @Query("select co from Connection co where co.isDeleted = false")
    List<Connection> findAllMarked();
}
