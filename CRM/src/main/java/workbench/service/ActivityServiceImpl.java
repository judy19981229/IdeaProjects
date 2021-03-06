package workbench.service;

import com.github.pagehelper.PageHelper;
import exception.ActivityException;
import org.springframework.stereotype.Service;
import vo.PageVo;
import workbench.dao.ActivityDao;
import workbench.dao.ActivityRemarkDao;
import workbench.entity.Activity;
import workbench.entity.ActivityRemark;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

    @Resource(name="activityDao")
    ActivityDao activityDao;

    @Resource(name="activityRemarkDao")
    ActivityRemarkDao activityRemarkDao;

    @Override
    public boolean saveActivity(Activity activity) throws ActivityException {
        int result=activityDao.saveActivity(activity);
        if(result==1){
            return true;
        }
        throw new ActivityException("添加市场活动失败，请重试");
    }

    @Override
    public PageVo<Activity> pageList(Map<String, Object> map) {
        int pageNo= (int) map.get("pageNo");
        int pageSize= (int) map.get("pageSize");
        //这里使用的是pageHelper插件进行分页操作
        PageHelper.startPage(pageNo,pageSize);
        List<Activity> list=activityDao.getActivityListByCondition(map);
        int total=activityDao.getTotalByCondition(map);
        PageVo<Activity> pageVo= new PageVo<>();
        pageVo.setTotal(total);
        pageVo.setDataList(list);
        return pageVo;
    }

    @Override
    public boolean delete(String[] ids) {
        boolean flag=true;

        //查询出需要删除的备注的条数
        int needDeleteNum=activityRemarkDao.getCountByIds(ids);
        //查询出实际删除的备注的条数
        int deleteNum =activityRemarkDao.deleteByIds(ids);
        if(needDeleteNum==deleteNum){
            activityDao.deleteByIds(ids);
        } else{
            flag=false;
        }
        return flag;
    }

    @Override
    public Activity getActivity(String id) {
        Activity activity=activityDao.getActivity(id);
        return activity;
    }

    @Override
    public boolean updateActivity(Activity activity) {
        int result=activityDao.updateActivity(activity);
        if(result==1){
            return true;
        }
        return false;
    }

    @Override
    public Activity detail(String id) {
        Activity activity=activityDao.detail(id);
        return activity;
    }

    @Override
    public List<ActivityRemark> getRemarkListById(String activityId) {
        List<ActivityRemark> list=activityRemarkDao.getRemarkListById(activityId);
        return list;
    }

    @Override
    public boolean saveRemark(ActivityRemark activityRemark) {
        int result=activityRemarkDao.saveRemark(activityRemark);
        if(result!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRemark(String id) {
        int result=activityRemarkDao.deleteRemark(id);
        if(result!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean editRemark(ActivityRemark activityRemark) {
        int result=activityRemarkDao.editRemark(activityRemark);
        if(result!=1){
            return false;
        }
        return true;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {
        List<Activity> list=new ArrayList<>();
        list=activityDao.getActivityListByClueId(clueId);
        return list;
    }

    @Override
    public List<Activity> getActivityListByName(String name, String clueId) {
        List<Activity> list=activityDao.getActivityListByName(name,clueId);
        return list;
    }

    @Override
    public List<Activity> getActivityByName(String activityName) {
        List<Activity> list=new ArrayList();
        list=activityDao.getActivityByName(activityName);
        return list;
    }
}
