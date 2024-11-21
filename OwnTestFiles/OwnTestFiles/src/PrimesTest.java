import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

    @Test
    void getPrimeFactorization() {
        // Test case 1: Prime number
        assertEquals(Map.of(7L, 1L), Primes.getPrimeFactorization(7L));

        // Test case 2: Composite number
        assertEquals(Map.of(2L, 3L, 5L, 1L), Primes.getPrimeFactorization(40L));

        // Test case 3: Large composite number
        assertEquals(Map.of(2L, 2L, 3L, 2L, 5L, 1L), Primes.getPrimeFactorization(180L));

        // Test case 4: Edge case (1)
        assertEquals(Map.of(), Primes.getPrimeFactorization(1L));
    }

    @Test
    void isPrime() {
        // Test case 1: Small prime numbers
        assertTrue(Primes.isPrime(2));
        assertTrue(Primes.isPrime(3));

        // Test case 2: Non-prime numbers
        assertFalse(Primes.isPrime(4));
        assertFalse(Primes.isPrime(9));

        // Test case 3: Larger prime numbers
        assertTrue(Primes.isPrime(29));
        assertTrue(Primes.isPrime(31));

        // Test case 4: Very large non-prime
        assertFalse(Primes.isPrime(1000000000L));
    }

    @Test
    void sieveOfEratosthenes() {
        // Test case 1: Single small number
        assertFalse(Primes.sieveOfEratosthenes(1)); // 1 is not prime
        assertTrue(Primes.sieveOfEratosthenes(2)); // 2 is prime

        // Test case 2: Small range
        assertTrue(Primes.sieveOfEratosthenes(3));
        assertFalse(Primes.sieveOfEratosthenes(4));
        assertTrue(Primes.sieveOfEratosthenes(5));

        // Test case 3: Larger range
        assertFalse(Primes.sieveOfEratosthenes(20)); // Not prime
        assertTrue(Primes.sieveOfEratosthenes(19)); // Prime
    }

    @Test
    void millerRabinTest() {
        // Test case 1: Small prime numbers
        assertTrue(Primes.millerRabinTest(2));
        assertTrue(Primes.millerRabinTest(3));

        // Test case 2: Non-prime numbers
        assertFalse(Primes.millerRabinTest(4));
        assertFalse(Primes.millerRabinTest(6));

        // Test case 3: Larger prime numbers
        assertTrue(Primes.millerRabinTest(89));
        assertTrue(Primes.millerRabinTest(97));

        // Test case 5: Very large composite number
        assertFalse(Primes.millerRabinTest(104730)); // 104730 is not prime
    }
}
