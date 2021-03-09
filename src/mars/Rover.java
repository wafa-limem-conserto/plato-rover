/**
 * 
 */
package mars;

/**
 * @author Wafa
 *
 */
public class Rover {
	
	private int x;
	private int y;
	private OrientationEnum orientation;
	
	public Rover(int x, int y, OrientationEnum orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	
	/**
	 * Function that moves the rover based on it's orientation
	 * @param mapMars
	 */
	private void move(MapMars mapMars) {
		int[] roverPosition = this.getPosition();
		switch (orientation) {
		case N : 
			roverPosition[1]++;
			verifyNextMove(roverPosition, mapMars);
			this.setPosition(roverPosition);
			break;
		case S : 
			roverPosition[1]--;
			verifyNextMove(roverPosition, mapMars);
			this.setPosition(roverPosition);
			break;
		case E : 
			roverPosition[0]++;
			verifyNextMove(roverPosition, mapMars);
			this.setPosition(roverPosition);
			break;
		case W : 
			roverPosition[0]--;
			verifyNextMove(roverPosition, mapMars);
			this.setPosition(roverPosition);
			break;
		}
			
	}
	
	/**
	 * Verify that the next move won't cause a damage to the rover
	 * @param nextPosition
	 * @param mapMars
	 */
	private void verifyNextMove(int[] nextPosition, MapMars mapMars) {
		if(nextPosition[0] > mapMars.getX() || nextPosition[0] < 0 || nextPosition[1] > mapMars.getY()|| nextPosition[1] < 0) {
			throw new RuntimeException("The Rover must not quit the area");
		}
		boolean samePosition = mapMars.getRoverList().stream() //
				.map(Rover::getPosition) //
				.anyMatch(position -> position[0] == nextPosition[0] && position[1] == nextPosition[1] );
		if(samePosition) {
			throw new RuntimeException("The Rover must not take the place of another rover");
		}
	}
	
	/**
	 * Execute the instruction. If it's a move 
	 * than the function move is called 
	 * otherwise the function computeOrientation is called.
	 * 
	 * @param instruction
	 * @param mapMars
	 */
	public void executeInstruction(InstructionEnum instruction, MapMars mapMars) {
		if(instruction.equals(InstructionEnum.M)) {
			move(mapMars);
		} else {
			computeOrientation(instruction);
		}
	}
	
	/**
	 * Compute the orientation of the rover based on the instruction
	 * @param instruction
	 */
	private void computeOrientation(InstructionEnum instruction) {
		if(instruction.equals(InstructionEnum.L)) {
			this.setOrientation(orientation.getLeftOrientation());
		} else {
			this.setOrientation(orientation.getRightOrientation());
		}
		
	}
	
	/**
	 * Print the postion of the Rover
	 */
	public void printPosition() {
		System.out.println( x + " " + y + " " + orientation);
	}
	
	/**
	/**Getters and Setters**
	*/
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public OrientationEnum getOrientation() {
		return orientation;
	}

	private void setOrientation(OrientationEnum orientation) {
		this.orientation = orientation;
	}
	
	private int[] getPosition() {
		return new int[] {getX(), getY()};
	}
	
	private void setPosition(int[] position) {
		this.x = position[0];
		this.y = position[1];
	}
	

}
