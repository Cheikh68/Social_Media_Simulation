package MyClasses;

import Skeleton.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Singleton that keeps record of banned units
 * */
public class Banned_creators extends Users {
    private static volatile Banned_creators instance;
    private static final List<Unit> banned_creators = new ArrayList<>();

    /**
     * Empty constructor, we don't need info
     * */
    private Banned_creators() {}

    /**
     * Get the one instance of this singleton
     * */
    public static Banned_creators getInstance(){
        Banned_creators result = instance;
        if(result == null){
            synchronized (Banned_creators.class) {
                result = instance;
                if(result == null) {
                    instance = result = new Banned_creators();
                }
            }
        }
        return result;
    }

    /**
     * Called when a unit has a reason to be banned
     * @param strikes the amount of chances they have been pardoned previously
     * @param u the unit
     * @return if the unit is banned or pardoned
     * */
    public boolean trigger(Unit u, int strikes){
        if(strikes >= 3) {
            banned_creators.add(u);
            return true;
        }
        return false;
    }

    /**
     * Unban a unit when they make an apology
     * @param u the unit
     * */
    public void unban(Unit u){
        banned_creators.remove(u);
    }

    /**
     * Method that prints all units that have ever been banned
     * */
    public void print_banned(){
        System.out.println("\nAll creators that have been banned:");
        for(Unit unit : banned_creators)
            System.out.println(unit.getType() + " " +unit.getName());
    }

    /**
     * Proxy between users and units, when a user wants to subscribe, this method checks if the unit is banned
     * If so, the subscription does not happen
     * @param u the unit
     * @param v the user wanting to subscribe
     * */
    public void subscribe(Unit u, Users v){
        if(!banned_creators.contains(u)) {
            u.addSubscriber(v);
            u.addObserver(v);
        }
    }

    /**
     * not used for this class
     * */
    @Override
    public void like_dislike(Observable o, Object arg, int random) {}

    /**
     * not used for this class
     * */
    @Override
    public void un_sub(Observable o, Object arg, int random) {}
}
