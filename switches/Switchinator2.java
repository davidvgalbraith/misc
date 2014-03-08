import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**Alternative solution to the switches problem**/
public class Switchinator2 {

    static HashSet<Integer> primes = new HashSet<Integer>();

    public static void main(String[] args) {

	int max = Integer.parseInt(args[0]);

	//get the primes
	File f = new File("primes.txt");
	Scanner s = null;
	try {
	    s = new Scanner(f);
	} catch (FileNotFoundException e) {
	    System.err.println("Feed me primes!");
	}
	while (s.hasNext()) {
	    primes.add(Integer.parseInt(s.nextLine()));
	}	

	int on = 0;
	for (int swich = 1; swich <= max; swich++) {
	    if (factcount(swich) % 2 == 0) { //swich will be flipped
		on++;                        //an even # of times
	    }
	}
	System.out.println(on);
    }
    //returns the number of prime factors of n
    static int factcount(int n) {
	HashSet<Integer> facts = new HashSet<Integer>();
	for (int a = 1; a <= Math.sqrt(n); a++) {
	    if (n % a == 0) {
		facts.add(a);
		facts.add(n /a);
	    }
	}
	int count = 0;
	for (Integer i : facts) {
	    if (primes.contains(i)) {
		count++;
	    }
	}
	return count;
    }
}