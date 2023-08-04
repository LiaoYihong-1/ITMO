package DataBase;
import Data.DotsTable;
import lombok.NoArgsConstructor;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
@NoArgsConstructor
public class DataBaseStorage implements Serializable {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(DotsTable.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public void addDot(DotsTable dot){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dot);
        transaction.commit();
        session.close();
    }

    public void deleteDot(DotsTable dot){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(dot);
        transaction.commit();
        session.close();
    }
}
