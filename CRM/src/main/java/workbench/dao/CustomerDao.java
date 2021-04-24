package workbench.dao;

import workbench.entity.Customer;

public interface CustomerDao {

    Customer getByName(String companyName);

    int save(Customer customer);
}
