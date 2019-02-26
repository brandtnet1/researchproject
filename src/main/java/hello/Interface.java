package hello;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Interface {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> routes = new ArrayList<String>();
		String fileExtensions[] = new String[2];

		String routeString;
		String frontEnd;
		String backEnd;
		String database;

		System.out.println("Here we will be getting types and requirements from user.\n");

		System.out.println("1 for Python/Flask, 2 for Express/NodeJS ");
		int selection = scan.nextInt();
		
		if(selection == 1){
			frontEnd = "Python";
			backEnd = "Flask";
			database = "SqlAlchemy";
		} else {
			frontEnd = "ExpressJS";
			backEnd = "NodeJS";
			database = "MongoDB";
		}
		

		try {
			ProjectGenerator pg = new ProjectGenerator(frontEnd, backEnd, database);
			pg.generate();
			
			fileExtensions[0] = frontEnd.toLowerCase().contains("express") ? ".js" : ".py";
			fileExtensions[1] = backEnd.toLowerCase().contains("node") ? ".jade" : ".html";
		} catch(IOException ex){
			System.out.println(ex);
		}
		
		routes.add("home");
		routes.add("login");
		routes.add("register");
		routes.add("logout");
		
		FileEditor editor = new FileEditor(selection);
					

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
