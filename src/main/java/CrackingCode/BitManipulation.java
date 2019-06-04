package CrackingCode;

public class BitManipulation {

    public static void main(String[] args) {
        System.out.println(printBinary(0.125));
        int n = updateBits(1024, 19, 2, 6);
        toBase(1024, 2);
        toBase(19, 2);
        toBase(n, 2);
    }

    public static int updateBits(int n, int m, int i, int j) {
        /* Create a mask to clear bits i through j in n. EXAMPLE: i = 2, j = 4. Result
         * should be 11100011. For simplicity, we'll use just 8 bits for the example. */

        int allOnes = ~0; // will equal sequence of all ls
        //toBase(allOnes, 2);
        // 1s before position j, then 0s. left = 11100000
        int left = allOnes << (j + 1);
        //toBase(left, 2);
        //1's after position i. right = 00000011
        int right = ((1 << i) - 1);
        //toBase(right, 2);
        // All 1s, except for 0s between i and j. mask 11100011
        int mask = left | right;
        //toBase(mask, 2);

        int n_cleared = n & mask; // Clear bits j through i.
        //toBase(n_cleared, 2);
        int m_shifted = m << i; // Move m into correct position.
        //toBase(m_shifted, 2);

        //toBase(n_cleared | m_shifted, 2);
        return (n_cleared | m_shifted); // OR them, and we're done!

    }

    public static void toBase(int number, int base) {
        String binary = "";
        //int temp = (number/2)+1;
        for (int j = 0; j < 16; j++) {
            try {
                binary += "" + number % base;
                number /= base;
            } catch (Exception e) {
            }
        }
        for (int j = binary.length() - 1; j >= 0; j--) {
            if ((j + 1) % 4 == 0) System.out.print(" ");
            System.out.print(binary.charAt(j));
        }
        System.out.println();
    }

    public static String printBinary(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }
        StringBuilder binary = new StringBuilder();
        binary.append(".");
        while (num > 0) {
            /* Setting a limit on length: 32 characters */
            if (binary.length() >= 32) {
                return "ERROR";
            }
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }

}
