package service;

import dao.StarDao;
import entity.Star;
import org.springframework.stereotype.Component;
import vo.StarAndClass;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class StarServiceImpl implements StarService{
    @Resource
    StarDao starDao;

    @Override
    public List<Star> selectStar() {
        return starDao.selectStar();
    }

    @Override
    public List<Map<String, Object>> selectClass() {
        return starDao.selectClass();
    }

    @Override
    public List<StarAndClass> selectStarAndClass(String name) {
        return starDao.selectStarAndClass(name);
    }
}
