import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileEditor {

	public FileEditor() {}
	
	public void appendToFile (String filePath, String text) {

        BufferedWriter bw = null;

        try {
             bw = new BufferedWriter(new FileWriter(filePath, true));
        	 bw.write(text);
        	 bw.newLine();
        	 bw.flush();
        } catch (IOException ioe) {
	        ioe.printStackTrace();
        } finally {
            if (bw != null) {
    	        try {
    	            bw.close();
    	        } catch (IOException ioe2) {
    	            System.out.println(ioe2);
    	        }
    	 }
      } 
   }
}
