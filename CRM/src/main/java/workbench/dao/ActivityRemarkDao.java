package workbench.dao;

public interface ActivityRemarkDao {
    int deleteByIds(String[] ids);

    int getCountByIds(String[] ids);
}
