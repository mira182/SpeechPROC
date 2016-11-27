/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 * @author mira
 */
public class PraatFunctions {
    
    private File soundFile;
    private File praatScript;
    
    private String fileName = PraatFunctions.class.getResource("../resources/get_formants.praat").getPath();
    public final static String command = "/usr/bin/praat Plosive1.wav --run \"get_formants.praat\"";
    
    public static String executeCommand(String command) {

		StringBuilder output = new StringBuilder();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
			while ((line = reader.readLine())!= null) {
				output.append(line).append(System.getProperty("line.separator"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}
    
}
