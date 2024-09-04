package com.tic;
import java.io.*;
import java.util.Scanner;

class Simple
{
    int score , x, y;

    Simple(int score)
    {
     this.score = score;
     x = y = -1;
    }

}

public class Control {
    Scanner scanner;
    Tictactoe Game;

    Control(Tictactoe Game)
    {
        this.Game = Game;
        scanner = new Scanner(System.in);
    }

    void printTable()
    {
        System.out.println();
        for (int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(Game.Get(i, j) + " ");
            }
            System.out.println();
        }
    }

    void playerTurn()
    {
        int x, y;

        do{
            System.out.println("Введіть координати: ");
            x = scanner.nextInt() -1;
            y = scanner.nextInt() -1;

        }while (!Game.turn(x, y));
    }


    Simple minmax(int depth, boolean botTurn)
    {
        if(depth == 0 || Game.is_end())
        {
            if(Game.checkWin(Game.X))
                return new Simple(Integer.MIN_VALUE);
            if(Game.checkWin(Game.O))
                return new Simple(Integer.MAX_VALUE);

            if(Game.is_end())
                return new Simple(Integer.MAX_VALUE);

            return new Simple(0);

        }
        Simple best;


        if(botTurn)
            best = new Simple(Integer.MIN_VALUE);
        else
            best = new Simple(Integer.MAX_VALUE);

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int dot;

                if (botTurn)
                    dot = Game.O;
                else
                    dot = Game.X;

                if (Game.set(i, j, dot)) {
                    Simple dimpl = minmax(depth - 1, !botTurn);
                    dimpl.x = i;
                    dimpl.y = j;


                    Game.clear(i, j);

                    if (botTurn) {
                        if (best.score < dimpl.score)
                            best = dimpl;
                    }
                    else if (best.score > dimpl.score)
                            best = dimpl;

                }
            }
        }

        return best;

    }

    void botTurn()
    {
        int h = 3;

        Simple result = minmax(h, true);

        while (result.x == -1)
        {
            h--;
            result = minmax(h, true);
        }

            Game.turn(result.x, result.y);

    }


    int Start()
    {
        while (true)
        {
            if(!Game.IsOturn()) {
                printTable();
                playerTurn();
            }
            else
            {
                botTurn();
            }
            if(Game.is_end())
            {
                if(Game.checkWin(Game.O))
                {
                    System.out.println("Ти програв!!");
                    return 1;
                }
                else if(Game.checkWin(Game.X))
                {
                    System.out.println("Ти виграв!!");
                    return 2;
                }
                else
                {
                    System.out.println("Нічія!!");
                    return 0;
                }

            }
        }

    }


}
