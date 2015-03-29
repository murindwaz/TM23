package ca.concordia.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@Suite.SuiteClasses({GameModelTest.class, GameUtilTest.class, PlayerTest.class})
public class GameTestSuite{

	public static void main(String[] args) {
		//GameTest.class, GameTest2.class, GameUtilTest.class, 
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameUtilTest.class));;
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameModelTest.class));;
		junit.textui.TestRunner.run(new JUnit4TestAdapter(PlayerTest.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(GameUtilTest.class));;
		//junit.textui.TestRunner.run(new JUnit4TestAdapter(GameTest.class));;
	}
}