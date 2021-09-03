import java.io.Serializable;

/**
 * The purpose of this class is to create a book of category of Fantasy
 * @author Karmandeep Singh
 *
 */
public class Fantasy extends Fiction implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/**Theme of book**/
	protected String theme; //Theme of book
	/**Description of the book**/
	protected String description;//Description of the book 
	

	
	
	public Fantasy( long bookId, String title, String author, String theme, String description) {
		super.title=title;
		super.category="Fantasy";
		super.author=author;
		this.theme=theme;
		this.description=description;
		this.bookId=bookId;
		
	}
	@Override
	public void printTitle() {
		
		super.printTitle();
		System.out.printf("%10s | %40s | %35s | %20s | %60s | ", "Category", "Title", "Author", "Theme", "Desciption");
		
	}
	
	@Override
	public void printBooks() {
		
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s",category, title, author, theme, description);
		System.out.println();
		
	}
	@Override
	public void printBookWithCheckout() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s | %30s",category, title, author, theme, description,checkoutDate.toString());
		System.out.println();
	}
	
}
