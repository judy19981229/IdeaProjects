package settings.service;

import org.springframework.stereotype.Service;
import settings.dao.DicTypeDao;
import settings.dao.DicValueDao;
import settings.entity.DicType;
import settings.entity.DicValue;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("dicService")
public class DicServiceImpl implements DicService{

    @Resource(name="dicTypeDao")
    private DicTypeDao dicTypeDao;

    @Resource(name="dicValueDao")
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getAll() {
        Map<String, List<DicValue>> map =new HashMap<>();
        //取出字典类型列表
        List<DicType> list=dicTypeDao.getDicType();
        for(DicType dicType:list){
            //获取每一种类型的字典类型编码
            String typeCode= dicType.getCode();
            //根据每一个字典类型来取得字典值列表
            List<DicValue> dvList=dicValueDao.getDicValue(typeCode);
            map.put(typeCode,dvList);
        }
        return map;
    }
}
