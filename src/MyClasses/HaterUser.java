package MyClasses;

import Skeleton.Unit;

import java.util.Observable;


/**
 * A type of user
 * */
public class HaterUser extends Users{

    /**
     * Method for the user to either like, dislike or ignore a post
     * @param random a randomly generated int, used for probability of viewing
     * @param o the observable unit
     * @param arg a specific notification
     * */
    @Override
    public void like_dislike(Observable o, Object arg, int random) {
        if(o instanceof Unit) {
            ((Unit) o).dislike();
            if (arg == "MEGA CONTROVERSIAL COMMENT")
                for(int i=0; i<10; i++)
                    (new HaterUser()).subscribe((Unit)o);
            else if (arg == "Controversial post")
                for(int i=0; i<5; i++)
                    (new HaterUser()).subscribe((Unit)o);
        }
    }

    /**
     * Unsubscribe method, never used for this sub class
     * @param random a randomly generated int, used for probability of viewing
     * @param o the observable unit
     * @param arg a specific notification
     * */
    @Override
    public void un_sub(Observable o, Object arg, int random) {}
}
