import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**brute force solution to the switches problem**/
public class Switchinator {
    public static void main(String[] args) {
	int max = Integer.parseInt(args[0]);
	File f = new File("primes.txt");
	Scanner s = null;
	try {
	    s = new Scanner(f);
	} catch (FileNotFoundException e) {
	    System.err.println("Feed me primes!");
	}
	HashSet<Integer> primes = new HashSet<Integer>();
	while (s.hasNext()) {
	    primes.add(Integer.parseInt(s.nextLine()));
	}
	int on = 0;
	HashSet<Integer> flipped = new HashSet<Integer>();
	for (int swich = 1; swich <= max; swich++) {
	    System.out.println(swich);
	    if (primes.contains(swich)) {
		for (int flip = swich; flip <= max; flip+=swich) {
		    if (flipped.contains(flip)) {
			flipped.remove(flip);
		    } else {
			flipped.add(flip);
		    }
		}
	    }
	}
	System.out.println(max - flipped.size());
    }
}