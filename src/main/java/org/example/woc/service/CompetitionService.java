package org.example.woc.service;
import org.example.woc.entity.Competition;
import java.util.List;

public interface CompetitionService {
    // 创建竞赛
    Competition createCompetition(Competition competition);

    // 获取所有竞赛
    List<Competition> getAllCompetitions();

    // 根据 ID 获取竞赛
    Competition getCompetitionById(Long id);

    // 更新竞赛
    Competition updateCompetition(Long id, Competition competition);

    // 删除竞赛
    void deleteCompetition(Long id);

    // 根据名称模糊查询竞赛
    List<Competition> searchCompetitionsByName(String name);
}
