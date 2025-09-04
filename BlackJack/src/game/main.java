package game;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import table.Card;
import table.Player;

public class main {
    public static int         rounds = 100;
    public static GameManager gm;

    public static void main ( final String[] args ) throws InterruptedException {
        final Scanner input = new Scanner( System.in );
        final Player player1 = new Player( "Andrew", 1000 );
        final ArrayList<Player> players = new ArrayList<Player>();
        players.add( player1 );
        gm = new GameManager( players, input );
        while ( true ) {
            // Begin game loop
            final Queue<Player> order = gm.setupRound();
            final Queue<Hand> hands = gm.getBets( order );

            gm.deal( hands );
            blackjacks( order );
            final Player dealer = getDealer( order );
            stayHitSplitDoubleLoop( order, input );
            final int dealerSum = dealersTurn( dealer, dealer.getHands().getFirst() );
            payout( dealerSum, order );
            printFull( order );
            Thread.sleep( 5000 );
            gm.collect( order, hands );
        }

    }

    private static void printHands ( final Queue<Player> order ) {
        for ( int i = 0; i < 50; i++ ) {
            System.out.println();
        }
        for ( final Player p : order ) {
            if ( p.getDealer() ) {

                System.out
                        .print( Card.cardCat( p.getHands().getFirst().getCards().get( 0 ).toString(), Card.blank() ) );
                System.out.println( "   " + p.getName() + "   Total: " + p.getHands().getFirst().getSum() );
            }
            else {
                for ( final Hand h : p.getHands() ) {
                    System.out.print( Card.cardCat( h.getCards() ) );
                    System.out.println( p.getName() + ": " + h.getBet() + "   Total: " + h.getSum() );
                }
            }

        }
    }

    private static void printFull ( final Queue<Player> order ) {
        for ( int i = 0; i < 50; i++ ) {
            System.out.println();
        }
        for ( final Player p : order ) {
            for ( final Hand h : p.getHands() ) {

                System.out.print( Card.cardCat( h.getCards() ) );
                System.out.println(
                        "   " + p.getName() + ": " + h.getBet() + "   Total: " + p.getHands().getFirst().getSum() );
            }
        }
    }

    private static void stayHitSplitDoubleLoop ( final Queue<Player> order, final Scanner input ) {
        for ( final Player p : order ) {
            if ( !p.getDealer() ) {
                for ( int i = 0; i < p.getHands().size(); i++ ) {
                    final Hand h = p.getHands().get( i );
                    if ( h.getDone() ) {
                        continue;
                    }
                    boolean going = true;
                    do {
                        printHands( order );
                        System.out.println( "---OPTIONS---" );
                        System.out.println( "        Hit: Draw another card for this hand" );
                        System.out.println( "      Stand: Keep your current cards for this hand" );
                        if ( h.canDoubleDown( p ) ) {
                            System.out.println( "Double Down: Double your bet, draw a single card then stand" );
                        }
                        if ( h.canSplit( p ) ) {
                            System.out
                                    .println( "      Split: Turn your current hand into two hands with the same bet" );
                        }
                        final String option = input.nextLine();

                        switch ( option.toLowerCase() ) {
                            case "hit":
                            case "h":
                                if ( !hit( p, h ) ) {
                                    going = false;
                                }
                                break;
                            case "stay":
                            case "stand":
                            case "st":
                                going = false;
                                break;
                            case "split":
                            case "sp":
                                if ( h.canSplit( p ) ) {
                                    split( p, h );
                                }
                                break;
                            case "double":
                            case "d":
                            case "dd":
                            case "double down":
                                if ( h.canDoubleDown( p ) ) {
                                    doubleDown( p, h );
                                    going = false;
                                }
                                break;

                        }
                    }
                    while ( going );
                }

            }
        }
    }

    private static boolean hit ( final Player p, final Hand h ) {
        h.addCard( gm.deal() );
        return h.getSum() <= 21;
    }

    private static void split ( final Player p, final Hand h ) {
        final Hand newHand = new Hand( h.getBet(), h.removeCard( 1 ) );
        p.addHand( newHand, p.getHands().indexOf( h ) + 1 );
        h.addCard( gm.deal() );
        newHand.addCard( gm.deal() );
    }

    private static boolean doubleDown ( final Player p, final Hand h ) {
        h.doubleDown();
        h.addCard( gm.deal() );
        return h.bust();
    }

    private static Player getDealer ( final Queue<Player> order ) {
        for ( final Player p : order ) {
            if ( p.getDealer() ) {
                return p;
            }
        }
        return null;
    }

    private static int dealersTurn ( final Player p, final Hand h ) {
        while ( h.getSum() < 17 ) {
            hit( p, h );
        }
        return h.getSum();
    }

    private static void payout ( final int dealerSum, final Queue<Player> players ) {
        for ( final Player p : players ) {
            for ( final Hand h : p.getHands() ) {
                if ( h.getSum() <= 21 && ( h.getSum() > dealerSum || dealerSum > 21 ) ) {
                    p.addChips( h.getBet() * 2 );
                }
                else if ( h.getSum() == dealerSum ) {
                    p.addChips( h.getBet() );
                }
            }
        }
    }

    private static ArrayList<Hand> blackjacks ( final Queue<Player> players ) {
        final ArrayList<Hand> bjs = new ArrayList<Hand>();
        for ( final Player p : players ) {
            for ( int i = 0; i < p.getHands().size(); i++ ) {
                final Hand h = p.getHands().get( i );
                if ( h.getSum() == 21 ) {
                    p.addChips( h.getBet() * 2.5 );
                    bjs.add( h );
                    h.setDone( true );
                }

            }
        }
        return bjs;

    }

}
