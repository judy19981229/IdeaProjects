package workbench.service;

import com.github.pagehelper.PageHelper;
import exception.ActivityException;
import org.springframework.stereotype.Service;
import vo.PageVo;
import workbench.dao.ActivityDao;
import workbench.entity.Activity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

    @Resource(name="activityDao")
    ActivityDao activityDao;

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


}
