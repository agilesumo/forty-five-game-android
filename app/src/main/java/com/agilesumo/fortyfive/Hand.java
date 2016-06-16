package com.agilesumo.fortyfive;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by andy on 04/06/2016.
 */
public class Hand {

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
    public Hand() {
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
/*
        playersCards[0] = new Card(Card.JACK,Card.CLUBS);
        playersCards[1] = new Card(Card.JACK, Card.HEARTS);
        playersCards[2] = new Card(4, Card.CLUBS);
        playersCards[3] = new Card(9, Card.HEARTS);
        playersCards[4] = new Card(Card.KING, Card.HEARTS);

        com1Cards[0] = new Card(2,Card.CLUBS);
        com1Cards[1] = new Card(Card.QUEEN, Card.DIAMONDS);
        com1Cards[2] = new Card(3, Card.HEARTS);
        com1Cards[3] = new Card(9, Card.CLUBS);
        com1Cards[4] = new Card(Card.KING, Card.SPADES);
        //upTrumpCard = deck.dealCard();

        com2Cards[0] = new Card(8, Card.DIAMONDS);
        com2Cards[1] = new Card(6, Card.SPADES);
        com2Cards[2] = new Card(Card.ACE, Card.CLUBS);
        com2Cards[3] = new Card(7, Card.SPADES);
        com2Cards[4] = new Card(Card.ACE, Card.SPADES);

        upTrumpCard = new Card(3,Card.DIAMONDS);

*/


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

    public void setThirdPlayed(Card theThirdPlayed, Player thePlayer){
        thirdPlayed = theThirdPlayed;
        thirdCardPlayer = thePlayer;
    }

    public void updateScore(){

        if(  isTrump(firstPlayed)  && !isTrump(secondPlayed) && !isTrump(thirdPlayed)){
            updateTrickWon(firstCardPlayer);
        }

        else if( !isTrump(firstPlayed) && isTrump(secondPlayed) && !isTrump(thirdPlayed) ){
            updateTrickWon(secondCardPlayer);
        }

        else if(!isTrump(firstPlayed) && !isTrump(secondPlayed) && isTrump(thirdPlayed)){
            updateTrickWon(thirdCardPlayer);
        }

        else if( isTrump(firstPlayed) && isTrump(secondPlayed) && isTrump(thirdPlayed)){
            updateAllTrumps();
        }

        else if(isTrump(firstPlayed) && isTrump(secondPlayed)){

            Card winningCard = getBetterTrump(firstPlayed, secondPlayed);
            if(winningCard.equals(firstPlayed)){
                updateTrickWon(firstCardPlayer);
            }
            else {
                updateTrickWon(secondCardPlayer);
            }
        }

        else if(isTrump(firstPlayed) && isTrump(thirdPlayed)){

            Card winningCard = getBetterTrump(firstPlayed, thirdPlayed);
            if(winningCard.equals(firstPlayed)){
                updateTrickWon(firstCardPlayer);
            }
            else {
                updateTrickWon((thirdCardPlayer));
            }

        }

        else if(isTrump(secondPlayed) && isTrump(thirdPlayed)){

            Card winningCard = getBetterTrump(secondPlayed, thirdPlayed);
            if(winningCard.equals(secondPlayed)){
                updateTrickWon(secondCardPlayer);
            }
            else {
                updateTrickWon(thirdCardPlayer);
            }
        }

        else if ( firstPlayed.getSuit() != secondPlayed.getSuit() &&
                firstPlayed.getSuit() != thirdPlayed.getSuit()){
            updateTrickWon(firstCardPlayer);

        }

        else if (firstPlayed.getSuit() == secondPlayed.getSuit() &&
                secondPlayed.getSuit() == thirdPlayed.getSuit()){
            updateAllSameOffSuit();

        }

        else if (firstPlayed.getSuit() == secondPlayed.getSuit()){

            Card winningCard = getBetterOffSuit(firstPlayed, secondPlayed);

            if(winningCard.equals(firstPlayed)){
                updateTrickWon(firstCardPlayer);
            }
            else {
                updateTrickWon(secondCardPlayer);
            }

        }

        else if (firstPlayed.getSuit() == thirdPlayed.getSuit()){

            Card winningCard = getBetterOffSuit(firstPlayed, thirdPlayed);

            if(winningCard.equals(firstPlayed)){
                updateTrickWon(firstCardPlayer);
            }

            else{
                updateTrickWon(thirdCardPlayer);
            }


        }

    }

    public int getScore(Player thePlayer){
        if(thePlayer.equals(player)){
            return playersScore;

        }

        else if(thePlayer.equals(computer1)){
            return com1Score;
        }

        else if(thePlayer.equals(computer2)){
            return com2Score;
        }

        else {
            return 1;
        }
    }

    public boolean isHandFinished(){
        return playersScore + com1Score + com2Score == 25;
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

        for(int i = 0; i < 5; i++) {
            if(isTrump(com2Cards[i])) {
                allTrumpsCom2.add(com2Cards[i]);
            }
        }

        String bestTrumpStr = "";


        if(allTrumpsPlayer.isEmpty() && allTrumpsCom1.isEmpty() && allTrumpsCom2.isEmpty()) {
            return "No best trump as no trump in this hand";
        }

        // only player has a trump
        else if(allTrumpsCom1.isEmpty() && allTrumpsCom2.isEmpty()){
            bestTrump = findBestTrump(allTrumpsPlayer);
            bestTrumpStr = "You had the best trump: ";
            playersScore +=5;

        }

        // only computer1 has a trump
        else if(allTrumpsPlayer.isEmpty() && allTrumpsCom2.isEmpty()){
            bestTrump = findBestTrump(allTrumpsCom1);
            bestTrumpStr = "Computer P1 had best trump: ";
            com1Score += 5;

        }

        // only computer2 has a trump
        else if(allTrumpsPlayer.isEmpty() && allTrumpsCom1.isEmpty()){
            bestTrump = findBestTrump(allTrumpsCom2);
            bestTrumpStr = "Computer P2 had best trump";
            com2Score +=5;

        }

        // Each player has at least one trump
        else if(!allTrumpsPlayer.isEmpty() && !allTrumpsCom1.isEmpty() && !allTrumpsCom2.isEmpty()){

            ArrayList<Card> eachBestTrump = new ArrayList<Card>();

            eachBestTrump.add(findBestTrump(allTrumpsPlayer));
            eachBestTrump.add(findBestTrump(allTrumpsCom1));
            eachBestTrump.add(findBestTrump(allTrumpsCom2));

            bestTrump = findBestTrump(eachBestTrump);

            if(bestTrump.equals(findBestTrump(allTrumpsPlayer))){
                bestTrumpStr = "You had best Trump: ";
                playersScore += 5;

            }

            else if(bestTrump.equals(findBestTrump(allTrumpsCom1))){
                bestTrumpStr = "Computer P1 had best trump: ";
                com1Score +=5;
            }

            else {
                bestTrumpStr = "Computer P2 had best trump: ";
                com2Score += 5;
            }

        }

        // only player and computer1 have trumps
        else if(allTrumpsCom2.isEmpty()){
            Card bestCardPlayer = findBestTrump(allTrumpsPlayer);
            Card bestCardCom1 = findBestTrump(allTrumpsCom1);
            bestTrump = getBetterTrump(bestCardPlayer, bestCardCom1);

            if(bestTrump.equals(bestCardPlayer)){
                bestTrumpStr = "You had best trump: ";
                playersScore += 5;
            }
            else {
                bestTrumpStr = "Computer P1 had best trump: ";
                com1Score += 5;
            }
        }

        // only player and computer2 have trumps
        else if(allTrumpsCom1.isEmpty()){
            Card bestCardPlayer = findBestTrump(allTrumpsPlayer);
            Card bestCardCom2 = findBestTrump(allTrumpsCom2);
            bestTrump = getBetterTrump(bestCardPlayer, bestCardCom2);

            if(bestTrump.equals(bestCardPlayer)){
                bestTrumpStr = "You had best trump: ";
                playersScore += 5;
            }
            else {
                bestTrumpStr = "Computer P2 had best trump: ";
                com2Score += 5;
            }

        }


        // only computer1 and computer2 have trumps
        else{
            Card bestCardCom1 = findBestTrump(allTrumpsCom1);
            Card bestCardCom2 = findBestTrump(allTrumpsCom2);
            bestTrump = getBetterTrump(bestCardCom1, bestCardCom2);

            if(bestTrump.equals(bestCardCom1)){
                bestTrumpStr = "Computer P1 had best trump: ";
                com1Score += 5;
            }
            else {
                bestTrumpStr = "Computer P2 had best trump: ";
                com2Score += 5;
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
        else if(wonTrickPlayer.equals(computer1)) {
            com1Score += 5;
        }
        else if(wonTrickPlayer.equals(computer2)){
            com2Score += 5;

        }
    }

    private void updateAllTrumps(){
        ArrayList<Card> trumpsToCompare = new ArrayList<Card>();
        trumpsToCompare.add(firstPlayed);
        trumpsToCompare.add(secondPlayed);
        trumpsToCompare.add(thirdPlayed);

        Card winningCard = findBestTrump(trumpsToCompare);

        if(firstPlayed.equals(winningCard)){
            updateTrickWon(firstCardPlayer);
        }
        else if(secondPlayed.equals(winningCard)){
            updateTrickWon(secondCardPlayer);
        }
        else if(thirdPlayed.equals(winningCard)){
            updateTrickWon(thirdCardPlayer);
        }
        else {
            Log.d("andyoc", "no winning card");
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
            else{
                return firstCard;
            }
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


    private Card getBetterOffSuit (Card firstCard, Card secondCard){
        int suit = firstCard.getSuit();

        Integer[] rankSuit;
        if(suit == Card.DIAMONDS){
            rankSuit = RANK_DIAMONDS;
        }
        else if(suit == Card.HEARTS){
            rankSuit = RANK_HEARTS;
        }
        else if(suit == Card.CLUBS){
            rankSuit = RANK_CLUBS;
        }
        else{
            rankSuit = RANK_SPADES;
        }


        if(Arrays.asList(rankSuit).indexOf(firstCard.getValue()) <
                Arrays.asList(rankSuit).indexOf(secondCard.getValue())){
            return firstCard;
        }
        else{
            return secondCard;
        }



    }



    private Card findBestOffSuit(ArrayList<Card> theCards){
        if(theCards.size() == 1){

            return theCards.get(0);


        }

        Card currentBest = getBetterOffSuit(theCards.get(0), theCards.get(1));

        for(int i=2; i<theCards.size(); i++){
            currentBest = getBetterOffSuit(currentBest, theCards.get(i));

        }

        return currentBest;
    }

    private void updateAllSameOffSuit(){
        ArrayList<Card> cardsToCompare = new ArrayList<Card>();
        cardsToCompare.add(firstPlayed);
        cardsToCompare.add(secondPlayed);
        cardsToCompare.add(thirdPlayed);

        Card winningCard = findBestOffSuit(cardsToCompare);

        if(firstPlayed.equals(winningCard)){
            updateTrickWon(firstCardPlayer);
        }
        else if(secondPlayed.equals(winningCard)){
            updateTrickWon(secondCardPlayer);
        }
        else if(thirdPlayed.equals(winningCard)){
            updateTrickWon(thirdCardPlayer);
        }
        else {
            Log.d("andyoc", "no winning card");
        }

    }




}
