package com.onix;

public class Main {
    private String count;
    public static void main(String[] args) {
        /**Ход игры:
         *
         * 1. Ввод параметров через CMD
         *  - Проверка параметров
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

        //Основной блок игры
        System.out.println("Available moves:");
        for(int i=0;i< args.length;i++) {
            System.out.println(String.format("%d - %s",i+1,args[i]));
        }
        System.out.println("0 - exit\n? - help");
        new game_rules(args);
        new generate_key();

        System.out.println("You win!");
        System.out.println("You lose!");
    }
}
