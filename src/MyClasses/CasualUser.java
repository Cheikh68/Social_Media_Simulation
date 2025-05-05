package MyClasses;

import Skeleton.Unit;

import java.util.Observable;


/**
 * A type of user
 * */
public class CasualUser extends Users{

    /**
     * Method for the user to either view or not the post of the unit
     * @param random a randomly generated int, used for probability of viewing
     * @return viewed or not
     * */
    @Override
    public boolean view(int random) {
        return random <= 50;     // 50% chance they will view the content
    }

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
                un_sub(o, arg, random);

            else if (arg == "Controversial post")
                ((Unit) o).dislike();

            else if(arg == "Travel vlog")
                for(int i=0; i<5; i++)
                    (createNewInstance()).subscribe((Unit)o);

            else if (random <= 50)
                ((Unit) o).like();
        }
    }
}
