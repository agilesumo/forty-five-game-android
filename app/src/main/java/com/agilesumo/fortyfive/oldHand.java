package com.agilesumo.fortyfive;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by andy on 04/06/2016.
 */
public class oldHand {

    private Card[] playersCards;

    private Card[] com1Cards;

    private Card[] com2Cards;

    private Deck deck = new Deck();

    private Card upTrumpCard;

    private Card firstPlayed;

    private Card secondPlayed;

    private Card thirdPlayed;


    private Player firstCardPlayer;

    private Player secondCardPlayer;

    private Player thirdCardPlayer;

    private int playersScore;

    private int com1Score;

    private int com2Score;

    private ArrayList<Card> allTrumpsPlayer;

    private ArrayList<Card> allTrumpsCom1;

    private ArrayList<Card> allTrumpsCom2;

    public static Player player;

    public static Player computer1;

    public static Player computer2;




    // ranks of trump cards excluding the ace of hearts
    private static final Integer [] RANK_TRUMPS_HEARTS = {
            5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 10, 9, 8, 7 ,6, 5, 4, 3,2 };

    private static final Integer [] RANK_TRUMPS_DIAMONDS = {
            5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 10, 9, 8, 7 ,6, 5, 4, 3,2 };

    private static final Integer [] RANK_TRUMPS_SPADES = {
            5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    private static final Integer [] RANK_TRUMPS_CLUBS = {
            5, Card.JACK, Card.ACE, Card.KING, Card.QUEEN, 2, 3, 4, 5, 6, 7, 8, 9, 10 };


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
    public oldHand() {
        playersCards = new Card[5];
        com1Cards = new Card[5];
        com2Cards = new Card[5];
        deck.shuffle();

        for(int i=0; i<5; i++){
            playersCards[i] = deck.dealCard();
            com1Cards[i] = deck.dealCard();
            com2Cards[i] = deck.dealCard();
        }
        upTrumpCard = deck.dealCard();




        player = new Player();
        computer1 = new Player();
        computer2 = new Player();

        firstCardPlayer = new Player();
        secondCardPlayer = new Player();
        thirdCardPlayer = new Player();

        playersScore = 0;
        com1Score = 0;
        com2Score = 0;

        allTrumpsPlayer = new ArrayList<Card>();
        allTrumpsCom1 = new ArrayList<Card>();
        allTrumpsCom2 = new ArrayList<Card>();



    }


    /******** temp constructer ***********

     public Hand() {
     playersCards = new Card[5];
     com1Cards = new Card[5];
     deck.shuffle();



     playersCards[0] = new Card(Card.JACK,Card.CLUBS);
     playersCards[1] = new Card(Card.JACK, Card.SPADES);
     playersCards[2] = new Card(4, Card.CLUBS);
     playersCards[3] = new Card(9, Card.HEARTS);
     playersCards[4] = new Card(Card.KING, Card.DIAMONDS);

     com1Cards[0] = new Card(2,Card.HEARTS);
     com1Cards[1] = new Card(Card.JACK, Card.DIAMONDS);
     com1Cards[2] = new Card(4, Card.DIAMONDS);
     com1Cards[3] = new Card(9, Card.DIAMONDS);
     com1Cards[4] = new Card(Card.KING, Card.HEARTS);
     //upTrumpCard = deck.dealCard();

     upTrumpCard = new Card(3,Card.DIAMONDS);

     player = new Player();
     computer1 = new Player();

     firstCardPlayer = new Player();
     secondCardPlayer = new Player();

     playersScore = 0;
     com1Score = 0;

     allTrumpsPlayer = new ArrayList<Card>();
     allTrumpsCom1 = new ArrayList<Card>();


     }*/



    public Card[] getPlayersCards(){
        return playersCards;
    }

    public Card[] getCom1Cards(){
        return com1Cards;
    }

    public Card[] getCom2Cards(){
        return com2Cards;
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

    public void setThirdPlayed(Card theSecondPlayed, Player thePlayer){
        thirdPlayed = theSecondPlayed;
        thirdCardPlayer = thePlayer;
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

    public boolean isHandFinished(){
        return playersScore + com1Score == 25;
    }

    // to be finished
    public String getBestTrumpStr(){
        Card bestTrump;
        for(int i = 0; i < 5; i++) {
            if(isTrump(playersCards[i])) {
                allTrumpsPlayer.add(playersCards[i]);
            }
        }
        for(int i = 0; i < 5; i++) {
            if(isTrump(com1Cards[i])) {
                allTrumpsCom1.add(com1Cards[i]);
            }
        }

        if(allTrumpsPlayer.isEmpty() && allTrumpsCom1.isEmpty()) {
            return "No best trump as no trump in this hand";
        }

        String bestTrumpStr = "";
        if(allTrumpsCom1.isEmpty()) {
            bestTrump = findBestTrump(allTrumpsPlayer);
            bestTrumpStr = "You had best Trump: ";
            playersScore += 5;
        }

        else if(allTrumpsPlayer.isEmpty()){
            bestTrump = findBestTrump(allTrumpsCom1);
            bestTrumpStr = "Computer P1 had best trump: ";
            com1Score +=5;
        }

        else {
            Card playerBestTrump = findBestTrump(allTrumpsPlayer);
            Card com1BestTrump = findBestTrump(allTrumpsCom1);

            ArrayList<Card> bothTrumps= new ArrayList<Card>();

            bothTrumps.add(playerBestTrump);
            bothTrumps.add(com1BestTrump);

            bestTrump = findBestTrump(bothTrumps);



            if(bestTrump.equals(playerBestTrump)){
                bestTrumpStr = "You had best Trump: ";
                playersScore += 5;

            }
            else if(bestTrump.equals(com1BestTrump)){
                bestTrumpStr = "Computer P1 had best trump: ";
                com1Score +=5;
            }

        }



        return bestTrumpStr + bestTrump.toString();
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

    private Card getBetterTrump (Card firstCard, Card secondCard){
        int suit = upTrumpCard.getSuit();
        // hearts is a special case because of ace of hearts as a trump
        if(suit == Card.HEARTS){
            if(Arrays.asList(RANK_TRUMPS_HEARTS).indexOf(firstCard.getValue()) <
                    Arrays.asList(RANK_TRUMPS_HEARTS).indexOf(secondCard.getValue())){
                return firstCard;
            }
            else{
                return secondCard;
            }

        }

        Integer[] rankTrumps;
        if(suit == Card.DIAMONDS){
            rankTrumps = RANK_TRUMPS_DIAMONDS;
        }
        else if(suit == Card.CLUBS){
            rankTrumps = RANK_TRUMPS_CLUBS;
        }
        else{
            rankTrumps = RANK_TRUMPS_SPADES;
        }

        // else the cards are one of the other suits diamonds, spades or clubs
        if(isAceOfHearts(firstCard)){
            if(Arrays.asList(rankTrumps).indexOf(Card.ACE) >
                    Arrays.asList(rankTrumps).indexOf(secondCard.getValue())){
                return secondCard;
            }
            else return firstCard;
        }

        else if(isAceOfHearts(secondCard)){
            if (Arrays.asList(rankTrumps).indexOf(Card.ACE) >
                    Arrays.asList(rankTrumps).indexOf(firstCard.getValue())) {
                return firstCard;
            }
            else{
                return secondCard;
            }

        }

        else{
            if(Arrays.asList(rankTrumps).indexOf(firstCard.getValue()) <
                    Arrays.asList(rankTrumps).indexOf(secondCard.getValue())){
                return firstCard;
            }
            else{
                return secondCard;
            }

        }

    }


    private Card findBestTrump(ArrayList<Card> theCards){
        if(theCards.size() == 1){

            return theCards.get(0);


        }

        Card currentBest = getBetterTrump(theCards.get(0),theCards.get(1));

        for(int i=2; i<theCards.size(); i++){
            currentBest = getBetterTrump(currentBest,theCards.get(i));
        }

        return currentBest;
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
                ( secondPlayed.getSuit() == Card.DIAMONDS || isAceOfHearts(secondPlayed))){

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
                ( secondPlayed.getSuit() == Card.SPADES || isAceOfHearts(secondPlayed))){

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
                ( secondPlayed.getSuit() == Card.CLUBS || isAceOfHearts(secondPlayed))){

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
