import java.util.HashMap;
import java.util.Map;

public class Primes {

    /**
     * In number theory, the prime factors of a positive integer are the prime numbers that divide that integer exactly. The prime
     * factorization of a positive integer is a list of the integer's prime factors, together with their multiplicities; the process
     * of determining these factors is called integer factorization. The fundamental theorem of arithmetic says that every positive
     * integer has a single unique prime factorization.
     */
    public static final Map<Long, Long> getPrimeFactorization(long number) {
        Map<Long, Long> map = new HashMap<Long, Long>();
        long n = number;
        int c = 0;
        // for each potential factor i
        for (long i = 2; i * i <= number; i++) {
            c = 0;
            // if i is a factor of N, repeatedly divide it out
            while (n % i == 0) {
                n = n / i;
                c++;
            }
            if (c > 0) { // Only add to the map if the factor count is greater than 0
                map.put(i, (long) c);
            }
        }
        if (n > 1) {
            map.put(n, 1L);
        }
        return map;
    }

    /**
     * isPrime() using the square root properties
     * <p>
     * 1 is not a prime. All primes except 2 are odd. All primes greater than 3
     * can be written in the form 6k+/-1. Any number n can have only one
     * prime factor greater than n. The consequence for primality testing of a
     * number n is: if we cannot find a number f less than or equal n that
     * divides n then n is prime: the only prime factor of n is n itself
     */
    public static final boolean isPrime(long number) {
        if (number == 1)
            return true;
        if (number < 4)
            return true; // 2 and 3 are prime
        if (number % 2 == 0)
            return false; // short circuit
        if (number < 9)
            return true; // we have already excluded 4, 6 and 8.
        if (number % 3 == 0)
            return false; // short circuit
        long r = (long) (Math.sqrt(number)); // n rounded to the greatest integer
        int f = 5;
        while (f <= r) {
            if (number % f == 0)
                return false;
            if (number % (f + 2) == 0)
                return false;
            f += 6;
        }
        return true;
    }

    /*
     * Sieve of Eratosthenes, only has to be set once.
     */
    private static boolean[] sieve = null;

    /**
     * In mathematics, the sieve of Eratosthenes is a simple, ancient algorithm for finding all prime numbers up to any given limit.
     */
    public static final boolean sieveOfEratosthenes(int number) {
        if (number == 1) {
            return false;
        }
        if (sieve == null || number >= sieve.length) {
            int start = 2;

            if (sieve == null) {
                sieve = new boolean[number + 1];
            } else if (number >= sieve.length) {
                boolean[] temp = new boolean[number + 1];
                System.arraycopy(sieve, 0, temp, 0, sieve.length);
                sieve = temp;
            }

            for (int i = start; i <= Math.sqrt(number); i++) {
                if (!sieve[i]) {
                    for (int j = i * 2; j <= number; j += i) {
                        sieve[j] = true;
                    }
                }
            }
        }
        return !sieve[number];
    }

    /**
     * Miller-Rabin primality test is the fastest way to check if number is prime.
     * Regular version of this algorithm returns false when number is composite and true
     * when number is probably prime. Here is implemented a deterministic version of this
     * algorithm, witnesses are not randomized. Used set of witnesses guarantees that result
     * will be correct for sure (not probably) for any number lower than 10^18.
     */
    public static final boolean millerRabinTest(int number) {
        final int[] witnesses = {2, 325, 9375, 28178, 450775, 9780504, 1795265022};

        if (number == 0 || number == 1)
            return false;
        if (number == 2 || number == 3)
            return true;

        int maximumPowerOf2 = 0;
        int d = number - 1;
        while (d % 2 == 0) {
            d /= 2;
            maximumPowerOf2++;
        }

        for (int a : witnesses) {
            if (a > number)
                break;

            int x = fastRecursiveExponentiationModulo(a, d, number);
            if (x == 1 || x == number - 1)
                continue;

            boolean isComposite = true;
            for (int r = 1; r < maximumPowerOf2; r++) {
                x = fastRecursiveExponentiationModulo(x, 2, number);
                if (x == number - 1) {
                    isComposite = false;
                    break;
                }
            }

            if (isComposite)
                return false;
        }

        return true;
    }

    private static int fastRecursiveExponentiation(int base, int exponent) {
        if (exponent == 0) return 1;
        int half = fastRecursiveExponentiation(base, exponent / 2);
        int result = half * half;
        if (exponent % 2 != 0) result *= base;
        return result;
    }

    private static int fastRecursiveExponentiationModulo(int base, int exponent, int modulus) {
        int result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            exponent >>= 1;
            base = (base * base) % modulus;
        }
        return result;
    }
}
