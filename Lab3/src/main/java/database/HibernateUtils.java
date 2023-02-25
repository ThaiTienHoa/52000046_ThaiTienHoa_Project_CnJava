package database;

import java.util.Properties;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import POJO.Manufacture;
import POJO.Phone;

public class HibernateUtils {
    //	muốn hằng số tạo ra 1 lần dùng chung static final
    private static final SessionFactory FACTORY;
    //khối tĩnh này chạy 1 lần
    static {
        Configuration conf=new Configuration();

//code cung cấp thông tin cấu hình
        Properties props =new Properties();
        props.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL,"jdbc:mysql://localhost:3306/lab03java");
        props.put(Environment.USER,"root");
        props.put(Environment.PASS,"");
        props.put(Environment.SHOW_SQL,"true");

        conf.setProperties(props);

////cung cấp thông tin cấu hình file xml
//			conf.configure("hibernate.cfg.xml");

        conf.addAnnotatedClass(Manufacture.class);//Khai bao tồn tại Category
        conf.addAnnotatedClass(Phone.class);//Khai bao tồn tại Product
        //ServiceRegistry lớp trừ tượng
        ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY =conf.buildSessionFactory(registry);

    }
    public static SessionFactory getFactory() {
        return FACTORY;
    }


}

