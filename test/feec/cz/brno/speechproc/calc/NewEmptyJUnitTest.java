/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.calc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mira
 */
public class NewEmptyJUnitTest {
    
    private List<String> parameters;

    @Before
    public void setUp() {
        parameters = new ArrayList<>();
        parameters.add("/media/mira/0f00c26e-7ed7-4b05-99c3-763797a05b44/mira/School/2016_17/DIPLOMKA/Praat/Plosive1.wav");
//        parameters.add("/media/mira/0f00c26e-7ed7-4b05-99c3-763797a05b44/mira/School/2016_17/DIPLOMKA/Praat/test.csv");
    }
    
    @Test
    public void testPraatCommand() {
        PraatFunctions pf = new PraatFunctions(new File("/media/mira/0f00c26e-7ed7-4b05-99c3-763797a05b44/mira/School/2016_17/DIPLOMKA/Praat/formants.praat"), 
                parameters);
        pf.executeCommand();
    }
}
