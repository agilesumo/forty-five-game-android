package com.agilesumo.fortyfive;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;




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

    private Card bestTrumpCard = null;


    private Player firstCardPlayer;

    private Player secondCardPlayer;

    private Player thirdCardPlayer;

    private Player aceTrumpsPlayer = null;

    private Player bestTrumpPlayer = null;

    private int playersScore;

    private int com1Score;

    private int com2Score;

    private int aceOfTrumpsPosition = -1;

    private ArrayList<Card> sortedTrumpsPlayer;

    private ArrayList<Card> sortedTrumpsCom1;

    private ArrayList<Card> sortedTrumpsCom2;

    private ArrayList<Card> sortedClubsCom1;

    private ArrayList<Card> sortedSpadesCom1;

    private ArrayList<Card> sortedHeartsCom1;

    private ArrayList<Card> sortedDiamondsCom1;

    private ArrayList<Card> sortedClubsCom2;

    private ArrayList<Card> sortedSpadesCom2;

    private ArrayList<Card> sortedHeartsCom2;

    private ArrayList<Card> sortedDiamondsCom2;

    private Player lastTrickWinner = null;

    private String bestTrumpStr;

    public static Player player = new Player();

    public static Player computer1 = new Player();

    public static Player computer2 = new Player();










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


        playersCards[0] = new Card(7,Card.DIAMONDS);
        playersCards[1] = new Card(Card.ACE, Card.CLUBS);
        playersCards[2] = new Card(Card.JACK, Card.DIAMONDS);
        playersCards[3] = new Card(6, Card.DIAMONDS);
        playersCards[4] = new Card(Card.QUEEN, Card.DIAMONDS);

        com1Cards[0] = new Card(4, Card.CLUBS);
        com1Cards[1] = new Card(Card.JACK, Card.CLUBS);
        com1Cards[2] = new Card(3, Card.DIAMONDS);
        com1Cards[3] = new Card(Card.ACE, Card.DIAMONDS);
        com1Cards[4] = new Card(3, Card.CLUBS);
        //upTrumpCard = deck.dealCard();

        com2Cards[0] = new Card(7, Card.HEARTS);
        com2Cards[1] = new Card(5, Card.DIAMONDS);
        com2Cards[2] = new Card(5, Card.DIAMONDS);
        com2Cards[3] = new Card(4, Card.DIAMONDS);
        com2Cards[4] = new Card(Card.KING, Card.CLUBS);

        upTrumpCard = new Card(Card.ACE, Card.HEARTS);

        */




        firstCardPlayer = new Player();
        secondCardPlayer = new Player();
        thirdCardPlayer = new Player();

        playersScore = 0;
        com1Score = 0;
        com2Score = 0;

        sortedTrumpsPlayer = new ArrayList<Card>();
        sortedTrumpsCom1 = new ArrayList<Card>();
        sortedTrumpsCom2 = new ArrayList<Card>();

        sortedSpadesCom1 = new ArrayList<Card>();
        sortedClubsCom1 = new ArrayList<Card>();
        sortedHeartsCom1 = new ArrayList<Card>();
        sortedDiamondsCom1 = new ArrayList<Card>();

        sortedSpadesCom2 = new ArrayList<Card>();
        sortedClubsCom2 = new ArrayList<Card>();
        sortedHeartsCom2 = new ArrayList<Card>();
        sortedDiamondsCom2 = new ArrayList<Card>();

        initialiseSortedLists();

        if(this.hasAceTrumps()){

            if(aceTrumpsPlayer.equals(computer1)){
                Card lowestCard = lowestRankedOffSuit(computer1);

                if (lowestCard == null){
                    lowestCard = sortedTrumpsCom1.get(0);
                }

                for(int i=0; i<5; i++){
                    if(com1Cards[i].equals(lowestCard)){
                        com1Cards[i] = upTrumpCard;
                    }
                }

                // remove all from list so the lists can be updated

                sortedTrumpsPlayer.clear();

                sortedTrumpsCom1.clear();
                sortedDiamondsCom1.clear();
                sortedHeartsCom1.clear();
                sortedSpadesCom1.clear();
                sortedClubsCom1.clear();

                sortedTrumpsCom2.clear();
                sortedDiamondsCom2.clear();
                sortedHeartsCom2.clear();
                sortedSpadesCom2.clear();
                sortedClubsCom2.clear();

                initialiseSortedLists();

            }

            if(aceTrumpsPlayer.equals(computer2)){
                Card lowestCard = lowestRankedOffSuit(computer2);

                if (lowestCard == null){
                    lowestCard = sortedTrumpsCom2.get(0);
                }

                for(int i=0; i<5; i++){
                    if(com2Cards[i].equals(lowestCard)){
                        com2Cards[i] = upTrumpCard;
                    }
                }

                // remove all from list so the lists can be updated

                sortedTrumpsPlayer.clear();

                sortedTrumpsCom1.clear();
                sortedDiamondsCom1.clear();
                sortedHeartsCom1.clear();
                sortedSpadesCom1.clear();
                sortedClubsCom1.clear();

                sortedTrumpsCom2.clear();
                sortedDiamondsCom2.clear();
                sortedHeartsCom2.clear();
                sortedSpadesCom2.clear();
                sortedClubsCom2.clear();



                initialiseSortedLists();


            }

        }

        createBestTrumpStr();


    }

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

    public Player getLastTrickWinner(){
        return lastTrickWinner;
    }

    public void setTrumpCard(Card trumpCard) {
        upTrumpCard = trumpCard;
    }

    public Player getBestTrumpPlayer(){
        return bestTrumpPlayer;
    }

    public void aceIsTrumpsUpdate(Player thePlayer){

        if(thePlayer.equals(computer1)){
            Card lowestCard = lowestRankedOffSuit(computer1);

            if (lowestCard == null){
                lowestCard = sortedTrumpsCom1.get(0);
            }

            for(int i=0; i<5; i++){
                if(com1Cards[i].equals(lowestCard)){
                    com1Cards[i] = upTrumpCard;
                }
            }

            // remove all from list so the lists can be updated

            sortedTrumpsPlayer.clear();

            sortedTrumpsCom1.clear();
            sortedDiamondsCom1.clear();
            sortedHeartsCom1.clear();
            sortedSpadesCom1.clear();
            sortedClubsCom1.clear();

            sortedTrumpsCom2.clear();
            sortedDiamondsCom2.clear();
            sortedHeartsCom2.clear();
            sortedSpadesCom2.clear();
            sortedClubsCom2.clear();

            initialiseSortedLists();

        }

        if(thePlayer.equals(computer2)){
            Card lowestCard = lowestRankedOffSuit(computer2);

            if (lowestCard == null){
                lowestCard = sortedTrumpsCom2.get(0);
            }

            for(int i=0; i<5; i++){
                if(com2Cards[i].equals(lowestCard)){
                    com2Cards[i] = upTrumpCard;
                }
            }

            // remove all from list so the lists can be updated

            sortedTrumpsPlayer.clear();

            sortedTrumpsCom1.clear();
            sortedDiamondsCom1.clear();
            sortedHeartsCom1.clear();
            sortedSpadesCom1.clear();
            sortedClubsCom1.clear();

            sortedTrumpsCom2.clear();
            sortedDiamondsCom2.clear();
            sortedHeartsCom2.clear();
            sortedSpadesCom2.clear();
            sortedClubsCom2.clear();



            initialiseSortedLists();

        }

        createBestTrumpStr();


    }

    public void setFirstPlayed(Card theFirstPlayed, Player thePlayer){
        firstPlayed = theFirstPlayed;
        firstCardPlayer = thePlayer;

        if (thePlayer.equals(computer1) || thePlayer.equals(computer2)){
            removeCardFromLists(theFirstPlayed, thePlayer);

        }
    }

    public void setSecondPlayed(Card theSecondPlayed, Player thePlayer){

        secondPlayed = theSecondPlayed;
        secondCardPlayer = thePlayer;

        if (thePlayer.equals(computer1) || thePlayer.equals(computer2)){
            removeCardFromLists(theSecondPlayed, thePlayer);

        }

    }

    public void setThirdPlayed(Card theThirdPlayed, Player thePlayer){
        thirdPlayed = theThirdPlayed;
        thirdCardPlayer = thePlayer;

        if(thePlayer.equals(computer1) || thePlayer.equals(computer2)) {
            removeCardFromLists(theThirdPlayed, thePlayer);

        }

    }

    public void updatePlayerCard(Card card, int position){
        playersCards[position] = card;
        sortedTrumpsCom1.clear();
        sortedDiamondsCom1.clear();
        sortedHeartsCom1.clear();
        sortedSpadesCom1.clear();
        sortedClubsCom1.clear();

        sortedTrumpsCom2.clear();
        sortedDiamondsCom2.clear();
        sortedHeartsCom2.clear();
        sortedSpadesCom2.clear();
        sortedClubsCom2.clear();

        initialiseSortedLists();
    }

    public boolean hasAceTrumps(){
        for(int i=0; i<5; i++){
            if(playersCards[i].getSuit() == upTrumpCard.getSuit() &&
                    playersCards[i].getValue() == Card.ACE){
                aceTrumpsPlayer = player;
                aceOfTrumpsPosition = i;
                return true;
            }

            else if(com1Cards[i].getSuit() == upTrumpCard.getSuit() &&
                    com1Cards[i].getValue() == Card.ACE){
                aceTrumpsPlayer = computer1;
                aceOfTrumpsPosition = i;
                return true;
            }

            else if(com2Cards[i].getSuit() == upTrumpCard.getSuit() &&
                    com2Cards[i].getValue() == Card.ACE){
                aceTrumpsPlayer = computer2;
                aceOfTrumpsPosition = i;
                return true;
            }

        }

        return false;
    }


    public Player getAceTrumpsPlayer(){
        return aceTrumpsPlayer;
    }

    public int getAceOfTrumpsPosition(){
        return aceOfTrumpsPosition;
    }


    public Card playFirstCard(Player thePlayer){
        if(thePlayer.equals(computer1)){

            if(!sortedTrumpsCom1.isEmpty()){
                int maxIndex = sortedTrumpsCom1.size()-1;
                return sortedTrumpsCom1.get(maxIndex);
            }

            else{
                return highestRankedOffSuit(thePlayer);
            }
        }

        else if(thePlayer.equals(computer2)){

            if(!sortedTrumpsCom2.isEmpty()){
                int maxIndex = sortedTrumpsCom2.size()-1;
                return sortedTrumpsCom2.get(maxIndex);
            }

            else{
                return highestRankedOffSuit(thePlayer);
            }
        }

        return null;
    }


    public Card playSecondCard(Player thePlayer){
        ArrayList<Card> sortedTrumps;

        if( thePlayer.equals(computer1)) {
            sortedTrumps = sortedTrumpsCom1;
        }

        else{
            sortedTrumps = sortedTrumpsCom2;

        }

        if(isTrump(firstPlayed)){

            if(!sortedTrumps.isEmpty()){
                Card cardToPlay = lowestHigherRankedTrump(firstPlayed, thePlayer);
                if(cardToPlay != null){
                    return cardToPlay;

                }
                else{
                    Card lowestTrump = sortedTrumps.get(0);
                    return lowestTrump;
                }
            }

            else{
                Card lowestCard = lowestRankedOffSuit(thePlayer);
                return lowestCard;
            }
        }

        else{
            Card cardToPlay = lowestHigherRankedOffSuit(firstPlayed, thePlayer);
            if(cardToPlay != null){
                return cardToPlay;
            }

            else if(!sortedTrumps.isEmpty()){
                return sortedTrumps.get(0);
            }

            cardToPlay = lowestRankedOffSuit(firstPlayed.getSuit(), thePlayer);
            if( cardToPlay != null){
                return cardToPlay;
            }

            else{
                Card lowestCard = lowestRankedOffSuit(thePlayer);

                return lowestCard;
            }

        }

    }




    public Card playThirdCard(Player thePlayer){

        ArrayList<Card> sortedTrumps;

        if(thePlayer.equals(computer1)){
            sortedTrumps = sortedTrumpsCom1;
        }

        else{
            sortedTrumps = sortedTrumpsCom2;

        }


        if(isTrump(firstPlayed) && !isTrump(secondPlayed)){

            if(!sortedTrumps.isEmpty()){
                Card cardToPlay = lowestHigherRankedTrump(firstPlayed, thePlayer);
                if(cardToPlay != null){
                    return cardToPlay;

                }
                else{
                    Card lowestTrump = sortedTrumps.get(0);
                    return lowestTrump;
                }
            }

            else{
                Card lowestCard = lowestRankedOffSuit(thePlayer);

                return lowestCard;
            }
        }

        else if(isTrump(firstPlayed) && isTrump(secondPlayed)){

            Card betterFirstTwo = getBetterTrump(firstPlayed, secondPlayed);

            if(!sortedTrumps.isEmpty()){

                Card cardToPlay = lowestHigherRankedTrump(betterFirstTwo, thePlayer);

                if(cardToPlay != null){
                    return cardToPlay;
                }

                else{
                    Card lowestTrump = sortedTrumps.get(0);
                    return lowestTrump;
                }

            }

            else{
                Card lowestCard = lowestRankedOffSuit(thePlayer);
                return lowestCard;
            }
        }

        else if(!isTrump(firstPlayed) && isTrump(secondPlayed)){

            if(!sortedTrumps.isEmpty()){

                Card cardToPlay = lowestHigherRankedTrump(secondPlayed, thePlayer);

                if (cardToPlay != null) {
                    return cardToPlay;
                }

            }

            Card cardToPlay = lowestRankedOffSuit(firstPlayed.getSuit(), thePlayer);

            if(cardToPlay != null){
                return cardToPlay;
            }

            else{

                Card lowestCard = lowestRankedOffSuit(thePlayer);

                if(lowestCard != null){
                    return lowestCard;
                }

                // this is triggered in the event of thePlayer having all trumps in his hand
                else{
                    Card lowestTrump = sortedTrumps.get(0);
                    return lowestTrump;
                }
            }

        }

        else if(!isTrump(firstPlayed) && !isTrump(secondPlayed) &&
                firstPlayed.getSuit() == secondPlayed.getSuit()){

            Card betterFirstTwo;

            if(isHigherOffSuit(firstPlayed, secondPlayed)){
                betterFirstTwo = firstPlayed;
            }

            else{
                betterFirstTwo = secondPlayed;
            }

            Card cardToPlay = lowestHigherRankedOffSuit(betterFirstTwo, thePlayer);

            if(cardToPlay != null){
                return cardToPlay;
            }

            else{

                if(!sortedTrumps.isEmpty()){
                    return sortedTrumps.get(0);
                }

                else{
                    // cardToPlay is the lowest value card in the suit of the leading card
                    cardToPlay = lowestRankedOffSuit(firstPlayed.getSuit(), thePlayer);

                    if(cardToPlay != null){
                        return cardToPlay;
                    }

                    // cardToPlay is the lowest value card in any off suit type
                    else{
                        cardToPlay = lowestRankedOffSuit(thePlayer);
                        return cardToPlay;
                    }

                }
            }



        }

        else if(!isTrump(firstPlayed) && !isTrump(secondPlayed) &&
                firstPlayed.getSuit() != secondPlayed.getSuit()){

            Card cardToPlay = lowestHigherRankedOffSuit(firstPlayed, thePlayer);

            if(cardToPlay != null){
                return cardToPlay;
            }

            else{

                if(!sortedTrumps.isEmpty()){
                    return sortedTrumps.get(0);
                }

                else{
                    // cardToPlay is the lowest value card in the suit of the leading card
                    cardToPlay = lowestRankedOffSuit(firstPlayed.getSuit(), thePlayer);

                    if(cardToPlay != null){
                        return cardToPlay;
                    }

                    // cardToPlay is the lowest value card in any off suit type
                    else{
                        cardToPlay = lowestRankedOffSuit(thePlayer);
                        return cardToPlay;
                    }

                }
            }

        }

        return new Card();

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

    public String getBestTrumpStr(){

        bestTrumpStr = "";


        if(bestTrumpPlayer == null) {
            bestTrumpStr = "No best trump as no trump in this hand";
        }

        // only player has a trump
        else if(bestTrumpPlayer.equals(player)){
            bestTrumpStr = "You had the best trump: ";
            playersScore +=5;

        }

        // only computer1 has a trump
        else if(bestTrumpPlayer.equals(computer1)){
            bestTrumpStr = "Computer P1 had best trump: ";
            com1Score += 5;

        }

        // only computer2 has a trump
        else if(bestTrumpPlayer.equals(computer2)){
            bestTrumpStr = "Computer P2 had best trump: ";
            com2Score +=5;

        }

        if(bestTrumpCard != null){
            bestTrumpStr += bestTrumpCard.toString();

        }

        return bestTrumpStr;
    }

    public void createBestTrumpStr(){

        bestTrumpStr = "";
        bestTrumpCard = null;


        if(sortedTrumpsPlayer.isEmpty() && sortedTrumpsCom1.isEmpty() && sortedTrumpsCom2.isEmpty()) {
            bestTrumpCard = null;
            bestTrumpPlayer = null;
        }

        // only player has a trump
        else if(sortedTrumpsCom1.isEmpty() && sortedTrumpsCom2.isEmpty()){
            bestTrumpCard = findBestTrump(sortedTrumpsPlayer);
            bestTrumpPlayer = player;
        }

        // only computer1 has a trump
        else if(sortedTrumpsPlayer.isEmpty() && sortedTrumpsCom2.isEmpty()){
            bestTrumpCard = findBestTrump(sortedTrumpsCom1);
            bestTrumpPlayer = computer1;

        }

        // only computer2 has a trump
        else if(sortedTrumpsPlayer.isEmpty() && sortedTrumpsCom1.isEmpty()){
            bestTrumpCard = findBestTrump(sortedTrumpsCom2);
            bestTrumpPlayer = computer2;

        }

        // Each player has at least one trump
        else if(!sortedTrumpsPlayer.isEmpty() && !sortedTrumpsCom1.isEmpty() && !sortedTrumpsCom2.isEmpty()){

            ArrayList<Card> eachBestTrump = new ArrayList<Card>();

            eachBestTrump.add(findBestTrump(sortedTrumpsPlayer));
            eachBestTrump.add(findBestTrump(sortedTrumpsCom1));
            eachBestTrump.add(findBestTrump(sortedTrumpsCom2));

            bestTrumpCard = findBestTrump(eachBestTrump);

            if(bestTrumpCard.equals(findBestTrump(sortedTrumpsPlayer))){
                bestTrumpPlayer = player;

            }

            else if(bestTrumpCard.equals(findBestTrump(sortedTrumpsCom1))){
                bestTrumpPlayer = computer1;
            }

            else {
                bestTrumpPlayer = computer2;
            }

        }

        // only player and computer1 have trumps
        else if(sortedTrumpsCom2.isEmpty()){
            Card bestCardPlayer = findBestTrump(sortedTrumpsPlayer);
            Card bestCardCom1 = findBestTrump(sortedTrumpsCom1);
            bestTrumpCard = getBetterTrump(bestCardPlayer, bestCardCom1);

            if(bestTrumpCard.equals(bestCardPlayer)){
                bestTrumpPlayer = player;
            }

            else {
                bestTrumpPlayer = computer1;
            }
        }

        // only player and computer2 have trumps
        else if(sortedTrumpsCom1.isEmpty()){
            Card bestCardPlayer = findBestTrump(sortedTrumpsPlayer);
            Card bestCardCom2 = findBestTrump(sortedTrumpsCom2);
            bestTrumpCard = getBetterTrump(bestCardPlayer, bestCardCom2);

            if(bestTrumpCard.equals(bestCardPlayer)){
                bestTrumpPlayer = player;
            }
            else {
                bestTrumpPlayer = computer2;
            }

        }


        // only computer1 and computer2 have trumps
        else{
            Card bestCardCom1 = findBestTrump(sortedTrumpsCom1);
            Card bestCardCom2 = findBestTrump(sortedTrumpsCom2);
            bestTrumpCard = getBetterTrump(bestCardCom1, bestCardCom2);

            if(bestTrumpCard.equals(bestCardCom1)){
                bestTrumpPlayer = computer1;
            }
            else {
                bestTrumpPlayer = computer2;
            }
        }

        if(bestTrumpPlayer != null) {

            if (bestTrumpPlayer.equals(player)) {
                sortedTrumpsPlayer.clear();

                sortedTrumpsCom1.clear();
                sortedDiamondsCom1.clear();
                sortedHeartsCom1.clear();
                sortedSpadesCom1.clear();
                sortedClubsCom1.clear();

                sortedTrumpsCom2.clear();
                sortedDiamondsCom2.clear();
                sortedHeartsCom2.clear();
                sortedSpadesCom2.clear();
                sortedClubsCom2.clear();


                initialiseSortedLists();
            }

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
        lastTrickWinner = wonTrickPlayer;
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


    // compares two trump cards returns -1 is first card is lower ranked trump
    // and returns 1 if second card is a lower ranked trump
    private int compareTrumps (Card firstCard, Card secondCard){
        int suit = upTrumpCard.getSuit();
        // hearts is a special case because of ace of hearts as a trump
        if(suit == Card.HEARTS){
            if(Arrays.asList(RANK_TRUMPS_HEARTS).indexOf(firstCard.getValue()) <
                    Arrays.asList(RANK_TRUMPS_HEARTS).indexOf(secondCard.getValue())){
                return 1;
            }
            else{
                return -1;
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
                return -1;
            }
            else{
                return 1;
            }
        }

        else if(isAceOfHearts(secondCard)){
            if (Arrays.asList(rankTrumps).indexOf(Card.ACE) >
                    Arrays.asList(rankTrumps).indexOf(firstCard.getValue())) {
                return 1;
            }
            else{
                return -1;
            }

        }

        else{
            if(Arrays.asList(rankTrumps).indexOf(firstCard.getValue()) <
                    Arrays.asList(rankTrumps).indexOf(secondCard.getValue())){
                return 1;
            }
            else{
                return -1;
            }

        }

    }

    private void sortTrumps(ArrayList<Card> listToBeSorted) {

        for (int i = 0; i < listToBeSorted.size(); i++) {

            int minIndex = i;

            for (int j = i + 1; j < listToBeSorted.size(); j++) {
                if (compareTrumps(listToBeSorted.get(j), listToBeSorted.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Card temp = listToBeSorted.get(i);
                listToBeSorted.set(i, listToBeSorted.get(minIndex));
                listToBeSorted.set(minIndex, temp);

            }


        }
    }

    private Card lowestHigherRankedTrump(Card theCard, Player thePlayer){

        if(thePlayer.equals(computer1)){
            for(int i=0; i<sortedTrumpsCom1.size();i++){
                if(compareTrumps(theCard, sortedTrumpsCom1.get(i)) < 0){
                    return sortedTrumpsCom1.get(i);
                }

            }

            return null;

        }

        else if(thePlayer.equals(computer2)){
            for(int i=0; i<sortedTrumpsCom2.size();i++){
                if(compareTrumps(theCard, sortedTrumpsCom2.get(i)) < 0){
                    return sortedTrumpsCom2.get(i);
                }

            }
            return null;
        }

        else{
            return null;
        }

    }

    // compares two off suit cards returns -1 is first card is lower ranked card
    // and returns 1 if second card is a lower ranked card
    private int compareOffSuit (Card firstCard, Card secondCard){
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
            return 1;
        }
        else{
            return -1;
        }



    }



    private void sortOffSuit(ArrayList<Card> listToBeSorted) {

        for (int i = 0; i < listToBeSorted.size(); i++) {

            int minIndex = i;

            for (int j = i + 1; j < listToBeSorted.size(); j++) {
                if (compareOffSuit(listToBeSorted.get(j), listToBeSorted.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Card temp = listToBeSorted.get(i);
                listToBeSorted.set(i, listToBeSorted.get(minIndex));
                listToBeSorted.set(minIndex, temp);

            }

        }
    }

    private boolean isHigherOffSuit(Card firstCard, Card secondCard){
        Integer[] firstSuitRank;
        if(firstCard.getSuit() == Card.HEARTS){
            firstSuitRank = RANK_HEARTS;
        }

        else if(firstCard.getSuit() == Card.DIAMONDS){
            firstSuitRank = RANK_DIAMONDS;
        }

        else if(firstCard.getSuit() == Card.CLUBS){
            firstSuitRank = RANK_CLUBS;
        }

        else{
            firstSuitRank = RANK_SPADES;

        }

        Integer[] secondSuitRank;

        if(secondCard.getSuit() == Card.HEARTS){
            secondSuitRank = RANK_HEARTS;
        }

        else if(secondCard.getSuit() == Card.DIAMONDS){
            secondSuitRank = RANK_DIAMONDS;
        }

        else if(secondCard.getSuit() == Card.CLUBS){
            secondSuitRank = RANK_CLUBS;
        }

        else{
            secondSuitRank = RANK_SPADES;

        }

        return (Arrays.asList(firstSuitRank).indexOf(firstCard.getValue()) <=
                Arrays.asList(secondSuitRank).indexOf(secondCard.getValue()));

    }

   private Card lowestHigherRankedOffSuit(Card theCard, Player thePlayer){

        if(thePlayer.equals(computer1)){

            ArrayList<Card> offSuitList;

            if(theCard.getSuit() == Card.HEARTS){
                offSuitList = sortedHeartsCom1;
            }

            else if(theCard.getSuit() == Card.DIAMONDS){
                offSuitList = sortedDiamondsCom1;
            }

            else if(theCard.getSuit() == Card.SPADES){
                offSuitList = sortedSpadesCom1;
            }

            else{
                offSuitList = sortedClubsCom1;
            }
            for(int i=0; i<offSuitList.size();i++){
                if(compareOffSuit(theCard, offSuitList.get(i)) < 0){
                    return offSuitList.get(i);
                }

            }

            return null;

        }

        else if(thePlayer.equals(computer2)){
            ArrayList<Card> offSuitList;

            if(theCard.getSuit() == Card.HEARTS){
                offSuitList = sortedHeartsCom2;
            }

            else if(theCard.getSuit() == Card.DIAMONDS){
                offSuitList = sortedDiamondsCom2;
            }

            else if(theCard.getSuit() == Card.SPADES){
                offSuitList = sortedSpadesCom2;
            }

            else{
                offSuitList = sortedClubsCom2;
            }
            for(int i=0; i<offSuitList.size();i++){
                if(compareOffSuit(theCard, offSuitList.get(i)) < 0){
                    return offSuitList.get(i);
                }

            }

            return null;
        }

        else{
            return null;
        }

    }

    private Card lowestRankedOffSuit(int theSuit, Player thePlayer){

        if(thePlayer.equals(computer1)){

            Card lowestOffSuit;

            if(theSuit == Card.HEARTS &&!sortedHeartsCom1.isEmpty()) {
                lowestOffSuit = sortedHeartsCom1.get(0);
                return lowestOffSuit;

            }

            else if(theSuit == Card.DIAMONDS && !sortedDiamondsCom1.isEmpty()) {
                lowestOffSuit = sortedDiamondsCom1.get(0);
                return lowestOffSuit;

            }

            else if(theSuit == Card.SPADES && !sortedSpadesCom1.isEmpty()){
                lowestOffSuit = sortedSpadesCom1.get(0);
                return lowestOffSuit;
            }

            else if(theSuit == Card.CLUBS && !sortedClubsCom1.isEmpty()){
                lowestOffSuit = sortedClubsCom1.get(0);
                return lowestOffSuit;
            }

            else {
                return null;
            }


        }

        else if(thePlayer.equals(computer2)){

            Card lowestOffSuit;

            if(theSuit == Card.HEARTS &&!sortedHeartsCom2.isEmpty()) {
                lowestOffSuit = sortedHeartsCom2.get(0);
                return lowestOffSuit;

            }

            else if(theSuit == Card.DIAMONDS && !sortedDiamondsCom2.isEmpty()) {
                lowestOffSuit = sortedDiamondsCom2.get(0);
                return lowestOffSuit;

            }

            else if(theSuit == Card.SPADES && !sortedSpadesCom2.isEmpty()){
                lowestOffSuit = sortedSpadesCom2.get(0);
                return lowestOffSuit;
            }

            else if(theSuit == Card.CLUBS && !sortedClubsCom2.isEmpty()){
                lowestOffSuit = sortedClubsCom2.get(0);
                return lowestOffSuit;
            }

            else {
                return null;
            }

        }


        else{
            return null;
        }

    }



    private Card highestRankedOffSuit(Player thePlayer){

        ArrayList<Card> sortedHearts;
        ArrayList<Card> sortedDiamonds;
        ArrayList<Card> sortedSpades;
        ArrayList<Card> sortedClubs;


        if(thePlayer.equals(computer1)) {
            sortedHearts = sortedHeartsCom1;
            sortedDiamonds = sortedDiamondsCom1;
            sortedSpades = sortedSpadesCom1;
            sortedClubs = sortedClubsCom1;

        }
        else{
            sortedHearts = sortedHeartsCom2;
            sortedDiamonds = sortedDiamondsCom2;
            sortedSpades = sortedSpadesCom2;
            sortedClubs = sortedClubsCom2;
        }

        ArrayList<Card> highestOffSuitList = new ArrayList<Card>();

        if(!sortedHearts.isEmpty()){
            highestOffSuitList.add(sortedHearts.get(sortedHearts.size()-1));
        }

        if(!sortedDiamonds.isEmpty()){
            highestOffSuitList.add(sortedDiamonds.get(sortedDiamonds.size()-1));
        }

        if(!sortedSpades.isEmpty()){
            highestOffSuitList.add(sortedSpades.get(sortedSpades.size()-1));

        }

        if(!sortedClubs.isEmpty()){
            highestOffSuitList.add(sortedClubs.get(sortedClubs.size()-1));
        }

        if(highestOffSuitList.size() == 1){
            return highestOffSuitList.get(0);
        }

        else if(highestOffSuitList.size() == 2){

            if(isHigherOffSuit(highestOffSuitList.get(0), highestOffSuitList.get(1))){
                return highestOffSuitList.get(0);
            }

            else{
                return highestOffSuitList.get(1);
            }

        }

        else if(highestOffSuitList.size() == 3){
            Card highestCard;

            if(isHigherOffSuit(highestOffSuitList.get(0), highestOffSuitList.get(1))){
                highestCard = highestOffSuitList.get(0);
            }

            else{
                highestCard = highestOffSuitList.get(1);
            }

            if(isHigherOffSuit(highestCard, highestOffSuitList.get(2))){
                return highestCard;
            }

            else{
                return highestOffSuitList.get(2);
            }

        }

        return null;

    }




    private Card lowestRankedOffSuit(Player thePlayer){


        ArrayList<Card> sortedHearts;
        ArrayList<Card> sortedDiamonds;
        ArrayList<Card> sortedSpades;
        ArrayList<Card> sortedClubs;


        if(thePlayer.equals(computer1)) {
            sortedHearts = sortedHeartsCom1;
            sortedDiamonds = sortedDiamondsCom1;
            sortedSpades = sortedSpadesCom1;
            sortedClubs = sortedClubsCom1;

        }
        else{
            sortedHearts = sortedHeartsCom2;
            sortedDiamonds = sortedDiamondsCom2;
            sortedSpades = sortedSpadesCom2;
            sortedClubs = sortedClubsCom2;
        }

        ArrayList<Card> lowestOffSuitList = new ArrayList<Card>();

        if(!sortedHearts.isEmpty()){
            lowestOffSuitList.add(sortedHearts.get(0));
        }

        if(!sortedDiamonds.isEmpty()){
            lowestOffSuitList.add(sortedDiamonds.get(0));
        }

        if(!sortedSpades.isEmpty()){
            lowestOffSuitList.add(sortedSpades.get(0));

        }

        if(!sortedClubs.isEmpty()){
            lowestOffSuitList.add(sortedClubs.get(0));
        }

        if(lowestOffSuitList.size() == 1){
            return lowestOffSuitList.get(0);
        }

        else if(lowestOffSuitList.size() == 2){

            if(isHigherOffSuit(lowestOffSuitList.get(0), lowestOffSuitList.get(1))){
                return lowestOffSuitList.get(1);
            }

            else{
                return lowestOffSuitList.get(0);
            }

        }

        else if(lowestOffSuitList.size() == 3){
            Card lowestCard;

            if(isHigherOffSuit(lowestOffSuitList.get(0), lowestOffSuitList.get(1))){
                lowestCard = lowestOffSuitList.get(1);
            }

            else{
                lowestCard = lowestOffSuitList.get(0);
            }

            if(isHigherOffSuit(lowestCard, lowestOffSuitList.get(2))){
                return lowestOffSuitList.get(2);
            }

            else{
                return lowestCard;
            }

        }

        return null;

    }



    private void updateAllTrumps(){
        ArrayList<Card> trumpsToCompare = new ArrayList<Card>();
        trumpsToCompare.add(firstPlayed);
        trumpsToCompare.add(secondPlayed);
        trumpsToCompare.add(thirdPlayed);

        Card winningCard = findBestTrump(trumpsToCompare);

        if(firstPlayed.equals(winningCard)){
            updateTrickWon(firstCardPlayer);
            lastTrickWinner = firstCardPlayer;
        }
        else if(secondPlayed.equals(winningCard)){
            updateTrickWon(secondCardPlayer);
            lastTrickWinner = secondCardPlayer;
        }
        else if(thirdPlayed.equals(winningCard)){
            updateTrickWon(thirdCardPlayer);
            lastTrickWinner = thirdCardPlayer;
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
            lastTrickWinner = firstCardPlayer;
        }
        else if(secondPlayed.equals(winningCard)){
            updateTrickWon(secondCardPlayer);
            lastTrickWinner = secondCardPlayer;
        }
        else if(thirdPlayed.equals(winningCard)){
            updateTrickWon(thirdCardPlayer);
            lastTrickWinner = thirdCardPlayer;
        }
        else {
            Log.d("andyoc", "no winning card");
        }

    }

    private void initialiseSortedLists(){

        for(int i = 0; i < 5; i++) {
            if(isTrump(playersCards[i])) {
                sortedTrumpsPlayer.add(playersCards[i]);
            }
        }

        for(int i = 0; i < 5; i++) {
            if(isTrump(com1Cards[i])) {
                sortedTrumpsCom1.add(com1Cards[i]);
            }

            else if(com1Cards[i].getSuit() == Card.SPADES){
                sortedSpadesCom1.add(com1Cards[i]);

            }

            else if(com1Cards[i].getSuit() == Card.CLUBS){
                sortedClubsCom1.add(com1Cards[i]);
            }

            else if(com1Cards[i].getSuit() == Card.HEARTS){
                sortedHeartsCom1.add(com1Cards[i]);
            }

            else{
                sortedDiamondsCom1.add(com1Cards[i]);
            }
        }

        for(int i = 0; i < 5; i++) {
            if(isTrump(com2Cards[i])) {
                sortedTrumpsCom2.add(com2Cards[i]);
            }

            else if(com2Cards[i].getSuit() == Card.SPADES){
                sortedSpadesCom2.add(com2Cards[i]);

            }

            else if(com2Cards[i].getSuit() == Card.CLUBS){
                sortedClubsCom2.add(com2Cards[i]);
            }

            else if(com2Cards[i].getSuit() == Card.HEARTS){
                sortedHeartsCom2.add(com2Cards[i]);
            }

            else{
                sortedDiamondsCom2.add(com2Cards[i]);
            }
        }

        //sort all trumps lists
        //see instance variables above for sorted trumps
        //see notes 45 folder

        if(!sortedTrumpsPlayer.isEmpty()){
            sortTrumps(sortedTrumpsPlayer);

        }

        if(!sortedTrumpsCom1.isEmpty()){
            sortTrumps(sortedTrumpsCom1);
        }


        if(!sortedTrumpsCom2.isEmpty()){
            sortTrumps(sortedTrumpsCom2);
        }

        if(!sortedClubsCom1.isEmpty()){
            sortOffSuit(sortedClubsCom1);
        }

        if(!sortedSpadesCom1.isEmpty()){
            sortOffSuit(sortedSpadesCom1);
        }

        if(!sortedHeartsCom1.isEmpty()){
            sortOffSuit(sortedHeartsCom1);
        }

        if(!sortedDiamondsCom1.isEmpty()){
            sortOffSuit(sortedDiamondsCom1);
        }

        if(!sortedClubsCom2.isEmpty()){
            sortOffSuit(sortedClubsCom2);
        }

        if(!sortedSpadesCom2.isEmpty()){
            sortOffSuit(sortedSpadesCom2);
        }

        if(!sortedHeartsCom2.isEmpty()){
            sortOffSuit(sortedHeartsCom2);
        }

        if(!sortedDiamondsCom2.isEmpty()){
            sortOffSuit(sortedDiamondsCom2);
        }


    }

    private void removeCardFromLists(Card theCard, Player thePlayer){

        if(thePlayer.equals(computer1)) {

            if (isTrump(theCard)) {
                for (int i = 0; i < sortedTrumpsCom1.size(); i++) {
                    if (sortedTrumpsCom1.get(i).equals(theCard)) {
                        sortedTrumpsCom1.remove(i);
                    }
                }
            }

            if (!sortedHeartsCom1.isEmpty()) {
                for (int i = 0; i < sortedHeartsCom1.size(); i++) {
                    if (sortedHeartsCom1.get(i).equals(theCard)) {
                        sortedHeartsCom1.remove(i);
                    }
                }
            }

            if (!sortedDiamondsCom1.isEmpty()) {
                for (int i = 0; i < sortedDiamondsCom1.size(); i++) {
                    if (sortedDiamondsCom1.get(i).equals(theCard)) {
                        sortedDiamondsCom1.remove(i);
                    }
                }
            }

            if (!sortedSpadesCom1.isEmpty()) {
                for (int i = 0; i < sortedSpadesCom1.size(); i++) {
                    if (sortedSpadesCom1.get(i).equals(theCard)) {
                        sortedSpadesCom1.remove(i);
                    }
                }
            }
            if (!sortedClubsCom1.isEmpty()){
                for (int i = 0; i < sortedClubsCom1.size(); i++) {
                    if (sortedClubsCom1.get(i).equals(theCard)) {
                        sortedClubsCom1.remove(i);
                    }
                }
            }
        }

        else if(thePlayer.equals(computer2)) {

            if (isTrump(theCard)) {
                for (int i = 0; i < sortedTrumpsCom2.size(); i++) {
                    if (sortedTrumpsCom2.get(i).equals(theCard)) {
                        sortedTrumpsCom2.remove(i);
                    }
                }
            }

            if (!sortedHeartsCom2.isEmpty()) {
                for (int i = 0; i < sortedHeartsCom2.size(); i++) {
                    if (sortedHeartsCom2.get(i).equals(theCard)) {
                        sortedHeartsCom2.remove(i);
                    }
                }
            }

            if (!sortedDiamondsCom2.isEmpty()) {
                for (int i = 0; i < sortedDiamondsCom2.size(); i++) {
                    if (sortedDiamondsCom2.get(i).equals(theCard)) {
                        sortedDiamondsCom2.remove(i);
                    }
                }
            }

            if (!sortedSpadesCom2.isEmpty()) {
                for (int i = 0; i < sortedSpadesCom2.size(); i++) {
                    if (sortedSpadesCom2.get(i).equals(theCard)) {
                        sortedSpadesCom2.remove(i);
                    }
                }
            }
            if (!sortedClubsCom2.isEmpty()){
                for (int i = 0; i < sortedClubsCom2.size(); i++) {
                    if (sortedClubsCom2.get(i).equals(theCard)) {
                        sortedClubsCom2.remove(i);
                    }
                }
            }
        }
    }


}