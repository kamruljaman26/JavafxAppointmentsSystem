package com.appointments.system.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {

    // encode and return the password
    public static PasswordManager getInstance(){
        return new PasswordManager();
    }

    public String encode(CharSequence charSequence) {
        // code copied from Savepoint
        /* MessageDigest instance for MD5. */
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        /* Add plain-text password bytes to digest using MD5 update() method. */
        m.update(charSequence.toString().getBytes());
        /* Convert the hash value into bytes */
        byte[] bytes = m.digest();
        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
        StringBuilder s = new StringBuilder();
        for (byte aByte : bytes) {
            s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        /* Complete hashed password in hexadecimal format */
        return s.toString();
    }

    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
