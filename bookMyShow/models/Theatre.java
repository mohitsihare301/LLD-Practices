package bookMyShow.models;

import java.util.List;

public class Theatre {
    private int id;
    private String name;
    private String address;
    private List<Screen>screens;

    public Theatre(int id,String name,String address, List<Screen>screens){
        this.id=id;
        this.name=name;
        this.address=address;
        this.screens=screens;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void addScreen(Screen screen){
        screens.add(screen);
    }
}
