package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Scanner;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.main.Game;

/**
 * Class Action handles action from all Brown and Green borders playing cards
 * 
 * @author Pascal Maniraho
 * @author Gustavo Pereira
 * @author Bhavik Desai
 * @author Jesus Esteban Garro Matamoros
 * @author Diego Pizarro
 */
public class Action {

	private Game game;
	private Gameboard gameBoard;
	private Player player;
	private Scanner keyIn;
	private Scanner input;
	private Player[] players;
	private Die die;

	Bank bank = Bank.getInstance();

	private int rollValue;

	/**
	 * Constructor: initilializes an action according to Card's ID.
	 * 
	 * @param cardId
	 */
	public Action(int cardId) {
		this.game = Game.getInstance();

		if (game.currentPlayer != -1) {
			this.gameBoard = game.getGameBoard();
			this.players = game.getPlayers();
			this.player = players[(game.currentPlayer)];
			this.die = new Die();
			rollValue = -1;
			switch (cardId) {

			//------------------------------------------------------------Grouped Actions: Cards with similar movements---------------------------------------------------	
			/**
			 * Group1: Get Cards from DrawDeck 1,3,36,42 - Get 2 77 - Get 3
			 * 87,88 - Get 4
			 */
			case 1:
			case 3:
			case 36:
			case 42:
				getCards(2);
				break;
			case 77:
				getCards(3);
				break;
			case 87:
			case 88:
				getCards(4);
				break;
				/**
				 * Group2: Earn Money 2,27,90 1 for each TroubleMarker on Board
				 * 14,40 2 for each Building on Board 61,83 3 for Minions on Isle of
				 * God 54,101 4 from all Players 84 5 from one Player
				 */
			case 2:
			case 27:
			case 90:
				earnMoney(1, 1);
				break;
			case 14:
			case 40:
				earnMoney(1, 2);
				break;
			case 61:
			case 83:
				earnMoney(1, 3);
				break;
			case 54:
			case 101:
				earnMoney(2, 4);
				break;
			case 84:
				earnMoney(3, 5);
				break;
				/**
				 * Group3: Pay another player then move minion 5,8,44
				 */
			case 5:
			case 8:
			case 44:
				paynRemoveMinion(2, cardId);
				break;
				/**
				 * Group4: Move a minion belonging to another player from one area
				 * to an adjacent area 11,62,72 15,18,92 Your own minion, not
				 * required to be adjacent 92 Your own minion and adjacent
				 */
			case 11:
			case 62:
			case 72:
				moveMinion(false, true, false);
				break;
			case 15:
			case 18:
				moveMinion(true, false, false);
				break;
			case 92:
				moveMinion(true, true, true);
				break;

				/** Group5: Place a minion
				 * 17,32
				 * 34 has TroubleMarker
				 * 28 has Building
				 * 31,29 inUE
				 */
			case 17:
				placeMinion(false, false, false, 1, true);
				break;
			case 32:
				placeMinion(false, false, false, 1, true);
				break;
			case 34:
				placeMinion(false, false, true, 1, true);
				break;
			case 28:
				placeMinion(false, true, false, 1, false);
				break;
			case 31:
				placeMinion(true, false, false, 1, false);
				break;
			case 29:
				placeMinion(true, false, false, 2, false);
				break;
				/** Group6: TakeLoan* 55,57
				 */
			case 55:
			case 57:
				takeLoan(10);
				break;

				/** Group7: TakeCard from Player: 56, 94
				 */
			case 56:
			case 94:
				takeCard();
				break;
				/** Group8: Play two other cards: 39, 63 
				 */
			case 39:
			case 63:
				play2Cards();
				break;

				/** Group9: 64, 78
				 * Roll die, earn or lose money/minion
				 */
			case 64:
				rollDieWinorLose(3, true);
				break;
			case 78:
				rollDieWinorLose(4, false);
				break;

				/** Group10: 22, 37, 46, 70, 71, 98
				 * Interrupt
				 */
			case 22:
			case 37:
			case 70:
			case 98:				
				// Interrupt( ); ???
				break;	
			case 46:
			case 71:
				// Interrupt( ); ???
				//placeMinion			
				break;

				/** Group11: 73, 97
				 * Get $5 or place this card as not playable
				 */
			case 73:
			case 97:				
				give5orLoseCard(cardId);
				break;	


				/** Group12: 89,79,47,85
				 * Discard Cards
				 */
			case 79:
				discardnTake(2);
				break;	
			case 89:				
				discardnTake(1);
				break;	
			case 47:
			case 85:
				player.removePlayerCard(player.getPlayerCards().get(chooseCard(player)));
				break;

				/** Group13: 91,99
				 * Choose one player. Give them one of your cards. They must give you $2 in return.
				 */
			case 91:
			case 99:				
				givenTake();
				break;	

				//------------------------------------------------------------Single Card Actions---------------------------------------------------	
				// 96: You may exchange your Personality card with one drawn randomly from those not in use.
			case 96:
				exchangePersonality( );
				break;	

				// 74: Choose a player. If he does not pay you $5 then you can remove one of his buildings from the board.
			case 74:
				give5orLoseBuilding( );
				break;				

				// 76: Shuffle the discard pile and draw four cards randomly. Place the remaining cards back as the discard pile.
			case 76:
				shuffleandGetDiscard( );
				break;				

				// 67: Look at all but one of the unused Personality cards.
			case 67:
				lookUnusedPersonalities();
				break;

				// 58: Each player must give you either $1 or one of their cards.
			case 58:
				get1fromOthers();
				break;

				// 6 : Remove one minion from Unreal Estate.
			case 6:
				removeUnrealEstateMinion();
				break;
				// 9 : Exchange minions position
			case 9:
				exchangeMinions();
				break;
				// 10 Look other player's cards and discard one
			case 10:
				discardOthersCard();
				// 12: Roll the die twice and remove one minion of your choice
				// from those areas.
			case 12:
				rollnRemoveMinion();
				break;
				// 13: Exchange your hand with that of another player.
			case 13:
				exchangeCards();
				break;
				//------------------------------------------------------------No Action Cards---------------------------------------------------	
				// Do nothing
			case 4 :
			case 7 :
			case 16 :
			case 21 :
			case 24 :
			case 25 :
			case 30 :
			case 38 :
			case 49 :
			case 50 :
			case 51 :
			case 59 :
			case 60 :
			case 65 :
			case 66 :
			case 68 :
			case 69 :
			case 75 :
			case 80 :
			case 81 :
			case 82 :
			case 86 :
			case 93 :
			case 95 :
			case 100:
				System.out.println("No Action.");
				break;
			default:
				System.out.println("Action Id doesn't exist.");
				break;
			}
		}
	}

