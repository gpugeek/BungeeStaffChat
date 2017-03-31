package me.jamesj.bungeestaffchat.utils;

import java.io.*;

/**
 * Created by James on 31/03/2017.
 * (c) JamesJ, or respective owners, 2017
 */
public class DiscUtils {

    private final static String UTF8 = "UTF-8";

    // -------------------------------------------- //
    // BYTE
    // -------------------------------------------- //


    public static byte[] readBytes(File file) throws IOException {
        int length = (int) file.length();
        byte[] output = new byte[length];
        InputStream in = new FileInputStream(file);
        int offset = 0;
        while (offset < length) {
            offset += in.read(output, offset, (length - offset));
        }
        in.close();
        return output;
    }

    public static void writeBytes(File file, byte[] bytes) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.close();
    }

    // -------------------------------------------- //
    // STRING
    // -------------------------------------------- //

    public static void write(File file, String content) throws IOException {
        writeBytes(file, utf8(content));
    }

    public static String read(File file) throws IOException {
        return utf8(readBytes(file));
    }
    public static byte[] utf8(String string) {
        try {
            return string.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String utf8(byte[] bytes) {
        try {
            return new String(bytes, UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
