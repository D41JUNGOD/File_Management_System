package file_manage;

import java.io.*;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class File_lock{

    private static final String algorithm = "AES";
    private static final String transformation = algorithm + "/ECB/PKCS5Padding";

    private Key key;

    public File_lock(String password) {
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), algorithm);
        this.key = key;
    }
    public File_lock() {

    }

    public void encrypt(String password,File source, File dest) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE,  key);
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(source));
            output = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[1024];
            int read = -1;
            output.write(password.getBytes());
            while ((read = input.read(buffer)) != -1) {
                output.write(cipher.update(buffer, 0, read));
            }
            output.write(cipher.doFinal());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ie) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ie) {
                }
            }
            source.delete();
        }

    }
    public int decrypt(String password, File source, File dest) throws Exception {
        String file_pw_str;
        Cipher cipher = Cipher.getInstance(transformation);
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(source));
            output = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[1024];
            byte[] file_pw_byte = new byte[16];
            int read = -1;
            input.read(file_pw_byte);
            file_pw_str = file_pw_byte.toString();
            System.out.println(file_pw_str);
            if(!file_pw_str.equals(password))
                return 0;

            SecretKeySpec key = new SecretKeySpec(password.getBytes(), algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            while ((read = input.read(buffer)) != -1) {
                output.write(cipher.update(buffer, 0, read));
            }
            output.write(cipher.doFinal());

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ie) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ie) {
                }
            }
        }
        return 1;
    }
}