	//------------------------------------------------------------User INPUT methods---------------------------------------------------	

	/**
	 * Choose a Player
	 * 
	 * @return choosenPlayer
	 */
	private int choosePlayer() {
		int choosenPlayer = 0;
		while(true){
			for (int i = 0; i < game.numberOfPlayers; i++) {
				if (game.currentPlayer != i) {
					System.out.println(i + "." + players[i].getPersonality().getName() + " " + players[i].getColor() + ",");
				}
			}
			choosenPlayer = keyIn.nextInt();
			if (players[choosenPlayer]==null) {
				System.out.println("Not a valid choice. Please enter Player again:");
				continue;
			}
			else
				break;
		}

		return choosenPlayer;
	}

	/**
	 * Choose a Card from Player
	 * 
	 * @param aPlayer
	 * @return choosenCard
	 */
	private int chooseCard(Player aPlayer) {
		int choosenCard;
		ArrayList<Card> playerCards;
		playerCards = aPlayer.getPlayerCards();

		while(true){
			for (int i = 0; i < playerCards.size(); i++) {
				if (playerCards.get(i).isPlayable)
					System.out.println(i + "." + playerCards.get(i).getName());
				else
					playerCards.remove(i);
			}
			choosenCard = keyIn.nextInt();

			if (playerCards.get(choosenCard)==null) {
				System.out.println("Not a valid choice. Please choose Card again:");
				continue;
			}
			else
				break;
		}
		return choosenCard;
	}

