package core;

public class Artist {
    private String nickname;
    private String first_name;
    private String last_name;
    private String biography;

    public Artist(String nickname, String first_name, String last_name, String biography) {
        this.nickname = nickname;
        this.first_name = first_name;
        this.last_name = last_name;
        this.biography = biography;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Artist {" +
                "Nickname: '" + nickname + '\'' +
                ", First name: '" + first_name + '\'' +
                ", Last name: '" + last_name + '\'' +
                ", Biography: '" + biography + '\'' +
                '}';
    }


}