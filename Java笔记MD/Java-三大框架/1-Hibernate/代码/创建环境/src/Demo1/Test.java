package Demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
    @org.junit.Test
    public void Test()
    {
        //1. 加载Hibernate核心配置文件
        Configuration configure = new Configuration().configure(); //就去找配置文件

        //2. 创建sessionFactory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        //3. 获取session
        Session session = sessionFactory.openSession();

        Students su = new Students();
        su.setName("Tahsi");
        su.setAge(18);
        su.setId(3);

        //4. 保存
        session.save(su);

        //5. 释放
        session.close();
        sessionFactory.close();
    }
}
