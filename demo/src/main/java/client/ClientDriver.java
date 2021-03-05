package client;

import java.io.File;
import java.net.URLEncoder;
import java.util.InputMismatchException;
import java.util.Scanner;
import Exception.RepException;

public class ClientDriver {

    public static AlbumClient albumClient;
    public static ArtistsClient artistClient;


    public static void main(String[] args){
        albumClient = new AlbumClient();
        artistClient = new ArtistsClient();
        printMainMenu();
    }

    private static void printMainMenu(){
        Scanner sc = new Scanner(System.in);
        int choose=0;
        boolean correct = true;

        System.out.println("\nWelcome to the Artists and Albums Service!\n Created by Kiho, Ivan and Febrian");
        System.out.println("1. Artist Service");
        System.out.println("2. Album Service");
        System.out.println("3. Quit Console");

        do {
            System.out.print("Please select the service you would like to use: ");
            try {
                choose = sc.nextInt();
                switch(choose){
                    case 1:
                        System.out.println("You have selected the Artist Service option!");
                        artistsDriver();

                        break;
                    case 2:
                        System.out.println("You have selected the Album Service option!");
                        albumsDriver();

                        break;
                    case 3:
                        System.out.println("You have selected the Quit option. The program is terminating...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input. Select again.");
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please put a correct number. Try again.");
            }

            if(choose == 1 || choose == 2)
                correct=false;

        }while(correct);


    }

    private static void artistsDriver(){
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            choose = ArtistsMenu(sc);

            switch(choose){
                case 1:
                    displayArtists();
                    break;
                case 2:
                    retrieveArtist(sc);
                    break;
                case 3:
                    addOrUpdateArtist(true, false, sc);
                    break;
                case 4:
                    addOrUpdateArtist(false, true, sc);
                    break;
                case 5:
                    deleteArtist(sc);
                    break;
                case 6:
                    System.out.println("Exiting artists driver.");
                    break;
                default:
                    System.out.println("You chose an invalid option.");
            }

        }while(choose != 6);

        printMainMenu();
    }

    private static void albumsDriver(){
        Scanner sc = new Scanner(System.in);
        int choose;

        do {
            choose = AlbumsMenu(sc);

            switch(choose){
                case 1:
                    displayAlbums();
                    break;
                case 2:
                    retrieveAlbum(sc);
                    break;
                case 3:
                    addOrUpdateAlbum(true, false, sc);
                    break;
                case 4:
                    addOrUpdateAlbum(false, true, sc);
                    break;
                case 5:
                    deleteAlbum(sc);
                    break;
                case 6:
                    System.out.println("Exiting albums driver.");
                    break;
                case 7:
                    displayAlbumImage();
                case 8:
                   uploadAlbumImage();
                default:
                    System.out.println("You chose an invalid option.");
            }

        }while(choose != 6);

        printMainMenu();
    }

    private static int AlbumsMenu(Scanner sc){
        System.out.println("\n\nMenu (Verb Lists) :");
        System.out.println("1. List all Albums");
        System.out.println("2. Return the specific album info");
        System.out.println("3. Add a new album to the collection");
        System.out.println("4. Update existing album info");
        System.out.println("5. Delete an existing album");
        System.out.println("6. Return to main menu");
        System.out.println("7. displayAlbumImage file Stuff (Placeholder)");
        System.out.println("8. uploadAlbumImage file Stuff (Placeholder)");
        System.out.println();

        System.out.print("Please choose one of the above numbers : ");
        try{
            int choose = sc.nextInt();
            System.out.println();
            return choose;
        }catch(InputMismatchException e){
            sc.nextLine();
            System.out.println("Please enter a number. Try again.");
            throw new RepException("Incorrect" + e);
//            return 0;
        }
    }

    private static int ArtistsMenu(Scanner sc){
        System.out.println("\n\nMenu (Verb Lists) :");
        System.out.println("1. List all Artists (nickname + full name)");
        System.out.println("2. Get Artist details (returns the artist full info including bio)");
        System.out.println("3. Add a new Artist to the collection");
        System.out.println("4. Update existing Artist");
        System.out.println("5. Delete an existing Artist");
        System.out.println("6. Return to main menu");
        System.out.println();

        System.out.print("Please select an option: ");

        try{
            int choose = sc.nextInt();
            sc.nextLine();
            return choose;
        }catch(InputMismatchException e){
            sc.nextLine();
            System.out.println("Please enter a number. Try again.");
            return 0;
        }
    }

    private static void displayAlbums(){
        albumClient.showAll();
    }

    private static void displayArtists(){
        artistClient.showAll();

    }

    private static void displayAlbumImage(){
        System.out.println("Here are the files you can retrieve");
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File("demo/src/main/java/savedFiles");

        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
        }

