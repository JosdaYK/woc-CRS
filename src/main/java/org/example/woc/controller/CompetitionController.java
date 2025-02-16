package org.example.woc.controller;

import org.example.woc.entity.Competition;
import org.example.woc.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions") // 定义根路径
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired // 注入 Service
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    // 创建比赛
    @PostMapping
    public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) {
        Competition savedCompetition = competitionService.createCompetition(competition);
        return ResponseEntity.ok(savedCompetition);
    }

    // 获取所有比赛
    @GetMapping
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        return ResponseEntity.ok(competitions);
    }

    // 根据 ID 获取比赛
    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        return ResponseEntity.ok(competition);
    }

    // 更新比赛
    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(
            @PathVariable Long id, @RequestBody Competition competition) {
        Competition updatedCompetition = competitionService.updateCompetition(id, competition);
        return ResponseEntity.ok(updatedCompetition);
    }

    // 删除比赛
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }

    // 根据名称搜索比赛
    @GetMapping("/search")
    public ResponseEntity<List<Competition>> searchCompetitionsByName(
            @RequestParam String name) {
        List<Competition> competitions = competitionService.searchCompetitionsByName(name);
        return ResponseEntity.ok(competitions);
    }
}