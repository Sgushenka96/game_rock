package com.onix;

public class game_rules {

    public game_rules(String[] args){
        int may_moves = args.length;
        this.step_PC(may_moves);


    }
    public int step_PC(int may_moves){
        int step_PC = (int)(Math.random()*may_moves)+1;

        return step_PC;
    }

    public int step(int may_moves,int step_PC, int step_Player){
        int[] win_pos = new int[may_moves/2];
        int[] lose_pos = new int[may_moves/2];
        //Выигрышные позиции
        for (int i=0;i<win_pos.length;i++){
            win_pos[i]=step_PC+1+i;
            if(win_pos[i]>may_moves){
                win_pos[i]=win_pos[i]-may_moves;
            }
            if(step_Player==win_pos[i]){
                System.out.println("you win");
                return 1;
            }
        }
        //Проигрышные позиции
        for (int i=0;i<lose_pos.length;i++){
            lose_pos[i]=step_PC-1-i;
            if(lose_pos[i]<0){
                lose_pos[i]=may_moves+lose_pos[i];
            }
            if(step_Player==lose_pos[i]){
                System.out.println("you lose");
            }
        }
        if(step_Player==step_PC)
            System.out.println("draw");

        return 0;
    }


}
