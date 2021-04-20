package workbench.service;

import org.springframework.stereotype.Service;
import workbench.dao.ClueDao;

import javax.annotation.Resource;

@Service("clueService")
public class ClueServiceImpl implements ClueService{

    @Resource(name="clueDao")
    private ClueDao clueDao;

}
