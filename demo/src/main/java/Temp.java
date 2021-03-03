//import implementation.*;
//import core.Artist;
//import java.util.Random;
//
//public class Temp {
//    public static void main(String[] args) {
//
//        ArtistsManager artistsManager = new ArtistsManager();
//
//        //Printing all artists
//        System.out.println("\n\nGetting all artists\n" + artistsManager.getAllArtists());
//
//        //Getting a specific artist
//        String nickname = artistsManager.getArtist(2).getNickname();
//        Artist testArtist = artistsManager.getArtist(nickname);
//        System.out.println("\n\nGetting artist with nickname : " + nickname + "\n" + testArtist);
//
//        //Updating a specific artist
//        if(artistsManager.updateArtist(nickname, "Cameron", "Diaz", "She's a sexy mamasita"))
//            System.out.println("\n\nAfter modified artist " + nickname + ":\n" + artistsManager.getArtist(nickname));
//
//        //Deleting a specific artist
//        artistsManager.deleteArtist(nickname);
//        System.out.println("\n\nGetting all artists after deleting artist with nickname " + nickname + "\n" + artistsManager.getAllArtists());
//
//    }
//
//    //Helper to get some random words
//    public static String getRandomWord(){
//        Random random = new Random();
//
//        return random.ints(97, 122 + 1)
//                .limit(10)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//    }
//}
