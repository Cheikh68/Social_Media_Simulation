/**
 * Name: Cheikh Saad Bou Sall
 * Date: April 25th, 2024
 * Description: Final project
 * Social media simulation
 */

import MyClasses.Banned_creators;
import MyClasses.Trending;
import Skeleton.SimulationInput;
import Skeleton.StatisticsContainer;
import java.util.List;
import java.util.Map;


/**
 * The main class is responsible for the testing. It has a helper method
 * that makes it easier to run many tests.
 **/
public class Main {

	/**
	 * Runs a test with the given input and returns the statistics
	 * produced from the test run. Simplifies the testing process.
	 * @param input The input to run the test with.
	 * @return The statistics of the test run.
	 **/
	public static StatisticsContainer runTest(SimulationInput input) {
		// Initialize the stats singleton here so the input can
		// be ignored in future calls
		StatisticsContainer stats = StatisticsContainer.getInstance(input);
		Matrix.run(input);
		return stats;
	}


	/**
	 * See method above for details.
	 **/
	public static StatisticsContainer runTest(Map<String, Map<String, List<String>>> input) {
		return runTest(new SimulationInput(input));
	}

	public static void main(String[] args) {

		SimulationInput si = new SimulationInput();

		// TESTING
		// Un-comment each portion here and also in the matrix class so that it can run separately


		// TEST 1
		/*
		si.addInput("Gamer", "Time", List.of("10"));
		si.addInput("Gamer", "ActionsPerSecond", List.of("1"));
		si.addInput("Gamer", "Actions", List.of("post video", "live stream", "post picture", "gaming event", "late night stream"));

		si.addInput("Vlogger", "Time", List.of("10"));
		si.addInput("Vlogger", "ActionsPerSecond", List.of("1"));
		si.addInput("Vlogger", "Actions", List.of("home vlog", "event vlog", "small trip", "restaurant vlog"));

		si.addInput("Controversial Commentary", "Time", List.of("10"));
		si.addInput("Controversial Commentary", "ActionsPerSecond", List.of("1"));
		si.addInput("Controversial Commentary", "Actions", List.of("hot topics", "Controversial post", "Random post", "Controversial post", "random post", "Apology video (with tears)"));

		si.addInput("Reaction channel", "Time", List.of("10"));
		si.addInput("Reaction channel", "ActionsPerSecond", List.of("1"));
		*/



		// TEST 2
		/*
		si.addInput("Gamer", "Time", List.of("10"));
		si.addInput("Gamer", "ActionsPerSecond", List.of("1"));
		si.addInput("Gamer", "Actions", List.of("post video", "live stream", "post picture", "gaming event", "late night stream"));

		si.addInput("Vlogger", "Time", List.of("10"));
		si.addInput("Vlogger", "ActionsPerSecond", List.of("1"));
		si.addInput("Vlogger", "Actions", List.of("home vlog", "event vlog", "small trip", "restaurant vlog"));

		si.addInput("Controversial Commentary", "Time", List.of("10"));
		si.addInput("Controversial Commentary", "ActionsPerSecond", List.of("1"));
		si.addInput("Controversial Commentary", "Actions", List.of("hot topics", "Controversial post", "Random post", "Controversial post", "random post", "Apology video (with tears)"));

		si.addInput("Reaction channel", "Time", List.of("20"));
		si.addInput("Reaction channel", "ActionsPerSecond", List.of("1"));
		*/


		/*
		// TEST 3
		si.addInput("Gamer", "Time", List.of("10"));
		si.addInput("Gamer", "ActionsPerSecond", List.of("1"));
		si.addInput("Gamer", "Actions", List.of("post video", "live stream", "post picture", "gaming event", "late night stream"));

		si.addInput("Vlogger", "Time", List.of("10"));
		si.addInput("Vlogger", "ActionsPerSecond", List.of("1"));
		si.addInput("Vlogger", "Actions", List.of("home vlog", "event vlog", "small trip", "restaurant vlog"));

		si.addInput("Controversial Commentary", "Time", List.of("10"));
		si.addInput("Controversial Commentary", "ActionsPerSecond", List.of("1"));
		si.addInput("Controversial Commentary", "Actions", List.of("Controversial post","Controversial post","Controversial post","Controversial post","Controversial post","Controversial post", "Apology video (with tears)"));

		si.addInput("Reaction channel", "Time", List.of("10"));
		si.addInput("Reaction channel", "ActionsPerSecond", List.of("1"));
		*/



		// KEEP FOR ALL TESTS
		StatisticsContainer stats = runTest(si);
		System.out.println("\n");
		stats.printStatisticsContainer();
		Trending.getInstance().print_trending();
		Banned_creators.getInstance().print_banned();

	}
}
