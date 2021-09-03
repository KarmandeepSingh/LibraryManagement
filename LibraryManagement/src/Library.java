import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The purpose of this class is to create a library system
 * 
 * @author Karmandeep Singh
 *
 */
public class Library implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ObjectOutputStream outputObj;
	private static ObjectInputStream inputObj;

	static ArrayList<User> members = new ArrayList<User>();

	ArrayList<Book> fantasy = new ArrayList<Book>();
	ArrayList<Book> comic = new ArrayList<Book>();
	ArrayList<Book> science = new ArrayList<Book>();
	ArrayList<Book> history = new ArrayList<Book>();
	ArrayList<Book> biography = new ArrayList<Book>();
	ArrayList<Book> books = new ArrayList<Book>();

	final protected static int CUSTOMER = 1;
	final protected static int STAFF = 2;

	public Library(Input input) {
		readBooks(input);
		readUsers();
	}


	/**
	 * Adds members to the database
	 * 
	 * @param input Reads input from the user and a file
	 */
	public static void addMember(Input input) {
		System.out.println("1. New Customer");
		System.out.println("2. New Staff Member");

		System.out.print("Enter your option: ");
		int option = 0;
		boolean control = true;

		do {
			try { //prevents the user from entering wrong option
				option = input.inputPositiveInteger();
				if (option != STAFF && option != CUSTOMER)
					throw new Exception();

				control = false;
			} catch (Exception e) {
				System.err.println("Wrong option entered");
				System.out.print("Enter correct option: ");
			}
		} while (control);

		switch (option) {
		case CUSTOMER://creates a customer
			members.add(new Customer());
			members.get(members.size() - 1).readPerson(input);
			break;
		case STAFF://creates a staff member
			members.add(new Staff());
			members.get(members.size() - 1).readPerson(input);
			break;

		}

	}

	/**
	 * Reads books from files
	 * 
	 * @param file Reads details from a file
	 */
	public void readBooks(Input file) {
		//reads all the books from their corresponding files
		readFantasy(file);
		readComic(file);
		readScience(file);
		readHistory(file);
		readBiography(file);

		books.addAll(fantasy);
		books.addAll(comic);
		books.addAll(science);
		books.addAll(history);
		books.addAll(biography);

	}

	/**
	 * Reads and stores fantasy books
	 * 
	 * @param file Reads details from a file
	 */
	private void readFantasy(Input file) {

		file.openFile("src\\Books\\Fiction\\fantasy.txt", ",");
		while (file.readHasNext()) {
			fantasy.add(new Fantasy(file.readNextLong(), file.readNextString(), file.readNextString(),
					file.readNextString(), file.readNextString()));

		}
	}

	/**
	 * Reads and stores comic books
	 * 
	 * @param file Reads details from a file
	 */
	private void readComic(Input file) {

		file.openFile("src\\Books\\Fiction\\comic.txt", ",");
		while (file.readHasNext()) {
			comic.add(new Comic(file.readNextLong(), file.readNextString(), file.readNextString(),
					file.readNextString(), file.readNextString()));

		}
	}

	/**
	 * Reads and stores science books
	 * 
	 * @param file Reads details from a file
	 */
	private void readScience(Input file) {

		file.openFile("src\\Books\\NonFiction\\science.txt", ",");
		while (file.readHasNext()) {
			science.add(new Science(file.readNextLong(), file.readNextString(), file.readNextString(),
					file.readNextString(), file.readNextString()));

		}
	}

	/**
	 * Reads and stores history books
	 * 
	 * @param file Reads details from a file
	 */
	private void readHistory(Input file) {

		file.openFile("src\\Books\\NonFiction\\history.txt", ",");
		while (file.readHasNext()) {
			history.add(new History(file.readNextLong(), file.readNextString(), file.readNextString(),
					file.readNextString()));

		}

	}

	/**
	 * Reads and stores biography books
	 * 
	 * @param file Reads details from a file
	 */
	private void readBiography(Input file) {

		file.openFile("src\\Books\\NonFiction\\biography.txt", ",");
		while (file.readHasNext()) {
			biography.add(new Biography(file.readNextLong(), file.readNextString(), file.readNextString(),
					file.readNextString(), file.readNextString()));

		}
	}

	/**
	 * Prints all the books borrowed by a user
	 * 
	 * @param userLogin Specifies the user
	 * @param input     Reads input from the user
	 */
	public void existingUserPrintBooks(User userLogin, Input input) {

		userLogin.printBorrowedBooks();

	}

	/**
	 * Adds books to the user
	 * 
	 * @param userLogin Specifies the user
	 * @param input     Reads input from the user
	 */
	public void userAddBook(User userLogin, Input input) {
		long tempId;

		boolean control = true;
		do {//allows the user to enter details in case of wrong input
			System.out.print("Enter book id: ");
			tempId = input.inputPositiveLong();
			for (int i = 0; i < books.size(); i++) {

				if (tempId == books.get(i).bookId) {//checks for book id in database
					userLogin.setBook(books.get(i));
					i = books.size();
					control = false;
				}
			}
			if (control)
				System.err.println("Invalid book id entered");
		} while (control);

	}

	/**
	 * A menu for customer interaction
	 * 
	 * @param userLogin Specifies the customer
	 * @param input     Reads input from the customer
	 */
	private void customerInteraction(User userLogin, Input input) {
		int choice;
		userLogin.calculateFine();
		boolean control = true;

		do {//keeps the user in menu until user chooses to exit
			if (userLogin.getFine() != 0) {
				System.out.println("****** You have unpaid fine ******");
			}//displays this header if user has unpaid fine
			userLogin.calculateFine();
			System.out.println("1. Add Book");
			System.out.println("2. Check for fine");
			System.out.println("3. Pay for fine");
			System.out.println("4. Show all borrowed books");
			System.out.println("5. Print all books");
			System.out.println("6. Return book");
			System.out.println("7. Remove this user");
			System.out.println("8. Display terms and conditions");
			System.out.println("9. <-Go Back");
			System.out.print("Enter option: ");

			choice = input.inputPositiveInteger();

			switch (choice) {
			case 1:
				userAddBook(userLogin, input);//allows the user to borrow books
				break;
			case 2:
				System.out.println("Fine: $" + userLogin.getFine());//returns fine to the user
				break;
			case 3:
				int tempFine;
				if (userLogin.getFine() != 0) { //allows the user to pay fine
					do {
						System.out.println("Fine: $" + userLogin.getFine());
						System.out.print("Enter amount: ");

						tempFine = input.inputPositiveInteger();
						
						if (tempFine != userLogin.getFine()) //prompts the user to enter exact amount 
							System.out.println("Enter exact fine");

						else {
							userLogin.payFine(tempFine);//pays the fine
							tempFine = 0;
						}

					} while (tempFine != userLogin.getFine());
				} else {
					System.out.println("No fine to pay");
				}
				break;
			case 4:
				existingUserPrintBooks(userLogin, input);//prints all the books borrowed by the user
				break;
			case 5:
				printBooks(input);//prints all or some books according to the user input
				break;
			case 6:

				if (userLogin.books.size() != 0) {//checks if user has borrowed any books or not
					if (User.totalFine != 0) {//checks if user has unpaid fine 
						int tempFine2;
						do { //tells user to pay fine 
							System.out.println("Fine: $" + userLogin.getFine());
							System.out.print("Enter amount: ");

							tempFine2 = input.inputPositiveInteger();
							if (tempFine2 != userLogin.getFine())
								System.out.println("Enter exact fine");

							else {
								userLogin.payFine(tempFine2);
								tempFine2 = 0;
							}

						} while (tempFine2 != userLogin.getFine());
					} else {
						int option2 = 0;
						do {

							System.out.println("1. Return all books");
							System.out.println("2. Return a specific book");
							System.out.print("Enter: ");
							option2 = input.inputPositiveInteger();

							if (option2 == 1) {//removes all the books
								userLogin.books.removeAll(userLogin.books);
								System.out.println("All books deposited");
							} else if (option2 == 2) {//removes a specific book
								returnBook(userLogin, input);
							} else {
								System.err.println("Enter a valid option");
							}
						} while (option2 != 1 && option2 != 2);
					}
				} else {
					System.out.println("No borrowed books");
				}
				break;
			case 7:
				if (User.totalFine == 0) {//checks if user has unpaid fine or not before removing the user
					String choice2 = "";
					System.out.println("Are You sure you want to remove this user(Y/n)"); //deletes the user
					choice2 = input.inputString();
					if (choice2.equals("Y") || choice2.equals("y")) {
						for (int i = 0; i < members.size(); i++) {
							if ((userLogin.getId() == members.get(i).getId())
									&& members.get(i).type.equals("Customer")) {
								members.remove(i);
							}
						}
						control = false;
					}
				} else {
					System.out.println("User can not be deleted without paying the fine");
				}
				break;
			case 8:
				System.out.println();
				System.out.println("1. Duration for borrowing the boooks: 14 days");
				System.out.println("2. Fine upon exceeding due date: $1 per day(after the 14th day)");
				System.out.println("3. Max books allowed per user: 5 books");
				System.out.println();
				break;
			case 9:
				control = false;
				break;
			default:
				System.err.println("Enter a valid option");
				control = true;
				break;

			}

		} while (control);
	}

	/**
	 * A menu for staff interaction
	 * 
	 * @param userLogin Specifies the staff member
	 * @param input     Reads input from the staff member
	 */
	private void staffInteraction(User userLogin, Input input) {
		int choice;
		userLogin.calculateFine();
		boolean control = true;

		do {
			if (userLogin.getFine() != 0) {
				System.out.println("****** You have unpaid fine ******");
			}
			userLogin.calculateFine();
			System.out.println("1. Add Book");
			System.out.println("2. Check for fine");
			System.out.println("3. Pay for fine");
			System.out.println("4. Show all borrowed books");
			System.out.println("5. Print all books");
			System.out.println("6. Return book");
			System.out.println("7. Remove this user");
			System.out.println("8. List all customers");
			System.out.println("9. Display terms and conditions");
			System.out.println("10. <-Go Back");
			System.out.print("Enter option: ");

			choice = input.inputPositiveInteger();

			switch (choice) {
			case 1:
				userAddBook(userLogin, input);//allows the user to borrow books
				break;
			case 2:
				System.out.println("Fine: $" + userLogin.getFine());//returns fine to the user
				break;
			case 3:
				int tempFine;
				if (userLogin.getFine() != 0) {
					do {
						System.out.println("Fine: $" + userLogin.getFine());
						System.out.print("Enter amount: ");

						tempFine = input.inputPositiveInteger();
						if (tempFine != userLogin.getFine())
							System.out.println("Enter exact fine");

						else {
							userLogin.payFine(tempFine);//pays the fine
							tempFine = 0;
						}

					} while (tempFine != userLogin.getFine());
				} else {
					System.out.println("No fine to pay");
				}
				break;
			case 4:
				existingUserPrintBooks(userLogin, input);//prints all the books borrowed by the user
				break;
			case 5:
				printBooks(input);//prints all or some books according to the user input
				break;
			case 6:

				if (userLogin.books.size() != 0) {
					if (User.totalFine != 0) {//checks for fine
						int tempFine2;
						do {
							System.out.println("Fine: $" + userLogin.getFine());
							System.out.print("Enter amount: ");

							tempFine2 = input.inputPositiveInteger();
							if (tempFine2 != userLogin.getFine())
								System.out.println("Enter exact fine");

							else {
								userLogin.payFine(tempFine2);//allows the user to pay fine
								tempFine2 = 0;
							}

						} while (tempFine2 != userLogin.getFine());
					} else {
						int option2 = 0;
						do {

							System.out.println("1. Return all books");
							System.out.println("2. Return a specific book");
							System.out.print("Enter: ");
							option2 = input.inputPositiveInteger();

							if (option2 == 1) {
								userLogin.books.removeAll(userLogin.books);//returns all the books
								System.out.println("All books deposited");
							} else if (option2 == 2) {
								returnBook(userLogin, input);//allows the user to return a specific book
							} else {
								System.err.println("Enter a valid option");
							}
						} while (option2 != 1 && option2 != 2);
					}
				} else {
					System.out.println("No borrowed books");
				}
				break;
			case 7:
				if (User.totalFine == 0) {//checks for fine before deleting the user
					String choice2 = "";
					System.out.println("Are You sure you want to remove this user(Y/n)");
					choice2 = input.inputString();
					if (choice2.equals("Y") || choice2.equals("y")) {
						for (int i = 0; i < members.size(); i++) {
							if ((userLogin.getId() == members.get(i).getId()) && members.get(i).type.equals("Staff")) {
								members.remove(i);
							}
						}
						control = false;
					}
				} else {
					System.out.println("User can not be deleted without paying the fine");
				}
				break;
			case 8:
				int count = 0;

				for (User u : members) {//checks whether there are customers in the database or not
					if (u.type.equals("Customer")) {
						count++;
					}
				}

				if (count != 0) {
					for (int i = 0; i < members.size(); i++) { //prints details of all the customers
						if (members.get(i).type.equals("Customer")) {
							System.out.printf("%25s | %25s | %15s | %15s | %10s | %n", "Customer Id", "Name", "Email",
									"Phone number", "fine");
							System.out.printf("%25d | %25s | %15s | %15d | %10d | %n", members.get(i).id,
									members.get(i).getFullName(), members.get(i).emailId, members.get(i).phoneNumber,
									members.get(i).getFine());
							System.out.println("List of all the books: ");
							members.get(i).printBorrowedBooks();

						}
					}
				} else {
					System.out.println("No customers in the database");
				}
				break;
			case 9:
				System.out.println();
				System.out.println("1. Duration for borrowing the boooks: 14 days");
				System.out.println("2. Fine upon exceeding due date: $1 per day(after the 14th day)");
				System.out.println("3. Max books allowed per user: 5 books");
				System.out.println();
				break;
			case 10:
				control = false;
				break;
			default:
				System.err.println("Enter a valid option");
				control = true;
				break;

			}

		} while (control);
	}

	/**
	 * A menu for user interaction
	 * 
	 * @param userLogin Specifies the user
	 * @param input     Reads input from the user
	 */
	public void userInteraction(User userLogin, Input input) {

		userLogin = userLogin(input);

		if (userLogin != null) {
			if (userLogin.type.equals("Customer")) { //customer interaction
				customerInteraction(userLogin, input);
			} else if (userLogin.type.equals("Staff")) {//staff interaction
				staffInteraction(userLogin, input);
			}
		}
	}

	/**
	 * Allows the user to return book
	 * 
	 * @param userLogin Specifies the user
	 * @param input     Reads user input
	 */
	public void returnBook(User userLogin, Input input) {

		long tempId;
//		userLogin=userLogin(input);
		boolean control = true;
		do {
			System.out.print("Enter the book id of the book that you want to return: ");
			tempId = input.inputPositiveLong();
			for (int i = 0; i < userLogin.books.size(); i++) {

				if (tempId == userLogin.books.get(i).bookId) { //removes a specific book
					userLogin.books.remove(i);
					System.out.println("Book deposited");
					i = 5;
					control = false;
				}
			}
			if (control)
				System.err.println("Invalid book id entered");
		} while (control);

	}

	/**
	 * Specifies a particular user
	 * 
	 * @param input Reads input from the user
	 * @return A specific user matching the provided details
	 */
	public User userLogin(Input input) {
		int option;
		User output = null;

		boolean control = true;
		do {
			System.out.println("1. Customer login");
			System.out.println("2. Staff login");
			System.out.println("3. <-Go Back");
			System.out.print("Enter option: ");
			option = input.inputPositiveInteger();

			switch (option) {
			case 1:
				int count = 0;
				for (int i = 0; i < members.size(); i++) {
					if (members.get(i).type.equals("Customer")) {
						count++;
					}
				}

				long tempId;
				if (count != 0) {
					do {
						System.out.print("Please enter customer id: ");
						tempId = input.inputPositiveLong();
						for (int i = 0; i < members.size(); i++) {
							if (tempId == members.get(i).getId() && members.get(i).getType().equals("Customer")) {
								output = members.get(i); //outputs the desired customer
								i = members.size();
							}
						}
						if (output == null)
							System.err.println("Invalid id entered");
						else {
							control = false;
						}

					} while (output == null);
				} else {
					System.err.println("No customers in the database");
					control = true;
				}
				break;

			case 2:
				int count2 = 0;

				for (int i = 0; i < members.size(); i++) {
					if (members.get(i).type.equals("Staff")) {
						count2++;
					}

				}
				if (count2 != 0) {
					do {
						System.out.print("Please enter staff id: ");
						tempId = input.inputPositiveLong();
						for (int i = 0; i < members.size(); i++) {
							if (tempId == members.get(i).getId() && members.get(i).getType().equals("Staff")) {
								output = members.get(i); //outputs the desired staff member
							}
						}
						if (output == null)
							System.err.println("Invalid id entered");
						else {
							control = false;
						}

					} while (output == null);
				} else {
					System.err.println("No Staff in the database");
					control = true;
				}

				break;
			case 3:
				control = false;
				break;
			default:
				System.err.println("Invalid option selected");
				control = true;

			}
		} while (control);
		return output;

	}

	/**
	 * Main menu
	 * 
	 * @param input Reads user input
	 */
	public void userMenu(Input input) {
		int option;
		boolean control = true;
		
		do {
			System.out.printf("%33s %n", "Welcome to My Library");
			for(int i=0;i<50;i++) {
				System.out.print("*");
			}
			System.out.println();
			System.out.println("1. Print books");
			System.out.println("2. Existing User Login");
			System.out.println("3. New user");
			System.out.println("4. Display terms and conditions");
			System.out.println("5. List all staff(Administrator)");
			System.out.println("6. Exit");

			System.out.print("Enter option: ");
			option = input.inputPositiveInteger();

			switch (option) {
			case 1:
				printBooks(input);
				break;
			case 2:
				if (members.size() == 0) {//checks if there are any users in the database or not
					System.err.println("No users found");
				} else {
					userInteraction(null, input);
				}
				break;
			case 3:
				addMember(input); //adds user to the database
				break;

			case 4:
				System.out.println();
				System.out.println("1. Duration for borrowing the boooks: 14 days");
				System.out.println("2. Fine upon exceeding due date: $1 per day(after the 14th day)");
				System.out.println("3. Max books allowed per user: 5 books");
				System.out.println();
				break;
			case 5:
				int count = 0;

				for (User u : members) {//checks whether there are staff in the database or not
					if (u.type.equals("Staff")) {
						count++;
					}
				}

				if (count != 0) {
					for (int i = 0; i < members.size(); i++) { //prints details of all the staff
						if (members.get(i).type.equals("Staff")) {
							System.out.printf("%25s | %25s | %25s | %15s | %10s | %n", "Staff Id", "Name", "Email",
									"Phone number", "fine");
							System.out.printf("%25d | %25s | %25s | %15d | %10d | %n", members.get(i).id,
									members.get(i).getFullName(), members.get(i).emailId, members.get(i).phoneNumber,
									members.get(i).getFine() );
							System.out.println("List of all the books: ");
							members.get(i).printBorrowedBooks();

						}
					}
				} else {
					System.err.println("No staff in the database");
				}
				break;
			case 6:
				control = false;
				System.out.println("Thank you for using the program");
				writeUsers();//saves user data to a particular file
				break;
			default:
				System.err.println("Enter a valid option");
				control = true;
				break;
			}

		} while (control);
	}

	/**
	 * Prints books
	 * 
	 * @param input Reads user input
	 */
	public void printBooks(Input input) {

		int choice = 1;
		System.out.println("1. Print all books");
		System.out.println("2. Print Fantasy books");
		System.out.println("3. Print Comic books");
		System.out.println("4. Print Science books");
		System.out.println("5. Print History books");
		System.out.println("6. Print Biography books");

		do {
			System.out.print("Enter option: ");
			choice = input.inputPositiveInteger();
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6)
				System.err.println("Enter a valid option");

		} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6);

		switch (choice) {

		case 1:

			printAllBooks();//prints all books

			break;
		case 2:
			for (int i = 0; i < fantasy.size(); i++) {
				if (i == 0) {
					fantasy.get(i).printTitle();
					System.out.println();
					for (int j = 0; j < 210; j++)
						System.out.print("*");

					System.out.println();
					fantasy.get(i).printBooks();
				} else {
					fantasy.get(i).printBooks();
				}
			}
			break;
		case 3:
			for (int i = 0; i < comic.size(); i++) {
				if (i == 0) {
					comic.get(i).printTitle();
					System.out.println();
					for (int j = 0; j < 210; j++)
						System.out.print("*");

					System.out.println();
					comic.get(i).printBooks();
				} else {
					comic.get(i).printBooks();
				}
			}
			break;
		case 4:
			for (int i = 0; i < science.size(); i++) {
				if (i == 0) {
					science.get(i).printTitle();
					System.out.println();
					for (int j = 0; j < 210; j++)
						System.out.print("*");

					System.out.println();
					science.get(i).printBooks();
				} else {
					science.get(i).printBooks();

				}
			}
			break;
		case 5:
			for (int i = 0; i < history.size(); i++) {
				if (i == 0) {
					history.get(i).printTitle();
					System.out.println();
					for (int j = 0; j < 210; j++)
						System.out.print("*");

					System.out.println();
					history.get(i).printBooks();
				} else {
					history.get(i).printBooks();
				}
			}
			break;
		case 6:
			for (int i = 0; i < biography.size(); i++) {
				if (i == 0) {
					biography.get(i).printTitle();
					System.out.println();
					for (int j = 0; j < 210; j++)
						System.out.print("*");

					System.out.println();
					biography.get(i).printBooks();
				} else {
					biography.get(i).printBooks();
				}
			}
			break;
		}

	}

	/**
	 * Prints all books
	 */
	private void printAllBooks() {
		for (int i = 0; i < fantasy.size(); i++) {
			if (i == 0) {
				fantasy.get(i).printTitle();
				System.out.println();
				for (int j = 0; j < 210; j++)
					System.out.print("*");

				System.out.println();
				fantasy.get(i).printBooks();
			} else {
				fantasy.get(i).printBooks();
			}
		}

		System.out.println();
		for (int i = 0; i < comic.size(); i++) {
			if (i == 0) {
				comic.get(i).printTitle();
				System.out.println();
				for (int j = 0; j < 210; j++)
					System.out.print("*");

				System.out.println();
				comic.get(i).printBooks();
			} else {
				comic.get(i).printBooks();
			}
		}
		System.out.println();
		for (int i = 0; i < science.size(); i++) {
			if (i == 0) {
				science.get(i).printTitle();
				System.out.println();
				for (int j = 0; j < 210; j++)
					System.out.print("*");

				System.out.println();
				science.get(i).printBooks();
			} else {
				science.get(i).printBooks();

			}
		}

		System.out.println();
		for (int i = 0; i < history.size(); i++) {
			if (i == 0) {
				history.get(i).printTitle();
				System.out.println();
				for (int j = 0; j < 210; j++)
					System.out.print("*");

				System.out.println();
				history.get(i).printBooks();
			} else {
				history.get(i).printBooks();
			}
		}
		System.out.println();
		for (int i = 0; i < biography.size(); i++) {
			if (i == 0) {
				biography.get(i).printTitle();
				System.out.println();
				for (int j = 0; j < 210; j++)
					System.out.print("*");

				System.out.println();
				biography.get(i).printBooks();
			} else {
				biography.get(i).printBooks();
			}
		}

	}

	/**
	 * Saves user data to a file
	 */
	private void writeUsers() {

		try {
			outputObj = new ObjectOutputStream(Files.newOutputStream(Paths.get("src\\Users\\users.ser")));
		} catch (IOException i) {
			System.out.println(i);
		}
		try {
			for (User u : members) {
				outputObj.writeObject(u);
			}
		} catch (IOException io) {

		}
	}

	/**
	 * Reads user data from a specific file
	 */
	private void readUsers() {
		try {
			inputObj = new ObjectInputStream(Files.newInputStream(Path.of("src\\Users\\users.ser")));
		} catch (Exception e) {

		}

		while (true) {
			try {
				members.add((User) inputObj.readObject());
			} catch (EOFException e) {
				break;

			} catch (ClassNotFoundException e) {

			} catch (IOException e) {

			} catch (NullPointerException n) {
				break;
			}
		}
	}

}
