package com.ancomape.libraryManager.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private Integer yearOfRelease;
    private String imgUrl;
    @Column(name = "isRead")
    private boolean isRead = false;
    private Integer rating = 0;




    public Book() {
    }

    public Book(Long id, String title, String author, Integer pages, Integer yearOfRelease, String imgUrl, boolean isRead, Integer rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.yearOfRelease = yearOfRelease;
        this.imgUrl = imgUrl;
        this.isRead = isRead;
        this.rating = rating;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", yearOfRelease=" + yearOfRelease +
                ", imgUrl='" + imgUrl + '\'' +
                ", isRead='" + isRead + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
