// Да се напише функција која за две текстуални низи (внесени од тастатура и дадени како аргументи на функцијата) ќе провери дали се анаграми. 
// Анаграм е збор или фраза кој може да се добие од буквите на друг збор или фраза (во случај на фраза бројот на празни места не мора да е ист).

package labVezbi;

import java.util.Arrays;

public class zad2 {

    public static void main(String[] args) {
        String text1 = "listen";
        String text2 = "silent";
        
        String text3 = "listen";
        String text4 = "splent";

        String text5 = "listen Zdravo";
        String text6 = "silent ozdrav";

        if (func(text1, text2)) {
            System.out.println("Tekstovite se anagrami.");
        } else {
            System.out.println("Tekstovite ne se anagrami.");
        }

        System.out.println();

        if (func(text3, text4)) {
            System.out.println("Tekstovite se anagrami.");
        } else {
            System.out.println("Tekstovite ne se anagrami.");
        }

        System.out.println();

        if (func(text5, text6)) {
            System.out.println("Tekstovite se anagrami.");
        } else {
            System.out.println("Tekstovite ne se anagrami.");
        }
    }
    
    private static boolean func(String str1, String str2) {
        
        str1 = str1.replaceAll(" ", "").toLowerCase();
        str2 = str2.replaceAll(" ", "").toLowerCase();

        
        if (str1.length() != str2.length()) {
            return false;
        }

        
        char[] niza1 = str1.toCharArray();
        char[] niza2 = str2.toCharArray();

        
        Arrays.sort(niza1);
        Arrays.sort(niza2);

        
        return Arrays.equals(niza1, niza2);
    }
}
