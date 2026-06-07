package org.example;

public class Main {
    // تم تصحيح تعريف الدالة الرئيسية هنا بإضافة public و String[] args
    public static void main(String[] args) {

        // تم تغيير IO إلى System.out
        System.out.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
            // تم تغيير IO إلى System.out هنا أيضاً
            System.out.println("i = " + i);
        }
    }
}