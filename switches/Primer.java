import java.util.HashSet;

public class Primer {
    static HashSet<Integer> primes = new HashSet<Integer>();
    public static void main(String[] args) {
	primes.add(2);
	for (int k = 3; k < 10000000; k++) {
	    if (isPrime(k)) {
		primes.add(k);
		System.out.println(k);
	    }
	}
    }

    static boolean isPrime(int n) {
	if (n < 2) {
	    return false;
	}
	for (Integer k : primes) {
	    if (n % k == 0) {
		return false;
	    }
	}
	return true;
    }
}