package ca.concordia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * TEst all four Test Cases.
 *
 */

@RunWith(Suite.class)
@SuiteClasses({ GameTest.class, GameTest2.class, GameTest3.class,
		GameTest4.class })
public class AllTests {

}
