package org.example.woc.repository;
import jakarta.validation.constraints.NotBlank;
import org.example.woc.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 根据学号查询成员
    Optional<Member> findByStudentId(String studentId);

    // 根据团队ID查询成员
    List<Member> findByTeamId(Long teamId);

    // 根据学院ID查询成员
    List<Member> findByAcademyId(Long academyId);

    // 检查学号是否已存在
    boolean existsByStudentId(String studentId);
}
