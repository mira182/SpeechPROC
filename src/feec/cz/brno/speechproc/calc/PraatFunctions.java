/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author mira
 */
public class PraatFunctions {
    Logger logger = LogManager.getLogger(PraatFunctions.class);
    
    private File praatScript;
    private List<String> parameters;
    
//    public final static String command = "/usr/bin/praat \"formants.praat\" Plosive1.wav formants.csv";

    public PraatFunctions(File praatScript, List<String> parameters) {
        this.praatScript = praatScript;
        this.parameters = parameters;
    }
    
    private String buildCommand() {
        StringBuilder command = new StringBuilder();
        
        command.append("praat --run ");
        command.append(praatScript.getAbsolutePath()).append(" ");
        parameters.forEach(param -> command.append(param).append(" "));
        
        return command.toString();
    }
    
    
    public String executeCommand() {

		StringBuilder output = new StringBuilder();
        String command = buildCommand();
//String command = "pwd";

		Process p;
		try {
            logger.debug("Executing command: {}", command);
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
			while ((line = reader.readLine())!= null) {
				output.append(line).append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
            logger.error("Error during executing praat command", e);
			e.printStackTrace();
		}

		return output.toString();
	}
    
}
