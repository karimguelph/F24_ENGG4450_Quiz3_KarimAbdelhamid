import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest {

    @Test
    void multiplication() {
        assertEquals(0, Multiplication.multiplication(0, 10));
        assertEquals(0, Multiplication.multiplication(0, 0)); // Correct result for 0 * 0
        assertEquals(50, Multiplication.multiplication(5, 10));
        assertEquals(-50, Multiplication.multiplication(5, -10));
        assertEquals(100, Multiplication.multiplication(10, 10));
    }

    @Test
    void multiplyUsingLoop() {
        assertEquals(0, Multiplication.multiplyUsingLoop(0, 10));
        assertEquals(50, Multiplication.multiplyUsingLoop(5, 10));
        assertEquals(-50, Multiplication.multiplyUsingLoop(5, -10));
        assertEquals(100, Multiplication.multiplyUsingLoop(10, 10));
        assertEquals(-100, Multiplication.multiplyUsingLoop(-10, 10));
    }

    @Test
    void multiplyUsingRecursion() {
        assertEquals(0, Multiplication.multiplyUsingRecursion(0, 10));
        assertEquals(50, Multiplication.multiplyUsingRecursion(5, 10));
        assertEquals(-50, Multiplication.multiplyUsingRecursion(5, -10));
        assertEquals(100, Multiplication.multiplyUsingRecursion(10, 10));
        assertEquals(-100, Multiplication.multiplyUsingRecursion(-10, 10));
    }

    @Test
    void multiplyUsingShift() {
        assertEquals(0, Multiplication.multiplyUsingShift(0, 10));
        assertEquals(50, Multiplication.multiplyUsingShift(5, 10));
        assertEquals(-50, Multiplication.multiplyUsingShift(5, -10));
        assertEquals(100, Multiplication.multiplyUsingShift(10, 10));
        assertEquals(-100, Multiplication.multiplyUsingShift(-10, 10));
    }

    @Test
    void multiplyUsingLogs() {
        assertEquals(0, Multiplication.multiplyUsingLogs(0, 10));
        assertEquals(50, Multiplication.multiplyUsingLogs(5, 10));
        assertEquals(-50, Multiplication.multiplyUsingLogs(5, -10));
        assertEquals(100, Multiplication.multiplyUsingLogs(10, 10));
        assertEquals(-100, Multiplication.multiplyUsingLogs(-10, 10));
    }

    @Test
    void multiplyUsingFFT() {
        assertEquals("0", Multiplication.multiplyUsingFFT("0", "10"));
        assertEquals("50", Multiplication.multiplyUsingFFT("5", "10"));
        assertEquals("-50", Multiplication.multiplyUsingFFT("5", "-10"));
        assertEquals("100", Multiplication.multiplyUsingFFT("10", "10"));
        assertEquals("-100", Multiplication.multiplyUsingFFT("-10", "10"));
        assertEquals("56088", Multiplication.multiplyUsingFFT("123", "456"));
        assertEquals("-56088", Multiplication.multiplyUsingFFT("-123", "456"));
    }
}
