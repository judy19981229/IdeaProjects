package workbench.dao;

import workbench.entity.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int deleteByIds(String[] ids);

    int getCountByIds(String[] ids);

    List<ActivityRemark> getRemarkListById(String activityId);

    int deleteRemark(String id);

    int saveRemark(ActivityRemark activityRemark);

    int editRemark(ActivityRemark activityRemark);
}
