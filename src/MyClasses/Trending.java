package MyClasses;

import Skeleton.Unit;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Singleton that keeps record of trending units
 * */
public class Trending {
    private static volatile Trending instance;
    private ArrayList<Unit> trending = new ArrayList<>();

    /**
     * Empty constructor, we don't need user info
     * */
    private Trending() {}

    /**
     * Get the one instance of this singleton
     * */
    public static Trending getInstance(){
        Trending result = instance;
        if(result == null){
            synchronized (Trending.class) {
                result = instance;
                if(result == null) {
                    instance = result = new Trending();
                }
            }
        }
        return result;
    }

    /**
     * When a unit meets the criteria to be trending, it calls this. Then for some time, another unit can
     * not call this again.
     * @param u the unit making the request
     * */
    public void add_trending(Unit u) throws InterruptedException {
        trending.add(u);
        Thread.sleep(10000);
    }

    /**
     * Method that prints all units that have ever trended
     * */
    public void print_trending() {
        System.out.println("All creators that have trended");
        for(Unit unit : trending)
            System.out.println(unit.getType() + " " +unit.getName());
    }
}
