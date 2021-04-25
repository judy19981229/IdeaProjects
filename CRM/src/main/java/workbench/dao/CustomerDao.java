package workbench.dao;

import workbench.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getByName(String companyName);

    int save(Customer customer);

    List<String> getCustomerName(String name);
}
