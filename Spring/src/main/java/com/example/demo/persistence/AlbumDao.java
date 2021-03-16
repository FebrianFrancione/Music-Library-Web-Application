package com.example.demo.persistence;



import com.example.demo.Entity.Album;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao {

    private PreparedStatement statement = null;

    public void insertImage(String ISRC, String cover_image_name, String image_mime, byte[] cover_image) throws FileNotFoundException {
        String sql = "Update albums_db.albums set cover_image_name = ?, image_mime=?, cover_image=? where ISRC=?;";
        JDBConfig jdbc = new JDBConfig();
        statement = jdbc.prepareStatement(sql);

        try {
            statement.setString(1, cover_image_name);
            statement.setString(2, image_mime);
            statement.setBytes(3,cover_image);
            statement.setString(4, ISRC);
//            statement.setString(7, album.getCover_image_name());
//            statement.setString(8, album.getImage_mime());
//            statement.setBytes(7, album.getCover_image());

            //Testing adding an image to DB
//            Path searchPath = Paths.get("demo/src/main/java/persistence/test_image.jpg").toAbsolutePath();
//            File f = new File(String.valueOf(searchPath));
//            FileInputStream fs= new FileInputStream(f);
//            statement.setBinaryStream(9,fs,(int)f.length());
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
    }

    public void insertImage2(String ISRC, String title, String description, int year, String artist_first_name,String artist_last_name, String cover_image_name, String image_mime, byte[] cover_image) throws FileNotFoundException {
        String sql = "insert into albums (ISRC,title,description,year,artist_first_name,artist_last_name, cover_image_name, image_mime, cover_image) values (?,?,?,?,?,?,?,?,?)";
        JDBConfig jdbc = new JDBConfig();
        statement = jdbc.prepareStatement(sql);

        try {

            statement.setString(1, ISRC);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setInt(4, year);
            statement.setString(5, artist_first_name);
            statement.setString(6, artist_last_name);
            statement.setString(7, cover_image_name);
            statement.setString(8, image_mime);
            statement.setBytes(9,cover_image);
//            statement.setString(7, album.getCover_image_name());
//            statement.setString(8, album.getImage_mime());
//            statement.setBytes(7, album.getCover_image());

            //Testing adding an image to DB
//            Path searchPath = Paths.get("demo/src/main/java/persistence/test_image.jpg").toAbsolutePath();
//            File f = new File(String.valueOf(searchPath));
//            FileInputStream fs= new FileInputStream(f);
//            statement.setBinaryStream(9,fs,(int)f.length());
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
    }

//    public void delete(String ISRC){
////        boolean deleted = false;
//        String sql = "delete from albums where ISRC=?";
//        JDBConfig jdbc = new JDBConfig();
//        statement = jdbc.prepareStatement(sql);
//        try {
//            statement.setString(1, ISRC);
//            statement.executeUpdate();
////            int deletedRow = statement.executeUpdate();
////            if(deletedRow == 1)
////                deleted = true;
//        }catch(SQLException e){
//            e.printStackTrace();
//        }finally {
//            jdbc.close();
//        }
////        return deleted;
//    }




    public Album findByISRCAndTitle(String ISRC, String theTitle) throws Exception, IOException {
        Album album = null;
        String title;
        String description;
        int year;
        String artist_first_name;
        String artist_last_name;
        String cover_image_name;
        String image_mime;
        byte[] cover_image;

        String sql = "select * from albums_db.albums where ISRC=? and title=?";
        JDBConfig jdbc = new JDBConfig();
        statement = jdbc.prepareStatement(sql);

        ResultSet resultSet = null;
        try {
            statement.setString(1, ISRC);
            statement.setString(2, theTitle);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                ISRC = resultSet.getString("ISRC");
                title = resultSet.getString("title");
                description = resultSet.getString("description");
                year = resultSet.getInt("year");
                artist_first_name = resultSet.getString("artist_first_name");
                artist_last_name = resultSet.getString("artist_last_name");
                cover_image_name = resultSet.getString("cover_image_name");
                image_mime = resultSet.getString("image_mime");
                cover_image = resultSet.getBytes("cover_image");

                album = new Album(ISRC, title, description, year, artist_first_name, artist_last_name,cover_image_name, image_mime, cover_image);
                return album;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }

        return album;
    }

    public List<Album> getAlbums(){

        List<Album> albums = new ArrayList<>();
        JDBConfig jdbc = new JDBConfig();
        ResultSet resultSet = null;
        try {
            String sql = "select * from albums";
            statement = jdbc.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String ISRC = resultSet.getString("ISRC");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int year = resultSet.getInt("year");
                String artist_first_name = resultSet.getString("artist_first_name");
                String artist_last_name = resultSet.getString("artist_last_name");
                String cover_image_name = resultSet.getString("cover_image_name");
                String image_mime = resultSet.getString("image_mime");
                byte[] cover_image = resultSet.getBytes("cover_image");
                Album newAlbum = new Album(ISRC, title, description, year, artist_first_name, artist_last_name,cover_image_name, image_mime, cover_image);
                albums.add(newAlbum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }

        return albums;
    }


    public void insertAlbum(Album album) throws FileNotFoundException {
        String sql = "insert into albums (ISRC,title,description,year,artist_first_name,artist_last_name) values (?,?,?,?,?,?)";
        JDBConfig jdbc = new JDBConfig();
        statement = jdbc.prepareStatement(sql);

        try {
            statement.setString(1, album.getISRC());
            statement.setString(2, album.getTitle());
            statement.setString(3, album.getDescription());
            statement.setInt(4, album.getYear());
            statement.setString(5, album.getArtist_first_name());
            statement.setString(6, album.getArtist_last_name());
//            statement.setString(7, album.getCover_image_name());
//            statement.setString(8, album.getImage_mime());
//            statement.setBytes(7, album.getCover_image());

            //Testing adding an image to DB
//            Path searchPath = Paths.get("demo/src/main/java/persistence/test_image.jpg").toAbsolutePath();
//            File f = new File(String.valueOf(searchPath));
//            FileInputStream fs= new FileInputStream(f);
//            statement.setBinaryStream(9,fs,(int)f.length());
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
    }

    public void delete(String ISRC){
//        boolean deleted = false;
        String sql = "delete from albums where ISRC=?";
        JDBConfig jdbc = new JDBConfig();
        statement = jdbc.prepareStatement(sql);
        try {
            statement.setString(1, ISRC);
            statement.executeUpdate();
//            int deletedRow = statement.executeUpdate();
//            if(deletedRow == 1)
//                deleted = true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
//        return deleted;
    }


//    public void updateAlbum(String ISRC,String title,String description,int year,String artist_first_name,String artist_last_name){
//        String sql = "update albums set title=?,description=?,year=?,artist_first_name=?, artist_last_name=? where ISRC=?";
//        JDBConfig jdbc = new JDBConfig();
//        statement = jdbc.prepareStatement(sql);
//        try {
//            statement.setString(1, title);
//            statement.setString(2, description);
//            statement.setInt(3, year);
//            statement.setString(4, artist_first_name);
//            statement.setString(5,artist_last_name);
//            statement.setString(6,ISRC);
//            statement.executeUpdate();
//        }catch(SQLException e){
//            e.printStackTrace();
//        }finally {
//            jdbc.close();
//        }
//    }
//


    public String getImageExtension(String MIME_type){
        String extension = "";

        switch(MIME_type){
            case "image/gif":
                extension = ".gif";
                break;
            case "image/png":
                extension = ".png";
                break;
            default:
                extension = ".jpg";
        }
        return extension;
    }
}


//
//import com.example.demo.Entity.Album;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.io.*;
//import java.math.BigDecimal;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.sql.*;
//import java.util.ArrayList;
//
//public class AlbumDao {
//
//    private PreparedStatement statement = null;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
////    public void AlbumObject(Object[] columns) {
////        this.id = (columns[0] != null)?((BigDecimal)columns[0]).intValue():0;
////        this.name = (String) columns[1];
////    }
//
//
//    public void insertAlbumTest(Album album) throws FileNotFoundException {
////        String sql = "insert into albums (ISRC,title,description,year,artist_first_name,artist_last_name,cover_image_name,image_mime,cover_image) values (?,?,?,?,?,?,?,?,?)";
//
//        String sql = "insert into albums (ISRC,title,description,year,artist_first_name,artist_last_name) values (?,?,?,?,?,?)";
//
//        Query q = entityManager.createNativeQuery(sql);
//        q.setParameter(1, album.getISRC());
//        q.setParameter(2, album.getTitle());
//        q.setParameter(3, album.getDescription());
//        q.setParameter(4, album.getImage_mime());
//        q.setParameter(5, album.getArtist_first_name());
//        q.setParameter(6, album.getArtist_last_name());
//    }
//
////        try {
////            entityManager.setString(1, ISRC);
////            statement.setString(2, title);
////            statement.setString(3, description);
////            statement.setInt(4, year);
////            statement.setString(5, artist_first_name);
////            statement.setString(6, artist_last_name);
////            statement.setString(7, image_name);
////            statement.setString(8, mime_type);
////
////            //Testing adding an image to DB
////            Path searchPath = Paths.get("demo/src/main/java/persistence/test_image.jpg").toAbsolutePath();
////            File f = new File(String.valueOf(searchPath));
////            FileInputStream fs= new FileInputStream(f);
////
//////            statement.setBinaryStream(9,fs,(int)f.length());
//////            statement.executeUpdate();
////
////        }catch(SQLException e){
////            e.printStackTrace();
////        }finally {
////            jdbc.close();
////        }
//
////    public ArrayList<Album> getAlbums(){
////
////        ArrayList<Album> albums = new ArrayList<>();
////        JDBConfig jdbc = new JDBConfig();
////        ResultSet resultSet = null;
////        try {
////            String sql = "select * from albums";
////            statement = jdbc.prepareStatement(sql);
////            resultSet = statement.executeQuery();
////            while (resultSet.next()) {
////                String ISRC = resultSet.getString("ISRC");
////                String title = resultSet.getString("title");
////                String description = resultSet.getString("description");
////                int year = resultSet.getInt("year");
////                String first_name = resultSet.getString("artist_first_name");
////                String last_name = resultSet.getString("artist_last_name");
////                String artist = first_name + " " + last_name;
////                Album newAlbum = new Album(ISRC, title, description, year, artist);
////                albums.add(newAlbum);
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            jdbc.close();
////        }
////
////        return albums;
////    }
////
////    public Album getAlbum(String ISRC) throws Exception, IOException {
////        Album album = null;
////        String title;
////        String description;
////        int year;
////        String first_name;
////        String last_name;
////        String cover_image_name;
////        String image_mime;
////
////        JDBConfig jdbc = new JDBConfig();
////        String sql = "select * from albums where ISRC=?";
////        statement = jdbc.prepareStatement(sql);
////        ResultSet resultSet = null;
////        try {
////            statement.setString(1, ISRC);
////            resultSet = statement.executeQuery();
////            while(resultSet.next()){
////                ISRC = resultSet.getString("ISRC");
////                title = resultSet.getString("title");
////                description = resultSet.getString("description");
////                year = resultSet.getInt("year");
////                first_name = resultSet.getString("artist_first_name");
////                last_name = resultSet.getString("artist_last_name");
////                String artist = first_name + " " + last_name;
////                album = new Album(ISRC, title, description, year, artist);
////
////                //Testing getting an image from DB
////                Blob blob = resultSet.getBlob("cover_image");
////                if(blob != null){
////                    byte[] b;
////                    cover_image_name = resultSet.getString("cover_image_name");
////                    image_mime = resultSet.getString("image_mime");
////
////                    if(cover_image_name == null)
////                        cover_image_name = "image";
////
////                    String extension = (image_mime != null) ? getImageExtension(image_mime) : ".jpg";
////                    String cover_image = cover_image_name + extension;
////                    Path searchPath = Paths.get("demo/src/main/java/persistence/" + cover_image).toAbsolutePath();
////                    File f=new File(String.valueOf(searchPath));
////                    FileOutputStream fs = new FileOutputStream(f);
////                    b = blob.getBytes(1, (int)blob.length());
////                    fs.write(b);
////                }
////
////            }
////        }catch(SQLException e){
////            e.printStackTrace();
////        }finally {
////            jdbc.close();
////        }
////
////        return album;
////    }
////
////    public void updateAlbum(String ISRC,String title,String description,int year,String artist_first_name,String artist_last_name){
////        String sql = "update albums set title=?,description=?,year=?,artist_first_name=?, artist_last_name=? where ISRC=?";
////        JDBConfig jdbc = new JDBConfig();
////        statement = jdbc.prepareStatement(sql);
////        try {
////            statement.setString(1, title);
////            statement.setString(2, description);
////            statement.setInt(3, year);
////            statement.setString(4, artist_first_name);
////            statement.setString(5,artist_last_name);
////            statement.setString(6,ISRC);
////            statement.executeUpdate();
////        }catch(SQLException e){
////            e.printStackTrace();
////        }finally {
////            jdbc.close();
////        }
////    }
////
////    public boolean deleteAlbum(String ISRC){
////        boolean deleted = false;
////        String sql = "delete from albums where ISRC=?";
////        JDBConfig jdbc = new JDBConfig();
////        statement = jdbc.prepareStatement(sql);
////        try {
////            statement.setString(1, ISRC);
////            int deletedRow = statement.executeUpdate();
////            if(deletedRow == 1)
////                deleted = true;
////
////        }catch(SQLException e){
////            e.printStackTrace();
////        }finally {
////            jdbc.close();
////        }
////        return deleted;
////    }
////
////    public String getImageExtension(String MIME_type){
////        String extension = "";
////
////        switch(MIME_type){
////            case "image/gif":
////                extension = ".gif";
////                break;
////            case "image/png":
////                extension = ".png";
////                break;
////            default:
////                extension = ".jpg";
////        }
////        return extension;
////    }
//}
