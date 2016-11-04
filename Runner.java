import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.math.BigInteger;

public class Runner
{
	public static final int LENGTH = 1023;

	public static void main(String[] args)
	{
		AES a = new AES();

		short[] cipherbytes =  a.encrypt("Thats my Kung Fu", "Pancakes are the best in the world!!");
		
		System.out.println(Arrays.toString(cipherbytes));
		
		String plaintext = a.decrypt("Thats my Kung Fu", cipherbytes);
		
		System.out.println(plaintext);
	}
}
