package ca.concordia.game.model;

import java.util.concurrent.atomic.AtomicInteger;

import ca.concordia.test.*;
/**
 * Class Bank handles the balance of the current instance of a bank, Further, it handles any deposits and withdraws made by Players as long as
 * there are sufficient resources.
 * There can only be one instance of Bank at any time.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */
public class Bank {

	private static Bank instance = null;
	private AtomicInteger total;
	

	/**
	 * Implements Bank as a singleton, as there will always be a single Bank per run.
	 * @return
	 */
	public static Bank getInstance() {
		if(instance == null) {
			instance = new Bank();
		}
		return instance;
	}
	
	/**
	 * Set new amount. Used usually when a game is loaded.
	 * @param amount
	 */
	public synchronized void setBankMoney(AtomicInteger amount) {
		 this.total = amount;
	}
	

	/**
	 * The bank starts with 120 coins.
	 * Constructor
	 */
	public Bank() {
		total = new AtomicInteger(120); // 17*$5 +  35*$1
	}
	/**
	 * Get's total amount available at the time.
	 * @return
	 */
	public synchronized int getTotal() {
		return this.total.intValue();
	}
	/**
	 * Deposit a cetain amount into the bank.
	 * @param amount
	 */
	public synchronized void deposit(int amount) {
		total.getAndAdd(amount);
	}
	/**
	 * Check if there's enough funds in the bank.
	 * @param amount
	 * @return
	 */
	public synchronized boolean hasEnoughFunds(int amount) {
		if(total.intValue() >= amount) return true;
		
		return false;
	}
	/**
	 * Withdraw a certain amount from the bank.
	 * @param amount
	 * @return
	 */
	public synchronized boolean withdraw(int amount) {
		if( this.hasEnoughFunds(amount) ) {
			total.getAndAdd(-1*amount);
			return true;
		}
		else {
			System.out.println("Not enough money in bank to withdraw!");
		}
		
		return false;
	}
	/**
	 * Transfer funds to a specified Player
	 * @param receipient
	 * @param amount
	 * @return
	 */
	public synchronized boolean transferFunds(Player receipient, int amount) {
		if( this.hasEnoughFunds(amount) ) {
			this.withdraw(amount);
			receipient.transferMoney(amount);
			return true;
		}
		else {
			System.out.println("Not enough money in bank to transfer money to Player!");
		}
		
		return false;
	}
	/**
	 * to String method for Bank.
	 */
	@Override public String toString(){
		return "The Discworld Ankh-Morpork Bank currently has $" + total;
	}
}
