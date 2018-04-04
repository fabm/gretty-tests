package pt.fabm

import org.apache.jmeter.engine.StandardJMeterEngine
import org.apache.jmeter.save.SaveService
import org.apache.jmeter.util.JMeterUtils
import org.apache.jorphan.collections.HashTree
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GrettyTests {
    @Test
    void jmeterTests() {
        def logger = LoggerFactory.getLogger('test')
        File report = 'report.jtl' as File
        report.delete()
        StandardJMeterEngine standardJMeterEngine = new StandardJMeterEngine();
        String propPath = getClass().getResource("/jmeter.properties").getFile();
        String jmeterHome = "/Users/francisco/Downloads/apache-jmeter-4.0";
        JMeterUtils.setJMeterHome(jmeterHome);
        SaveService.loadProperties();

        JMeterUtils.loadJMeterProperties(propPath);
        File file = new File(getClass().getResource("/jmeter-sample.jmx").toURI());

        HashTree hashTree = SaveService.loadTree(file);

        standardJMeterEngine.configure(hashTree);
        standardJMeterEngine.run();
        def list = new XmlSlurper().parse(report)
        def assertionErrors = (list.httpSample.assertionResult.error as List)
        assert assertionErrors.every{ it.text() == 'false' }
    }
}
