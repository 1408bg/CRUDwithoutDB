package App;

import java.util.Scanner;

public class Stack {
    private String[] Data;
    private int size;
    private int cursor;
    private Scanner sc;
    private String ans;
    private String r;
    private int intbf;
    private String strbf;
    private final String Name;

    private static String[] result;

    public String getData(int i) {
        if (i >= this.size) {
            return "-1";
        }

        return this.Data[i];
    }

    public String getName(){
        return Name;
    }

    public int getSize(){
        return this.size;
    }

    public int set(int i, String s) {
        if (i >= this.size) {
            return -1;
        }
        this.Data[i] = s;
        return 0;
    }

    Stack(String n) {
        this.r = "";
        this.size = 0;
        this.cursor = 0;
        Name = n;
        result = new String[]{"Fail", "Success"};
    }

    public void run() {
        this.sc = new Scanner(System.in);
        while (true) {
            System.out.print("====================\nStackCommandList\nName : " + Name +"\n\nCreate\nRead\nUpdate\nDelete\nExit\n\nInputCommand\n>> ");
            ans = sc.nextLine().toLowerCase();
            if (ans.equals("create")) {
                System.out.print("Input Data\n>> ");
                r = Integer.toString(this.Create(sc.nextLine()));
            } else if (ans.equals("read")) {
                System.out.print("Reading..\n");
                r = Integer.toString(this.Read());
            } else if (ans.equals("update")) {
                System.out.print("Input IndexNo\n>> ");
                intbf = sc.nextInt();
                sc.nextLine();
                System.out.print("Input Data\n>> ");
                strbf = sc.nextLine();
                r = Integer.toString(this.Update(intbf, strbf));
            } else if (ans.equals("delete")) {
                System.out.print("Input IndexNo\n>> ");
                r = Integer.toString(this.Delete(sc.nextInt()));
                sc.nextLine();
            } else if (ans.equals("exit")) {
                r = "0";
                break;
            } else {
                r = "-1";
                System.out.println("wrong command\n");
            }
            System.out.println("Result : " + result[Integer.valueOf(r) + 1] + "\n");
        }
        System.out.println("\nExited");
    }

    private String[] Add() {
        return new String[++this.size];
    }

    private int Create(String s) {
        try {
            String[] temp = this.Data;
            this.Data = Add();

            for (int i = 0; i < this.cursor; i++) {
                this.Data[i] = temp[i];
            }
            set(this.cursor++, s);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    private int Read() {
        if (this.size == 0) {
            return -1;
        }
        for (int i = 0; i < this.size; i++) {
            System.out.println(Integer.toString(i) + ". " + getData(i));
        }
        return 0;
    }

    private int Update(int i, String s){
        return set(i, s);
    }

    private int Delete(int i){
        String[] temp;
        if (set(i, "Error") == -1){
            return -1;
        }
        temp = new String[--this.size];
        for (int j = 0; j < i; j++){
            temp[j] = this.Data[j];
        }
        for (int j = i; j < this.size; j++){
            temp[j] = this.Data[j+1];
        }
        this.Data = temp;
        this.cursor--;

        return 0;
    }

    public String[] Out() {
        return this.Data;
    }

    public void In(String[] inL, int s){
        this.Data = new String[s];
        this.size = s;
        this.cursor = s;

        for (int i = 0; i < this.cursor; i++) {
            this.Data[i] = inL[i];
        }
    }
}
