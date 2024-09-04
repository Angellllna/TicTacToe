package com.tic;


import java.io.IOException;

public class Tici {
    public static void main(String[] args) throws IOException {
//        Tictactoe t = new Tictactoe();
//        Control control = new Control(t);
//
//        control.Start();
        PlayersController test = new PlayersController("test.txt");

        test.Start();
    }
}