package com.onix;


import com.github.freva.asciitable.AsciiTable;

public class generate_table {
    private final String[] header = {"№","Value"};
    private String[][] data;
    public generate_table(String[] args){
        data = new String[args.length][2];
        for (int i=0;i<args.length;i++){
            data[i][0]= String.valueOf(i+1);
            data[i][1]=args[i];
        }

    }
    void show_table(){
        System.out.println(AsciiTable.getTable(this.header,this.data));
    }

}
