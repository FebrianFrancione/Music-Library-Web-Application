package com.example.demo.Service;

import com.example.demo.Entity.Album;
import java.util.List;

public interface AlbumService {

    List<Album> getAlbums();
    Album getAlbum(String ISRC);
    Album findByISRCAndTitle(String ISRC, String title);
    Album createNewAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name);
    void deleteAlbum(String ISRC);
    void modifyAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name);
    //cover images CRUD
    Album getCoverImage(String ISRC);
    void deleteCoverImage();
    void updateCoverImage();
}
