import MyClasses.*;
import Skeleton.SimulationInput;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * The class that is responsible for running the simulation.
 * You will need to modify the run method to initialize, and run all of your units.
 * */
public class Matrix {
	public static void run(SimulationInput input){

		// TEST 1 and 2
		/*
		Gamer g1 = new Gamer("Joe", input, "Gamer");
		for(int i=0; i<33; i++)
			(new HaterUser()).subscribe(g1);
		for(int i=0; i<33; i++)
			(new CasualUser()).subscribe(g1);
		for(int i=0; i<33; i++)
			(new LoyalUser()).subscribe(g1);
		Thread tjoe = new Thread(g1);
		tjoe.start();

		Semaphore semaphore = new Semaphore(0);
		Controversial_Commentary c1 = new Controversial_Commentary("Eddy", input, "Controversial Commentary", semaphore);
		for(int i=0; i<33; i++)
			(new HaterUser()).subscribe(c1);
		for(int i=0; i<33; i++)
			(new CasualUser()).subscribe(c1);
		for(int i=0; i<33; i++)
			(new LoyalUser()).subscribe(c1);
		Thread teddy = new Thread(c1);
		teddy.start();

		Reaction_channel r1 = new Reaction_channel("Bob", input, "Reaction channel" ,semaphore, "Eddy");
		for(int i=0; i<33; i++)
			(new HaterUser()).subscribe(r1);
		for(int i=0; i<33; i++)
			(new CasualUser()).subscribe(r1);
		for(int i=0; i<33; i++)
			(new LoyalUser()).subscribe(r1);
		Thread tbob = new Thread(r1);
		tbob.start();

		Vlogger v1 = new Vlogger("Eva", input, "Vlogger");
		for(int i=0; i<33; i++)
			(new HaterUser()).subscribe(v1);
		for(int i=0; i<33; i++)
			(new CasualUser()).subscribe(v1);
		for(int i=0; i<33; i++)
			(new LoyalUser()).subscribe(v1);
		Thread teva = new Thread(v1);
		teva.start();

		try {
			teddy.join();
			tjoe.join();
			tbob.join();
			teva.join();
		} catch (InterruptedException e) {}
		 */



		// TEST 3
		/*
		Gamer g1 = new Gamer("Joe", input, "Gamer");
		(new HaterUser()).subscribe(g1);
		(new CasualUser()).subscribe(g1);
		(new LoyalUser()).subscribe(g1);
		Thread tjoe = new Thread(g1);
		tjoe.start();

		Semaphore semaphore = new Semaphore(0);
		Controversial_Commentary c1 = new Controversial_Commentary("Eddy", input, "Controversial Commentary", semaphore);
		(new HaterUser()).subscribe(c1);
		(new CasualUser()).subscribe(c1);
		(new LoyalUser()).subscribe(c1);
		Thread teddy = new Thread(c1);
		teddy.start();

		Reaction_channel r1 = new Reaction_channel("Bob", input, "Reaction channel" ,semaphore, "Eddy");
		(new HaterUser()).subscribe(r1);
		(new CasualUser()).subscribe(r1);
		(new LoyalUser()).subscribe(r1);
		Thread tbob = new Thread(r1);
		tbob.start();

		Vlogger v1 = new Vlogger("Eva", input, "Vlogger");
		(new HaterUser()).subscribe(v1);
		(new CasualUser()).subscribe(v1);
		(new LoyalUser()).subscribe(v1);
		Thread teva = new Thread(v1);
		teva.start();

		try {
			teddy.join();
			tjoe.join();
			tbob.join();
			teva.join();
		} catch (InterruptedException e) {}
		*/

	}
}
