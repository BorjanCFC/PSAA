// Given a string str consisting of opening and closing parenthesis ‘(‘ and ‘)‘. 
// Find the length of the longest valid parenthesis substring.

// Examples: 

// Input: ((()
// Output : 2
// Explanation : Longest Valid Parentheses Substring is ().


// Input: )()())
// Output : 4
// Explanation: Longest Valid Parentheses Substring is ()()

import java.util.Stack;

public class zad43 {
    public static void main(String[] args) {
        String s = ")()())";
        System.out.println(maxLength(s));
    }
        
    private static int maxLength(String s) {
        Stack<Character> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c); 
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
                count += 2; 
                
            }
        }

        return count;
    }
}
