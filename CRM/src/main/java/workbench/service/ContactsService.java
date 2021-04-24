package workbench.service;

import workbench.entity.Activity;
import workbench.entity.Contacts;

import java.util.List;

public interface ContactsService {

    List<Contacts> getContacts(String contactsName);
}
