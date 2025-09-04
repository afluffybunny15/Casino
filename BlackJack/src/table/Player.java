package table;

import java.util.ArrayList;

import game.Hand;

public class Player {
    private String          name;
    private ArrayList<Hand> hands;
    private double          chips;
    private boolean         dealer = false;

    public Player ( final String name ) {
        this( name, 0 );
    }

    public Player ( final String name, final double chips ) {
        this.name = name;
        this.chips = chips;
        this.hands = new ArrayList<Hand>();
    }

    public void setName ( final String name ) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }

    public void addHand ( final Hand hand ) {
        addHand( hand, hands.size() );
    }

    public void addHand ( final Hand hand, final int idx ) {
        hands.add( idx, hand );
    }

    public void removeHand ( final Hand hand ) {
        hands.remove( hand );
    }

    public ArrayList<Hand> getHands () {
        return hands;
    }

    public double getChips () {
        return chips;
    }

    public void addChips ( final double chips ) {
        if ( chips > 0 ) {
            this.chips += chips;
        }
    }

    public void setChips ( final double chips ) {
        if ( chips > 0 ) {
            this.chips = chips;
        }
    }

    public void setDealer ( final boolean dealer ) {
        this.dealer = dealer;
    }

    public boolean getDealer () {
        return dealer;
    }

    public void removeChips ( final double chips ) {
        if ( this.chips - chips < 0 ) {
            throw new IllegalArgumentException( "Not enough chips" );
        }
        this.chips -= chips;
    }

    public void removeHands () {
        this.hands = new ArrayList<Hand>();
    }
}
