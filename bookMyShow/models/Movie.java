package bookMyShow.models;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private double duration;
    private String language;
    private String genre;

    public Movie(int id,String title,double duration,String language, String genre){
        this.id=id;
        this.title=title;
        this.duration=duration;
        this.language=language;
        this.genre=genre;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return genre;
    }
}
