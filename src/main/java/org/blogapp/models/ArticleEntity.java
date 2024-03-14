package org.blogapp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@Table(name = "articles", schema = "public")
@Entity
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "slug")
    private String slug;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "date")
    private LocalDate publishDate;

    public ArticleEntity(){

        this.publishDate = LocalDate.now();
        this.slug = publishDate + "-" + new Random().nextLong(10000) + new Date().getTime();
    }

    public String getSlug(){
        return slug;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