	/**
	 * Choose an Area
	 * 
	 * @param color
	 * @param hasMinions
	 * @param hasBuilding
	 * @param hasTroubleMarker
	 * @param adjacentAreas
	 * @return choosenArea
	 */
	private int chooseArea(Colors color, boolean hasMinions, boolean hasBuilding, boolean hasTroubleMarker, ArrayList<Area> aAreas) {
		int choosenArea;
		ArrayList<Integer> areas = null;
		boolean found;
		while(true){
			for (int i = 0; i < 12; i++) {
				if (hasMinions){
					if (gameBoard.getAreas().get(i).getMinions().isEmpty())
						continue;
					if (color!=null){
						found = false;	
						for(int m=0;m<gameBoard.getAreas().get(i).getMinions().size();m++)
							if(gameBoard.getAreas().get(i).getMinions().get(m).getColor() == color){
								found = true;
								break;
							}
						if (found==false)
							continue;
					}
				}
				if (hasBuilding){
					if (gameBoard.getAreas().get(i).getMinions().isEmpty())
						continue;
					if ( (color!=null) &&
							(gameBoard.getAreas().get(i).getBuildingColor() != color))
						continue;
				}				
				if (aAreas != null)
					for (int a = 0; a<aAreas.size();a++)
						if (aAreas.get(a).getCityCard().getAdjacentAreas().get(i) == null)
							continue;
				if (hasTroubleMarker)
					if (gameBoard.troubleMarkersAreas().get(i) == null)
						continue;
				System.out.println(i + "." + gameBoard.getAreas().get(i).toString());
				areas.add(i); //valid
			}
			if (areas.isEmpty()){
				System.out.println("No valid Areas found.");
				return -1;
			}

			choosenArea = keyIn.nextInt();

			if (areas.indexOf(choosenArea)==-1) {
				System.out.println("Not a valid choice. Please choose Area again:");
				continue;
			}
			else
				break;
		}

		return choosenArea;

	}
	//------------------------------------------------------------Action methods---------------------------------------------------	
	/**
	 * Add card to player playing cards.
	 * 
	 * @param nbCards
	 */
	private void getCards(int nbCards) {
		for (int i = 0; i < nbCards; i++) {
			if (game.getDecks().get("B").getSizeDeck() > 0)
				player.receiveCard(game.getDecks().get("B").getCard());
			else
				player.receiveCard(game.getDecks().get("G").getCard());
		}
	}

	/**
	 * Earn money for each piece on the board.
	 * 
	 * @param amount
	 * @param mode
	 *            : 1 for each TroubleMarker 2 for each Building 3 for Minions
	 *            on Isle of God ) 4 from all Players 5 from one Player
	 */
	private void earnMoney(int amount, int mode) {
		for (int i = 1; i <= 12; i++) {
			switch (mode) {
			case 1:
				if (gameBoard.getAreas().get(i).getTroubleMarker())
					bank.transferFunds(player, amount);
				break;
			case 2:
				if (gameBoard.getAreas().get(i).getBuilding())
					bank.transferFunds(player, amount);
				break;
			case 3:
				amount = amount * gameBoard.getAreas().get(10).getMinions().size();
				bank.transferFunds(player, amount);
				break;
			case 4:
				for (int p = 1; p <= game.getPlayers().length; p++)
					players[p].transferMoneyto(amount, player);
				break;
			case 5:
				System.out.println("Choose a player to take $" + amount + " dollars from:");
				players[choosePlayer()].transferMoneyto(amount, player);
				break;
			}

		}
	}

	/**
	 * Pay another player then remove one minion
	 * 
	 * @param amount
	 * @param cardId
	 */
	private void paynRemoveMinion(int amount, int cardId) {
		int choosenPlayer = 0;
		int choosenArea = 0;
		System.out.println("Choose a player to receive $" + amount + " dollars:");
		choosenPlayer = choosePlayer();
		player.transferMoneyto(amount, players[choosenPlayer]);
		switch (cardId) {
		case 44:
			moveMinion(true, false, false);
			return;
		case 5:
			System.out.println("Player " + choosenPlayer
					+ "must choose an area with trouble marker to remove one minion (not your choice)");
			break;
		case 8:
			System.out.println("You can choose a minion to remove:");
			break;
		}
		System.out.println("Select the color of minion you wish to remove:");
		String tmpColor = input.next();
		Colors color = Colors.colorForString(tmpColor);

		choosenArea = chooseArea(color, true, true, false, null);
		if (choosenArea == -1)
			return;
		// Remove a minion of the color specified by the player.
		gameBoard.getAreas().get(choosenArea).removeMinion(color);
		// Update the status of the player to whom the minion belonged to.
		Player playerbycolor = game.getPlayerByColor(color);
		playerbycolor.removeMinionOnBoard(choosenArea);
	}

	/**
	 * Remove one minion from Unreal Estate.
	 */
	private void removeUnrealEstateMinion() {
		gameBoard.getAreas().get(2).getMinions().remove(1);
	}

