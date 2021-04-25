package workbench.service;

import org.springframework.stereotype.Service;
import workbench.dao.CustomerDao;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Resource(name="customerDao")
    CustomerDao customerDao;

    @Override
    public List<String> getCustomerName(String name) {
        List<String> nameList=new ArrayList<>();
        nameList=customerDao.getCustomerName(name);
        return nameList;
    }
}
