package implementation;

import core.Album;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AlbumsManager {

    private static CopyOnWriteArrayList<Album> albums;

    //constructor initializing albums arraylist
    public AlbumsManager() {
        albums = new CopyOnWriteArrayList<>();
    }

    //create new album
    public Album createAlbum(String ISRC, String title, String description, int year, String artist){
        if(getAlbum(ISRC) == null){
            Album newAlbum = new Album(ISRC, title, description, year, artist);
//            Album newAlbum = new Album(ISRC, title, description, year, artist);
            albums.add(newAlbum);
            return newAlbum;
        } else {
            return null;
        }
    }

    public boolean deleteAlbum(String ISRC) {
        return albums.removeIf(p -> (p.getISRC().equals(ISRC)));
    }

    public String getAllAlbums(){
        StringBuilder albumsString= new StringBuilder();
        Iterator<Album> itr = albums.iterator();
        while(itr.hasNext()) {
            albumsString.append(itr.next().toString()).append("\n");
        }
        return albumsString.toString();
        //return albums.stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    public Album getAlbum(String ISRC){
        return albums.stream().filter(album1 -> album1.getISRC().equals(ISRC))
                .findFirst()
                .orElse(null);
    }

    public boolean hasAlbums(){
        return !albums.isEmpty();
    }

    public Album getAlbum(String ISRC, String title){
        return albums.stream().filter(album1 -> album1.getISRC().equals(ISRC) && album1.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public boolean updateAlbum(String ISRC, String title, String description, int year, String artist){

        Album album = getAlbum(ISRC);

        if(album != null){
            albums.stream().filter(album1 -> album1.getISRC().equals(ISRC))
                    .forEach(al -> {
                        al.setTitle(title);
                        al.setDescription(description);
                        al.setYear(year);
                        al.setArtist(artist);
                    });
            return true;
        }

        return false;
    }
}
