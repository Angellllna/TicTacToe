package com.tic;

public class Tictactoe {
    final int X = 1;
    final int O = 2;

    boolean is_0_turn;
    Board board = new Board();

    Tictactoe() {
        this.is_0_turn = false;
    }

    Tictactoe(boolean is_0_turn) {
        this.is_0_turn = is_0_turn;
    }

    boolean checkWin(int dot)
    {
        for (int i = 0; i < 3; i++)
            if(checkrow(i, dot) || checkcolumn(i, dot))
                return true;
        if(checkd(dot) || checkdb(dot))
            return true;
        return false;
    }

    boolean checkrow(int i, int dot)
    {
        if (board.Get(i, 0)== dot && board.Get(i, 1) == dot &&
                board.Get(i, 2) == dot)
            return true;
        return false;
    }

    boolean checkcolumn(int i, int dot)
    {
        if (board.Get(0, i) == dot && board.Get(1, i) == dot &&
                board.Get(2, i)== dot)
        return true;
        return false;
    }

    boolean checkd(int dot)
    {
        if (board.Get(0, 0) == dot && board.Get(1, 1) == dot &&
                board.Get(2, 2)== dot)
            return true;
        return false;
    }

    boolean checkdb(int dot)
    {
        if (board.Get(2, 0) == dot && board.Get(1, 1) == dot &&
                board.Get(0, 2)== dot)
            return true;
        return false;
    }


    boolean turn(int x, int y)
    {
        boolean f;

        if(is_0_turn)
            f = board.set(x, y, O);
        else
            f = board.set(x, y, X);

        if(f)
            is_0_turn ^= true;

        return f;
    }

    void clear(int x, int y)
    {
        board.clear(x, y);

    }

    char Get(int x, int y)
    {
        int v = board.Get(x, y);
        if(v == O)
            return 'O';
        if(v == X)
            return 'X';

        return '•';

    }
    boolean is_end()
    {
        if(checkWin(O) || checkWin(X) || board.isTableFull())
            return true;
        return false;

    }

    boolean set(int x, int y, int dot)
    {
        return board.set(x, y, dot);
    }


    boolean IsOturn()
    {
        return is_0_turn;
    }

}
