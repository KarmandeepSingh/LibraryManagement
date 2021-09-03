import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/*
 * Student Name: Karmandeep Singh
 * Lab Professor Name: Karan Kalsi
 * Lab Section Number: 301
 * Due Date: Sunday August 8, 2021
*/
/**
 * The purpose of this class is to create a user and further extend it to
 * customer and staff
 * 
 * @author Karmadeep Singh
 *
 */
public class User extends Person implements Serializable, Policies {
	private static final long serialVersionUID = 1L;
	/** Id of the user **/
	protected long id;// Id of the user
	/** Type of user **/
	protected String type;// Type of user

	protected ArrayList<Book> books = new ArrayList<Book>();
	/** Fine to be paid by the user **/
	protected static long totalFine;// Fine to be paid by the user
	DateTimeFormatter format;
	// SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

	public User() {

	}

	public User(ArrayList<Book> books) {
		super();
		this.books = books;

	}

	/**
	 * Reads various attributes of the user from keyboard
	 */
	@Override
	public void readPerson(Input input) {
		System.out.print("Enter First Name: ");
		firstName = input.inputString();

		System.out.print("Enter Last Name: ");
		lastName = input.inputString();

		System.out.print("Enter your email: ");
		emailId = input.inputString();

		System.out.print("Enter your phone number: ");
		phoneNumber = input.inputPositiveLong();

	}

	/**
	 * Adds a book to the user's account
	 * 
	 * @param chosenBook Book chosen by the user
	 */
	protected void setBook(Book chosenBook) {
		calculateFine();
		if (books.size() < Policies.MAX_BOOKS && totalFine == 0) {

			books.add(chosenBook);
			books.get(books.indexOf(chosenBook)).setCheckoutDate(LocalDate.now()); // sets the checkout date to current
																					// date

			// books.get(books.indexOf(chosenBook)).setCheckoutDate(LocalDate.of(2021, 7,
			// 7));
			System.out.println("Book has been added");

		} else {
			System.out.println("You can not borrow more books");
		}
		if (totalFine != 0) {
			System.out.println("You can not borrow books until you have paid the pending fines");
		}

	}

	/**
	 * Returns the type of user
	 * 
	 * @return Type of user
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the id of user
	 * 
	 * @return Id of user
	 */
	public long getId() {
		return id;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	/**
	 * Prints all the borrowed books
	 */
	public void printBorrowedBooks() {

		if (books.size() == 0) {
			System.out.println("No books borrowed");
		}
		for (Book b : books) {
			b.printTitle();

			System.out.printf("%30s |", "Checkout Date");
			System.out.println();
			for (int i = 0; i < 245; i++)
				System.out.print("*");

			System.out.println();
			b.printBookWithCheckout();

			System.out.println();

		}
	}

	/**
	 * Calculates the fine due by the user
	 */
	@Override
	public void calculateFine() {
		totalFine = 0;
		if (books.size() != 0) {
			for (int i = 0; i < books.size(); i++) {
				totalFine += (ChronoUnit.DAYS.between(books.get(i).getCheckoutDate(), LocalDate.now()) - 14)
						* Policies.FINE;

			}

			if (totalFine < 0) {
				totalFine = 0;
			}

		}

	}

	/**
	 * Returns fine due by the user
	 * 
	 * @return Fine due by the user
	 */
	public long getFine() {
		calculateFine();
		return totalFine;
	}

	/**
	 * Pays the fine and returns the book
	 * 
	 * @param paidFine Amount deposited by the user
	 */
	public void payFine(int paidFine) {
		calculateFine();

		totalFine = totalFine - paidFine;
		books.removeAll(books);
		System.out.println("Fine has been paid and all books have been deposited");
	}
}
