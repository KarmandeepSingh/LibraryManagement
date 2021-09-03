import java.io.Serializable;
/**
 * The purpose of this class is to create a book of category of Biography
 * @author Karmandeep Singh
 *
 */
public class Biography extends NonFiction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**Person about whom biography is written**/
	protected String about;//Person about whom biography is written
	/**Description of the book**/
	protected String description;//Description of the book

//	public Biography() {
//		super.category="Biography";
//	}

	public Biography(long bookId,String title, String author, String about, String description) {
		super.title = title;
		super.author = author;
		super.category = "Biography";
		this.about = about;
		this.description = description;
		super.bookId = bookId;
	}

	@Override
	public void printTitle() {
		super.printTitle();
		System.out.printf("%10s | %40s | %35s | %20s | %60s | ", "Category", "Title", "Author", "About", "Desciption");
		
	}

	@Override
	public void printBooks() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s", category, title, author, about, description);
		System.out.println();

	}
	@Override
	public void printBookWithCheckout() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s | %30s",category, title, author, about, description,checkoutDate.toString());
		System.out.println();
	}
}
