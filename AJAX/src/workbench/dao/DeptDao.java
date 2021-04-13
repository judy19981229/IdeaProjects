package workbench.dao;

import util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDao {
    JdbcUtil util=new JdbcUtil();

    public int check(String deptName){
        String sql="select count(*) from dept where dname=?";
        int result=0;
        ResultSet rs=null;
        PreparedStatement ps= util.createStatement(sql);
        try {
            ps.setString(1,deptName);
            rs= ps.executeQuery();
            while(rs.next()){
                result=rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            util.close(rs);
        }
        return result;
    }

    public int delete(String array[]){
        int result=0;
        StringBuffer sql=new StringBuffer("delete from dept where deptno in(");
        for(int i=0;i< array.length;i++){
            String data=array[i];
            if(i>0){
                sql.append(",");
            }
            sql.append(data);
        }
        sql.append(")");
        JdbcUtil util=new JdbcUtil();
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
}
