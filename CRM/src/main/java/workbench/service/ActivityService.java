package workbench.service;

import exception.ActivityException;
import vo.PageVo;
import workbench.entity.Activity;
import workbench.entity.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    boolean saveActivity(Activity activity) throws ActivityException;
    PageVo<Activity> pageList(Map<String,Object> map);

    boolean delete(String[] ids);

    Activity getActivity(String id);

    boolean updateActivity(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListById(String activityId);

    boolean deleteRemark(String id);

    boolean saveRemark(ActivityRemark activityRemark);

    boolean editRemark(ActivityRemark activityRemark);
}
