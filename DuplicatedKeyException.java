/*
 * @author Rowena
 * This class implements the duplicated key exception.
 * This is invoked when the key already exists within the dictionary when the put method is called.
 */

public class DuplicatedKeyException extends Exception {
	
	public DuplicatedKeyException (String message){
		super (message);
	}
	
}
