package org.blogapp.models.managers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.blogapp.models.ArticleEntity;
import org.blogapp.models.UserEntity;

import java.util.List;

public class ArticleManager {
    private static final ArticleManager instance = new ArticleManager();
    private SessionFactory sessionFactory = null;

    private ArticleManager(){
        try {
            this.sessionFactory = new Configuration().addAnnotatedClass(ArticleEntity.class).addAnnotatedClass(UserEntity.class).buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Hibernate session factory");
        }
    }

    public static ArticleManager init() {
        return instance;
    }

    public List<ArticleEntity> getAllArticles(){
        try(Session session = sessionFactory.openSession()){
            Query<ArticleEntity> query = session.createQuery("from ArticleEntity order by publishDate desc", ArticleEntity.class);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void addArticle(ArticleEntity article){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(article);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArticleEntity getArticleBySlug(String slug){
        try(Session session = sessionFactory.openSession()){
            Query<ArticleEntity> query = session.createQuery("from ArticleEntity where slug = :slug", ArticleEntity.class);
            query.setParameter("slug", slug);
            return query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteArticleById(Integer id){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ArticleEntity where id = :id");
            query.setParameter("id", id);
            int rowCount = query.executeUpdate();
            transaction.commit();
            return rowCount > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void updateArticle(ArticleEntity articleEntity){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(articleEntity);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
