package org.example.woc.repository;
import org.example.woc.entity.Competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    @Query("SELECT c FROM Competition c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Competition> findByNameContainingIgnoreCase(@Param("name") String name);

    boolean existsByName(String name);

}

