package org.example.woc.service.impl;

import org.example.woc.entity.Competition;
import org.example.woc.service.CompetitionService;
import org.example.woc.repository.CompetitionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import org.springframework.beans.BeanUtils;

import jakarta.validation.Valid;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    // 通过构造函数注入 Repository
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    // 创建竞赛
    @Override
    @Transactional
    public Competition createCompetition(@Valid Competition competition) {
        // 检查竞赛名称是否已经存在（如果需要）
        if (competitionRepository.existsByName(competition.getName())) {
            throw new RuntimeException("Competition with the same name already exists: " + competition.getName());
        }
        return competitionRepository.save(competition);
    }

    // 获取所有竞赛
    @Override
    @Transactional(readOnly = true)
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    // 根据 ID 获取竞赛
    @Override
    @Transactional(readOnly = true)
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
    }

    // 更新竞赛
    @Override
    @Transactional
    public Competition updateCompetition(Long id, Competition competition) {
        Competition existingCompetition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
        // 复制属性，排除 id 字段
        BeanUtils.copyProperties(competition, existingCompetition, "id");
        return competitionRepository.save(existingCompetition);
    }

    // 删除竞赛
    @Override
    @Transactional
    public void deleteCompetition(Long id) {
        if (!competitionRepository.existsById(id)) {
            throw new RuntimeException("Competition not found with id: " + id);
        }
        competitionRepository.deleteById(id);
    }

    // 根据名称模糊查询竞赛
    @Override
    @Transactional(readOnly = true)
    public List<Competition> searchCompetitionsByName(String name) {
        return competitionRepository.findByNameContainingIgnoreCase(name);
    }
}
