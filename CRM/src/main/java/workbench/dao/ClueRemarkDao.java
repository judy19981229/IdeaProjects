package workbench.dao;

import workbench.entity.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> getByClueId(String clueId);

    int deleteById(ClueRemark clueRemark);
}
