package pt.fabm

import org.apache.jmeter.engine.StandardJMeterEngine
import org.apache.jmeter.reporters.ResultCollector
import org.apache.jmeter.reporters.Summariser
import org.apache.jmeter.samplers.SampleEvent
import org.apache.jmeter.save.SaveService
import org.apache.jmeter.util.JMeterUtils
import org.apache.jorphan.collections.HashTree
import org.junit.Test

import java.util.concurrent.atomic.AtomicBoolean

class JmeterTests {
    @Test
    void jmeterTests() {
        StandardJMeterEngine standardJMeterEngine = new StandardJMeterEngine();
        String propPath = getClass().getResource("/jmeter.properties").getFile();
        String jmeterHome = System.getProperty('jmeter.home')
        JMeterUtils.setJMeterHome(jmeterHome)
        SaveService.loadProperties()

        JMeterUtils.loadJMeterProperties(propPath);
        File file = new File(getClass().getResource("/jmeter-sample.jmx").toURI());

        HashTree hashTree = SaveService.loadTree(file)

        AtomicBoolean success = new AtomicBoolean()

        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "main summariser");

        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName){
                @Override
                void sampleOccurred(SampleEvent e) {
                    success.set(e.result.successful)
                    super.sampleOccurred(e)
                }
            }
        }

        ResultCollector logger = new ResultCollector(summer)
        hashTree.add(hashTree.getArray()[0],logger)

        standardJMeterEngine.configure(hashTree)
        standardJMeterEngine.run()

        assert success.get()
    }
}
