package workbench.dao;

import workbench.entity.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int saveActivity(Activity activity);
    List<Activity> getActivityListByCondition(Map<String,Object> map);
    int getTotalByCondition(Map<String,Object> map);
}
