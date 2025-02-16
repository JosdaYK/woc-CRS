package org.example.woc.controller;

import org.example.woc.entity.Member;
import org.example.woc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 创建成员
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        return ResponseEntity.ok(createdMember);
    }

    // 根据ID获取成员
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 获取所有成员
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // 更新成员信息
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member updatedMember = memberService.updateMember(id, member);
        return ResponseEntity.ok(updatedMember);
    }

    // 删除成员
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    // 根据学号查询成员
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Member> getMemberByStudentId(@PathVariable String studentId) {
        Optional<Member> member = memberService.getMemberByStudentId(studentId);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 根据团队ID查询成员
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Member>> getMembersByTeamId(@PathVariable Long teamId) {
        List<Member> members = memberService.getMembersByTeamId(teamId);
        return ResponseEntity.ok(members);
    }

    // 根据学院ID查询成员
    @GetMapping("/academy/{academyId}")
    public ResponseEntity<List<Member>> getMembersByAcademyId(@PathVariable Long academyId) {
        List<Member> members = memberService.getMembersByAcademyId(academyId);
        return ResponseEntity.ok(members);
    }
}
