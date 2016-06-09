package com.agilesumo.fortyfive;

import java.util.Arrays;



/**
 * Created by andy on 04/06/2016.
 */
public class Hand {

    private Card[] playersCards;

    private Card[] com1Cards;

    private Deck deck = new Deck();

    private Card upTrumpCard;

    private Card firstPlayed;

    private Card secondPlayed;

    private Player firstCardPlayer;

    private Player secondCardPlayer;

    private int playersScore;

    private int com1Score;

    public static Player player;

    public static Player computer1;

    // ranks of trump cards excluding the ace of hearts
    private static final Integer [] RANK_TRUMPS_HEARTS = {
        5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 10, 9, 8, 7 ,6, 5, 4, 3,2 };

    private static final Integer [] RANK_TRUMPS_DIAMONDS = {
        5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 10, 9, 8, 7 ,6, 5, 4, 3,2 };

    private static final Integer [] RANK_TRUMPS_SPADES = {
            5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 2, 3, 4, 5, 6, 7, 9, 10 };

    private static final Integer [] RANK_TRUMPS_CLUBS = {
            5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 2, 3, 4, 5, 6, 7, 9, 10 };


    private static final Integer [] RANK_SPADES = {
            Card.KING, Card.QUEEN, Card.JACK, Card.ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    private static final Integer [] RANK_CLUBS = {
            Card.KING, Card.QUEEN, Card.JACK, Card.ACE, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    private static final Integer [] RANK_HEARTS = {
            Card.KING, Card.QUEEN, Card.JACK, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

    private static final Integer [] RANK_DIAMONDS = {
            Card.KING, Card.QUEEN, Card.JACK, 10, 9, 8, 7, 6, 5, 4, 3, 2, Card.ACE };





    /**
     * Constructs a new Deck object containing 52 cards.
     */

     //====================origional constructer see temp one below for testing
    public Hand() {
        playersCards = new Card[5];
        com1Cards = new Card[5];
        deck.shuffle();
        for(int i=0; i<5; i++){
            playersCards[i] = deck.dealCard();
            com1Cards[i] = deck.dealCard();
        }
        upTrumpCard = deck.dealCard();

        player = new Player();
        computer1 = new Player();

        firstCardPlayer = new Player();
        secondCardPlayer = new Player();

        playersScore = 0;
        com1Score = 0;

    }

    /********** temp constructer ***********

    public Hand() {
        playersCards = new Card[5];
        com1Cards = new Card[5];
        deck.shuffle();
        for(int i=0; i<3; i++){
            playersCards[i] = deck.dealCard();
            com1Cards[i] = deck.dealCard();
        }
        playersCards[4] = new Card(Card.ACE, Card.HEARTS);
        com1Cards[4] = new Card(Card.ACE, Card.DIAMONDS);

        playersCards[3] = new Card(5, Card.CLUBS);
        //com1Cards[3] = new Card(5,Card.DIAMONDS);
        com1Cards[3] = new Card(Card.JACK, Card.DIAMONDS);


        //upTrumpCard = deck.dealCard();

        upTrumpCard = new Card(3,Card.DIAMONDS);

        player = new Player();
        computer1 = new Player();

        firstCardPlayer = new Player();
        secondCardPlayer = new Player();

        playersScore = 0;
        com1Score = 0;

    }

     */

    public Card[] getPlayersCards(){
        return playersCards;
    }

    public Card[] getCom1Cards(){
        return com1Cards;
    }

    public Card getTrumpCard(){
        return upTrumpCard;
    }

    public void setTrumpCard(Card trumpCard) {
        upTrumpCard = trumpCard;
    }

    public void setFirstPlayed(Card theFirstPlayed, Player thePlayer){
        firstPlayed = theFirstPlayed;
        firstCardPlayer = thePlayer;
    }

    public void setSecondPlayed(Card theSecondPlayed, Player thePlayer){
        secondPlayed = theSecondPlayed;
        secondCardPlayer = thePlayer;
    }

    public void updateScore(){
        if(  isTrump(firstPlayed)  && !isTrump(secondPlayed)){
            updateTrickWon(firstCardPlayer);
        }
        else
        if( isTrump(secondPlayed)  && !isTrump(firstPlayed)){
            updateTrickWon(secondCardPlayer);
        }

        // to be done
        else if( isTrump(firstPlayed) && isTrump(secondPlayed)){

            updateBothDiamondsTrumps();
            updateBothHeartsTrumps();
            updateBothSpadesTrumps();
            updateBothClubsTrumps();

        }

        else if ( !isTrump(firstPlayed) && !isTrump(secondPlayed)&& firstPlayed.getSuit() != secondPlayed.getSuit()){
            updateTrickWon(firstCardPlayer);

        }

        else if( !isTrump(firstPlayed) && !isTrump(secondPlayed)){
            updateBothSpades();
            updateBothClubs();
            updateBothDiamonds();
            updateBothHearts();
        }

    }

    public int getScore(Player thePlayer){
        if(thePlayer.equals(player)){
            return playersScore;

        }

        else if(thePlayer.equals(computer1)){
            return com1Score;
        }
        else {
            return 1;
        }
    }

    //=======================================================================================
    // Helper Methods
    //=======================================================================================

    private boolean isAceOfHearts (Card theCard){
        return ( (theCard.getSuit() == Card.HEARTS) && ( theCard.getValue() == Card.ACE) );
    }

    private  boolean isTrump (Card theCard){
        return ( (theCard.getSuit() == upTrumpCard.getSuit()) || isAceOfHearts(theCard));
    }

    private void updateTrickWon(Player wonTrickPlayer){
        if(wonTrickPlayer.equals(player)){
            playersScore += 5;
        }
        else if(wonTrickPlayer.equals(computer1)){
            com1Score +=5;

        }
    }

    private void updateBothSpades(){
        if( (firstPlayed.getSuit() == Card.SPADES ) && ( secondPlayed.getSuit() == Card.SPADES) ){

            // SEE http://stackoverflow.com/questions/6171663/how-to-find-index-of-int-array-in-java-from-a-given-value
            if( (Arrays.asList(RANK_SPADES).indexOf(firstPlayed.getValue()))  <
                    (java.util.Arrays.asList(RANK_SPADES).indexOf(secondPlayed.getValue() ))){
                        updateTrickWon(firstCardPlayer);

            }

            else if( (Arrays.asList(RANK_SPADES).indexOf(secondPlayed.getValue()))  <
                    (Arrays.asList(RANK_SPADES).indexOf(firstPlayed.getValue() ))){

                        updateTrickWon(secondCardPlayer);

            }
        }
    }

    private void updateBothClubs(){
        if( (firstPlayed.getSuit() == Card.CLUBS ) && ( secondPlayed.getSuit() == Card.CLUBS) ){

            // SEE http://stackoverflow.com/questions/6171663/how-to-find-index-of-int-array-in-java-from-a-given-value
            if( (Arrays.asList(RANK_CLUBS).indexOf(firstPlayed.getValue()))  <
                    (Arrays.asList(RANK_CLUBS).indexOf(secondPlayed.getValue() ))){
                        updateTrickWon(firstCardPlayer);


            }

            else if( (Arrays.asList(RANK_CLUBS).indexOf(secondPlayed.getValue()))  <
                    (Arrays.asList(RANK_CLUBS).indexOf(firstPlayed.getValue() ))){
                        updateTrickWon(secondCardPlayer);

            }
        }
    }

    private void updateBothDiamonds(){
        if( (firstPlayed.getSuit() == Card.DIAMONDS ) && ( secondPlayed.getSuit() == Card.DIAMONDS) ){

            // SEE http://stackoverflow.com/questions/6171663/how-to-find-index-of-int-array-in-java-from-a-given-value
            if( (Arrays.asList(RANK_DIAMONDS).indexOf(firstPlayed.getValue()))  <
                    (Arrays.asList(RANK_DIAMONDS).indexOf(secondPlayed.getValue() ))){
                        updateTrickWon(firstCardPlayer);
            }

            else if( (Arrays.asList(RANK_DIAMONDS).indexOf(secondPlayed.getValue()))  <
                    (Arrays.asList(RANK_DIAMONDS).indexOf(firstPlayed.getValue() ))){

                        updateTrickWon(secondCardPlayer);

            }
        }
    }


    private void updateBothHearts(){
        if( (firstPlayed.getSuit() == Card.HEARTS ) && ( secondPlayed.getSuit() == Card.HEARTS) ){

            // SEE http://stackoverflow.com/questions/6171663/how-to-find-index-of-int-array-in-java-from-a-given-value
            if( (Arrays.asList(RANK_HEARTS).indexOf(firstPlayed.getValue()))  <
                    (Arrays.asList(RANK_HEARTS).indexOf(secondPlayed.getValue() ))){

                        updateTrickWon(firstCardPlayer);
            }

            else if( (Arrays.asList(RANK_HEARTS).indexOf(secondPlayed.getValue()))  <
                    (Arrays.asList(RANK_HEARTS).indexOf(firstPlayed.getValue() ))){

                        updateTrickWon(secondCardPlayer);

            }


        }
    }

    // method to be finished
    private void updateBothDiamondsTrumps(){
        if( (firstPlayed.getSuit() == Card.DIAMONDS || isAceOfHearts(firstPlayed)) &&
                ( secondPlayed.getSuit() == Card.DIAMONDS) || isAceOfHearts(secondPlayed)){

            if( isAceOfHearts(firstPlayed)){
                if( Arrays.asList(RANK_TRUMPS_DIAMONDS).indexOf(Card.ACE) >
                        Arrays.asList(RANK_TRUMPS_DIAMONDS).indexOf(secondPlayed.getValue())){
                    updateTrickWon(secondCardPlayer);
                }
                else {
                    updateTrickWon(firstCardPlayer);
                }
            }

            else if(isAceOfHearts(secondPlayed)){

                if( Arrays.asList(RANK_TRUMPS_DIAMONDS).indexOf(Card.ACE) >
                        Arrays.asList(RANK_TRUMPS_DIAMONDS).indexOf(firstPlayed.getValue())){
                    updateTrickWon(firstCardPlayer);
                }
                else {
                    updateTrickWon(secondCardPlayer);
                }

            }

            else{
                if(Arrays.asList(RANK_TRUMPS_DIAMONDS).indexOf(firstPlayed.getValue()) <
                        Arrays.asList(RANK_TRUMPS_DIAMONDS).indexOf(secondPlayed.getValue())){
                    updateTrickWon(firstCardPlayer);
                }
                else{
                    updateTrickWon(secondCardPlayer);
                }

            }


        }
    }

    private void updateBothHeartsTrumps(){
        if( (firstPlayed.getSuit() == Card.HEARTS && secondPlayed.getSuit() == Card.HEARTS) ){

            if(Arrays.asList(RANK_TRUMPS_HEARTS).indexOf(firstPlayed.getValue()) <
                        Arrays.asList(RANK_TRUMPS_HEARTS).indexOf(secondPlayed.getValue())){
                updateTrickWon(firstCardPlayer);
            }
            else{
                updateTrickWon(secondCardPlayer);
            }

        }

    }

    private void updateBothSpadesTrumps(){
        if( (firstPlayed.getSuit() == Card.SPADES || isAceOfHearts(firstPlayed)) &&
                ( secondPlayed.getSuit() == Card.SPADES) || isAceOfHearts(secondPlayed)){

            if( isAceOfHearts(firstPlayed)){
                if( Arrays.asList(RANK_TRUMPS_SPADES).indexOf(Card.ACE) >
                        Arrays.asList(RANK_TRUMPS_SPADES).indexOf(secondPlayed.getValue())){
                    updateTrickWon(secondCardPlayer);
                }
                else {
                    updateTrickWon(firstCardPlayer);
                }
            }

            else if(isAceOfHearts(secondPlayed)){

                if( Arrays.asList(RANK_TRUMPS_SPADES).indexOf(Card.ACE) >
                        Arrays.asList(RANK_TRUMPS_SPADES).indexOf(firstPlayed.getValue())){
                    updateTrickWon(firstCardPlayer);
                }
                else {
                    updateTrickWon(secondCardPlayer);
                }

            }

            else{
                if(Arrays.asList(RANK_TRUMPS_SPADES).indexOf(firstPlayed.getValue()) <
                        Arrays.asList(RANK_TRUMPS_SPADES).indexOf(secondPlayed.getValue())){
                    updateTrickWon(firstCardPlayer);
                }
                else{
                    updateTrickWon(secondCardPlayer);
                }

            }


        }
    }

    private void updateBothClubsTrumps(){
        if( (firstPlayed.getSuit() == Card.CLUBS || isAceOfHearts(firstPlayed)) &&
                ( secondPlayed.getSuit() == Card.CLUBS) || isAceOfHearts(secondPlayed)){

            if( isAceOfHearts(firstPlayed)){
                if( Arrays.asList(RANK_TRUMPS_CLUBS).indexOf(Card.ACE) >
                        Arrays.asList(RANK_TRUMPS_CLUBS).indexOf(secondPlayed.getValue())){
                    updateTrickWon(secondCardPlayer);
                }
                else {
                    updateTrickWon(firstCardPlayer);
                }
            }

            else if(isAceOfHearts(secondPlayed)){

                if( Arrays.asList(RANK_TRUMPS_CLUBS).indexOf(Card.ACE) >
                        Arrays.asList(RANK_TRUMPS_CLUBS).indexOf(firstPlayed.getValue())){
                    updateTrickWon(firstCardPlayer);
                }
                else {
                    updateTrickWon(secondCardPlayer);
                }

            }

            else{
                if(Arrays.asList(RANK_TRUMPS_CLUBS).indexOf(firstPlayed.getValue()) <
                        Arrays.asList(RANK_TRUMPS_CLUBS).indexOf(secondPlayed.getValue())){
                    updateTrickWon(firstCardPlayer);
                }
                else{
                    updateTrickWon(secondCardPlayer);
                }

            }


        }
    }


}
