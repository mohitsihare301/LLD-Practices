package bookMyShow.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import bookMyShow.models.Show;
import java.util.List;
public class ShowService {
    private static ShowService instance;
    private Map<Integer,Show> shows = new ConcurrentHashMap<>();
    private ShowService(){

    };

    public static synchronized ShowService getInstance(){
        if(instance==null){
            instance=new ShowService();
        }
        return instance;
    }

    public boolean addShow(Show show){
        shows.put(show.getId(),show);
        return true;
    }

    public Show getShow(int showId){
        return shows.get(showId);
    }

    public List<Show>getShows(int movieId){
        return shows.values().stream().filter(show -> show.getMovie().getId()==movieId).toList();
    }

}
