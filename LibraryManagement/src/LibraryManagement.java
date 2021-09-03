/**
 * The purpose of this class is to run the program by using all the available classes
 * @author Karmandeep Singh
 *
 */
public class LibraryManagement {

	public static void main(String[] args) {

		Input input = new Input();
		Library lib = new Library(input);
		
			

		lib.userMenu(input);

	}

}
