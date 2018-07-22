package com.moheqionglin.javassist;

import javassist.*;

public class Cl {
    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        replaceMethodBody("com.moheqionglin.javassist.Student", "execute", "System.out.println(\"this method is changed dynamically!\");");
        Student student = new Student();
        student.execute();
    }

    public static void replaceMethodBody(String clazzName, String methodName, String newMethodBody) {
        try {
            CtClass clazz = ClassPool.getDefault().get(clazzName);
            CtMethod method = clazz.getDeclaredMethod(methodName);
            method.setBody(newMethodBody);
            clazz.toClass();
        } catch (NotFoundException | CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }
}
