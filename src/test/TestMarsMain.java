package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mars.MarsMain;

public class TestMarsMain {
	
	private static final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void mainNominalWhenInvokePrintlnThenOutputCaptorSuccess() {
	    
	    //When
	    MarsMain.main(new String[] {"c:\\bnp\\input_ok.txt"});
	      
	   //Then 
	    Assert.assertEquals("1 3 N\r\n" + 
	    		"5 1 E", outputStreamCaptor.toString()
	      .trim());
	}
	
	@Test
	void mainWhenTheRoverQuitArea() {
		String path = "src/test/resources/input_out_of_map.txt";
		
	    Throwable exception = assertThrows(RuntimeException.class, () -> MarsMain.main(new String[] {getAbsolutePathFromPath(path)}));
	    assertEquals("The Rover must not quit the area", exception.getMessage());
	}
	
	@Test
	void mainWhenTheRoverTakePlaceOfAnotherRover() {
		String path = "src/test/resources/input_crash.txt";
	    Throwable exception = assertThrows(RuntimeException.class, () -> MarsMain.main(new String[] {getAbsolutePathFromPath(path)}));
	    assertEquals("The Rover must not take the place of another rover", exception.getMessage());
	}
	
	@Test
	void mainWhenTheNumberOfLinesIsWrong() {

		String path = "src/test/resources/input_wrong_number_of_lines.txt";

	    Throwable exception = assertThrows(RuntimeException.class, () -> MarsMain.main(new String[] {getAbsolutePathFromPath(path)}));
	    assertEquals("The number of lines of the file must be at least 3 and must be impair", exception.getMessage());
	}
	
	/**
	 * @param path
	 * @return
	 */
	private String getAbsolutePathFromPath(String path) {
		File file = new File(path);
		return file.getAbsolutePath();
	}
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	}

}
