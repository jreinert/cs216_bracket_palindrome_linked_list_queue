/* Description: This program pushes a string of brackets to a stack and then checks to see if the sequence of brackets is balanced:
 * i.e. it contains no unmatched brackets and the subset of brackets enclosed within the confines of a matched pair of brackets is also a 
 * matched pair of brackets
 * Author: Jeremy Reinert
 * Date: 4/8/19
 * Version: 1.0
 */

// Import Stack Library
import java.util.Stack;

public class TestBrackets {

	public static void main(String[] args) {
		// Vars
		String s = "{[()]}";
		String s1 = "{[(])}";
		String s2 = "{{[[(())]]}}";
		
		System.out.println(isBalanced(s));
		System.out.println(isBalanced(s1));
		System.out.println(isBalanced(s2));
	}
	
	public static boolean isBalanced(String s) {
		
		Stack<Character> stack = new Stack<Character>();
		
		for(int i = 0; i < s.length(); i++) {
			// Check if charAt(i) is equal to {, [, or ( and if so, push to stack
			if(s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') { 
				stack.push(s.charAt(i));
			}
			
			// Check if stack is empty AND charAt(i) is equal to }, ], or ) and if so, return false
			else if(stack.isEmpty() && (s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')'))
				return false;
			
			// Check if charAt(i) is equal to }, ], ) and compare with most recent value pushed to the stack -- if the two are not equal, return false
			else if(s.charAt(i) == '}' && stack.pop() != '{' || s.charAt(i) == ']' && stack.pop() != '[' || s.charAt(i) == ')' && stack.pop() != '(') {
				return false;
			}
		}
		
		// Return boolean from isEmpty() method. If stack is empty, balanced brackets were found in the string
		return stack.isEmpty();
	}
}
