/*
За внесен стринг да се одреди минималниот број на поделби потребни да се 
направат за секој добиен потстринг да претставува палиндром (притоа, стринг со
должина 1 е палиндром).
*/

public class zad11 {
    public static void main(String[] args) {
        String str = "xabaay";
        System.out.println(minPartitions(str, 0, str.length() - 1));
    }

    private static int minPartitions(String str, int start, int end) {
        if (start >= end || isPalindrom(str, start, end)) {
            return 0;
        }
        
        int result = Integer.MAX_VALUE, count;
        
        for (int i = start; i < end; i++) {
            count = minPartitions(str, start, i) + minPartitions(str, i + 1, end) + 1;
            result = Math.min(result, count);
        }
        
        return result;
    }
    
    private static boolean isPalindrom(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}