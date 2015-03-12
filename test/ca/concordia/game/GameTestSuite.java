package ca.concordia.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@Suite.SuiteClasses({GameModelTest.class})
public class GameTestSuite{

	public static void main(String[] args) {
		//GameTest.class, GameTest2.class, GameUtilTest.class, 
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameModelTest.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(GameTest2.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(GameUtilTest.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(GameTest.class));;
	}
}