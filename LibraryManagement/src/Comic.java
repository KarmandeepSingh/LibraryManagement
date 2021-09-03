import java.io.Serializable;
/**
 * The purpose of this class is to create a book of category of comic
 * @author Karmandeep Singh
 *
 */
public class Comic extends Fiction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**Style of the book**/
	protected String style;//Style of the book
	/**Description of the book**/
	protected String description;//Description of the book
	

	public Comic(long bookId,String title, String author,String style, String description) {
		super.title=title;
		super.author=author;
		super.category="Comic";
		this.style=style;
		this.description=description;
		this.bookId=bookId;
	}
	
	@Override
	public void printTitle() {
		super.printTitle();
		System.out.printf("%10s | %40s | %35s | %20s | %60s | ", "Category", "Title", "Author", "Style", "Desciption");
		
	}
	@Override
	public void printBooks() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s",category, title, author, style, description);
		System.out.println();
		
	}
	@Override
	public void printBookWithCheckout() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s | %30s",category, title, author, style, description,checkoutDate.toString());
		System.out.println();
	}
}
