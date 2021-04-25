package workbench.dao;

import workbench.entity.TranHistory;

import java.util.List;

public interface TranHistoryDao {

    int save(TranHistory tranHistory);

    List<TranHistory> getHistoryList(String tranId);
}
