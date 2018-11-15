import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.List;

public class ProjectGenerator {
	
	private String frontEnd;
	private String backEnd;
	private String database;
	
	private List<String> commands = new ArrayList<String>();                
    private ProcessBuilder pb;
	
	public ProjectGenerator(String frontEnd, String backEnd, String database) {
		
		this.frontEnd = frontEnd;
		this.backEnd = backEnd;
		this.database = database;
		
	}
	
	protected void generateProject() {
		
	}

}
