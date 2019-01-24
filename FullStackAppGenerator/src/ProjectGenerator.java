import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.util.ArrayList;

public class ProjectGenerator {

	private String frontEnd;
	private String backEnd;
	private String database;
	
	private ArrayList<String> commands = new ArrayList<String>();

    private ProcessBuilder pb;
	private Process p;

	public ProjectGenerator(String frontEnd, String backEnd, String database) {

		this.frontEnd = frontEnd;
		this.backEnd = backEnd;
		this.database = database;

	}

	protected void generate() throws IOException {
		
		File dir = new File("../../.");
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
			if(file.getName().equals("webapp")){
				boolean result = deleteDirectory(new File("../../webapp"));
				if(result) break;
			}
		}
		
		File webapp = new File("../../webapp");
		webapp.mkdir();

		if(frontEnd.toLowerCase().contains("express")) {
			
			commands.add("express"); commands.add("app");
		 	buildProcess(commands, "../../webapp");

			commands.clear();
			wait(10000);
			
			commands.add("npm"); commands.add("install");
			buildProcess(commands, "../../webapp/app");
			
			commands.clear();
			wait(2000);
			
			commands.add("npm"); commands.add("install"); commands.add("mongoose"); commands.add("--save");
			buildProcess(commands, "../../webapp/app");
			
			commands.clear();
			wait(2000);
			
			commands.add("npm"); commands.add("install"); commands.add("passport"); commands.add("passport-local");
			commands.add("passport-local-mongoose"); commands.add("--save");
			buildProcess(commands, "../../webapp/app");
			
			commands.clear();
			wait(2000);
			
			commands.add("npm"); commands.add("install"); commands.add("express-session"); commands.add("--save");
			buildProcess(commands, "../../webapp/app");
		
		} else {

			File config = new File("../../webapp/config.py");
			config.createNewFile();
			File site = new File("../../webapp/site.py");
			site.createNewFile();
			File app = new File("../../webapp/app");
			app.mkdir();

			File init = new File("../../webapp/app/__init__.py");
			init.createNewFile();
			File forms = new File("../../webapp/app/forms.py");
			forms.createNewFile();
			File models = new File("../../webapp/app/models.py");
			models.createNewFile();
			File routes = new File("../../webapp/app/routes.py");
			routes.createNewFile();
			File templates = new File("../../webapp/app/templates");
			templates.mkdir();

		}

		System.out.println("\nApp foundation generated!\n");

	}
	
	protected void buildProcess(ArrayList<String> commands, String directory) throws IOException {
			
			if(isWindows()){
				commands.set(0, commands.get(0) + ".cmd");
			}
			
			pb = new ProcessBuilder(commands);
			pb.directory(new File(directory));
		 	p = pb.start();
		 	
		    InputStream is = p.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
		    String line;
		    while ((line = br.readLine()) != null) {
		      System.out.println(line);
		    }
		    System.out.println("Process Finished!");
	}
	
	private void wait(int length){
		try {
			    Thread.sleep(length);
		}
		catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
		}
	}
	
	private static boolean isWindows() {
    	return System.getProperty("os.name").toLowerCase().contains("win");
	}
	
	private boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
}
