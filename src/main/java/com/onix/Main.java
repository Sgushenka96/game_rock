package com.onix;

import com.github.freva.asciitable.AsciiTable;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    private static generate_table table;
    private static generate_key key_s;
    private static game_rules game;

    private static int step_PC;
    private static int step_Player;
    private static int count;
    private static byte[] key;

    //private generate_table table;
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        /**Ход игры:
         *
         * 1. Ввод параметров через CMD
         *  - Проверка параметров
         *  - Ход Компьютера и выдача HMAC-сообщения
         * 2. Отображение нумерованного списка с опциями меню
         * 3.
         */



        //Проверка аргументов
        if (args.length%2==0 || args.length==0){
            System.out.println("Wrong parametrs!\nOnly odd count of parametrs and more than 3");
            System.exit(0);
        }
        for (int i=0;i<args.length;i++){
            for (int j=0; j<args.length;j++){
                if(args[i].equals(args[j]) && i!=j){
                    System.out.println("Wrong parametrs!\nWithout duplicate values");
                    System.exit(0);
                }
            }
        }
        table = new generate_table(args);
        key_s = new generate_key();   //Генерация ключа
        key = key_s.key_gen();
        game = new game_rules(args);
        step_PC = game.step_PC(args.length);    //Ход Компьютера
        key_s.digestToHMAC(String.valueOf(step_PC),key);    //HMAС сообщение
        count = 0;

        //Отображение меню
        System.out.println("Available moves:");
        for(int i=0;i< args.length;i++) {
            System.out.println(String.format("%d - %s",i+1,args[i]));
        }
        System.out.print("0 - exit\n? - help\nEnter your move:");

        call_console(); //выбор команды
        System.out.println("Your move: "+args[step_Player-1]);
        System.out.println("Computer move: "+args[step_PC-1]);
        count = game.step(args.length, step_PC,step_Player);


        while (count!=1){   //Цикл игры
            step_PC = game.step_PC(args.length);    //Ход Компьютера
            key_s.digestToHMAC(String.valueOf(step_PC),key);    //HMAС сообщение
            System.out.print("Enter your move:");
            call_console();
            System.out.println("Your move: "+args[step_Player-1]);
            System.out.println("Computer move: "+args[step_PC-1]);
            count = game.step(args.length, step_PC,step_Player);
        }
        System.out.println("Key: "+ key_s.bytesToNext(key));

    }
    public static void call_console(){
        //Выбор пункта меню
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        if (cmd.equals("0")){
            System.out.println("Exit from game...");
            System.exit(0);
        }
        if (cmd.equals("?"))
            table.show_table();
        if (cmd.matches("[0-9]")){
            step_Player = Integer.parseInt(cmd);
        }
    }

}
