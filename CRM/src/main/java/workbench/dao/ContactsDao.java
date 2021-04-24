package workbench.dao;

import workbench.entity.Contacts;

import java.util.List;

public interface ContactsDao {

    int save(Contacts contacts);

    List<Contacts> getContacts(String contactsName);
}
