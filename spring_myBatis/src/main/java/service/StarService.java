package service;

import entity.Star;
import vo.StarAndClass;

import java.util.List;
import java.util.Map;

public interface StarService {
    public List<Star> selectStar();
    public List<Map<String,Object>> selectClass();
    public List<StarAndClass> selectStarAndClass(String name);
}
