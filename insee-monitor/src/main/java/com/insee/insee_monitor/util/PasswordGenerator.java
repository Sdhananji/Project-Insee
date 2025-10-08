package com.insee.insee_monitor.util;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PasswordGenerator {
    private static final String UPPER="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER="abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS="0123456789";
    private static final String SYMBOLS="@#$";
    private static final String CHARACTERS=UPPER+LOWER+DIGITS+SYMBOLS;

    private static final int PASSWORD_LENGTH = 12;
     private static final SecureRandom RANDOM=new SecureRandom();

    public static String generatePassword(int length){
        if (length<4){
            throw new IllegalArgumentException("length should be at least 4 to include all character types");
        }

        List<Character> passwordChars=new ArrayList<>(length);

        //ensure at least one character from each category
        passwordChars.add(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
        passwordChars.add(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
        passwordChars.add(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        passwordChars.add(SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length())));

        for (int i=4;i<PASSWORD_LENGTH;i++){
            passwordChars.add(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        //shuffle to avoid predictable sequences
        Collections.shuffle(passwordChars,RANDOM);
        
        StringBuilder password=new StringBuilder();
        for(char c:passwordChars)password.append(c);
        return password.toString();



    }
    public static String generatePassword(){
        return generatePassword(12);

    }

        
}
