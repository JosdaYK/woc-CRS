package org.example.woc.service.impl;

import org.example.woc.repository.MemberRepository;
import org.example.woc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.woc.entity.Member;


import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) {
        // 验证学号是否唯一
        if (memberRepository.existsByStudentId(member.getStudentId())) {
            throw new RuntimeException("学号已存在：" + member.getStudentId());
        }
        // 保存成员
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(Long id, Member member) {
        // 检查成员是否存在
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("成员不存在：" + id));
        // 更新字段
        existingMember.setName(member.getName());
        existingMember.setStudentId(member.getStudentId());
        existingMember.setTeamId(member.getTeamId());
        existingMember.setAcademyId(member.getAcademyId());
        existingMember.setPhone(member.getPhone());
        existingMember.setIsCaptain(member.getIsCaptain());
        // 保存更新
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        // 检查成员是否存在
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("成员不存在：" + id);
        }
        memberRepository.deleteById(id);
    }

    @Override
    public Optional<Member> getMemberByStudentId(String studentId) {
        return memberRepository.findByStudentId(studentId);
    }

    @Override
    public List<Member> getMembersByTeamId(Long teamId) {
        return memberRepository.findByTeamId(teamId);
    }

    @Override
    public List<Member> getMembersByAcademyId(Long academyId) {
        return memberRepository.findByAcademyId(academyId);
    }
}
