package com.sap.ase;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    static class Formatter {
        String[][] numberMarks = new String[][]{
                {" _ ", "   ", " _ ", " _ ", "   ", " _ ", " _ ", " _ ", " _ ", " _ ",},
                {"| |", "|  ", " _|", " _|", "|_|", "|_ ", "|_ ", "  |", "|_|", "|_|",},
                {"|_|", "|  ", "|_ ", " _|", "  |", " _|", "|_|", "  |", "|_|", " _|",}};

        public List<StringBuilder> getLines() {
            return lines;
        }

        List<StringBuilder> lines = new ArrayList();
        Formatter() {
            lines.add(new StringBuilder());
            lines.add(new StringBuilder());
            lines.add(new StringBuilder());
        }

        private void outputBuzz() {
            getLines().get(0).append("      __ __ ");
            getLines().get(1).append("|_ | | /  / ");
            getLines().get(2).append("|_||_|/_ /_ ");
        }

        private void outputFizz() {
            getLines().get(0).append(" _    __ __ ");
            getLines().get(1).append("|_  |  /  / ");
            getLines().get(2).append("|   | /_ /_ ");
        }

        private void outputNum(int i) {
            char[] numberChars = String.valueOf(i).toCharArray();
            for (char numberChar : numberChars) {
                int i1 = Integer.parseInt("" + numberChar);
                getLines().get(0).append(numberMarks[0][i1]);
                getLines().get(1).append(numberMarks[1][i1]);
                getLines().get(2).append(numberMarks[2][i1]);
            }

        }

        private void outputSeparator(List<StringBuilder> list) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    list.get(i).append(",");
                } else {
                    list.get(i).append(" ");
                }
            }
        }

        private String getResult() {
            return getLines().get(0).toString() + "\n" + getLines().get(1).toString() + "\n" + getLines().get(2).toString();
        }
    }


    public String countTo(int end) {
        if (end <= 0) return "";

        Formatter formatter = new Formatter();
        for (int i = 1; i <= end; i++) {
            if (i % 3 == 0) {
                formatter.outputFizz();
                if(i % 5 == 0){
                    formatter.outputBuzz();
                }
            } else if (i % 5 == 0) {
                formatter.outputBuzz();
            } else {
                formatter.outputNum(i);
            }
            if (i < end) {
                formatter.outputSeparator(formatter.getLines());
            }
        }
        String output = formatter.getResult();
        System.out.println(output);
        return output;
    }

}
