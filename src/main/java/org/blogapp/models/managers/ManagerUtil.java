package org.blogapp.models.managers;

import org.blogapp.models.ArticleEntity;
import org.blogapp.models.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManagerUtil {
    private static SessionFactory sessionFactory = null;
    private ManagerUtil(){}

    public static SessionFactory getFactory() {
        try {
            if(sessionFactory == null) {
                sessionFactory = new Configuration().
                        addAnnotatedClass(ArticleEntity.class).
                        addAnnotatedClass(UserEntity.class).
                        buildSessionFactory();
            }
            return sessionFactory;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Hibernate session factory");
        }
    }
}
