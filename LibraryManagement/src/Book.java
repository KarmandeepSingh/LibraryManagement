import java.io.Serializable;
import java.time.LocalDate;

/**
 * The purpose of this class is to create a book
 * @author karma
 *
 */
public abstract class Book implements Serializable {

	
	private static final long serialVersionUID = 1L;
	/**Id of the book**/
	protected long bookId;//Id of the book
	/**Genre of the of the book**/
	protected String genre;//Genre of the of the book
	/**Title of the book**/
	protected String title;//Title of the book
	/**Checkout date of the book**/
	protected LocalDate checkoutDate;//Checkout date of the book

	public Book() {

	}

	/**
	 * Prints the header and various details of the books
	 */
	public abstract void printTitle();

	/**
	 * Prints the books
	 */
	public abstract void printBooks();

	/**
	 * Prints the book with checkout date listed
	 */
	public abstract void printBookWithCheckout();

	/**
	 * Registers the checkout date
	 * @param checkoutDate Checkout Date of the book
	 */
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	

	/**
	 * Returns the Checkout date of the book
	 * @return Checkout date of the book
	 */
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

}
