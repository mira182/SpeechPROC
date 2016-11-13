/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author mira
 */
public class PraatFunction {
    
    public final static String command = "/usr/bin/praat data/hello.wav --run \"my script.praat\"";
    
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
