package unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DeleteControllerTest.class, DownloadTest.class, NewsDataListTest.class, NewsDataTest.class,
		TagControllerTest.class })
public class AllTests {

}
