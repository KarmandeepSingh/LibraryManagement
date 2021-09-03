/**
 * Defines various policies of the library
 * @author Karmandeep Singh
 *
 */
public interface Policies {

	/**Fine for the user**/
	final public static int FINE =1;//Fine for the user
	/**Time in days allowed for the user to borrow books**/
	final public static int BORROW_TIME=14;//Time in days allowed for the user to borrow books
	/**Maximum books allowed for the user to borrow**/
	final public static int MAX_BOOKS=5;//Maximum books allowed for the user to borrow
	
	/**
	 * Calculates the fine 
	 */
	public void calculateFine();
	
}
