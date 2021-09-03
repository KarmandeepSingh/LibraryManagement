import java.io.Serializable;

/**
 * The purpose of this class is to create a Staff member
 * @author Karmandeep Singh
 *
 */
public class Staff extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	/**Age of the staff member*/
	protected int age;//Age of the staff member
	/**Floor number registered to the staff member**/
	protected int floorNum;//Floor number registered to the staff member
	/**Section registered to the staff member*/
	protected char section;//Section registered to the staff member

	public Staff() {
		super.type = "Staff";
	}

	public Staff(int age, int floorNum, char section) {
		super();
		this.age = age;
		super.type = "Staff";

		this.floorNum = floorNum;
		this.section = section;

	}

	
	@Override
	public void readPerson(Input input) {

		boolean control = true;
		do { //allows the user to re-enter details
			try {
				super.readPerson(input);
				boolean control2=true;
				boolean idCheck=true;
				long tempId = 0;
				do {
					System.out.print("Enter Staff Id: ");
					
					tempId = input.inputPositiveLong();
					
					
					for(int i = 0; i < Library.members.size(); i++) {
						if ((tempId == Library.members.get(i).getId())&& Library.members.get(i).type.equals("Staff")) {
							idCheck=false;
							i=Library.members.size();
							System.err.println("This staff id already exists");
							System.out.println("Try again");
						}
						else {
							idCheck=true;
						}
					}
					
					if(idCheck==false) {
						control2=true;
					}
					else {
						control2=false;
						id=tempId;
					}
					
				}while(control2);
				System.out.print("Enter your age(18-65): "); //checks for the age
				age = input.inputPositiveInteger();
				if (age < 18 && age > 65)
					throw new Exception();

				System.out.print("Enter your floor number(0-3): ");
				floorNum = input.inputInteger();

				while (floorNum > 3 && floorNum<0) {//checks for the floor
					System.out.println("This building has only 3 floors, try again: ");
					floorNum = input.inputPositiveInteger();
				}

				do {
					System.out.print("Enter your section(A-D): ");// checks for the section
					section = input.inputOnlyLetter();
					section = Character.toUpperCase(section);
					if (section != 'A' && section != 'B' && section != 'C' && section != 'D')
						System.err.println("Enter a valid section");
				} while (section != 'A' && section != 'B' && section != 'C' && section != 'D');

				control = false;

			} catch (Exception e) {
				System.err.println("Staff member can not be younger than 18 years or older than 65");
				System.out.println("Enter again: ");
			}
			

			
		} while (control);

	}



}
