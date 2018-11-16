import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class ProjectGenerator {

	private String frontEnd;
	private String backEnd;
	private String database;

	private List<String> commands = new ArrayList<String>();
  private ProcessBuilder pb;
	private Process p;

	public ProjectGenerator(String frontEnd, String backEnd, String database) {

		this.frontEnd = frontEnd;
		this.backEnd = backEnd;
		this.database = database;

	}

	protected void generate() throws IOException {

		if(frontEnd.contains("Express")) {

			pb = new ProcessBuilder("express.cmd","webapp");
			pb.directory(new File("../../"));
		 	p = pb.start();

			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}

			pb = new ProcessBuilder("npm.cmd","install");
			pb.directory(new File("../../webapp"));
		 	p = pb.start();

		} else {

			File webapp = new File("../../webapp");
			webapp.mkdir();

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
			File templates = new File("../../webapp/templates");
			templates.mkdir();

		}

	}
}
