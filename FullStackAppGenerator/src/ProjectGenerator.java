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

		if(frontEnd == "ExpressJS") {

			pb = new ProcessBuilder("express.cmd","app");
			pb.directory(new File("../../"));
		 	p = pb.start();

			pb = new ProcessBuilder("npm.cmd","install");
			pb.directory(new File("../../"));
		 	p = pb.start();

		} else {

			File app = new File("../../app");
			app.mkdir();

		}

	}

}
