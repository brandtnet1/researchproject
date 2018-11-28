import java.io.IOException;
import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String frontEnd = "";
		String backEnd = "";
		String database = "";

		System.out.println("Here we will be getting types and requirements from user.\n");

		System.out.println("Would you like a Python or ExpressJS front end: ");
		frontEnd = scan.nextLine();
		
		System.out.println("Would you like a NodeJS or FlaskJS back end: ");
		backEnd = scan.nextLine();
		
		System.out.println("Would you like a SQLAlchemy or MongoDB database: ");
		database = scan.nextLine();

		try{
			
			ProjectGenerator pg = new ProjectGenerator(frontEnd, backEnd, database);
			pg.generate();
			
			FileEditor editor = new FileEditor();
			
		} catch(IOException ex){
			System.out.println(ex);
		}

		// System.out.println("Once we get requirements we will create a 'project' object "
		// 		+ "which will stand in as our intermin full stack appliction");
		//
		// System.out.println("The list of requirements and the project object will be passed "
		// 		+ "into a 'compiler' or sorts which will create the routes, pages and necessary logic");
		//
		// System.out.println("A database will be created and instantiated with the necessary "
		// 		+ "tables and it will be added to the project file");
		//
		// System.out.println("Project will be tested 'WORK IN PROGRESS'");
		//
		// System.out.println("Project will be spit out into your github");

	}

}
