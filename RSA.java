import java.math.BigInteger;
import java.util.Scanner;

class RSA {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the message to be encrypted:");
        int msg = scanner.nextInt();

        System.out.println("Enter the first prime number (p):");
        int p = scanner.nextInt();

        System.out.println("Enter the second prime number (q):");
        int q = scanner.nextInt();

        BigInteger n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
        BigInteger z = BigInteger.valueOf(p - 1).multiply(BigInteger.valueOf(q - 1));
        System.out.println("The value of z = " + z);

        BigInteger d = BigInteger.valueOf(2);
        while (d.compareTo(z) < 0) {
            if (gcd(d, z).equals(BigInteger.ONE)) {
                break;
            }
            d = d.add(BigInteger.ONE);
        }
        System.out.println("The value of d = " + d);

        BigInteger e = BigInteger.ZERO;
        for (int i = 0; i <= 9; i++) {
            BigInteger x = BigInteger.ONE.add(BigInteger.valueOf(i).multiply(z));
            if (x.mod(d).equals(BigInteger.ZERO)) {
                e = x.divide(d);
                break;
            }
        }
        System.out.println("The value of e = " + e);

        BigInteger message = BigInteger.valueOf(msg);
        BigInteger c = message.modPow(e, n);
        System.out.println("Encrypted message is : " + c);

        BigInteger msgback = c.modPow(d, n);
        System.out.println("Decrypted message is : " + msgback);

        scanner.close();
    }

    static BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO))
            return b;
        else
            return gcd(b.mod(a), a);
    }
}
