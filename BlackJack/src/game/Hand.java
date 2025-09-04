package game;

import java.util.ArrayList;

import table.Card;
import table.Player;

public class Hand {
    private final ArrayList<Card> cards;
    private double                bet;
    private boolean               done;

    public Hand ( final double bet ) {
        this( bet, null );
    }

    public Hand ( final double bet, final Card c ) {
        this.cards = new ArrayList<Card>();
        if ( c != null ) {
            cards.add( c );
        }
        this.bet = bet;
        done = false;
    }

    public void reset () {
        cards.clear();

    }

    public void addCard ( final Card card ) {
        cards.add( card );
    }

    public double getBet () {
        return bet;
    }

    public void addCards ( final ArrayList<Card> cards ) {
        this.cards.addAll( cards );
    }

    public boolean canDoubleDown ( final Player p ) {
        if ( cards.size() != 2 ) {
            return false;
        }
        return ( p.getChips() >= bet );
    }

    public boolean canSplit ( final Player p ) {
        if ( cards.size() != 2 ) {
            return false;
        }
        if ( p.getChips() < bet * 2 ) {
            return false;
        }
        return ( cards.get( 0 ).isATen() && cards.get( 1 ).isATen()
                || cards.get( 0 ).getRank() == cards.get( 1 ).getRank() );
    }

    public double doubleDown () {
        bet *= 2;
        done = true;
        return bet;
    }

    public ArrayList<Card> getCards () {
        return cards;
    }

    public int getSum () {
        int total = 0;
        boolean hasAce = false;
        for ( final Card c : cards ) {
            if ( c.getRank() != Card.Rank.Ace ) {
                if ( c.isATen() ) {
                    total += 10;
                }
                else {
                    total += c.getRank().ordinal() + 2;
                }
            }
            else {
                hasAce = true;
                total += 11;
            }
        }
        if ( hasAce && total > 21 ) {
            total -= 10;
        }

        return total;
    }

    public boolean bust () {
        return getSum() > 21;
    }

    public boolean getDone () {
        return done;
    }

    public void setDone ( final boolean done ) {
        this.done = done;
    }

    public int compareTo ( final Hand o ) {
        return this.getSum() - o.getSum();
    }

    public Card removeCard ( final int idx ) {
        return cards.remove( idx );
    }

}
