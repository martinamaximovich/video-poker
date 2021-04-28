/**
 * This class represents a player in the video poker game.
 * 
 * @author: Martina Maximovich
 * @date: 03/29/21
 * @uni: mm5775
*/

public class Player {
	
		
    private int bankroll; // The amount of tokens the player has.
    private int bet; // The amount of tokens the player bets in a round.
	
    // This constructor takes no inputs and initializes the instance variables
    // bankroll and bet.
    public Player(){		
        bankroll = 100;
        bet = 0;
    }
	
    // This method allows the player to make a bet and adjusts their
    // bankroll to reflect that bet.
    public void bets(int amt){
        int amount = amt;
        bet = amount;
        
        bankroll -= bet;
    }
    
    // This method adjusts the bankroll if the player wins by the 
    // multiple of their bet and the winnings of their card.
    public void winnings(int odds){
        bankroll += bet * odds; 
    }
    
    // This is an accessor method for the instance variable bankroll.
    public int getBankroll(){
        return bankroll;
    }

    
}


