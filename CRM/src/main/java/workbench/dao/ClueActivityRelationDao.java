package workbench.dao;


import workbench.entity.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {

    List<ClueActivityRelation> getByClueId(String clueId);

    int deleteClue(String id);

    int bund(ClueActivityRelation clueActivityRelation);

}
