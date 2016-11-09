import java.util.Scanner;

public class SHARunner
{
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
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

		System.out.println("SHA-256: " + hashDigest.toString());
		reader.close();
	}
}
