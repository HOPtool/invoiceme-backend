package com.hoptool.resources;


/**
 *
 * @author tshaguy
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**

/***********************************************************
 * Introduction to Computers and Programming (Fall 2009)   *
 * Professor Evan Korth                                    *
 *                                                         *
 * File Name: RandomCharacter.java                         *
 * PIN: K0002F09085                                        *
 * Description: Generating Random Characters               *
 *                                                         *
 * Focus:                                                  *
 * a. Generating Random Characters                         *
 ***********************************************************/

// Beginning of class RandomCharacter
public class RandomCharacter {    
    
    
    
        public static String doRandomMixed()
        {
            String resp = "";
            try
            {
                
                
            final int NUM_OF_CHARS = 10;
        final int CHARS_PER_LINE = 11;

        // Print random characters between '!' and '~', 25 chars per line
            
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            
            resp += getRandomChar('!', '~');
        }
            
               
            
             resp = resp.replaceAll("`", "!").replaceAll("'", "@");
                
                
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
          return resp;
        }
        
        public static String doRandomMixed(int NUM_OF_CHARS )
        {
            String resp = "";
            try
            {
                
                
            //final int NUM_OF_CHARS = 10;
        int CHARS_PER_LINE = NUM_OF_CHARS + 1;

        // Print random characters between '!' and '~', 25 chars per line
            
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            
            resp += getRandomChar('!', '~');
        }
            
               
            
             resp = resp.replaceAll("`", "!").replaceAll("'", "@");//.replaceAll("\\", "~");
                
                
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
          return resp;
        }
        
        public static String doRandomPass(int len)
        {
        String resp = "";
        try
        {
               final String alphabet = "1234567890!@#$%^&*()~ASQWERTYUIOPDFGHJKLZXVBNM:?qwertyuiopasdfghjklzxcvbnm";
                final int N = alphabet.length();
                Random rd = new Random();
                int iLength = len;
                StringBuilder sb = new StringBuilder(iLength);
                for (int i = 0; i < iLength; i++) {
                    sb.append(alphabet.charAt(rd.nextInt(N)));
                }
                
                resp = sb.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return resp;  
       }

    // Beginning of method main
    public static void main(String[] args) {    

        // Declare final variables
        final int NUM_OF_CHARS = 10;
        final int CHARS_PER_LINE = 11;
            
            /*
             final int NUM_OF_CHARS = 175;
        final int CHARS_PER_LINE = 25;
            */

        // Print random characters between '!' and '~', 25 chars per line
            /*
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            if ((i + 1) % CHARS_PER_LINE == 0) 
                System.out.println("#### "+getRandomChar('!', '~'));
            else
                System.out.print(">>>> "+getRandomChar('!', '~') + " ");
        }
            */
            
            System.out.print(" YES : "+doRandomMixed().replaceAll("`", "!"));
            //System.out.print(getRandomChar('!', '~') + " ");
                    
    } // End of main

    /* Generate a random character between fromChar and toChar */
    // Beginning of char getRandomChar(char, char)
    public static char getRandomChar(char fromChar, char toChar) {

        // Get the Unicode of the character
        int unicode = fromChar + (int)((toChar - fromChar + 1) * Math.random());

        // Return the character
        return (char)unicode;
    
    } // End of char getRandomChar(char, char)

    /* Generate a random character */
    // Beginning of char getRandomChar()
    public static char getRandomChar() {
        
        return getRandomChar('\u0000', '\uFFFF');
    
    } // of char getRandomChar()
    
} // End of class RandomCharacter
