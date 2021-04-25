package workbench.dao;

import workbench.entity.Tran;

public interface TranDao {

    int save(Tran t);

    Tran getById(String id);
}
