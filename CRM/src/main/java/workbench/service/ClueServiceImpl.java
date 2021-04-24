package workbench.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.DateTimeUtil;
import utils.UUIDUtil;
import workbench.dao.*;
import workbench.entity.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("clueService")
public class ClueServiceImpl implements ClueService{
    //线索相关表
    @Resource(name="clueDao")
    private ClueDao clueDao;
    @Resource(name="clueRemarkDao")
    private ClueRemarkDao clueRemarkDao;
    @Resource(name="clueActivityRelationDao")
    private ClueActivityRelationDao clueActivityRelationDao;
    //客户相关表
    @Resource(name="customerDao")
    private CustomerDao customerDao;
    @Resource(name="customerRemarkDao")
    private CustomerRemarkDao customerRemarkDao;
    //联系人相关表
    @Resource(name="contactsDao")
    private ContactsDao contactsDao;
    @Resource(name="contactsRemarkDao")
    private ContactsRemarkDao contactsRemarkDao;
    @Resource(name="contactsActivityRelationDao")
    private ContactsActivityRelationDao contactsActivityRelationDao;
    //交易相关表
    @Resource(name="tranDao")
    private TranDao tranDao;
    @Resource(name="tranHistoryDao")
    private TranHistoryDao tranHistoryDao;

    @Override
    public boolean save(Clue clue) {
        int result=clueDao.save(clue);
        if(result!=1){
            return false;
        }
        return true;
    }

    @Override
    public Clue detail(String id) {
        return clueDao.detail(id);
    }

    @Override
    public boolean deleteClue(String id) {
        int result=clueActivityRelationDao.deleteClue(id);
        if(result!=1){
            return false;
        }
        return true;
    }

    @Override
    public boolean bund(String clueId, String[] activityIds) {
        for(String activityId:activityIds){
            ClueActivityRelation clueActivityRelation=new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setActivityId(activityId);
            int result=clueActivityRelationDao.bund(clueActivityRelation);
            if(result!=1){
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean convert(String clueId, Tran t, String createBy) {
        boolean flag=true;
        String createTime= DateTimeUtil.getSysTime();
        //1）通过线索id获取线索对象（封装了线索的信息）
        Clue clue=clueDao.getById(clueId);

        //2）通过线索对象提取客户信息，当该客户不存在，新建客户(查询公司名称)
        String companyName=clue.getCompany();
        Customer customer=customerDao.getByName(companyName);
        //如果customer为空，说明客户不存在，需要新建
        if(customer==null){
            customer=new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(companyName);
            customer.setAddress(clue.getAddress());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setDescription(clue.getDescription());
            customer.setContactSummary(clue.getContactSummary());
            customer.setOwner(clue.getOwner());
            customer.setCreateBy(createBy);
            customer.setCreateTime(createTime);
            int count1=customerDao.save(customer);
            if(count1!=1){
                flag=false;
            }
        }//在2）中已经获得了客户对象，可以在后面直接获取客户对象的信息(id)

        //3）通过线索对象提取联系人信息，保存联系人
        Contacts contacts=new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setSource(clue.getSource());
        contacts.setOwner(clue.getOwner());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setFullname(clue.getFullname());
        contacts.setEmail(clue.getEmail());
        contacts.setDescription(clue.getDescription());
        contacts.setCustomerId(customer.getId());
        contacts.setCreateBy(createBy);
        contacts.setCreateTime(createTime);
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setAppellation(clue.getAppellation());
        contacts.setAddress(clue.getAddress());
        int count2=contactsDao.save(contacts);
        if(count2!=1){
            flag=false;
        }//在3）之后已经获得了联系人对象，可以在后面直接使用联系人对象的信息(id)

        //4)线索备注转换到客户备注和联系人备注
        //查询出与该线索关联的备注信息列表
        List<ClueRemark> clueRemarkList=clueRemarkDao.getByClueId(clueId);
        for(ClueRemark clueRemark:clueRemarkList){
            //取出备注信息，主要转换到客户备注和联系人备注的就是这个备注信息
            String noteContent=clueRemark.getNoteContent();
            //创建客户备注对象，添加客户备注
            CustomerRemark customerRemark=new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateBy(createBy);
            customerRemark.setCreateTime(createTime);
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setEditFlag("0");
            customerRemark.setNoteContent(noteContent);
            int count3=customerRemarkDao.save(customerRemark);
            if(count3!=1){
                flag=false;
            }
            //创建联系人备注对象，添加联系人备注
            ContactsRemark contactsRemark=new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setCreateBy(createBy);
            contactsRemark.setCreateTime(createTime);
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setEditFlag("0");
            contactsRemark.setNoteContent(noteContent);
            int count4=contactsRemarkDao.save(contactsRemark);
            if(count4!=1){
                flag=false;
            }
        }
        //5)线索和市场活动的关系转换到联系人和市场活动的关系
        //查询出与该条线索关联的市场活动，查询与市场活动的关联关系列表
        List<ClueActivityRelation> carList=new ArrayList<>();
        carList=clueActivityRelationDao.getByClueId(clueId);
        //遍历出每一条与市场活动关联的关联关系记录
        for(ClueActivityRelation clueAR:carList){
            //从遍历出来的记录中取出关联的市场活动的id
            String activityId=clueAR.getActivityId();
            //创建联系人与市场活动关联对象，让第三步生成的联系人与市场活动关联
            ContactsActivityRelation contactsAR=new ContactsActivityRelation();
            contactsAR.setId(UUIDUtil.getUUID());
            contactsAR.setActivityId(activityId);
            contactsAR.setContactsId(contacts.getId());
            int count5=contactsActivityRelationDao.save(contactsAR);
            if(count5!=1){
                flag=false;
            }
        }
        //6)如果有创建交易需要，创建一条交易
        if(t!=null){//t对象封装好的信息：
            //id,money,name,expectedDate,stage,activityId,createTime,createBy
            //接下来可以通过第一步生成的clue对象，填充t对象
            t.setSource(clue.getSource());
            t.setOwner(clue.getOwner());
            t.setNextContactTime(clue.getNextContactTime());
            t.setDescription(clue.getDescription());
            t.setCustomerId(customer.getId());
            t.setContactSummary(clue.getContactSummary());
            t.setContactsId(contacts.getId());
            int count6=tranDao.save(t);
            if(count6!=1){
                flag=false;
            }
            //7）如果创建了交易，创建该交易下的交易历史（一对多，一个交易有多个交易历史）
            TranHistory tranHistory=new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateBy(createBy);
            tranHistory.setCreateTime(createTime);
            tranHistory.setTranId(t.getId());
            tranHistory.setExpectedDate(t.getExpectedDate());
            tranHistory.setMoney(t.getMoney());
            tranHistory.setStage(t.getStage());
            int count7=tranHistoryDao.save(tranHistory);
            if(count7!=1){
                flag=false;
            }
        }
        //8)删除线索备注
        /*for(ClueRemark clueRemark:clueRemarkList){
            int count8=clueRemarkDao.deleteById(clueRemark);
            if(count8!=1){
                flag=false;
            }
        }*/
        //9)删除线索和市场活动的关系
        /*for(ClueActivityRelation clueAR:carList){
            int count9=clueActivityRelationDao.deleteClue(clueAR.getId());
            if(count9!=1){
                flag=false;
            }
        }*/
        //10)删除线索
        /*int count10=clueDao.delete(clueId);
        if(count10!=1){
            flag=false;
        }*/

        return flag;

    }

}
