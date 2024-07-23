package App;

import java.util.Scanner;

public class Controller {
    private static String[] result;
    private Stack[] Data;
    private int size;
    private int cursor;
    private String[] Dtemp;
    private int Ltemp;

    Controller(){
        this.size = 0;
        this.cursor = 0;
        result = new String[]{"Fail", "Success"};
    }
    public void run() {
        Scanner sc = new Scanner(System.in);
        String ans;
        String r = "";
        while(true){
            System.out.print("====================\nAppCommandList\n\nCreate\nList\nEnter\nDrop\nExit\n\nInputCommand\n>> ");
            ans = sc.nextLine().toLowerCase();
            if (ans.equals("create")) {
                System.out.print("Input Table Name\n>> ");
                ans = sc.nextLine();
                r = Integer.toString(Create(ans));
            } else if (ans.equals("list")) {
                System.out.print("Reading..\n");
                r = Integer.toString(List());
            } else if (ans.equals("enter")) {
                System.out.print("Input Table Name\n>> ");
                ans = sc.nextLine();
                r = Integer.toString(Enter(ans));
            } else if (ans.equals("drop")){
                System.out.print("Input Table Name\n>> ");
                ans = sc.nextLine();
                r = Integer.toString(Drop(ans));
            } else if (ans.equals("exit")) {
                break;
            }
            System.out.println("Result : " + result[Integer.parseInt(r) + 1] + "\n");
        }
        System.out.println("\nExited");
    }
    private int Create(String n){
        try {
            Stack[] temp = this.Data;
            this.Data = new Stack[++this.size];

            for (int i = 0; i < this.cursor; i++) {
                this.Data[i] = temp[i];
                Ltemp = this.Data[i].getSize();
                Dtemp = new String[Ltemp];
                for (int j = 0; j < Ltemp; j++) {
                    Dtemp[j] = this.Data[i].Out()[j];
                }
                this.Data[i].In(Dtemp, Ltemp);
            }
            this.Data[this.cursor++] = new Stack(n);

            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    private int List(){
        if (this.size == 0) {
            return -1;
        }
        for (int i = 0; i < this.size; i++){
            System.out.println(Integer.toString(i)+". "+Data[i].getName());
        }
        return 0;
    }

    private int Enter(String n){
        for (int i = 0; i < this.size; i++){
            if (Data[i].getName().equals(n)){
                Data[i].run();
                return 0;
            }
        }
        return -1;
    }

    private int Drop(String n){
        if (this.size == 0) {
            return -1;
        }
        for (int i = 0; i < this.size; i++){
            if (Data[i].getName().equals(n)){
                Stack[] temp;
                temp = new Stack[--this.size];
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
        }

        return -1;
    }
}