package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class myBatisUtil {
    private static SqlSessionFactory factory=null;
    static{
        String config="myBatis.xml";
            try {
                InputStream in = Resources.getResourceAsStream(config);
                SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
                factory= builder.build(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    //1.获取sqlSession的方法
    public static SqlSession getSqlSession(){
        SqlSession sqlSession=null;
        if(factory!=null){
             sqlSession= factory.openSession(); //非自动提交事物
        }
        return sqlSession;
    }
}
