package exceptions;

public class NotFoundException extends Exception {
	private String object;
	private int id;
	
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
	 * Exception message
	 */
	public String getMessage() {
		return object + " with id: " + id + " was not found.";
	}

}