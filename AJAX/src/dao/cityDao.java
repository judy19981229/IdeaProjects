package dao;

import entity.city;
import util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cityDao {
    public List find(String provinceId){
        List list=new ArrayList();
        JdbcUtil util=new JdbcUtil();
        ResultSet rs=null;
        String sql="select * from city where provinceId=?";
        PreparedStatement ps=util.createStatement(sql);
        try {
            ps.setString(1,provinceId);
            rs=ps.executeQuery();
            while(rs.next()){
                Integer cityId=rs.getInt("cityId");
                String cityName=rs.getString("cityName");
                city ct=new city(cityId,cityName,provinceId);
                list.add(ct);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            util.close(rs);
        }
        return list;
    }
}
