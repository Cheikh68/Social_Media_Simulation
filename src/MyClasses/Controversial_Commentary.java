package MyClasses;

import Skeleton.SimulationInput;
import Skeleton.Unit;
import Skeleton.WorkerStatistic;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * one type of unit
 * */
public class Controversial_Commentary extends Unit{
    private int index;
    private String previous_upload;
    private Semaphore semaphore;
    private boolean on_break;
    private int strikes;
    private boolean banned;


    /**
     * Specific constructor for this subunit
     * @param name The name of the unit.
     * @param input The input settings.
     * @param type the type of the unit
     * @param semaphore contains the permit for when reaction should be made
     * */
    public Controversial_Commentary (String name, SimulationInput input, String type, Semaphore semaphore) {
        super(name, input, type);
        this.getStats().addStatistic("VideosUploaded", new WorkerStatistic("VideosUploaded"));
        this.getStats().addStatistic("BreaksTaken", new WorkerStatistic("BreaksTaken"));
        this.type = type;
        this.input = input;
        index = 0;
        this.semaphore = semaphore;
        this.on_break = false;
    }


    /**
     * Specific constructor for this subunit
     * @param input The input settings.
     * @param type the type of the unit
     * @param semaphore contains the permit for when reaction should be made
     * */
    public Controversial_Commentary(SimulationInput input, String type, Semaphore semaphore) {
        super(input, type);
        this.type = type;
        this.input = input;
        index = 0;
        this.semaphore = semaphore;
        this.on_break = false;
    }


    /**
     * specific action of commentator
     * */
    @Override
    public void performAction() {
        if(trending) {
            System.out.println("Controversial commentary channel: " + name + " is trending. To seek more attention, they post: MEGA CONTROVERSIAL COMMENT");
            notifyObservers("MEGA CONTROVERSIAL COMMENT");
            previous_upload = "MEGA CONTROVERSIAL COMMENT";
            strikes++;
            banned = Banned_creators.getInstance().trigger(this, strikes);
            semaphore.release();
        }
        else {
            try {
                List<String> actions = input.getInput(type, "Actions");
                if(index >= actions.size())
                    index = 0;
                String actionToPerform = actions.get(index);
                System.out.println("Controversial commentary channel " + name + " posted: " + actionToPerform);
                setChanged();
                notifyObservers(actionToPerform);
                semaphore.release();
                previous_upload = actionToPerform;
                index++;
            } catch (RuntimeException e) {
                System.out.println("Error retrieving actions: " + e.getMessage());
            }
        }
    }


    /**
     * specific statistics submission of commentator
     * */
    @Override
    public void submitStatistics() {
        this.getStats().getStatistic("VideosUploaded").addValue(1);
        if(on_break){
            this.getStats().getStatistic("BreaksTaken").addValue(1);
            on_break = false;
        }
    }


    /**
     * specific start off actions of commentator
     * */
    @Override
    public void run() {
        if (Objects.equals(previous_upload, "Apology video (with tears)")) {
            try {
                Thread.sleep(4000);
                Banned_creators.getInstance().unban(this);
                banned = false;
                on_break = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyObservers("Taking a break");
        }
        super.run();
    }
}
