package com.example.demo.Service;

import com.example.demo.Entity.Album;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface AlbumService {

    List<Album> getAlbums();
    Album findByISRCAndTitle(String ISRC, String title) throws Exception;
    Album createNewAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name) throws FileNotFoundException;
    void deleteAlbum(String ISRC);
    void modifyAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name) throws FileNotFoundException;

    void upload(String ISRC, String cover_image_name, String image_mime, byte[] cover_image) throws FileNotFoundException;

    //    Album getAlbum(String ISRC);
////    Album getAlbumHtml(String ISRC);


//    //cover images CRUD
//    Album getCoverImage(String ISRC);
//    void deleteCoverImage();
//    void updateCoverImage();
}
