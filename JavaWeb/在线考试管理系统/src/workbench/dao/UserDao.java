package workbench.dao;

import workbench.entity.users;
import util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcUtil util=new JdbcUtil();
    //用户注册
    public int add(users user){
        String sql="insert into users(userName,password,sex,email)"+
                "values(?,?,?,?)";
        PreparedStatement ps=util.createStatement(sql);
        int result=0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }
    //查询所有用户信息
    public List findAll(){
        String sql="select * from users";
        PreparedStatement ps=util.createStatement(sql);
        ResultSet rs=null;
        List userList=new ArrayList();
        try {
            rs=ps.executeQuery();
            while (rs.next()){
                Integer userId=rs.getInt("userId");
                String userName=rs.getString("userName");
                String password=rs.getString("password");
                String sex=rs.getString("sex");
                String email=rs.getString("email");
                users us=new users(userId,userName,password,sex,email);
                userList.add(us);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return userList;
    }
}
