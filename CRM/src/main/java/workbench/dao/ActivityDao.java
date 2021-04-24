package workbench.dao;

import org.apache.ibatis.annotations.Param;
import workbench.entity.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int saveActivity(Activity activity);
    List<Activity> getActivityListByCondition(Map<String,Object> map);
    int getTotalByCondition(Map<String,Object> map);
    int deleteByIds(String[] ids);

    Activity getActivity(String id);

    int updateActivity(Activity activity);

    Activity detail(String id);

    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> getActivityListByName(@Param("name") String name,
                                         @Param("clueId") String clueId);

    List<Activity> getActivityByName(String activityName);
}
