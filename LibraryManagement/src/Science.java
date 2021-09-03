import java.io.Serializable;
/**
 * The purpose of this class is to create a book of category of Science
 * @author Karmandeep Singh
 *
 */
public class Science extends NonFiction implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/**Topic of book**/
	protected String topic; //Topic of book
	/**Description of book**/
	protected String description;//Description of book
	
	
	public Science() {
		super.category="Science";
	}
	public Science(long bookId,String title, String author,String topic, String description) {
		super.title=title;
		super.author=author;
		this.topic=topic;
		this.description=description;
		super.bookId=bookId;
		super.category="Science";
	}
	@Override
	public void printTitle() {
		super.printTitle();
		System.out.printf("%10s | %40s | %35s | %20s | %60s | ", "Category", "Title", "Author", "Topic", "Desciption");
		
		
	}
	@Override
	public void printBooks() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s",category, title, author, topic, description);
		System.out.println();
		
	}
	@Override
	public void printBookWithCheckout() {
		super.printBooks();
		System.out.printf("| %10s | %40s | %35s | %20s | %60s | %30s",category, title, author, topic, description,checkoutDate.toString());
		System.out.println();
	}
	
}
