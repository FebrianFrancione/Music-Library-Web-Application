package com.example.demo.Entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISRC")
    private String ISRC;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private int year;

    @Column(name = "artist_first_name")
    private String artist_first_name;

    @Column(name = "artist_last_name")
    private String artist_last_name;

    @Column(name = "cover_image_name")
    private String cover_image_name;

    @Column(name = "image_mime")
    private String image_mime;

    //    @Column(name = "cover_image")
//    private String cover_image;
    @Column(name = "cover_image")
    private byte[] cover_image;


    public Album(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name) {
        this.ISRC = ISRC;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist_first_name = artist_first_name;
        this.artist_last_name = artist_last_name;
    }

    public Album(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name, String cover_image_name, String image_mime, byte[] cover_image) {
        this.ISRC = ISRC;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist_first_name = artist_first_name;
        this.artist_last_name = artist_last_name;
        this.cover_image_name = cover_image_name;
        this.image_mime = image_mime;
        this.cover_image = cover_image;
    }

    public Album() {
    }




    @Override
    public String toString() {
        return "Album{" +
                "ISRC='" + ISRC + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", artist_first_name='" + artist_first_name + '\'' +
                ", artist_last_name='" + artist_last_name + '\'' +
                ", cover_image_name='" + cover_image_name + '\'' +
                ", image_mime='" + image_mime + '\'' +
                ", cover_image='" + cover_image + '\'' +
                '}';
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArtist_first_name() {
        return artist_first_name;
    }

    public void setArtist_first_name(String artist_first_name) {
        this.artist_first_name = artist_first_name;
    }

    public String getArtist_last_name() {
        return artist_last_name;
    }

    public void setArtist_last_name(String artist_last_name) {
        this.artist_last_name = artist_last_name;
    }

    public String getCover_image_name() {
        return cover_image_name;
    }

    public void setCover_image_name(String cover_image_name) {
        this.cover_image_name = cover_image_name;
    }

    public String getImage_mime() {
        return image_mime;
    }

    public void setImage_mime(String image_mime) {
        this.image_mime = image_mime;
    }

    public byte[] getCover_image() {
        return cover_image;
    }

    public void setCover_image(byte[] cover_image) {
        this.cover_image = cover_image;
    }
}

