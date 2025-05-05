package MyClasses;

import Skeleton.SimulationInput;
import Skeleton.Unit;
import Skeleton.WorkerStatistic;

import java.util.List;
import java.util.Random;

/**
 * A type of unit
 * */
public class Gamer extends Unit {
    private boolean on_break;

    /**
     * Specific constructor for this subunit
     * @param name The name of the unit.
     * @param input The input settings.
     * @param type the type of the unit
     * */
    public Gamer(String name, SimulationInput input, String type) {
        super(name, input, type);
        this.getStats().addStatistic("VideosUploaded", new WorkerStatistic("VideosUploaded"));
        this.getStats().addStatistic("BreaksTaken", new WorkerStatistic("BreaksTaken"));
        this.type = type;
        this.input = input;
        this.on_break = false;
    }

    /**
     * Another specific constructor for this sub-unit
     * @param input The input settings.
     * @param type the type of the unit
     * */
    public Gamer(SimulationInput input, String type) {
        super(input, type);
        this.type = type;
        this.input = input;
        this.on_break = false;
    }


    /**
     * specific action of gamer
     * */
    @Override
    public void performAction() {
        Random random = new Random();
        if(trending) {
            System.out.println("Gamer " + name + " DOES A GIVEAWAY!! Winner: " + getSubcribers().get(random.nextInt(getSubcribers().size())));
            notifyObservers("Giveaway");
        }
        else {
            try {
                List<String> actions = input.getInput(type, "Actions");
                String actionToPerform = actions.get(random.nextInt(actions.size())); // Random action
                System.out.println("Gamer " + name + " posted: " + actionToPerform);
                setChanged();
                notifyObservers();
            } catch (RuntimeException e) {
                System.out.println("Error retrieving actions: " + e.getMessage());
            }
        }
    }

    /**
     * specific statistics submission of gamer
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
     * specific start off actions of gamer
     * */
    @Override
    public void run() {
        Random rand = new Random();
        if (rand.nextInt(10) == 0) {  // There's a 1 in 10 chance to take a break
            try {
                Thread.sleep(4000);
                on_break = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyObservers("Taking a break");
        }
        super.run();
    }
}
