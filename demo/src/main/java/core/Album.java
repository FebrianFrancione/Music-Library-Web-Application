package core;

public class Album {
    private String ISRC;
    private String title;
    private String description;
    private int year;
    private Artist artist;

    public Album(){}

    public Album (String ISRC, String title, String description, int year, String firstName, String lastName){
        this.ISRC = ISRC;
        this.title = title;
        this.description = description;
        this.year = year;
        artist = new Artist(firstName, lastName);
    }

    public String toString() {
        return "Album{" +
                "ISRC: '" + ISRC + '\'' +
                ", title: '" + title + '\'' +
                ", description: '" + description + '\'' +
                ", release year: " + year +
                ", artist: '" + artist.getFirst_name() + ' ' + artist.getLast_name() + '\'' +
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

}