	/**
	 * Exchange the positions of any two minions on the board.
	 */
	private void exchangeMinions() {
		int fromArea;
		int toArea;
		while (true) {

			System.out.println("Select the color of minion you wish to move:");
			String tmpColor = input.next();
			Colors color = Colors.colorForString(tmpColor);
			Player playerbycolorfrom = game.getPlayerByColor(color);
			System.out.println("Choose an area to move one minion from:");
			fromArea = this.chooseArea(color, true, false, false, null);

			System.out.println("Select the color of minion you wish to move out:");
			tmpColor = input.next();
			color = Colors.colorForString(tmpColor);

			System.out.println("Choose an area to move one minion to:");
			toArea = this.chooseArea(color,true,false, false, null);
			if (fromArea == -1 || toArea== -1)
				return;

			Player playerbycolorto = game.getPlayerByColor(color);
			if (playerbycolorto.moveMinionToNewArea(toArea, fromArea)) {
				if (playerbycolorfrom.moveMinionToNewArea(fromArea, toArea))
					break;
			}
		}
	}

	/**
	 * Look other player's cards and discard one
	 */
	private void discardOthersCard() {
		ArrayList<Card> playerCards;
		int cardNb;
		System.out.println("Select a Player to loose a card:");
		playerCards = players[choosePlayer()].getPlayerCards();
		System.out.println("Select a card to discard:");
		for (int i = 0; i < playerCards.size(); i++) {
			System.out.println(i + playerCards.get(i).toString());
		}
		cardNb = keyIn.nextInt();
		playerCards.remove(cardNb);
	}

	/**
	 * Move a minion from one area to another area.
	 * 
	 * @param isYour
	 * @param isAdjacent
	 * @param hasTroubleMarker
	 */
	private void moveMinion(boolean isYour, boolean isAdjacent, boolean hasTroubleMarker) {
		int fromArea, toArea;
		String tmpColor;
		Colors color = null;
		Player playerbycolor = null;
		ArrayList<Area> aAreas = null;
		while (true) {

			if (!isYour) {
				System.out.println("Select the color of minion you wish to move:");
				tmpColor = input.next();
				color = Colors.colorForString(tmpColor);
				playerbycolor = game.getPlayerByColor(color);

				if (color.equals(player.color)) {
					System.out.println("Minion should belong to another player.");
					continue;
				}
			} else if (color.equals(player.color) != isYour) {
				System.out.println("Minion should belong to you.");
				continue;
			}

			System.out.println("Choose an area to move this minion from:");
			fromArea = this.chooseArea(color,true,false, hasTroubleMarker, null);

			System.out.println("Choose an area to move this minion to:");
			aAreas.add(0,gameBoard.getAreas().get(fromArea));
			toArea = this.chooseArea(null, false, false, false, aAreas);

			if (fromArea == -1 || toArea== -1){ 
				System.out.println("Choose another color");
				continue;
			}


			if (!isYour) {
				if (playerbycolor.moveMinionToNewArea(fromArea, toArea))
					break;
				else {
					System.out.println("Not possible to move to this Area");
					continue;
				}
			} else {
				if (player.moveMinionToNewArea(fromArea, toArea))
					break;
				else {
					System.out.println("Not possible to move to this Area");
					continue;
				}
			}
		}

	}

	/**
	 * Roll the die twice and remove one minion of your choice from those areas,
	 * even if there is no trouble there.
	 */
	private void rollnRemoveMinion() {
		for (int i = 0; i == 2; i++) {

			System.out.println("Rolling the die...");
			rollValue = die.roll();

			System.out.println("Select the minion from Area " + rollValue + ":");

			if (gameBoard.getAreas().get(rollValue).getMinions().isEmpty()) {
				System.out.println("Area has no Minions");
				continue;
			}

			for (int count = 0; count == gameBoard.getAreas().get(rollValue).getMinions().size(); count++) {
				System.out.println(count + ". "
						+ gameBoard.getAreas().get(rollValue).getMinions().get(count).toString());
			}

			int pieceChoosen = keyIn.nextInt();

			gameBoard.getAreas().get(rollValue).getMinions().remove(pieceChoosen);

		}
	}

	/**
	 * Exchange your hand with that of another player.
	 */
	private void exchangeCards() {
		int choosenPlayer = 0;
		ArrayList<Card> playerCards;

		System.out.println("Select a player to exchange all your Cards:");
		playerCards = players[choosePlayer()].getPlayerCards();

		players[choosenPlayer].loseAllCards();
		players[choosenPlayer].receiveAllCards(players[game.currentPlayer].getPlayerCards());

		players[game.currentPlayer].loseAllCards();
		players[game.currentPlayer].receiveAllCards(playerCards);
	}

