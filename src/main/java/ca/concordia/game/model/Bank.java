package ca.concordia.game.model;

import java.util.concurrent.atomic.AtomicInteger;

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
	 * 
	 */
	public Bank() {
		total = new AtomicInteger(120); // 17*$5 +  35*$1
	}
	
	public synchronized int getTotal() {
		return this.total.intValue();
	}
	
	public synchronized void deposit(int amount) {
		total.getAndAdd(amount);
	}
	
	public synchronized boolean hasEnoughFunds(int amount) {
		if(total.intValue() >= amount) return true;
		
		return false;
	}
	
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
	
	@Override public String toString(){
		return "The Discworld Ankh-Morpork Bank currently has $" + total;
	}
}
