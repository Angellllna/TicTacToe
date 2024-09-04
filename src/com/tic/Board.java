package com.tic;

public class Board {

    final int empty = 0;
    int table[] [];

    Board()
    {
        table = new int[3][3];

    }

    boolean isTableFull()
    {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(table[i][j] == empty)
                    return false;
        return true;
    }

    int Get(int x, int y)
    {
        return table[x][y];
    }

    boolean set(int x, int y, int v)
    {
        if(isCellValid(x, y) && table[x][y] == empty)
        {
            table[x][y] = v;
            return true;
        }
        return false;
    }

    void clear(int x, int y)
    {
        if(isCellValid(x, y))
        {
            table[x][y] = empty;
        }
    }

    boolean isCellValid(int x, int y)
    {
        if(x < 0 || y < 0 || x >= 3 || y >= 3)
            return false;
        return true;
    }


}
