package com.insee.insee_monitor.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {


    @Test
    void testGeneratePasswordLength(){
        String password =PasswordGenerator.generatePassword();
        assertEquals(12,password.length(),"Password should be 12 characters long") ;
       }

    @Test
    void testGeneratePasswordCharacters(){
        String password=PasswordGenerator.generatePassword();
        assertTrue(password.matches("[A-Za-z0-9@#$]+"),"Password should contain valid characters");
    }

    @Test
    void testGeneratePsswordRandomness(){
        String pas1=PasswordGenerator.generatePassword();
        String pas2=PasswordGenerator.generatePassword();

        assertNotEquals(pas1,pas2,"Psswords should not be identical");
    }
}
