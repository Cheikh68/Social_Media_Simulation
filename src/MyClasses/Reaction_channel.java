package MyClasses;

import Skeleton.SimulationInput;
import Skeleton.Unit;
import Skeleton.WorkerStatistic;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * one type of unit
 * */
public class Reaction_channel extends Unit {
    private Semaphore semaphore;
    private String react_name;
    private boolean available_content;


    /**
     * Specific constructor for this subunit
     * @param name The name of the unit.
     * @param input The input settings.
     * @param type the type of the unit
     * @param react_name the creator this channel reacts to
     * @param semaphore contains the permit for when reaction should be made
     * */
    public Reaction_channel(String name, SimulationInput input, String type, Semaphore semaphore, String react_name) {
        super(name, input, type);
        this.getStats().addStatistic("VideosUploaded", new WorkerStatistic("VideosUploaded"));
        this.type = type;
        this.input = input;
        this.semaphore = semaphore;
        this.react_name = react_name;
        this.available_content = false;
    }


    /**
     * Specific constructor for this subunit
     * @param input The input settings.
     * @param type the type of the unit
     * @param react_name the creator this channel reacts to
     * @param semaphore contains the permit for when reaction should be made
     * */
    public Reaction_channel(SimulationInput input, String type, Semaphore semaphore, String react_name) {
        super(input, type);
        this.type = type;
        this.input = input;
        this.semaphore = semaphore;
        this.react_name = react_name;
        this.available_content = false;
    }


    /**
     * specific action of reactor
     * */
    @Override
    public void performAction() {
        if(trending)
            System.out.println("Reactor " + name + " is trending !! They say: THANK YOU FOR THE SUPPORT!");

        else{
            try {
                if(semaphore.tryAcquire(this.input.getIntegerInput(type, "Time"), TimeUnit.MILLISECONDS)) {
                    available_content = true;
                    try {
                        System.out.println("Reactor " + name + " posted: Reacting to " + react_name + "'s last upload");
                        setChanged();
                        notifyObservers();
                    } catch (RuntimeException e) {
                        System.out.println("Error retrieving actions: " + e.getMessage());
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * specific statistics submission of reactor
     * */
    @Override
    public void submitStatistics() {
        if(available_content) {
            this.getStats().getStatistic("VideosUploaded").addValue(1);
            available_content = false;
        }
    }
}
