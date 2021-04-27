package workbench.service;

import workbench.entity.Tran;
import workbench.entity.TranHistory;

import java.util.List;
import java.util.Map;

public interface TranService {
    boolean save(Tran t, String customerName);

    Tran getById(String id);

    List<TranHistory> getHistoryList(String tranId);

    boolean changeStage(Tran tran);

    Map<String, Object> getCharts();
}
