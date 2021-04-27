package workbench.dao;

import workbench.entity.Tran;

import java.util.List;
import java.util.Map;

public interface TranDao {

    int save(Tran t);

    Tran getById(String id);

    int changeStage(Tran tran);

    int getTotal();

    List<Map<String, Object>> getCharts();
}
