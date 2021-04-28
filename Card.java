/**
 * This class represents a card in the video poker game. 
 * 
 * @author: Martina Maximovich
 * @date: 03/29/21
 * @uni: mm5775
*/

public class Card implements Comparable<Card>{
	
    private int suit; // The suit of a card, encoded by integers 1-4.
    private int rank; // The rank of a card, encoded by integers 1-13.
    
    // This constructor accepts two integers as input and initializes
    // the instance variables suit and rank.
    public Card(int s, int r){
        //make a card with suit s and rank r
        suit = s;
        rank = r;
    }
	
    // This method compares cards by rank (and suit if they're tied by rank)
    // so that they may be easily sorted.
    public int compareTo(Card c){
        int answer = 0;
        if (this.rank < c.rank) {
            answer = -1;
        }
        
        if (this.rank > c.rank) {
            answer = 1;
        }
        
        if (this.rank == c.rank) {
            if (this.suit < c.suit) {
                answer = -1;
            }
            
            if (this.suit > c.suit) {
                answer = 1;
            }
            
        }
        return answer;

    }
  
    // This method converts a card's integer rank into a string.
    private String rankToString() {
        String stringRank = "";
        
        if (rank == 1) {
            stringRank = "A";
        }
        
        else if (rank == 11) {
            stringRank = "J";
        }
        
        else if (rank == 12) {
            stringRank = "Q";
        }
        
        else if (rank == 13) {
            stringRank = "K";
        }
        
        else {
            stringRank = Integer.toString(rank);
        }
        
        return stringRank;
        
    }
    
    // This method converts a card's integer suit into a string.
    private String suitToString() {
        String stringSuit = "";
        
        if (suit == 1) {
            stringSuit = "Club";
        }
        
        else if (suit == 2) {
            stringSuit = "Diamond";
        }
        
        else if (suit == 3) {
            stringSuit = "Heart";
        }
        
        else if (suit == 4) {
            stringSuit = "Spade";
        }
        
        return stringSuit;
        
    }

    // This method prints a Card object.
    public String toString(){
        String stringSuit = suitToString();
        String stringRank = rankToString();
        
        String printCard = stringSuit + " " + stringRank;
        
        return printCard;
    }
    
    // This method swaps the contents of two card objects.
    public void swapCard(Card card2) { 
        int originalSuit = this.suit;
        int originalRank = this.rank;
        
        this.suit = card2.suit;
        this.rank = card2.rank;
        
        card2.suit = originalSuit;
        card2.rank = originalRank;
           
    }
    
    // This is an accessor method for the instance variable suit.
    public int returnSuit() {
        return this.suit;
    }
    
    // This is an accessor method for the instance variable rank.
    public int returnRank() {
        return this.rank;
    }

}
