package workbench.service;

import org.springframework.stereotype.Service;
import utils.DateTimeUtil;
import utils.UUIDUtil;
import workbench.dao.CustomerDao;
import workbench.dao.TranDao;
import workbench.dao.TranHistoryDao;
import workbench.entity.Customer;
import workbench.entity.Tran;
import workbench.entity.TranHistory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tranService")
public class TranServiceImpl implements TranService{

    @Resource(name="tranDao")
    private TranDao tranDao;

    @Resource(name="tranHistoryDao")
    private TranHistoryDao tranHistoryDao;

    @Resource(name="customerDao")
    private CustomerDao customerDao;

    @Override
    public boolean save(Tran t, String customerName) {
        //t里面少了一个customerId,根据customerName查出customer
        //如果不存在该customer，新建一个，把id存入t，如果存在，直接把id存入t
        //创建交易，交易历史
        boolean flag=true;
        Customer customer=customerDao.getByName(customerName);
        if(customer==null){
            customer=new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(customerName);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setCreateBy(t.getCreateBy());
            customer.setContactSummary(t.getContactSummary());
            customer.setNextContactTime(t.getNextContactTime());
            customer.setOwner(t.getOwner());
            int count1=customerDao.save(customer);
            if(count1!=1){
                flag=false;
            }
        }
        String customerId=customer.getId();
        t.setCustomerId(customerId);
        int count2=tranDao.save(t);
        if(count2!=1){
            flag=false;
        }
        TranHistory tranHistory=new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setMoney(t.getMoney());
        tranHistory.setStage(t.getStage());
        tranHistory.setTranId(t.getId());
        tranHistory.setExpectedDate(t.getExpectedDate());
        tranHistory.setCreateBy(t.getCreateBy());
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        int count3=tranHistoryDao.save(tranHistory);
        if(count3!=1){
            flag=false;
        }
        return flag;
    }

    @Override
    public Tran getById(String id) {
        //所有者，客户名称，市场活动源，联系人名称 要连四张表
        Tran tran=tranDao.getById(id);
        return tran;
    }

    @Override
    public List<TranHistory> getHistoryList(String tranId) {
        List<TranHistory> list=new ArrayList<>();
        list=tranHistoryDao.getHistoryList(tranId);
        return list;
    }

    @Override
    public boolean changeStage(Tran tran) {
        boolean flag=true;
        //改变交易阶段
        int count1=tranDao.changeStage(tran);
        if(count1!=1){
            flag=false;
        }
        //生成交易历史
        TranHistory tranHistory=new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        //这里要注意，交易历史的创建人是交易的修改人
        tranHistory.setCreateBy(tran.getEditBy());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setTranId(tran.getId());
        int count2= tranHistoryDao.save(tranHistory);
        if(count2!=1){
            flag=false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getCharts() {
        //取得total
        int total=tranDao.getTotal();
        //取得dataList(name:value)
        List<Map<String,Object>> dataList=tranDao.getCharts();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("dataList",dataList);
        return map;
    }
}
