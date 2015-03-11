package ca.concordia.game.gameState;

public class StateContext {
	private StateLike myState;
	
	
    StateContext() {
        setState(new StatePlay());
    }
	/**
     * Setter method for the state.
     * Normally only called by classes implementing the State interface.
     * @param newState the new state of this context
     */
    void setState(final StateLike newState) {
        myState = newState;
    }
 
    public void writeName(final String name) {
        //myState.writeName(this, name);
    }

}
