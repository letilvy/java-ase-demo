package com.sap.ase;

public class FizzBuzz {

    public String countTo(int c) {
        if(c<=0) return "";

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for(int i=1; i<=c; i++) {
            if (i%3==0) {
                sb1.append(" _    __ __ ");
                sb2.append("|_  |  /  / ");
                sb3.append("|   | /_ /_ ");
            }
            if(i%5==0) {
                sb1.append("      __ __ ");
                if (i < c) {
                    sb1.append(" ");
                }
                sb2.append("|_ | | /  / ");
                if (i < c) {
                    sb2.append(" ");
                }
                sb3.append("|_||_|/_ /_ ");
                if (i < c) {
                    sb3.append(",");
                }
            }
            if (i%3==0 && i%5!=0) {
                if (i < c) {
                    sb1.append(" ");
                    sb2.append(" ");
                    sb3.append(",");
                }
            }
            if (i%3!=0 && i%5!=0) {
                String[][] numberMarks = new String[][]{
                        {" _ ","   "," _ "," _ ","   "," _ "," _ "," _ "," _ "," _ ",},
                        {"| |","|  "," _|"," _|","|_|","|_ ","|_ ","  |","|_|","|_|",},
                        {"|_|","|  ","|_ "," _|","  |"," _|","|_|","  |","|_|"," _|",}};

                char[] numberChars = String.valueOf(i).toCharArray();
                for(char numberChar: numberChars) {
                    int i1 = Integer.parseInt(""+numberChar);
                    sb1.append(numberMarks[0][i1]);
                    sb2.append(numberMarks[1][i1]);
                    sb3.append(numberMarks[2][i1]);
                }
                if (i < c) {
                    sb1.append(" ");
                    sb2.append(" ");
                    sb3.append(",");
                }
            }
        }
        System.out.println(sb1.toString() + "\n" + sb2.toString() + "\n" + sb3.toString());
        return sb1.toString() + "\n" + sb2.toString() + "\n" + sb3.toString();
    }

}
