package MyClasses;

import Skeleton.Unit;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;


/**
 * Users superclass
 * */
public abstract class Users implements Observer {

    /**
     * Empty constructor, we don't need user info
     * */
    Users(){}

    /**
     * method that subscribes this user to a unit
     * @param u the unit we are subscribing to
     * */
    public void subscribe(Unit u){
        Banned_creators.getInstance().subscribe(u, this);
    }

    /**
     * Helper method that creates an instance of this user
     * @return the instance
     * */
    public Users createNewInstance() {
        try {
            return this.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create a new instance", e);
        }
    }

    /**
     * Template method pattern: when users (observers/subscribers) are notified of upload, do the actions
     * @param arg a specific notification
     * @param o the observable unit
     * */
    @Override
    public void update(Observable o, Object arg) {
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        boolean viewed = view(random_number);
        if(viewed){
            like_dislike(o, arg, random_number);
            share(o, arg, random_number);
        }
    }

    /**
     * Method for the user to either view or not the post of the unit
     * @param random a randomly generated int, used for probability of viewing
     * @return viewed or not
     * */
    public boolean view(int random) {
        return random <= 95;     // 95% chance they will view the content
    }

    /**
     * Method for the user to either like, dislike or ignore a post
     * @param random a randomly generated int, used for probability of viewing
     * @param o the observable unit
     * @param arg a specific notification
     * */
    public abstract void like_dislike(Observable o, Object arg, int random);

    /**
     * Unsubscribing method, only used by one subclass
     * @param random a randomly generated int, used for probability of viewing
     * @param o the observable unit
     * @param arg a specific notification
     * */
    public void un_sub(Observable o, Object arg, int random) {
        ((Unit) o).removeSubscriber(this);
    }

    /**
     * Sharing (creating new subs), common to all subclasses. The new instances will be of the same type
     * As the subclass that calls this
     * @param random a randomly generated int, used for probability of viewing
     * @param o the observable unit
     * @param arg a specific notification
     * */
    public void share(Observable o, Object arg, int random) {
        if(random <= 15)
            if(o instanceof Unit)
                (createNewInstance()).subscribe((Unit)o);
        if(o instanceof Gamer)
            if(arg == "Giveaway")
                for(int i=0; i<10; i++)
                    (new CasualUser()).subscribe((Unit)o);
    }
}
