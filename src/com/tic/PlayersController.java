package com.tic;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
//import javafx.util.Pair;
import java.util.Scanner;

class Info implements Comparable<Info> {
    public final String name;
    public final Integer cntWin, cntLose;

    Info(String name, int cntWin, int cntLose) {
        this.name = name;
        this.cntWin = cntWin;
        this.cntLose = cntLose;
    }

    public int compareTo(Info x) {
        if(x.cntWin == this.cntWin) {
            if(x.cntLose == this.cntLose) {
                return this.name.compareTo(x.name);
            } else
                return x.cntLose.compareTo(this.cntLose);
        } else
            return x.cntWin.compareTo(this.cntWin);
    }
}

public class PlayersController {
    String filename;

    PlayersController(String filename) {
        this.filename = filename;
    }

    Set<Info> load() throws IOException {
        Set<Info> result = new TreeSet<Info>();

        File file = new File("info.tici");
        if(file.exists()) {
            FileReader fileReader = new FileReader("info.tici");
            Scanner scanner = new Scanner(fileReader);
            int cnt = scanner.nextInt();
            scanner.nextLine();
            for(int i = 0; i < cnt; ++i) {
                String name;
                int cntWin, cntLose;
                name = scanner.nextLine();
                cntWin = scanner.nextInt();
                cntLose = scanner.nextInt();
                scanner.nextLine();

                result.add(new Info(name, cntWin, cntLose));
            }
        }

        return result;
    }

    void save(Set<Info> info) throws IOException {
        FileWriter fileWriter = new FileWriter("info.tici");
        fileWriter.write(new String(info.size() + "\n"));
        for(Info x : info) {
            fileWriter.write(new String(x.name + "\n"));
            fileWriter.write(new String(x.cntWin + " " + x.cntLose + "\n"));
        }

        fileWriter.flush();
    }

    Info name_exists(Set<Info> info, String name) {
        for(Info x : info)
            if(x.name.equals(name))
                return x;
        return null;
    }

    void Start() throws IOException {
        Set<Info> info = load();

        String user_name;
        {
            System.out.println("Введіть ім'я: ");
            Scanner scanner = new Scanner(System.in);
            user_name = scanner.nextLine();
        }

        if(name_exists(info, user_name) == null) {
            System.out.println("Новий гравець");
        }

        Tictactoe tictactoe = new Tictactoe();

        Control control = new Control(tictactoe);

        int res = control.Start();


        Info user = name_exists(info, user_name);

        if(user != null)
            info.remove(user);

        if(user == null)
        {
            user = new Info(user_name, 0, 0);
        }
        if(res == 2)
            user = new Info(user_name, user.cntWin+1, user.cntLose);
        if(res == 1)
            user = new Info(user_name, user.cntWin, user.cntLose+1);

        info.add(user);

        for (Info x: info) {
            System.out.println(x.name + " " + x.cntWin + ":" + x.cntLose);
        }

        save(info);

    }
}
