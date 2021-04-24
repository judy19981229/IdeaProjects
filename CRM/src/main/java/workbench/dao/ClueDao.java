package workbench.dao;


import workbench.entity.Activity;
import workbench.entity.Clue;
import workbench.entity.ClueActivityRelation;

import java.util.List;

public interface ClueDao {


    int save(Clue clue);

    Clue detail(String id);

    Clue getById(String clueId);

    int delete(String clueId);
}
