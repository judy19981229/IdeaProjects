package workbench.service;

import exception.SaveActivityException;
import org.springframework.stereotype.Service;
import workbench.dao.ActivityDao;
import workbench.entity.Activity;

import javax.annotation.Resource;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

    @Resource(name="activityDao")
    ActivityDao activityDao;

    @Override
    public boolean saveActivity(Activity activity) throws SaveActivityException {
        int result=activityDao.saveActivity(activity);
        if(result==1){
            return true;
        }
        throw new SaveActivityException("添加市场活动失败，请重试");
    }
}
