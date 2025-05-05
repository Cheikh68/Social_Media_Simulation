package Skeleton;

import java.lang.RuntimeException;
import java.util.*;


/**
 * This class helps you retrieve values from the given input.
 * */
public class SimulationInput {
	// The inner implementation of the input
	private Map<String, Map<String, List<String>>> input;

	/**
	 * Default constructor that initializes the input map.
	 * Mimics the original style by allowing a default setup.
	 */
	public SimulationInput() {
		this(new HashMap<>());
	}

	/**
	 * Primary constructor that accepts a predefined map configuration.
	 * This constructor allows more controlled setup, useful for testing or predefined configurations.
	 *
	 * @param input The input in the form of a map of maps, with strings mapping to list of strings.
	 */
	public SimulationInput(Map<String, Map<String, List<String>>> input) {
		this.input = input;
	}

	/**
	 * Use to set the input array to something else (complete overwrite).
	 *
	 * @param input The input in the form of an array of string arrays.
	 * */
	public void setInputArray(Map<String, Map<String, List<String>>> input) {
		this.input = input;
	}

	/**
	 * Add a new input key/value pairing.
	 *
	 * @param key The name of the input (used for searching).
	 * @param value The value of the input.
	 * */
	public void addInput(String type, String key, List<String> value) {
		input.computeIfAbsent(type, k -> new HashMap<>()).put(key, new ArrayList<>(value));
	}

	/**
	 * Return the first value in the input converted to an Integer.
	 * s
	 * @param key The key to search for.
	 * */
	public Integer getIntegerInput(String type, String key) {
		return Integer.valueOf(this.getInput(type, key).get(0));
	}

	/**
	 * Return the values in the input.
	 * 
	 * @param key The key to search for.
	 * */
	public List<String> getInput(String type, String key) {
		if (!input.containsKey(type)) {
			throw new RuntimeException("Configuration type not found: " + type);
		}
		Map<String, List<String>> typeConfig = input.get(type);
		if (!typeConfig.containsKey(key)) {
			throw new RuntimeException("Configuration key not found for type " + type + ": " + key);
		}
		return new ArrayList<>(typeConfig.get(key));
	}

	/**
	 * Searches for a given key in the input array.
	 * 
	 * @param key The input key to search for (the first value).
	 * @return An array of values found at the key.
	 * @throws RuntimeException Throws this failure when we can't
	 * 		   find the input key. This should fail the entire
	 * 		   simulation.
	 **/
	private List<String> findKey(String type, String key) {
		if (input.containsKey(type)) {
			Map<String, List<String>> typeConfig = input.get(type);
			if (typeConfig.containsKey(key)) {
				return typeConfig.get(key);
			}
		}
		return null;  // key not found
	}
}
