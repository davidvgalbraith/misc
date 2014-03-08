import java.util.*;
import java.io.*;

class StackMachine {

    Stack<Integer> theStack = new Stack<Integer>();
    public int evaluate(String input) {
	try {
	    for (int index = 0; index < input.length(); index++) {
		String nextchar = input.substring(index, index+1);
		if (nextchar.equals("+")) {
		    int first = theStack.pop();
		    int second = theStack.pop();
		    int sum = first + second;
		    if (sum > 4095 || sum < 0) { //12-bit precision
			return -1;
		    } else {
			theStack.push(sum);
			continue;
		    }
		}
		if (nextchar.equals("*")) {
		    int firstm = theStack.pop();
		    int secondm = theStack.pop();
		    int product = firstm * secondm;
		    if (product > 4095 || product < 0) { //12-bit precision
			return -1;
		    } else {
			theStack.push(product);
			continue;
		    }
		}
		int digit = Integer.parseInt(nextchar);
		theStack.push(digit);
	    }
	} catch (EmptyStackException e) {
	    return -1;
	} catch (NumberFormatException f) {
	    return -1;
	}
	if (theStack.isEmpty()) {
	    return -1;
	}
	int result = theStack.pop();
	if (!theStack.isEmpty()) {
	    return -1;
	}
	return result;
    }
    
    /**
     * Receives input on a single line from standard in. Computes
     * and prints the result on a single line to standard out. For
     * example, the input
     *
     * 11+2*
     * 
     * should yield the result:
     * 
     * 4
     */
    public static void main(String args[]) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	String input = reader.readLine();
	int result = new StackMachine().evaluate(input);
	System.out.println(result);
    }
	
}