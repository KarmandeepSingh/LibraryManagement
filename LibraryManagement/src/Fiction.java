import java.io.Serializable;

/**
 * Purpose of this class is to create a book of fiction genre 
 * @author Karmandeep Singh
 *
 */
public class Fiction extends Book implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/**Author of the book**/
	protected String author;//Author of the book
	/**Category of the book**/
	protected String category;//Category of the book

	public Fiction() {
		super.genre = "Fiction";
	}

	@Override
	public void printTitle() {
		
		System.out.printf("%10s | %15s | ", "Book Id", "Genre");
	}

	@Override
	public void printBooks() {
		System.out.printf("%10d | %15s ", bookId, genre);
	}

	@Override
	public void printBookWithCheckout() {
		System.out.printf("%10d | %15s ", bookId, genre);

	}

}
