# video-poker
Text-based video poker game to be run on a terminal programmed in Java.

This program has 4 classes: Game, Player,
Card, and Deck, wherein player/card/deck all communicate with game in order to 
make the video poker game possible. The Deck class is responsible for generating
the 52-card deck of cards used in poker, as well as methods related to the deck
like shuffling it and dealing a card from it. The Player class is responsible
for keeping track of the video poker player's scoreboard while playing the game,
and has methods allowing the player to make a bet, to adjust the player's 
bankroll, and to access the player's bankroll in order to serve that purpose. 
The Card class is responsible for generating individual cards for the video
poker game, and has methods that access a given card's rank/suit as well as 
neatly print the contents of a card. It also implements a compareTo in order
to enable the cards to be sorted in the Game class, which is necessary in order
to make methods that will easily evaluate the contents of a player's hand, as 
well as has a method to swap two Card objects in order to facilitate shuffling
in the Deck class. 


The Game class, to which the Player, Card, and Deck classes all communicate, 
contains the methods directly related to the Game itself. There is an exchange
method to exchange the cards the player designates they want to exchange, 
a method that allows the player to choose whether or not to play again,
a checkHand() method which calls upon a vast array of helper methods named after
different types of hands (such as a royal flush or straight) in order to
evaluate the player's hand, and a sort method to sort the player's cards. 
Furthermore, in order to facilitate testing of the game, while one Game 
constructor is used to play another game, another Game constructor is used
explicitly to test the game's code. It allows one to directly input cards
in the command line and for them to be added to the player's hand and evaluated,
so that one can easily see whether, for instance, cards are being properly
evaluated as a certain type of hand, like a royal flush or full house. To this
end, there is also a testPlay method that is only called in PokerTest if command
line arguments are given, and if so, evaluates the hand that was passed and
prints the result.

In order to play this game, compile and run PokerTest.java, enter the amount of
tokens you'd like to exchange (integer between 1-5), then answer which cards
you'd like to exchange, after which your card will be evaluated and your
bankroll updated. You can then decide whether to continue playing or to finish
the game.
