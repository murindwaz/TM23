package ca.concordia.game;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;






import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses(Test.class)
public class GameTestSuite extends TestCase{

	// text test runner that tells if tests fails
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(FristTest.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(SecondTest.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(ThirdTest.class));;
	}
}