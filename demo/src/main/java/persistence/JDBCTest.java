package persistence;

import core.Album;
import core.LogEntry;
import persistence.helpers.LogEntryType;

import java.util.ArrayList;

public class JDBCTest {

    public static void main(String[] args) throws Exception {
        AlbumDao albumDao = new AlbumDao();
        LogsDao logsDao = new LogsDao();
        StringBuilder albumsString= new StringBuilder();
        StringBuilder logsString= new StringBuilder();
        ArrayList<LogEntry> logs;

        System.out.println("\nInserting album with ISRC 100album");
        albumDao.insertAlbum("100album","random_title","new desc",2000,"Sam","Doe", "test_image","image/jpeg");
        System.out.println("Successfully inserted album!");

        System.out.println("\nGetting album with ISRC 100album");
        Album album = albumDao.getAlbum("100album");
        System.out.println(album.toString());
        System.out.println("Successfully retrieved album!");

        System.out.println("\nUpdating album with ISRC 100album");
        albumDao.updateAlbum("100album","89title","updated desc",2020,"Ivan","Garzon");

        System.out.println("\nGetting all albums");
        ArrayList<Album> albums = albumDao.getAlbums();

        if(!albums.isEmpty()){
            for(Album a: albums)
                albumsString.append(a.toString()).append("\n");
            System.out.println(albumsString);
        }

/*
        //TO TEST DELETE, INSERT THE ALBUM WITH THE ISRC
        System.out.println("\nDeleting album with ISRC 89album");
        if(albumDao.deleteAlbum("89album"))
            System.out.println("Album deleted");

*/
        System.out.println("\nInserting a new Log Entry for ISRC 100album");
        logsDao.addLogEntry("100album", LogEntryType.CREATE.getValue());
        System.out.println("Successfully inserted Log Entry!");

        System.out.println("\nGetting all Log Entries");
        logs = logsDao.getLogEntries();

        if(!logs.isEmpty()){
            for(LogEntry log: logs)
                logsString.append(log.toString()).append("\n");
            System.out.println(logsString);
            logsString.setLength(0);
        }

        System.out.println("\nGetting all Log Entries from 2021-03-02 to 2021-03-05");
        logs = logsDao.filterLogsByDates("2021-03-02", "2021-03-05");

        if(!logs.isEmpty()){
            for(LogEntry log: logs)
                logsString.append(log.toString()).append("\n");
            System.out.println(logsString);
            logsString.setLength(0);
        }


        System.out.println("\nGetting all Log Entries from 2021-03-04");
        logs = logsDao.filterLogsByDates("2021-03-04", "");

        if(!logs.isEmpty()){
            for(LogEntry log: logs)
                logsString.append(log.toString()).append("\n");
            System.out.println(logsString);
            logsString.setLength(0);
        }


        System.out.println("\nGetting all Log Entries up to 2021-03-04");
        logs = logsDao.filterLogsByDates("", "2021-03-04");

        if(!logs.isEmpty()){
            for(LogEntry log: logs)
                logsString.append(log.toString()).append("\n");
            System.out.println(logsString);
            logsString.setLength(0);
        }

        System.out.println("\nGetting all Log Entries by type of change CREATE");
        logs = logsDao.filterLogsByChange(LogEntryType.CREATE.getValue());

        if(!logs.isEmpty()){
            for(LogEntry log: logs)
                logsString.append(log.toString()).append("\n");
            System.out.println(logsString);
            logsString.setLength(0);
        }


    }


}