	/**
	 * Place a minion
	 * 
	 * @param isUEadjacent
	 * @param hasownBuilding
	 * @param hasTroubleMarker
	 * @param times
	 * @param skipAdjacenAreaCheck
	 */
	private void placeMinion(boolean UEadjacent, boolean hasownBuilding, boolean hasTroubleMarker, int times, boolean skipAdjacenAreaCheck) {
		ArrayList<Area> aAreas = null;

		if (!skipAdjacenAreaCheck)
			for (int a=0; a<player.getMinionsOnArea().length;a++)
				if(aAreas.add(gameBoard.getAreas().get(player.getMinionsOnArea()[a])));
		for (int i = 1; i <= times; i++) {
			int toArea;

			System.out.println("Choose an area to place this minion:");

			if (UEadjacent){
				aAreas.add(0, gameBoard.getAreas().get(2));
				toArea = this.chooseArea(null,false,false, false, aAreas );	
			}
			toArea = this.chooseArea(null,false, hasownBuilding, hasTroubleMarker, aAreas);

			if (!player.putNewMinionOnBoard(toArea, false))
				System.out.println("Not possible to place Minion");
		}
	}

	/**
	 * Take a loan of @amount from the bank. At the end of the game you must pay
	 * back $12 or lose 15 points.
	 * 
	 * @param amount
	 */
	private void takeLoan(int amount) {
		player.takeLoan(amount);
		player.addMoney(amount);
	}

	/**
	 * Select one player. They must give you two cards of their choice.
	 */
	private void takeCard() {
		int choosenPlayer = 0;
		int choosenCard;

		System.out.println("Select a player to get 2 Cards:");
		choosenPlayer = choosePlayer();
		System.out.println("Choosen Player(NOT YOU) must choose 2 Cards to give you:");

		for (int i = 0; i < 2; i++) {
			choosenCard = chooseCard(players[choosenPlayer]);
			players[choosenPlayer].transferCard(choosenCard, player);
		}
	}

	/**
	 * Each player must give you either $1 or one of their cards.
	 */
	private void get1fromOthers() {
		int option;

		for (int i = 0; i < game.getNumberOfPlayers(); i++) {
			System.out.println("Player: " + players[i].getColor().toString() + ". Choose 1 for Money or 2 for Card:");
			while(true){
				option = keyIn.nextInt();
				if (option == 1) {
					players[i].transferMoneyto(1, player);
					break;
				} else if(option == 2) {
					players[i].transferCard(chooseCard(players[i]), player);
					break;
				} else {
					System.out.println("Invalid option");
				}
			}
		}
	}

	/**
	 * Play any two other cards from your hand.
	 */
	private void play2Cards() {
		for (int count = 0; count < 2; count++) {
			System.out.println("Choose another card to play:");
			new Action(chooseCard(player));
		}
	}

	//78: Roll the die. On a roll of '7' or more you take $3 from a player of your choice. 
	//On a roll of a '1' you must remove one of your minions from the board. All other results have no effect.
	//64: Roll the die. On a roll of '7' or more you take $4 from the bank. 
	//On a roll of' l' you must pay $2 to the bank or remove one of your minions from the board. All other results have no effect.
	/**
	 * 	Roll the die. On a roll of '7' or more you take @amount from a player of your choice or bank.
	 * @param amount
	 * @param is64 (cardID)
	 */
	private void rollDieWinorLose(int amount, boolean is64) {
		int choosenOption = 0;
		int choosenArea = 0;

		rollValue = die.roll();
		System.out.println("Rolling the die...: "+rollValue);

		if ( rollValue >= 7 ){
			if (is64) //Amount from the Bank
				bank.transferFunds(player, amount);
			else{    // Amount from another Player
				System.out.println("Choose a Player to get $"+amount);
				players[choosePlayer()].transferMoneyto(amount, player);   
			}
		}

		if ( rollValue == 1) {
			if (is64) {
				System.out.println("Choose either:");
				System.out.println("1. Pay $2 to the bank or;");
				System.out.println("2. Remove one of your minions; :");
				while(true){
					choosenOption = keyIn.nextInt();
					if (choosenOption != 1 && choosenOption != 2){
						System.out.println("Not a valid choice. Choose again:");
						continue;
					}
					else
						break;	
				}
			}	
			if (is64 == true && choosenOption == 1 && player.payMoney(2)==true)
				bank.deposit(2);
			else{
				System.out.println("Choose Area:");
				choosenArea = chooseArea(player.color,true, false,false, null);
				gameBoard.getAreas().get(choosenArea).removeMinion(player.color);

			}
		}

	}	

