package MyClasses;

import Skeleton.SimulationInput;
import Skeleton.Unit;
import Skeleton.WorkerStatistic;

import java.util.List;
import java.util.Random;


/**
 * A type of unit
 * */
public class Vlogger extends Unit {
    private boolean traveled;


    /**
     * Specific constructor for this subunit
     * @param name The name of the unit.
     * @param input The input settings.
     * @param type the type of the unit
     * */
    public Vlogger(String name, SimulationInput input, String type) {
        super(name, input, type);
        this.getStats().addStatistic("VideosUploaded", new WorkerStatistic("VideosUploaded"));
        this.getStats().addStatistic("BreaksTaken", new WorkerStatistic("BreaksTaken"));
        this.getStats().addStatistic("Travels", new WorkerStatistic("Travels"));
        this.type = type;
        this.input = input;
        traveled = false;
    }

    /**
     * Another specific constructor for this sub-unit
     * @param input The input settings.
     * @param type the type of the unit
     * */
    public Vlogger(SimulationInput input, String type) {
        super(input, type);
        this.type = type;
        this.input = input;
        traveled = false;
    }


    /**
     * specific action of vlogger
     * */
    @Override
    public void performAction() {
        if(trending)
            System.out.println(name + " says: THANK YOU FOR THE SUPPORT!");

        else if(traveled) {
            System.out.println("Vlogger " + name + " posted: " + "Travel vlog");
            notifyObservers("Travel vlog");
        }

        else {
            try {
                Random random = new Random();
                List<String> actions = input.getInput(type, "Actions");
                String actionToPerform = actions.get(random.nextInt(actions.size())); // Random action
                System.out.println("Vlogger " + name + " posted: " + actionToPerform);
                setChanged();
                notifyObservers();
            } catch (RuntimeException e) {
                System.out.println("Error retrieving actions: " + e.getMessage());
            }
        }
    }

    /**
     * specific statistics submission of vlogger
     * */
    @Override
    public void submitStatistics() {
        this.getStats().getStatistic("VideosUploaded").addValue(1);
        if(traveled) {
            this.getStats().getStatistic("Travels").addValue(1);
            traveled = false;
        }
    }

    /**
     * specific start off actions of vlogger
     * */
    @Override
    public void run() {
        Random rand = new Random();
        if (rand.nextInt(10) >= 7) {  // There's a 1 in 10 chance to take a break
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyObservers("Taking a break");
            this.getStats().getStatistic("BreaksTaken").addValue(1);
        }

        if (rand.nextInt(10) >= 8){
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            traveled = true;
            notifyObservers("Traveling");
        }

        super.run();
    }
}
