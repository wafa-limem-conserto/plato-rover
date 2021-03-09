/**
 * 
 */
package mars;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Wafa
 *
 */
public enum OrientationEnum {
	
	N('N', 360), // North
	E('E', 90), //Est
	S('S', 180), //South
	W('W', 270); // Ouest
	
	private char orientation;
	private int modulo;


	private OrientationEnum(char orientation, int modulo) {
		this.orientation = orientation;
		this.modulo = modulo;
	}
	
	public OrientationEnum getLeftOrientation() {
		int newModulo = (this.getModulo() - 90); 
		return getByModulo(newModulo);
	}
	
	public OrientationEnum getRightOrientation() {
		int newModulo = (this.getModulo() + 90); 
		return getByModulo(newModulo);
	}
	
	public char getOrientation() {
		return orientation;
	}
	
	public int getModulo() {
		return modulo;
	}
	
	public OrientationEnum getByModulo(int modulo) {
		List<OrientationEnum> orientationEnumList = Arrays.asList(OrientationEnum.values());
		Optional<OrientationEnum> optionalOrientationEnum = orientationEnumList.stream() //
				.filter(orientationEnum -> orientationEnum.getModulo() % 360 == modulo % 360) //
				.findFirst();
		return optionalOrientationEnum.orElseThrow(()->new RuntimeException("No orientation for this modulo : " + modulo));
	}
}
