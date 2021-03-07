package services;

import core.Album;
import implementation.AlbumsManager;

import java.util.ArrayList;
import java.util.Random;

public class TestMain extends Thread{

    //create new AlbumsManager obj
//    static AlbumsManager albumsManager = new AlbumsManager();
//
//    public void run()
//    {
//        int year = (int)(Math.random() * (2020 - 1979 + 1) + 1979);
//        for(int i = 0; i < 4; i++) {
//            String ISRC = getRandomWord();
//            System.out.println("\n\nAdding thread album " + ISRC + "\n");
//            albumsManager.createAlbum(ISRC, getRandomWord(), getRandomWord(), year, getRandomWord());
//
//            if(albumsManager.updateAlbum(ISRC, "Gonzaga 68", "Gonzaga '68 bootleg features the band performing life", 1994, "Led Zeppelin"))
//                System.out.println("\n\nAfter modified album " + ISRC + ":\n" + albumsManager.getAlbum(ISRC));
//
//            year = (int)(Math.random() * (2020 - 1979 + 1) + 1979);
//        }
//
//    }
//
//    public static void main(String[] args) throws InterruptedException{
//
//        //create new Album ArrayList
//        ArrayList<Album> albums = new ArrayList<>();
//        //random year generated
//        int year = (int)(Math.random() * (2020 - 1979 + 1) + 1979);
//
//        TestMain t = new TestMain();
//        t.start();
//
//        //Creating 10 random albums and artists
//        for(int i = 0; i < 8; i++) {
//            String ISRC = getRandomWord();
//            String title = getRandomWord();
//            String description = getRandomWord();
//            String artist = getRandomWord();
//
//            Album newAlbum = new Album(ISRC, title, description, year, artist);
//            albums.add(newAlbum);
//            albumsManager.createAlbum(ISRC, title, description, year, artist);
//            year = (int)(Math.random() * (2020 - 1979 + 1) + 1979);
//
//            //Printing all albums and artists
//            System.out.println("\n\nGetting all albums\n" + albumsManager.getAllAlbums());
//        }
//
//        //Getting a specific album
//        String isrc = albums.get(3).getISRC();
//        Album testAlbum = albumsManager.getAlbum(isrc);
//        System.out.println("\n\nGetting album with ISRC : " + isrc + "\n" + testAlbum);
//
//        //Updating a specific album
//        if(albumsManager.updateAlbum(isrc, "Gonzaga 68", "Gonzaga '68 bootleg features the band performing life", 1994, "Led Zeppelin"))
//            System.out.println("\n\nAfter modified album " + isrc + ":\n" + albumsManager.getAlbum(isrc));
//
//        //Deleting a specific album
//        albumsManager.deleteAlbum(isrc);
//        System.out.println("\n\nGetting all albums after deleting album with ISRC " + isrc + "\n" + albumsManager.getAllAlbums());
//
//
//    }
//
//    //Helper to get some random words
//    public static String getRandomWord(){
//        Random random = new Random();
//        return random.ints(97, 122 + 1)
//                .limit(10)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//    }
}
