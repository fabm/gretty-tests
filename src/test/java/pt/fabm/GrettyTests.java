package pt.fabm;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jorphan.collections.HashTree;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GrettyTests {
    @Test
    public void myTest() throws URISyntaxException, IOException {
        StandardJMeterEngine standardJMeterEngine = new StandardJMeterEngine();
        File file = new File(getClass().getResource("/jmeter-tests.jmx").toURI());
        HashTree hashTree = SaveService.loadTree(file);

    }
}
