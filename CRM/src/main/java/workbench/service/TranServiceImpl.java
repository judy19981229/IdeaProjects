package workbench.service;

import workbench.dao.TranDao;
import workbench.dao.TranHistoryDao;

import javax.annotation.Resource;

public class TranServiceImpl implements TranService{

    @Resource(name="tranDao")
    private TranDao tranDao;

    @Resource(name="tranHistoryDao")
    private TranHistoryDao tranHistoryDao;
    
}
