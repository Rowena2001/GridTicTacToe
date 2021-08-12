/*
 * @author Rowena
 * This class implements the inexistent key exception.
 * This is invoked when the key does not exist in the dictionary when the remove method is called.
 */

public class InexistentKeyException extends Exception {
	
	public InexistentKeyException (String message) {
		super (message);
	}
	
}
