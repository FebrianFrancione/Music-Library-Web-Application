package persistence;

import core.Album;
import java.util.ArrayList;

public class JDBCTest {

    public static void main(String[] args) {
        AlbumDao albumDao = new AlbumDao();
        StringBuilder albumsString= new StringBuilder();
        ArrayList<Album> albums = albumDao.getAlbums();

        System.out.println("\nInserting album with ISRC 89album");
        albumDao.insertAlbum("89album","89title","new desc",1919,"John","Doe");

        System.out.println("\nUpdating album with ISRC 11album");
        albumDao.updateAlbum("11album","89title","updated desc",2020,"Ivan","Garzon");

        if(!albums.isEmpty()){
            for(Album a: albums)
                albumsString.append(a.toString()).append("\n");
            System.out.println(albumsString);
        }

        System.out.println("\nGetting album with ISRC 89album");
        Album album = albumDao.getAlbum("89album");
        System.out.println(album.toString());

        System.out.println("\nDeleting album with ISRC 89album");
        if(albumDao.deleteAlbum("89album"))
            System.out.println("Album deleted");

    }


}
