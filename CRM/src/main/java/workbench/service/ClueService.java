package workbench.service;

import workbench.entity.Clue;
import workbench.entity.Tran;

public interface ClueService {
    boolean save(Clue clue);

    Clue detail(String id);

    boolean deleteClue(String id);

    boolean bund(String clueId, String[] activityIds);

    boolean convert(String clueId, Tran t, String createBy);
}
