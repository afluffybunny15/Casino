package game;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import table.Card;
import table.Deck;
import table.Player;

public class GameManager {
    private final ArrayList<Player> players;
    private final Scanner           input;
    private static Deck             deck = new Deck( 4 );

    public GameManager ( final ArrayList<Player> players, final Scanner input ) {

        // 8000000
        // Eight million
        // 8,000,000 hands of blackjack
        this.input = input;
        this.players = players;

    }

    public Queue<Player> setupRound () {

        final Queue<Player> order = new ConcurrentLinkedQueue<Player>();
        for ( final Player p : players ) {
            if ( p != null ) {
                if ( p.getChips() > 0 ) {
                    order.add( p );
                }
                else {
                    System.out.println( "Player " + p.getName() + " is out of money and has been removed." );
                    players.remove( p );
                }
            }
        }
        final Player dealer = new Player( "Dealer", 10000 );
        order.add( dealer );
        dealer.setDealer( true );

        return order;
    }

    public Queue<Hand> getBets ( final Queue<Player> order ) {
        final Queue<Hand> hands = new ConcurrentLinkedQueue<Hand>();
        for ( final Player p : order ) {
            if ( p.getDealer() ) {
                final int bet = 0;
                final Hand hand = new Hand( bet );
                p.addHand( hand );
                hands.add( hand );
                continue;
            }
            int numHands = -1;
            do {
                System.out.println( "How many hands would you like to play, " + p.getName() + "?" );
                numHands = input.nextInt();
            }
            while ( numHands <= 0 );

            for ( int i = 0; i < numHands; i++ ) {
                double bet = -1;
                do {
                    System.out.println( "For hand #" + ( i + 1 ) + ", how much would you like to bet?" );
                    bet = input.nextDouble();
                }
                while ( bet < 0 || bet >= p.getChips() );
                p.removeChips( bet );
                System.out.println( p.getName() + " now has " + p.getChips() + " chips remaining." );
                final Hand hand = new Hand( bet );
                p.addHand( hand );
                hands.add( hand );
            }
        }
        input.nextLine();
        return hands;
    }

    public void deal ( final Queue<Hand> hands ) {
        for ( final Hand h : hands ) {
            h.addCard( deck.deal() );
        }
        for ( final Hand h : hands ) {
            h.addCard( deck.deal() );
        }
    }

    public void collect ( final Queue<Player> players, final Queue<Hand> hands ) {
        deck.discardCards( players, hands );
    }

    public Card deal () {
        return deck.deal();
    }

}
