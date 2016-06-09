package com.agilesumo.fortyfive;

/**
 *  Created by andy on 31/05/2016
 * An object of type Card represents a playing card from a
 * standard Poker deck, including Jokers.  The card has a suit, which
 * can be spades, hearts, diamonds, clubs, or joker.  A spade, heart,
 * diamond, or club has one of the 13 values: ace, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, jack, queen, or king.  Note that "ace" is considered to be
 * the smallest value.  A joker can also have an associated value;
 * this value can be anything and can be used to keep track of several
 * different jokers.
 */
public class Card {

    // Codes for the 4 suits, plus Joker.
    public final static int HEARTS = 0;
    public final static int DIAMONDS = 1;
    public final static int CLUBS = 2;
    public final static int SPADES = 3;
    public final static int JOKER = 4;

    public final static int ACE = 1;      // Codes for the non-numeric cards.
    public final static int JACK = 11;    //   Cards 2 through 10 have their
    public final static int QUEEN = 12;   //   numerical values for their codes.
    public final static int KING = 13;

    // spades images no duplicates
    private static Integer[] spadesImageIds = {
            R.drawable.ace_spades,
            R.drawable.two_spades,
            R.drawable.three_spades,
            R.drawable.four_spades,
            R.drawable.five_spades,
            R.drawable.six_spades,
            R.drawable.seven_spades,
            R.drawable.eight_spades,
            R.drawable.nine_spades,
            R.drawable.ten_spades,
            R.drawable.jack_spades,
            R.drawable.queen_spades,
            R.drawable.king_spades
    };

    // clubs images no duplicates
    private static Integer[] clubsImageIds = {
            R.drawable.ace_clubs,
            R.drawable.two_clubs,
            R.drawable.three_clubs,
            R.drawable.four_clubs,
            R.drawable.five_clubs,
            R.drawable.six_clubs,
            R.drawable.seven_clubs,
            R.drawable.eight_clubs,
            R.drawable.nine_clubs,
            R.drawable.ten_clubs,
            R.drawable.jack_clubs,
            R.drawable.queen_clubs,
            R.drawable.king_clubs
    };

    // hearts images no duplicates
    private static Integer[] heartsImageIds = {
            R.drawable.ace_hearts,
            R.drawable.two_hearts,
            R.drawable.three_hearts,
            R.drawable.four_hearts,
            R.drawable.five_hearts,
            R.drawable.six_hearts,
            R.drawable.seven_hearts,
            R.drawable.eight_hearts,
            R.drawable.nine_hearts,
            R.drawable.ten_hearts,
            R.drawable.jack_hearts,
            R.drawable.queen_hearts,
            R.drawable.king_hearts
    };

    // diamonds images no duplicates
    private static Integer[] diamondsImageIds = {
            R.drawable.ace_diamonds,
            R.drawable.two_diamonds,
            R.drawable.three_diamonds,
            R.drawable.four_diamonds,
            R.drawable.five_diamonds,
            R.drawable.six_diamonds,
            R.drawable.seven_diamonds,
            R.drawable.eight_diamonds,
            R.drawable.nine_diamonds,
            R.drawable.ten_diamonds,
            R.drawable.jack_diamonds,
            R.drawable.queen_diamonds,
            R.drawable.king_diamonds
    };

    /**
     * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
     * CLUBS, or JOKER.  The suit cannot be changed after the card is
     * constructed.
     */
    private final int suit;

    /**
     * The card's value.  For a normal card, this is one of the values
     * 1 through 13, with 1 representing ACE.  For a JOKER, the value
     * can be anything.  The value cannot be changed after the card
     * is constructed.
     */
    private final int value;

    /**
     * Creates a Joker, with 1 as the associated value.  (Note that
     * "new Card()" is equivalent to "new Card(1,Card.JOKER)".)
     */
    public Card() {
        suit = JOKER;
        value = 1;
    }

    /**
     * Creates a card with a specified suit and value.
     * @param theValue the value of the new card.  For a regular card (non-joker),
     * the value must be in the range 1 through 13, with 1 representing an Ace.
     * You can use the constants Card.ACE, Card.JACK, Card.QUEEN, and Card.KING.
     * For a Joker, the value can be anything.
     * @param theSuit the suit of the new card.  This must be one of the values
     * Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS, or Card.JOKER.
     * @throws IllegalArgumentException if the parameter values are not in the
     * permissible ranges
     */
    public Card(int theValue, int theSuit) {
        if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS &&
                theSuit != CLUBS && theSuit != JOKER)
            throw new IllegalArgumentException("Illegal playing card suit");
        if (theSuit != JOKER && (theValue < 1 || theValue > 13))
            throw new IllegalArgumentException("Illegal playing card value");
        value = theValue;
        suit = theSuit;
    }

    /**
     * Returns the suit of this card.
     * @returns the suit, which is one of the constants Card.SPADES,
     * Card.HEARTS, Card.DIAMONDS, Card.CLUBS, or Card.JOKER
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Returns the value of this card.
     * @return the value, which is one of the numbers 1 through 13, inclusive for
     * a regular card, and which can be any value for a Joker.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a String representation of the card's suit.
     * @return one of the strings "Spades", "Hearts", "Diamonds", "Clubs"
     * or "Joker".
     */
    public String getSuitAsString() {
        switch ( suit ) {
            case SPADES:   return "Spades";
            case HEARTS:   return "Hearts";
            case DIAMONDS: return "Diamonds";
            case CLUBS:    return "Clubs";
            default:       return "Joker";
        }
    }

    /**
     * Returns a String representation of the card's value.
     * @return for a regular card, one of the strings "Ace", "2",
     * "3", ..., "10", "Jack", "Queen", or "King".  For a Joker, the
     * string is always numerical.
     */
    public String getValueAsString() {
        if (suit == JOKER)
            return "" + value;
        else {
            switch ( value ) {
                case 1:   return "Ace";
                case 2:   return "2";
                case 3:   return "3";
                case 4:   return "4";
                case 5:   return "5";
                case 6:   return "6";
                case 7:   return "7";
                case 8:   return "8";
                case 9:   return "9";
                case 10:  return "10";
                case 11:  return "Jack";
                case 12:  return "Queen";
                default:  return "King";
            }
        }
    }

    /**
     * Returns a string representation of this card, including both
     * its suit and its value (except that for a Joker with value 1,
     * the return value is just "Joker").  Sample return values
     * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades",
     * "Joker", "Joker #2"
     */
    public String toString() {
        if (suit == JOKER) {
            if (value == 1)
                return "Joker";
            else
                return "Joker #" + value;
        }
        else
            return getValueAsString() + " of " + getSuitAsString();
    }

    /**
     * Returns a String representation of the card's value.
     * @return for a regular card, one of the strings "Ace", "2",
     * "3", ..., "10", "Jack", "Queen", or "King".  For a Joker, the
     * string is always numerical.
     */
    public int getCardImageId() {

        if(suit == SPADES){
            return spadesImageIds[value-1];
        }
        else if(suit == CLUBS){
            return clubsImageIds[value-1];
        }
        else if(suit == HEARTS){
            return heartsImageIds[value-1];
        }
        else{
            return diamondsImageIds[value-1];
        }


    }



} // end class Card