        try{
            System.out.println("Enter the image file");
            albumClient.getAlbumImage();
        }catch (InputMismatchException e){
            System.out.println("You put the wrong information. Try again.");
            System.out.println();
            e.printStackTrace();
        }

    }

    private static void  uploadAlbumImage(){
        System.out.println("Upload files");
    }

    private static void retrieveAlbum(Scanner sc){
        String ISRC;
        String title;

        System.out.println("Please enter the ISRC and title of the album.");
        try{
            System.out.print("ISRC: ");
            ISRC = sc.next();
            sc.nextLine();

            System.out.print("Title: ");
            title = sc.nextLine();
            title = URLEncoder.encode(title);

            albumClient.getAlbums(ISRC, title);

        }catch(InputMismatchException e){
            System.out.println("You put the wrong information. Try again.");
            System.out.println();
        }
    }

    private static void retrieveArtist(Scanner sc){
        String nickname;

        System.out.println("Please enter the nickname of the artist.");
        try{
            System.out.print("Nickname: ");
            nickname = sc.nextLine();
            nickname = URLEncoder.encode(nickname);

            artistClient.getArtist(nickname);

        }catch(InputMismatchException e){
            System.out.println("You put the wrong information. Try again.");
            System.out.println();
        }
    }

    private static void addOrUpdateAlbum(boolean add, boolean update, Scanner sc){
        String ISRC;
        String title;
        String description;
        int year;
        String artist;

        System.out.println("Please enter the information of the album.");
        System.out.println("(Must have a valid ISRC, Title, Year and Artist)");
        try {
            System.out.print("ISRC: ");
            ISRC = sc.next();
            sc.nextLine();

            System.out.print("Title: ");
            title = sc.nextLine();
            title = URLEncoder.encode(title);

            System.out.print("Description: ");
            description = sc.nextLine();
            description = URLEncoder.encode(description);

            System.out.print("Year: ");
            year = sc.nextInt();
            sc.nextLine();

            System.out.print("Artist: ");
            artist = sc.nextLine();
            artist = URLEncoder.encode(artist);

            if(ISRC.isEmpty() || title.isEmpty() || artist.isEmpty()){
                throw new InputMismatchException("A field is missing an input!");
            }else if(year < 1950){
                throw new InputMismatchException("The year must be greater than or equal to 1950!");
            }
            else if(add) {
                if (description.isEmpty()) {
                    description = null;
                }
                albumClient.addAlbum(ISRC, title, description, artist, year);
            }
            else if(update) {
               if(description.isEmpty()){
                    description = null;
                }
                albumClient.updateAlbum(ISRC, title, description, artist, year);
            }

        }catch(InputMismatchException e){
            System.out.println("You put the wrong information. " + e.getMessage() + " Try again.");
            System.out.println();
        }
    }


    private static void addOrUpdateArtist(boolean add, boolean update, Scanner sc){
        String nickname;
        String first_name;
        String last_name;
        String biography;

        System.out.println("Please enter the information of the artist.");
        try {
            System.out.print("Nickname: ");
            nickname = sc.nextLine();
            System.out.print("First name: ");
            first_name = sc.nextLine();
            System.out.print("Last name: ");
            last_name = sc.nextLine();
            System.out.print("Biography: ");
            biography = sc.nextLine();

            if(add == true){
                nickname = URLEncoder.encode(nickname);
                first_name = URLEncoder.encode(first_name);
                last_name = URLEncoder.encode(last_name);
                biography = URLEncoder.encode(biography);
                if(nickname.isEmpty()){
                    throw new InputMismatchException("Nickname Error! Nickname field cannot be left blank!");
                }else if(first_name.isEmpty()){
                    throw new InputMismatchException("First Name Error! First Name field cannot be left blank!");
                }else if (last_name.isEmpty()){
                    throw new InputMismatchException("Last Name Error! Last Name field cannot be left blank!");
                }
                artistClient.sendArtist(nickname, first_name, last_name, biography);
            } else if(update == true) {
                artistClient.updateArtist(nickname, first_name, last_name, biography);
            }

        }catch(InputMismatchException e){
            System.out.println("You put the wrong information: " + e.getMessage() + " Try again.");
        }
    }

    private static void deleteAlbum(Scanner sc){
        String ISRC;

        System.out.println("What album do you want to delete?");
        try{
            System.out.print("ISRC: ");
            ISRC = sc.next();
            albumClient.deleteAlbum(ISRC);

        }catch(InputMismatchException e){
            System.out.println("You put the wrong information. Try again.");
            System.out.println();
        }
    }

    private static void deleteArtist(Scanner sc){
        String nickname;

        System.out.println("Please enter the nickname of the artist to delete.");
        try{
            System.out.print("Nickname: ");
            nickname = sc.nextLine();
            nickname = URLEncoder.encode(nickname);
            artistClient.deleteArtist(nickname);

        }catch(InputMismatchException e){
            System.out.println("You put the wrong information. Try again.");
            System.out.println();
        }
    }

}
