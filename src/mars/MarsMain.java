/**
 * 
 */
package mars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Wafa
 *
 */
public class MarsMain {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		MapMars mapMars;
		Map<Rover, String> mapEntries = new LinkedHashMap<>();
		if(args.length==0) {
			throw new RuntimeException("Please provide the path to a valid input file");
		}
		String path = args[0];
		verifyNumberOfLines(path);
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
			mapMars = initialiseMap(bufferedReader.readLine());
			initialiseRovers(bufferedReader, mapEntries, mapMars);
			runExcutions(mapEntries, mapMars);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// check if the number of lines in the file is upper than 2 and it's impair
	private static void verifyNumberOfLines(String path) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
			long lines = bufferedReader.lines().count();
			if(lines < 3 || lines%2 == 0) {
				throw new RuntimeException("The number of lines of the file must be at least 3 and must be impair");
			}
		} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	/**
	 * Initialising the map with the x and y of the first line of the file
	 * @param line
	 * @return
	 */
	private static MapMars initialiseMap(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, " ");
		return new MapMars(Integer.valueOf(tokenizer.nextToken()), Integer.valueOf(tokenizer.nextToken()));
	}
	

	/**
	 * Extract the values of the rovers from the file and the sequences. The couple Rover, sequence is stored in a map
	 * 
	 * @param bufferedReader
	 * @param mapEntries
	 * @param mapMars
	 */
	private static void initialiseRovers(BufferedReader bufferedReader, Map<Rover, String> mapEntries, MapMars mapMars ) {
		String line1;
		String line2;
		try {
			while((line1 = bufferedReader.readLine()) != null
				&& (line2 = bufferedReader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line1, " ");
				Rover rover = new Rover(Integer.valueOf(tokenizer.nextToken()), Integer.valueOf(tokenizer.nextToken()), OrientationEnum.valueOf(tokenizer.nextToken()));
				mapEntries.put(rover, line2);
				
				mapMars.setRoverList(new ArrayList<Rover>());
				mapMars.getRoverList().add(rover);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * RunExcutions for each Rover in the file
	 * @param mapEntries
	 * @param mapMars
	 */
	private static void runExcutions(Map<Rover, String> mapEntries, MapMars mapMars) {
		for(Map.Entry<Rover, String> entry : mapEntries.entrySet()) {
		    Rover key = entry.getKey();
		    String value = entry.getValue();
		    for(int i = 0; i < value.length(); i++) {
		    	InstructionEnum instruction = InstructionEnum.valueOf(String.valueOf(value.charAt(i)));
		    	key.executeInstruction(instruction, mapMars);
		    }
		    key.printPosition();
		}
	}

}
