package exceptions;

public class NotFoundException extends Exception {
	private String object;
	private int id;
	private String input;
	
	/**
	 * Constructor for the NotFoundException class
	 * @param object - name of the object. Example: City, Product
	 * @param id - id of the object
	 */
	public NotFoundException(String object, int id) {
		this.object = object;
        this.id = id;
	}

	/**
	 * Constructor for the NotFoundException class
	 * @param object - name of the object. Example: City, Product
	 * @param id - id of the object
	 */
	public NotFoundException(String object, String input) {
		this.object = object;
        this.input = input;
	}

	/**
	 * Exception message
	 */
	public String getMessage() {
		if(input == null) {return object + " with id: " + id + " was not found.";}
		else {return object + " with email: " + input + " was not found.";}
	}

}