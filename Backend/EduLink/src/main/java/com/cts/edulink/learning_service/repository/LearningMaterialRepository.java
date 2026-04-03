package com.cts.edulink.learning_service.repository;


import jakarta.transaction.Transactional;
import com.cts.edulink.learning_service.entity.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE LearningMaterial l SET l.viewCount = l.viewCount + 1 WHERE l.materialId = :id")
    void incrementViewCount(@Param("id") Long id);

    List<LearningMaterial> findByCourseId(Long courseId);
}