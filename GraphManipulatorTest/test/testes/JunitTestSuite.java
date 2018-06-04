package testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EdgeTest.class, GraphManipulatorTest.class, GraphTest.class, VertexTest.class })
public class JunitTestSuite {

}
