/*
 * @author Rowena
 * This class implements a dictionary using a hash table in which collisions are resolved using separate chaining.
 * The hash table will store objects of the class Data.
 */

public class Dictionary {
	
	private Node[] dict;
	int count;
	int dictSize;
	
	public Dictionary(int size) { 	// constructor
		this.dict = new Node[size];
		this.count = 0;
		this.dictSize = size;
	}
	
	// hashcode: converts the key of the record into an integer
	private int hash(String key) { 
		
		int x = 39;
		int keyLength = key.length();
		
		int sum = 0;
		
		char c = key.charAt(keyLength-1);
		int cInt = (int)c;
		sum = cInt % dictSize;
		for(int i = keyLength-2; i >= 0; i--) {
			char c2 = key.charAt(i);
			int c2Int = (int)c2;
			sum = (sum * x + c2Int) % dictSize;
		}
		return sum;
	}
	
	// puts record into dictionary using hash
	// returns 1 and throws exception if there is a collision, otherwise returns 0
	public int put(Data record) throws DuplicatedKeyException {
		
		int result = 0;
		String key = record.getKey(); // gets the string key of record
		int hashValue = hash(key); // converts string key into an integer
		
		Node<Data> newNode = new Node(record); // creates a new node that stores record
		
		if(dict[hashValue] == null) { // if dict at hashValue is empty, point to newNode
			dict[hashValue] = newNode;
			count++;
		}
		else { // if dict at hashValue is not empty, add newNode to the end of the list
			Node<Data> current; // points to the current/rear of the list
			current = dict[hashValue];
			if(current.getData().getKey().equals(key)) {
				throw new DuplicatedKeyException("duplicate key");
			}
			while(current.getNext() != null) { // current starts at the front and moves to the end node
				current = current.getNext();
			}
			current.setNext(newNode);
			newNode.setPrev(current);
			current = newNode;
			count++;
			result = 1;
		}
		return result;
		
	}
	
	// removes data (referenced by key) stored in dict
	// throws exception if key does not exist
	public void remove(String key) throws InexistentKeyException { 
		
		int hashValue = hash(key);
		
		if(dict[hashValue] != null) {
			Node<Data> current; // points to the current/rear of the list
			current = dict[hashValue];
			// while the key inside current is not equal to param key and we have not reached the end, get next
			while(!current.getData().getKey().equals(key) && current.getNext() != null) { 
				current = current.getNext();
			}
			if(current.getData().getKey().equals(key)) { // if the keys match, remove
				if(current.getPrev() != null && current.getNext() != null) {
					current.getPrev().setNext(current.getNext()); // gets the node before current and sets the next to current's next
					current.getNext().setPrev(current.getPrev()); // sets the prev of next to current's prev
				}
				current = null;
				count--;
			}
			else {
				throw new InexistentKeyException("inexistent key"); // throws exception if key is not found
			}
		}
		else {
			throw new InexistentKeyException("inexistent key");
		}
	}
	
	// returns data stored in dict referenced by key
	public Data get(String key) {
		
		Data result;
		
		int hashValue = hash(key);
		
		if(dict[hashValue] != null) {
			Node<Data> current; // points to the current/rear of the list
			current = dict[hashValue];
			// while the key inside current is not equal to param key and we have not reached the end, get next
			while(current.getNext() != null && !current.getData().getKey().equals(key)) { 
				current = current.getNext();
			}
			if(current.getData().getKey().equals(key)) { // if the keys match, return data
				result = current.getData();
			}
			else { // returns null if key is not found
				result = null;
			}
		}
		else {
			result = null; // returns null if dict[hashValue] is empty
		}	
		return result;
	}
	
	// returns number of data items in dict
	public int numDataItems() {
		return count;
	}
}