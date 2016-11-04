import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;

public class SHARunner
{
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		StringBuilder binary = new StringBuilder();
		String message = null;
		byte[] orginal;

		System.out.println("Enter the message you would like to hash: ");
		message = reader.nextLine();
		
		//gets the bytes[] of the string enters
		orginal = message.getBytes();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		SHA256 sha = new SHA256();
		
		String hashDigest = sha.digest(orginal);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\nSHA-256: " + hashDigest.toString());
	}
}
