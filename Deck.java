/**
 * This class represents a deck of cards in the video poker game. 
 * 
 * @author: Martina Maximovich
 * @date: 03/29/21
 * @uni: mm5775
*/

import java.util.Arrays; 
import java.util.Random; 
public class Deck {
	
    private Card[] cards; // the cards in the deck
    private int top; //the index of the top of the deck

    // This constructor initializes the instance variables cards, an array
    // of Card objects that represents the deck of cards, and top,
    // which represents the index of the top of the deck. 
    public Deck() {
        //make a 52 card deck here
        cards = new Card[52];
        
        int i = 0;
        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[i] = new Card(suit, rank); 
                i++;
            }
        }
       
        top = 0; 
    }
	
    // This method implements the Fisher-Yates algorithm to shuffle the deck.
    public void shuffle(){
        for (int i = 0; i < cards.length; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(52 - i) + i;
            
            Card currentCard = cards[i];
            Card otherCard = cards[randomIndex];
            
            currentCard.swapCard(otherCard);
     
        }
        
        top = 0; 
    }
    
    // This method deals a card by returning a card at the top of the deck.
    public Card deal(){ 
        Card card = cards[top];
        top++;
        
        return card;
    }
	

}
