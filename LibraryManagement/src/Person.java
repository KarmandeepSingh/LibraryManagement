import java.io.Serializable;
/**
 * The purpose of this class is to create a person
 * @author karma
 *
 */
public abstract class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The first name of the person **/
	protected String firstName; // stores the first name of the person

	/** The last name of the person **/
	protected String lastName; // stores the last name of the person

	/** The email of the person **/
	protected String emailId; // stores the email of the person

	/** The phone number of the person **/
	protected long phoneNumber; // stores the phone number of the person

	/**
	 * No arg constructor for the person class
	 * 
	 */

	// no arg constructor
	Person() {

	}

	/**
	 * Parameterized constructor for the person class
	 * 
	 * @param fName First Name of the person
	 * @param lName The last name of the person
	 * @param email The email of the person
	 * @param ph    The phone number of the person
	 */

	// parameterized constructor
	Person(String fName, String lName, String email, long ph) {
		this.firstName = fName;
		this.lastName = lName;

		this.emailId = email;
		this.phoneNumber = ph;

	}

	/**
	 * Reads Person details
	 * @param input Reads input from the user
	 */
	public abstract void readPerson(Input input);



}
