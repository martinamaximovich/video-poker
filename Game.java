/** 
 * This class represents a Video Poker game.
 * 
 * @author: Martina Maximovich
 * @date: 03/29/21
 * @uni: mm5775
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
    private Player p; // The player in the video poker game.
    private Deck cards; // The deck of cards to draw cards from.
    private ArrayList<Card> hand; // The player's cards.
    private int currentWinnings; // The multiplier of the player's current hand.
	
    // This constructor is utilized to test the game's code. It takes an 
    // array of strings as a command line argument, interprets them as cards,
    // and adds them to them to the player's hand.
    public Game(String[] testHand) {
        hand = new ArrayList<Card>();
        
        for (int i = 0; i < testHand.length; i++) {
            String currentCard = testHand[i];
            String stringSuit = currentCard.substring(0, 1);
            int suit = 0;
            int rank = 0;
            
            if (currentCard.length() == 2) {
                rank = Integer.parseInt(currentCard.substring(1, 2));
            }
            
            else if (currentCard.length() == 3) {
                rank = Integer.parseInt(currentCard.substring(1, 3));
            }
            
            
            if (stringSuit.equals("c")) {
                suit = 1;
            }
            
            else if (stringSuit.equals("d")) {
                suit = 2;
            }
            
            else if (stringSuit.equals("h")) {
                suit = 3;
            }
            
            else if (stringSuit.equals("s")) {
                suit = 4;
            }
            
            Card card = new Card(suit, rank);
            hand.add(card);
            
        }
		
    }
	
    // This no-argument constructor is to actually play a normal game.
    // It initializes the instance variables p, the player in the game,
    // cards, the deck of cards, hand, the cards that the player has, and
    // currentWinnings, the multiplier of the player's most recently
    // evaluated card.
    public Game() {
        p = new Player();
        cards = new Deck();
        hand = new ArrayList<Card>();
        currentWinnings = 0;
    }
	
    // This method plays the video poker game.
    public void play() {
        boolean incorrectInput = true;
        int humanBet = 0;
        Scanner scanner = new Scanner(System.in);
        
        cards.shuffle();
        
        while (incorrectInput) {
            System.out.println("\nYou have " + p.getBankroll() + " tokens.");
            System.out.println("Please enter a bet of 1-5 tokens.");
            humanBet = Integer.parseInt(scanner.next());
            
            if (humanBet >= 1 && humanBet <= 5) {
                incorrectInput = false;
            }
        }
        
        p.bets(humanBet);
       
        for (int i = 0; i < 5; i++) {
            hand.add(cards.deal());
        }
        
        System.out.println("\nHere are your cards:");
        sort();
        
        for (Card card : hand) {
            System.out.println(card);
        }
        
        exchange();
        
        System.out.println("\nHere are your cards:");
        
        sort(); 
        
        for (Card card : hand) {
            System.out.println(card);
        }
        
        checkHand();
        
        p.winnings(currentWinnings);
        
        System.out.println("You now have " + p.getBankroll() + " tokens."); 
        
        playAgain();
	
    }
    
    // This method exchanges the cards the user responds they want
    // to exchange with cards from the top of the deck.
    private void exchange() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nDo you want to exchange your first card?" + 
                           " Enter yes or no.");
        String exchange1 = scanner.next();
        if (exchange1.equals("yes")) {
            hand.set(0, cards.deal());
        }
        
        System.out.println("Do you want to exchange your second card?" +
                          " Enter yes or no.");
        String exchange2 = scanner.next();
        if (exchange2.equals("yes")) {
            hand.set(1, cards.deal());
        }
        
        
        System.out.println("Do you want to exchange your third card?" +
                          " Enter yes or no.");
        String exchange3 = scanner.next();
        if (exchange3.equals("yes")) {
            hand.set(2, cards.deal());
        }
        
        System.out.println("Do you want to to exchange your fourth card?" +
                           " Enter yes or no.");
        String exchange4 = scanner.next();
        if (exchange4.equals("yes")) {
            hand.set(3, cards.deal());
            
        }
        
        System.out.println("Do you want to exchange your fifth card?" +
                           " Enter yes or no.");
        String exchange5 = scanner.next();
        if (exchange5.equals("yes")) {
            hand.set(4, cards.deal());
        }
            
    }
    
    // This method allows the user to play again if desired, and shows them
    // their final amount of tokens if they do not want to play again.
    private void playAgain() {
        System.out.println("\nWould you like to play another round? Enter" +
                           " 'yes' or 'no.'");
        
        Scanner scanner = new Scanner(System.in);
        String playAgain = scanner.next();
        
        if (playAgain.equals("yes")) {
            hand.clear();
            play();
        }
        
        else if (playAgain.equals("no")) {
            System.out.println("\nYou finished the game with " + 
                               p.getBankroll() + " tokens.");
        }
                           
    }
    
    // This method is used for testing the checkHand method. It
    // evaluates the hand that was passed as a command-line
    // argument and prints the result.
    public void testPlay() {        
         sort();
         checkHand();
    }
	
    // This method evaluates a hand, prints the result to the console so 
    // the user can see it, and returns the multiplier that the bet should 
    // be multiplied by.
	 public int checkHand() {                
        if (royalFlush()) {
            System.out.println("\nYou got a royal flush!");
        }
        
        else if (straightFlush()) {
            System.out.println("\nYou got a straight flush!");
        }
        
        else if (fourOfAKind()) {
            System.out.println("\nYou got a four of a kind!");
        }
        
        else if (fullHouse()) {
            System.out.println("\nYou got a full house!");
        }
        
        else if (flush()) {
            System.out.println("\nYou got a flush!");
        }
        
        else if (straight()) {
            System.out.println("\nYou got a straight!");
        }
        
        else if (threeOfAKind()) {
            System.out.println("\nYou got a three of a kind!");
        }
        
        else if (twoPair()) {
            System.out.println("\nYou got two pairs!");
        }
        
        else if (onePair()) {
            System.out.println("\nYou got one pair!");
        }
        
        else if (noPair()) {
            System.out.println("\nYou got a no pair.");
        }
         
        return currentWinnings;
		
    }
      
    // This method checks if a hand is a royal flush.
    private boolean royalFlush() {  
        boolean royalFlush = false;
        
        if (royalStraight() && flush()) {
               royalFlush = true;
               currentWinnings = 250;
        }
        
        return royalFlush;
        
    }
    
    // This method checks if a hand is a royal straight.
    private boolean royalStraight() { 
        boolean royalStraight = false;
        
        int firstRank = hand.get(0).returnRank();
        int secondRank = hand.get(1).returnRank();
        int thirdRank = hand.get(2).returnRank();
        int fourthRank = hand.get(3).returnRank();
        int fifthRank = hand.get(4).returnRank();
        
        if (firstRank == 1 && secondRank == 10 && 
           thirdRank == 11 && fourthRank == 12 &&
           fifthRank == 13) {
            royalStraight = true;
        }
        
        return royalStraight;
    }
    
    // This method checks if a hand is a straight flush.
    private boolean straightFlush() {
        if (straight() && flush()) {
            currentWinnings = 50;
        }
        
        return straight() && flush();
    }
    
    // This method checks if a hand is a four of a kind.
    private boolean fourOfAKind() {
        boolean fourOfAKind = false;
     
        for (int i = 0; i < hand.size(); i++) {
            int counter = 0;
            Card currentCard = hand.get(i);
            for (int j = 0; j < hand.size(); j++) {
                Card nextCard = hand.get(j);
                if (currentCard.returnRank() == nextCard.returnRank()) {
                    counter++;
                } 
            }
            if (counter == 4) {
                fourOfAKind = true;
                currentWinnings = 25;
            }
        }
        
        return fourOfAKind;
    }
        
    // This method checks if a hand is a flush.
    private boolean flush() {
        boolean flush = true;
        
        // checks whether next card has same suit
        for (int i = 0; i < hand.size() - 1; i++) {
            Card currentCard = hand.get(i);
            Card nextCard = hand.get(i + 1);
            
            if (currentCard.returnSuit() != nextCard.returnSuit()) { 
                flush = false;
            }
        }
        
        if (flush == true) {
            currentWinnings = 5;
        }
        
        return flush;
        
    }
    
    // This method checks if a hand is a full house.
    private boolean fullHouse() {
        boolean fullHouse = false;
        
        int firstRank = hand.get(0).returnRank();
        int secondRank = hand.get(1).returnRank();
        int thirdRank = hand.get(2).returnRank();
        int fourthRank = hand.get(3).returnRank();
        int fifthRank = hand.get(4).returnRank();
        
        if ((firstRank == secondRank) && (secondRank == thirdRank) &&
            (fourthRank == fifthRank)) {
            fullHouse = true;
            currentWinnings = 6;
        }
        
        else if ((thirdRank == fourthRank) && (fourthRank == fifthRank) &&
                (firstRank == secondRank)) {
            fullHouse = true;
            currentWinnings = 6;
        }
        
        return fullHouse;
    }
    
    // This method checks if a hand is a straight.
    private boolean straight() { 
        boolean straight = true;
        
        // checks whether rank of next card is 1 more than rank of 
        // current card
        for (int i = 0; i < hand.size() - 1; i++) {
            Card currentCard = hand.get(i);
            Card nextCard = hand.get(i + 1);
            
            if (currentCard.returnRank() != nextCard.returnRank() - 1) { 
                straight = false;
            }
            
            // make exception for royal straight
            if (royalStraight()) {
                straight = true;
            }
        }
        
        if (straight == true) {
            currentWinnings = 4;
        }
        
        return straight;
    }
    
    // This method checks if a hand is a three of a kind.
    private boolean threeOfAKind() { 
        boolean threeOfAKind = false; 
        for (int i = 0; i < hand.size(); i++) {
            int counter = 0;
            Card currentCard = hand.get(i);
            for (int j = 0; j < hand.size(); j++) {
                Card nextCard = hand.get(j);
                if (currentCard.returnRank() == nextCard.returnRank()) {
                    counter++;
                }   
            }
            if (counter == 3) {
                threeOfAKind = true;
                currentWinnings = 3;
            }
        }
        
        return threeOfAKind;
    }
    
    // This method checks if a hand is a two pair.
    private boolean twoPair() { 
        int pairCounter = 0;
        boolean twoPair = false;
        
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            for (int j = 0; j < hand.size(); j++) {
                Card nextCard = hand.get(j);
                if (i != j) {
                    if (currentCard.returnRank() == nextCard.returnRank()) {
                        pairCounter++;
                    }
                }
            }
        }
        
        if (pairCounter == 4) { // pairCounter = (# pairs) * 2
            twoPair = true;
            currentWinnings = 2;
        }
        
        return twoPair;
    }
   
    // This method checks if a hand is a one pair.
    private boolean onePair() {
        boolean onePair = false;
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            for (int j = 0; j < hand.size(); j++) {
                Card nextCard = hand.get(j);
                if (i != j) { 
                    if (currentCard.returnRank() == nextCard.returnRank()) {
                        onePair = true;
                        currentWinnings = 1;
                    }
                }
            }
        }
        
        return onePair;
    }
    
    // This method checks if a hand is a no pair.
    private boolean noPair() {
        boolean noPair = true;
        if (onePair() || twoPair() || threeOfAKind() || straight() || flush() ||
           fullHouse() || fourOfAKind() || straightFlush() || royalFlush()) {
            noPair = false;
        }
        
        if (noPair == true) {
            currentWinnings = 0;
        }
        
        return noPair;    
    }
    
    
    // This method sorts the player's hand according to rank, and if there
    // is a tie looks at the suit.
    private void sort() {
        Collections.sort(hand);
    }
	

}
