import java.io.IOException;

public class Interface {

	public static void main(String[] args) {
		try{

			// ProjectGenerator pg = new ProjectGenerator("Python", "Flask", "SQLAlchemy");
			ProjectGenerator pg = new ProjectGenerator("ExpressJS", "NodeJS", "MongoDB");

			pg.generate();
		} catch(IOException ex){
			System.out.println(ex);
		}


		System.out.println("Here we will be getting types and requirements from user.");

		System.out.println("Once we get requirements we will create a 'project' object "
				+ "which will stand in as our intermin full stack appliction");

		System.out.println("The list of requirements and the project object will be passed "
				+ "into a 'compiler' or sorts which will create the routes, pages and necessary logic");

		System.out.println("A database will be created and instantiated with the necessary "
				+ "tables and it will be added to the project file");

		System.out.println("Project will be tested 'WORK IN PROGRESS'");

		System.out.println("Project will be spit out into your github");

	}

}
