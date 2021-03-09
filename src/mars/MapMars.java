/**
 * 
 */
package mars;

import java.util.List;

/**
 * @author Wafa
 *
 */
public class MapMars {
	
	private int x;
	private int y;
	List<Rover> roverList;
	
	public MapMars(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public List<Rover> getRoverList() {
		return roverList;
	}

	public void setRoverList(List<Rover> roverList) {
		this.roverList = roverList;
	}
	
	
	
	

}
