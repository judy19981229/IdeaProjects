package workbench.service;

import org.springframework.stereotype.Service;
import workbench.dao.ContactsDao;
import workbench.entity.Contacts;

import javax.annotation.Resource;
import java.util.List;

@Service("contactsService")
public class ContactsServiceImpl implements ContactsService{
    @Resource(name="contactsDao")
    ContactsDao contactsDao;

    @Override
    public List<Contacts> getContacts(String contactsName) {
        List<Contacts> cList=contactsDao.getContacts(contactsName);
        return cList;
    }
}
