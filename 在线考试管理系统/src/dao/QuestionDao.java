package dao;

import entity.Questions;
import util.JdbcUtil;

import javax.sql.rowset.JdbcRowSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionDao {
    private JdbcUtil util=new JdbcUtil();
    public int add(Questions question){
        int result=0;
        String sql="insert into questions(title,optionA,optionB,optionC,optionD,answer) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement ps= util.createStatement(sql);
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            util.close();
        }
        return  result;
    }
    public List find(){
        JdbcUtil util=new JdbcUtil();
        List list=new ArrayList();
        ResultSet rs=null;
        String sql="select * from questions";
        PreparedStatement ps= util.createStatement(sql);
        try {
            rs=ps.executeQuery();
            while(rs.next()){
                Integer questionId=rs.getInt("questionId");
                String title=rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer=rs.getString("answer");
                Questions question=new Questions
                        (questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }
    public int delete(String questionId){
        JdbcUtil util=new JdbcUtil();
        int result=0;
        String sql="delete from questions where questionId=?";
        PreparedStatement ps= util.createStatement(sql);
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            util.close();
        }
        return  result;
    }

    public int update(Questions question){
        JdbcUtil util=new JdbcUtil();
        int result=0;
        String sql="update questions set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=?" +
                "where questionId=?";
        PreparedStatement ps= util.createStatement(sql);
        try {
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getAnswer());
            ps.setInt(7,Integer.valueOf(question.getQuestionId()));
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            util.close();
        }
        return result;
    }
    public List exam(){
        List list= new ArrayList<>();
        JdbcUtil util = new JdbcUtil();
        ResultSet rs=null;
        String sql="select * from questions order by rand() limit 0,4";
        PreparedStatement ps=util.createStatement(sql);
        try {
            rs=ps.executeQuery();
            while(rs.next()){
                Integer questionId=rs.getInt("questionId");
                String title=rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer=rs.getString("answer");
                Questions question=new Questions
                        (questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }
}

