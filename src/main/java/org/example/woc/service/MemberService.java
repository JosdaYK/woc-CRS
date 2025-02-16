package org.example.woc.service;
import org.example.woc.entity.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    // 创建成员
    Member createMember(Member member);

    // 根据ID获取成员
    Optional<Member> getMemberById(Long id);

    // 获取所有成员
    List<Member> getAllMembers();

    // 更新成员信息
    Member updateMember(Long id, Member member);

    // 删除成员
    void deleteMember(Long id);

    // 根据学号查询成员
    Optional<Member> getMemberByStudentId(String studentId);

    // 根据团队ID查询成员
    List<Member> getMembersByTeamId(Long teamId);

    // 根据学院ID查询成员
    List<Member> getMembersByAcademyId(Long academyId);
}
