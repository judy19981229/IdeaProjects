package dao;

import entity.Questions;
import entity.users;
import util.JdbcUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcUtil util = new JdbcUtil();

    //用户注册
    //JDBC规范中，Connection创建与销毁最浪费时间
    public int add(users user) {
        String sql = "insert into users(userName,password,sex,email)" +
                "values(?,?,?,?)";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //重载
    public int add(users user, HttpServletRequest request) {
        String sql = "insert into users(userName,password,sex,email)" +
                "values(?,?,?,?)";
        PreparedStatement ps = util.createStatement(sql, request);
        int result = 0;
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(request);
        }
        return result;
    }

    //查询所有用户信息
    public List findAll() {
        String sql = "select * from users";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        List userList = new ArrayList();
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                users us = new users(userId, userName, password, sex, email);
                userList.add(us);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return userList;
    }

    //用户删除
    public int delete(String userId) {
        String sql = "delete from users where userId=?";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setInt(1, Integer.valueOf(userId));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    public int deleteChecked(String[] array) {
        int result = 0;
        StringBuffer sql = new StringBuffer("delete from users where userId in(");
        for (int i = 0; i < array.length; i++) {
            String data=array[i];
            if (i > 0) {
                sql.append(",");
            }
            sql.append(data);
        }
        sql.append(")");
        PreparedStatement ps= util.createStatement(sql.toString());
        try {
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            util.close();
        }
        return result;
    }
    //登录验证
    public int check(String userName){
        int result=0;
        String sql="select count(*) from users where userName=?";
        ResultSet rs=null;
        PreparedStatement ps= util.createStatement(sql);
        try {
            ps.setString(1,userName);
            rs=ps.executeQuery();
            while(rs.next()){
                result=rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }

    public int login(String userName, String password) {
        String sql = "select count(*) from users where userName=? and password=?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        int result = 0;
        try {
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }

    //用户信息更新
    public int update(users user) {
        int result = 0;
        String sql = "update users set userName=?,password=?,sex=?,email=? where userId=?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getUserId());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }
}

