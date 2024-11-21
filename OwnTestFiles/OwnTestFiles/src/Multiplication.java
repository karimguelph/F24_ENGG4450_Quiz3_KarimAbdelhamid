public class Multiplication {

    public static class Complex {
        private double real;
        private double imaginary;

        public Complex() {
            this.real = 0.0;
            this.imaginary = 0.0;
        }

        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imaginary + other.imaginary);
        }

        public Complex multiply(Complex other) {
            double realPart = this.real * other.real - this.imaginary * other.imaginary;
            double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
            return new Complex(realPart, imaginaryPart);
        }

        public double abs() {
            return Math.sqrt(real * real + imaginary * imaginary);
        }
    }

    public static class FastFourierTransform {
        public static void cooleyTukeyFFT(Complex[] data) {
            int n = data.length;
            if (n <= 1) return;

            Complex[] even = new Complex[n / 2];
            Complex[] odd = new Complex[n / 2];
            for (int i = 0; i < n / 2; i++) {
                even[i] = data[i * 2];
                odd[i] = data[i * 2 + 1];
            }

            cooleyTukeyFFT(even);
            cooleyTukeyFFT(odd);

            for (int k = 0; k < n / 2; k++) {
                double t = -2 * Math.PI * k / n;
                Complex exp = new Complex(Math.cos(t), Math.sin(t));
                Complex temp = exp.multiply(odd[k]);
                data[k] = even[k].add(temp);
                data[k + n / 2] = even[k].add(new Complex(-temp.real, -temp.imaginary));
            }
        }
    }

    public static final long multiplication(int a, int b) {
        long result = ((long) a) * ((long) b);
        return result;
    }



    public static final long multiplyUsingLoop(int a, int b) {
        int absB = b < 0 ? -b : b; // Avoiding Math.abs
        long result = 0;
        for (int i = 0; i < absB; i++) {
            result += a;
        }
        return (b < 0) ? -result : result;
    }

    public static final long multiplyUsingRecursion(int a, int b) {
        if (a == 0 || b == 0) {
            return 0; // Base case to handle multiplication by zero
        }
        int absB = b < 0 ? -b : b; // Avoiding Math.abs
        if (absB == 1) {
            return b < 0 ? -a : a; // Return `a` or `-a` depending on the sign of `b`
        }
        long result = a + multiplyUsingRecursion(a, absB - 1);
        return (b < 0) ? -result : result; // Adjust the sign based on `b`
    }

    public static final long multiplyUsingShift(int a, int b) {
        int absA = a < 0 ? -a : a; // Avoiding Math.abs
        int absB = b < 0 ? -b : b;

        long result = 0L;
        while (absA > 0) {
            if ((absA & 1) > 0)
                result += absB; // Is odd
            absA >>= 1;
            absB <<= 1;
        }

        return (a > 0 && b > 0 || a < 0 && b < 0) ? result : -result;
    }

    public static final long multiplyUsingLogs(int a, int b) {
        long absA = a < 0 ? -a : a; // Avoiding Math.abs
        long absB = b < 0 ? -b : b;
        long result = (long) Math.round(Math.pow(10, (Math.log10(absA) + Math.log10(absB))));
        return (a > 0 && b > 0 || a < 0 && b < 0) ? result : -result;
    }

    public static String multiplyUsingFFT(String a, String b) {
        if (a.equals("0") || b.equals("0")) {
            return "0";
        }
        boolean negative = false;
        if ((a.charAt(0) == '-' && b.charAt(0) != '-') || (a.charAt(0) != '-' && b.charAt(0) == '-')) {
            negative = true;
        }
        if (a.charAt(0) == '-') {
            a = a.substring(1);
        }
        if (b.charAt(0) == '-') {
            b = b.substring(1);
        }
        int size = 1;
        while (size < (a.length() + b.length())) {
            size *= 2;
        }
        Complex[] aCoefficients = new Complex[size];
        Complex[] bCoefficients = new Complex[size];
        for (int i = 0; i < size; i++) {
            aCoefficients[i] = new Complex();
            bCoefficients[i] = new Complex();
        }
        for (int i = 0; i < a.length(); i++) {
            aCoefficients[i] = new Complex((double) (a.charAt(a.length() - i - 1) - '0'), 0.0);
        }
        for (int i = 0; i < b.length(); i++) {
            bCoefficients[i] = new Complex((double) (b.charAt(b.length() - i - 1) - '0'), 0.0);
        }

        FastFourierTransform.cooleyTukeyFFT(aCoefficients);
        FastFourierTransform.cooleyTukeyFFT(bCoefficients);

        for (int i = 0; i < size; i++) {
            aCoefficients[i] = aCoefficients[i].multiply(bCoefficients[i]);
        }
        for (int i = 0; i < size / 2; i++) {
            Complex temp = aCoefficients[i];
            aCoefficients[i] = aCoefficients[size - i - 1];
            aCoefficients[size - i - 1] = temp;
        }
        FastFourierTransform.cooleyTukeyFFT(aCoefficients);

        int[] res = new int[size];
        int pass = 0;
        for (int i = 0; i < size; i++) {
            res[i] = pass + (int) Math.floor((aCoefficients[i].abs() + 1) / size);
            if (res[i] >= 10) {
                pass = res[i] / 10;
                res[i] %= 10;
            } else {
                pass = 0;
            }
        }

        StringBuilder result = new StringBuilder();
        if (negative) {
            result.append('-');
        }
        boolean startPrinting = false;
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] != 0) {
                startPrinting = true;
            }
            if (startPrinting) {
                result.append(res[i]);
            }
        }
        return result.toString();
    }
}
