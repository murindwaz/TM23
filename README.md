# TM23


Game - Homework


This is a commit for testing purposes. 


### Specs 

	- Use of incremental software delivery: We use Github
	- Use of Design patterns (Identify and write them down here )  
	- 


### Environment Setup 
	- Eclipse IDE ( Luna or Mars ) 
	- On Windows, install Git from 
	- On Linux/Mac use the command line utility
		```sh
			$ sudo apt-get install git #ubuntu 
			$ sudo yum install git 	   #centos, fedora etc.
		```
	- Install Maven for Eclipse (m2eclipse - luna/mars depending on version ) 
	- 

### Approach TDD ( Test Driven Development ) 
	- JUnit 4 
	- src/test/java/GameTestSuite.java 
	- Code coverage EclEmma Plugin for Eclipse 
	- 

### First build 
	- GameEngine : tasks 
			- initialize the game state from a flat file( JSON with GSON is fast to work with )
			- have two moves and save another state to the same flat file 
			- has to know about all components:
				- model ( Player, Gameboard, Cards, Characters ) 
	- 


### Eclipse tips 
	- how to uppercase/lowercase (link)[http://www.daveoncode.com/2009/08/25/eclipse-shortcut-switch-convert-uppercase-text-cod-lowercase/]
		- Lower case: CTRL+SHIFT+Y (CMD+SHIFT+Y on Mac OS X)
		- Upper case: CTRL+SHIFT+X (CMD+SHIFT+X on Mac OS X)
		- Format source code: CTRL+SHIFT+F (CMD+SHIFT+F on Mac OS X)
	- 


### How to contribute?
	- We are going to contribute to the code using ("pull-requests")[https://help.github.com/articles/using-pull-requests/]
	- The good way to do it was yo use all group members as contributors( having access to the repo ) but free version of github looks not to support that feature. 
	