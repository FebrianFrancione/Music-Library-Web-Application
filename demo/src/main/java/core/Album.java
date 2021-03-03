package core;

public class Album {
    private String ISRC;
    private String title;
    private String description;
    private int year;
    private String artist;

    public Album (String ISRC, String title, String description, int year, String artist){
        this.ISRC = ISRC;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist = artist;
    }

    public String toString() {
        return "Album{" +
                "ISRC: '" + ISRC + '\'' +
                ", title: '" + title + '\'' +
                ", description: '" + description + '\'' +
                ", release year: " + year +
                ", artist: '" + artist + '\'' +
                '}';
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

