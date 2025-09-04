package table;

import java.util.ArrayList;
import java.util.Queue;

import game.Hand;
import table.Card.Rank;
import table.Card.Suit;

public class Deck {
    ArrayList<Card> cards;
    ArrayList<Card> discard;

    public Deck ( final int numDecks ) {
        cards = initDeck( numDecks );
    }

    public ArrayList<Card> getCards () {
        return cards;
    }

    public Card deal () {
        if ( cards.size() == 0 ) {
            cards = shuffle( discard );
        }
        return cards.removeLast();
    }

    private ArrayList<Card> initDeck ( final int numDecks ) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for ( int i = 0; i < numDecks; i++ ) {
            for ( final Suit suit : Card.Suit.values() ) {
                for ( final Rank rank : Card.Rank.values() ) {
                    cards.add( new Card( suit, rank ) );
                }
            }
        }
        cards = shuffle( cards );
        discard = new ArrayList<Card>();
        return cards;
    }

    private ArrayList<Card> shuffle ( final ArrayList<Card> cards ) {
        final ArrayList<Card> shuffled = new ArrayList<Card>();
        for ( final Card card : cards ) {
            shuffled.add( (int) ( Math.random() * ( shuffled.size() + 1 ) ), card );
        }
        return shuffled;
    }

    public void discardCards ( final Queue<Player> players, final Queue<Hand> hands ) {
        for ( final Hand h : hands ) {
            for ( final Card c : h.getCards() ) {
                discard.add( c );
            }
        }
        for ( final Player p : players ) {
            p.removeHands();
        }
    }

}
