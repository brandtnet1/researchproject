package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

@RestController
public class HelloController {
    
    ProjectGenerator pg;

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/generatePython")
    public void generatePython() {
        try {
            pg = new ProjectGenerator("Python", "Flask", "SQLAlchemy");
            pg.generate();
        } catch(IOException ex){
			System.out.println(ex);
		}
        
        // return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/generateNode")
    public void generateNode() {
        
        try {
            pg = new ProjectGenerator("ExpressJS", "NodeJS", "MongoDB");
            pg.generate();
        } catch(IOException ex){
			System.out.println(ex);
		}
        
        // return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/pushGit")
    public void pushGit() {
        
        try {
            pg = new ProjectGenerator();
            pg.pushToGit();
        } catch(IOException ex){
			System.out.println(ex);
		}
        
        // return "Greetings from Spring Boot!";
    }

}