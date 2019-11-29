package com.sap.ase;

public class StringCalculator {

    public static final String DEFAULT_DELIMITER = ",";
    public static final String CUSTOMER_DELIMITER_PREFIX = "//";
    public static final int CUSTOMER_HEADER_LENGTH = 4;

    public int add(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        return addNumbers(getDelimiterRegex(s), getNumbers(s));
    }

    private int addNumbers(String delimiters, String numbers) {
        int returnValue = 0;
        for (String n : numbers.split(delimiters)) {
            int num = Integer.parseInt(n);
            if (num < 0) {
                throw new RuntimeException("negatives not allowed");
            }
            returnValue += num;
        }
        return returnValue;
    }

    private String getNumbers(String s) {
        if (isCustomerDelimiter(s)) {
            return s.substring(CUSTOMER_HEADER_LENGTH);
        }
        return s;
    }

    private boolean isCustomerDelimiter(String s) {
        return s.startsWith(CUSTOMER_DELIMITER_PREFIX);
    }

    private String getDelimiterRegex(String s) {
        if (isCustomerDelimiter(s)) {
            return regexOfDelimiter(String.valueOf(s.charAt(2)));
        }
        return regexOfDelimiter(DEFAULT_DELIMITER);
    }

    private String regexOfDelimiter(String delimiter) {
        return "[\n" + delimiter + "]";
    }
}
