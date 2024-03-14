package org.blogapp.models.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.blogapp.models.ArticleEntity;
import org.blogapp.models.UserEntity;
import org.blogapp.models.UserType;
import org.blogapp.utils.PasswordEncryption;

public class UserManager {
    private static final UserManager instance = new UserManager();
    private SessionFactory sessionFactory = null;

    private UserManager(){
        try {
            this.sessionFactory = new Configuration().addAnnotatedClass(ArticleEntity.class).addAnnotatedClass(UserEntity.class).buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Hibernate session factory");
        }
    }

    public static UserManager init(){
        return instance;
    }

    public boolean registerUser(String username, String password, String email){
        try(Session session = sessionFactory.openSession()){
            String generatedSalt = PasswordEncryption.generateSalt();
            String hashedPassword = PasswordEncryption.hashPassword(password, generatedSalt);

            Transaction transaction = session.beginTransaction();
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(hashedPassword);
            userEntity.setEmail(email);
            userEntity.setSalt(generatedSalt);
            userEntity.setUserType(UserType.USER);
            session.persist(userEntity);
            transaction.commit();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password){
        try(Session session = sessionFactory.openSession()){
            Query<UserEntity> query = session.createQuery("from UserEntity where username = :user", UserEntity.class);
            query.setParameter("user", username);
            UserEntity userEntity = query.uniqueResult();
            if(userEntity == null) return false;
            if(PasswordEncryption.authenticate(password, userEntity.getPassword(), userEntity.getSalt())) return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public UserEntity getUserByUsername(String username){
        try(Session session = sessionFactory.openSession()){
            Query<UserEntity> query = session.createQuery("from UserEntity where username = :user", UserEntity.class);
            query.setParameter("user", username);
            return query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
