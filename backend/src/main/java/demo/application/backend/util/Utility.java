package demo.application.backend.util;

/**
 * @author H.Tabei
 */
public class Utility {

	/**
	 * The main goal of this method is to insert 0 'pad' to the left of 'number' until it's size reaches to 'length'.
	 * 
	 * @param number a number which is to be left-padded.
	 * @param length final length of output string.
	 * @return String left padded number
	 */
	public static String zeroPadLeft(long number, int length) {
	    return String.format("%0"+length+"d", number);
	}
}
