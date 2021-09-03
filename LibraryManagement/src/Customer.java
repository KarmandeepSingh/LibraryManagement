import java.io.Serializable;
/**
 * 
 * The purpose of this class is to create a user of type Customer
 * @author Karmandeep Singh
 *
 */
public class Customer extends User implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public Customer() {
		super.type = "Customer";
		
	}

	/**
	 * Sets the customer id
	 * @param custId Customer id
	 */
	protected void setCustId(long custId) {
		super.id = custId;
		super.type = "Customer";
	}

	@Override
	public void readPerson(Input input) {
		super.readPerson(input);		
		boolean control=true;
		boolean idCheck=true;
		long tempId = 0;
		
		do {
			System.out.print("Enter Customer Id: ");
			
			tempId = input.inputPositiveLong();
			
			
			for(int i = 0; i < Library.members.size(); i++) {
				if ((tempId == Library.members.get(i).getId())&& Library.members.get(i).type.equals("Customer")) {
					idCheck=false;
					i=Library.members.size();
					System.err.println("This customer id already exists");
					System.out.println("Try again");
				}
				else {
					idCheck=true;
				}
			}
			
			if(idCheck==false) {
				control=true;
			}
			else {
				control=false;
				id=tempId;
			}
			
		}while(control);
		
		
		
		
		
		
	}

	/**
	 * Returns the customer id
	 * @return Customer id
	 */
	public long getCustId() {
		return id;
	}
}
