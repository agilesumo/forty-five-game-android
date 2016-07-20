package com.agilesumo.fortyfive;

/**
 * Created by andy on 08/07/2016.
 */
public class FortyFiveGame {

    private int playersScore = 0;

    private int computer1Score = 0;

    private int computer2Score = 0;

    private Player dealer;





    public FortyFiveGame(){

        dealer = Hand.computer2;

    }

    public void nextDealer(){
        if(dealer.equals(Hand.computer2)){
            dealer = Hand.player;
        }

        else if(dealer.equals(Hand.player)){
            dealer = Hand.computer1;
        }

        else{
            dealer = Hand.computer2;
        }
    }

    public boolean isGameOver(){

        return playersScore >= 45 || computer1Score >= 45 || computer2Score >= 45;
    }

    public Player getWinner(){

        if(isGameOver()){
            if(playersScore >= computer1Score && playersScore >= computer2Score){
                return Hand.player;
            }

            else if(computer1Score > playersScore && computer1Score >= computer2Score){
                return Hand.computer1;
            }

            else{
                return Hand.computer2;
            }
        }

        else{
            return null;
        }
    }

    public Player getDealer(){
        return dealer;
    }

    public void addToScore(Player thePlayer){

        if(thePlayer.equals(Hand.player)){
            playersScore += 5;
        }

        else if(thePlayer.equals(Hand.computer1)){
            computer1Score += 5;
        }

        else if(thePlayer.equals(Hand.computer2)){
            computer2Score += 5;
        }
    }

    public int getTotalScore(Player thePlayer){

        if(thePlayer.equals(Hand.player)){
            return playersScore;
        }

        else if(thePlayer.equals(Hand.computer1)){
            return computer1Score;
        }

        else if(thePlayer.equals(Hand.computer2)){
            return computer2Score;
        }

        return 1;
    }

}
