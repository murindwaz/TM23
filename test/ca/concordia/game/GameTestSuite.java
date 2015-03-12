package ca.concordia.game;

import org.junit.Test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({GameTest.class, GameTest2.class, GameTest3.class, GameModelTest.class})
public class GameTestSuite extends TestCase{

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameTest2.class));;
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameTest3.class));;
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameModelTest.class));;
		junit.textui.TestRunner.run(new JUnit4TestAdapter(GameTest.class));;
	}
}