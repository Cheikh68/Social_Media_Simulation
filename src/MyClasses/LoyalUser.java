package MyClasses;

import Skeleton.Unit;

import java.util.Observable;


/**
 * A type of user
 * */
public class LoyalUser extends Users{

    /**
     * Method for the user to either like, dislike or ignore a post
     * @param random a randomly generated int, used for probability of viewing
     * @param o the observable unit
     * @param arg a specific notification
     * */
    @Override
    public void like_dislike(Observable o, Object arg, int random) {
        if(o instanceof Unit) {
            if (arg == "MEGA CONTROVERSIAL COMMENT")
                ((Unit) o).dislike();

            else if (arg == "Controversial post"){}


            else if (random <= 95)
                ((Unit) o).like();
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
