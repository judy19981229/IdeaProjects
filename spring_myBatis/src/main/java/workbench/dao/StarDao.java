package workbench.dao;

import workbench.entity.Star;
import vo.StarAndClass;

import java.util.List;
import java.util.Map;

public interface StarDao {
    public List<Star> selectStar();
    public List<Map<String,Object>> selectClass();
    public List<StarAndClass> selectStarAndClass(String name);
}
