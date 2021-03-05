package persistence;

import core.Album;
import java.util.ArrayList;

public class JDBCTest {

    public static void main(String[] args) throws Exception {
        AlbumDao albumDao = new AlbumDao();
        StringBuilder albumsString= new StringBuilder();
        ArrayList<Album> albums = albumDao.getAlbums();

        System.out.println("\nInserting album with ISRC new_alb");
        albumDao.insertAlbum("new_alb","random_title","new desc",2000,"Sam","Doe", "test_image","image/jpeg");

        System.out.println("\nGetting album with ISRC new_alb");
        Album album = albumDao.getAlbum("new_alb");
        System.out.println(album.toString());

        System.out.println("\nUpdating album with ISRC new_alb");
        albumDao.updateAlbum("new_alb","89title","updated desc",2020,"Ivan","Garzon");

        if(!albums.isEmpty()){
            for(Album a: albums)
                albumsString.append(a.toString()).append("\n");
            System.out.println(albumsString);
        }

        /*
        System.out.println("\nDeleting album with ISRC new_alb");
        if(albumDao.deleteAlbum("new_alb"))
            System.out.println("Album deleted");

         */

    }


}
