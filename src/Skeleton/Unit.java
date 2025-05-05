package Skeleton;

import MyClasses.Trending;
import MyClasses.Users;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Abstract class that represents a unit in the simulation.
 * All objects running in the simulation must implement this, or a modified
 * subclass of it.
 * */
public abstract class Unit extends Observable implements Runnable {
	protected String name; // Name of the unit
	protected String type; // Influencer type
	private ArrayList<Users> subcribers = new ArrayList<>(); // list of subscribers
	private int subs;
	protected SimulationInput input; // The input settings
	private Statistics stats;
	protected boolean trending;
	private int trending_threshold;


	public Unit(SimulationInput input, String type) {
		this("DefaultUnitName", input, type);
	}

	/**
	 * General constructor for the Skeleton.Unit.
	 * @param name The name of the unit.
	 * @param input The input settings.
	 * */
	public Unit(String name, SimulationInput input, String type) {
		this.name = name;
		this.input = input;
		this.type = type;
		subs = 0;
		trending = false;
		trending_threshold = 200;

		// Get the statistics object for this Skeleton.Unit
		this.stats = StatisticsContainer.getInstance().addComponent(this.getName());

		// Add a statistic for the number of active units performing an action and other stats
		this.stats.addStatistic("Likes", new WorkerStatistic("Likes"));
		this.stats.addStatistic("Dislikes", new WorkerStatistic("Dislikes"));
		this.stats.addStatistic("NewSubs", new WorkerStatistic("NewSubs"));
		this.stats.addStatistic("UnSubs", new WorkerStatistic("UnSubs"));
	}

	public Statistics getStats() {
		return this.stats;
	}

	/**
	 * getter for the number of subs
	 * @return the number
	 */
	public int getSubs() {
		return subs;
	}

	/**
	 * New subscriber
	 * @param user the user that subbed
	 */
	public void addSubscriber(Users user) {
		subcribers.add(user);
		subs++;
		Statistic sub_stat = this.getStats().getStatistic("NewSubs");
		sub_stat.addValue(1); // Incrementing the number of subscribers by 1
		addObserver(user);
	}

	/**
	 * A user unsubscribes
	 * @param user the user that un subbed
	 */
	public void removeSubscriber(Users user) {
		subcribers.remove(user);
		subs--;
		Statistic unsub_stat = this.getStats().getStatistic("UnSubs");
		unsub_stat.addValue(1); // Incrementing the number of un-subscriptions by 1
		deleteObserver(user);
	}

	/**
	 * A user likes the unit's post
	 */
	public void like(){
		Statistic like_stat = this.getStats().getStatistic("Likes");
		like_stat.addValue(1); // Incrementing the number of likes by 1
	}

	/**
	 * A user dislikes the unit's post
	 */
	public void dislike(){
		Statistic dislike_stat = this.getStats().getStatistic("Dislikes");
		dislike_stat.addValue(1); // Incrementing the number of dislikes by 1
	}


	/**
	 * Has your unit perform a single action. For example:
	 * 		Minning some ore.
	 * 		Depositing some cargo.
	 * 		Drive to a light.
	 * 		Gathering produce from a producer.
	 * 		...
	 * 
	 * Your Skeleton.Unit may do a portion of a larger action as well
	 * at this stage and hold a state of where it is along the path.
	 * 
	 * The unit could also attempt it all at once, but consider
	 * how that will impact drift in the timing of the actions
	 * (actions/second). There is nothing wrong with sleeping
	 * with much longer than the actions/second rate though, and
	 * it's encouraged to do so in some areas of your simulation.
	 * 
	 * */
	public abstract void performAction();

	/**
	 * Submit some statistics to the Skeleton.Statistics object.
	 * 
	 * This should handle sending statistics that don't depend on
	 * what happens in the performAction method. In there, you may
	 * want to also submit statistics (ore mined during action,
	 * kilometers driven, tonnage of cargo deposited, etc.).
	 * 
	 * */
	public abstract void submitStatistics();

	/** Returns the Skeleton.SimulationInput **/
	public SimulationInput getSimInput() {
		return this.input;
	}

	/** Returns the Skeleton.Unit's name. **/
	public String getName() {
		return this.name;
	}

	public String getType() {
		return type;
	}

	public ArrayList<Users> getSubcribers() {
		return subcribers;
	}

	/** Units are equal when their names are equal. **/
	public boolean equals(Object o) {
		return ((Unit) o).getName().equals(this.getName());
	}


	/**
	 * This is the method which runs the Skeleton.Unit for `Time` seconds (specified) in
	 * the input and performs `ActionsPerSecond` actions/second during this time.
	 *
	 * The run method also slighlty handles drift in timing. This means that the
	 * amount of time your methods take will impact the amount of time the Skeleton.Unit
	 * waits until it attempts to perform an action again.
	 *
	 * If you override this method in a subclass, ensure that all of the
	 * above is still implemented.
	 *
	 * The run method performs the following:
	 * 		Add a worker/unit to the stats object.
	 * 		Perform the action.
	 * 		Submit the statistics.
	 * 		Remove the worker/unit from the stats object.
	 * 		Wait until the next action needs to be performed.
	 *
	 * */
	public void run() {
		// Convert the times to MS (using seconds makes the user input nicer)
		long totalTime = this.input.getIntegerInput(type, "Time") * 1000;
		long msPerAction = (long) ((
			1 / (double) this.input.getIntegerInput(type, "ActionsPerSecond")
		) * 1000);

		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < totalTime) {
			long actionStart = System.currentTimeMillis();

			/* Perform whatever this unit needs to do, and submit the statistics
			   to the Skeleton.Statistics singleton container. You can modify this to submit
			   statistics while performing the action. You likely want to do it in
			   your subclass. */

			Trending trend;
			if(subs >= trending_threshold) {
				trend = Trending.getInstance();
				if(trend != null) {
                    try {
                        trend.add_trending(this);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    trending = true;
					trending_threshold *= 2;
				}
			}

			this.performAction();
			this.submitStatistics();

			/*
				Wait until the next action should be performed. The subtraction
				handles drift caused by amount of time the methods you implement
				take. However, this isn't perfect and you'll still find a bit.

				You might find your methods taking too long, then you'll actions
				will be constantly firing with no wait. The drift subtracted below
				could be a good candidate for a statistic as it acts as a proxy
				measurement for the amount of time your Skeleton.Unit takes to perform its
				actions.
			*/
			try {
				TimeUnit.MILLISECONDS.sleep(
					Math.max(
						0,
						msPerAction - (System.currentTimeMillis() - actionStart)
					)
				);
			} catch (InterruptedException e) {
				e.printStackTrace(System.out);
			}

			trending = false;
		}
	}
}