	/**
	 *  Look at all but one of the unused Personality cards.
	 * 
	 */
	private void lookUnusedPersonalities( ) {
		ArrayList<Card> personalityCards = game.getDecks().get("P").getArrayDeck();
		for (int i=1; i<personalityCards.size(); i++)
			System.out.println(i+"."+personalityCards.get(i).toString());
	}


	/**
	 * Select another player. If they do not give you $5 then place this card in front of them. 
	 * This card now counts towards their hand size of five cards when they come to refill their hand. 
	 * They cannot get rid of this card.
	 */
	private void give5orLoseCard( int cardId ) {
		int choosenPlayer = choosePlayer();

		System.out.println("Player: " + players[choosenPlayer].getColor().toString() + ". Choose 1 for Money or 2 for Card:");
		if (keyIn.nextInt() == 1) {
			players[choosenPlayer].transferMoneyto(5, player);
		} else {
			player.transferCard(cardId, players[choosenPlayer]);
			players[choosenPlayer].getPlayerCards().get(cardId).isPlayable = false;
		}
	}


	/**
	 * Choose a player. If he does not pay you $5 then you can remove one of his buildings from the board.
	 */
	private void give5orLoseBuilding( ) {
		int choosenPlayer;

		while(true){
			choosenPlayer = choosePlayer();
			if ((players[choosenPlayer].getBuildingOnHand()==6)&&(players[choosenPlayer].getMoney() <5)){
				System.out.println("Broken player, choose another one");
				continue;
			}
			break;
		}

		if (players[choosenPlayer].getBuildingOnHand()==6){
			System.out.println("Player dont have available Building, $5 will be transfered.");
			players[choosenPlayer].transferMoneyto(5, player);
			return;
		}

		if (players[choosenPlayer].getMoney()<5){
			System.out.println("Player dont have money, will lose Building. Choose Area:");
			int choosenArea = chooseArea(players[choosenPlayer].color,false,true, false, null);
			if(gameBoard.getAreas().get(choosenArea).removeBuilding());	 
			return;
		}

		System.out.println("Player: " + players[choosenPlayer].getColor().toString() + ". Choose 1 for Money or 2 for Building:");
		if (keyIn.nextInt() == 1) {
			players[choosenPlayer].transferMoneyto(5, player);
		} else {
			while(true){

				System.out.println("Choose Area:");
				int choosenArea = chooseArea(null,false,true,false, null);
				if(gameBoard.getAreas().get(choosenArea).removeBuilding());	 
				return;
			}
		}
		return;
	}

	/**
	 * Shuffle the discard pile and draw four cards randomly. Place the remaining cards back as the discard pile.
	 */
	private void shuffleandGetDiscard( ) {
		game.getDecks().get("D").shuffle();
		player.receiveCard(game.getDecks().get("D").getCard());
	}

	/** Group12: 89,79
	 * Discard as many cards as you wish and take @amount for each one discarded.
	 * @param amount
	 */
	private void discardnTake(int amount) {
		while(player.getPlayerCards().size()>0){
			System.out.println("Would you like to select a PlayerCard to discard and get $"+amount+"? (1)Yes (2)No:");
			if(keyIn.nextInt()==1){
				int choosenCard = chooseCard(player);
				player.removePlayerCard(player.getPlayerCards().get(choosenCard));
				bank.transferFunds(player, amount);
			}else {
				break;
			}
		}
	}

	/** Group13: 91,99
	 * Choose one player. Give them one of your cards. They must give you $2 in return.
	 */
	private void givenTake( ) {
		int choosenPlayer = choosePlayer( );
		System.out.println("Choose a card to give:");
		int choosenCard = chooseCard(player);
		player.transferCard(choosenCard, players[choosenPlayer]);
		players[choosenPlayer].transferMoneyto(2, player);
	}

	/** 96: You may exchange your Personality card with one drawn randomly from those not in use.
	 */
	private void exchangePersonality( ) {
		int choosenCard;
		ArrayList<Card> personalityCards = game.getDecks().get("P").getArrayDeck();
		System.out.println("Choose a new Personality:");
		for (int i=0; i<personalityCards.size(); i++)
			System.out.println(i+"."+personalityCards.get(i).toString());
		while(true){
			choosenCard = keyIn.nextInt();
			try {
				player.setPersonality(personalityCards.get(choosenCard));
				break;
			} catch (IndexOutOfBoundsException  e) {
				System.out.println("Not a valid choice");
				continue;
			}
		}

	}

}
