import java.io.Serializable;
/**
 * The purpose of this class is to create a book of category of History
 * @author Karmandeep Singh
 *
 */
public class History extends NonFiction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**Description of the book**/
	protected String description;//Description of the book
	
//	public History() {
//		super.category="History";
//	}
	public History(long bookId,String title, String author, String description) {
		super.title=title;
		super.author=author;
		super.category="History";
		this.description=description;
		super.bookId=bookId;
	}
	@Override
	public void printTitle() {
		super.printTitle();
		System.out.printf("%10s | %40s | %35s | %83s | ", "Category", "Title", "Author", "Desciption");
		
	}
	@Override
	public void printBooks() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %83s",category, title, author, description);
		System.out.println();
		
	}
	@Override
	public void printBookWithCheckout() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %83s | %30s",category, title, author, description,checkoutDate.toString());
		System.out.println();
	}
	
}
