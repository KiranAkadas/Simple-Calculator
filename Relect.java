import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.zip.*;

public class Relect {

    public static void main(String[] args) {
        try {
            List<String> classNames = new ArrayList<String>();
            List<ZipEntry> classEntries = new ArrayList<ZipEntry>();

            ZipInputStream zip = new ZipInputStream(
                    new FileInputStream("/Users/murtazaicecreamwala/Documents/GitHub/Simple-Calculator/classes.jar"));
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/', '.'); // including “.class”
                    if (className.indexOf("$") == -1 && className.indexOf("com.simplemobiletools.calculator") > -1) {
                        // This ZipEntry represents a class. Now, what class does it represent?
                        classEntries.add(entry);
                        // classNames.add(className.substring(0, className.length() -
                        // “.class”.length()));
                    }

                }
            }
            for (int i = 0; i < classEntries.size(); i++) {
                ZipEntry entry = classEntries.get(i);
                final Class<?> c = entry.getClass();
                Class[] declaredClasses = c.getDeclaredClasses();
                Field[] fields = c.getDeclaredFields();
                Method[] methods = c.getDeclaredMethods();
            }
            System.out.println(classEntries);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }

    }

}