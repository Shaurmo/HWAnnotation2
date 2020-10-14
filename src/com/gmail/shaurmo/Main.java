package com.gmail.shaurmo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        TextContainer textContainer = new TextContainer("Text to save");
        Class<?> cls = textContainer.getClass();
        boolean saverAnnotationIsSet = false;

        if (cls.isAnnotationPresent(SaveTo.class)) {
            SaveTo saveTo = cls.getAnnotation(SaveTo.class);
            String filePath = saveTo.filePath();
            Method[] methods = cls.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    saverAnnotationIsSet = true;
                    try {
                        method.invoke(textContainer, filePath);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Save to file " + filePath + " is Done!");
                    break;
                }
            }
            if (!saverAnnotationIsSet) {
                System.out.println("Method is not annotated");
            }
        } else {
            System.out.println("Class is not annotated");
        }
    }
}
