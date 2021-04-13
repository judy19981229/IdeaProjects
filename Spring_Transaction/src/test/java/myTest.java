import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import workbench.service.SalesGoodsService;

public class myTest {
    @Test
    public void test01(){
        String config="applicationContext.xml";
        ApplicationContext applicationContext=
                new ClassPathXmlApplicationContext(config);
        SalesGoodsService salesGoodsService=
                (SalesGoodsService) applicationContext.getBean("salesGoodsServiceImpl");
        System.out.println(salesGoodsService.getClass().getName());
        salesGoodsService.buy(1002,10);
    }
    @Test
    public void test02(){}
}
