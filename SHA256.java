//Authors: Bradley Levine, Julian Zebrowski
//Project 3: Feeling Insecure
//Files: AES.java, SecureSystem.java, SHA256.java
//Description: A secure system, nuff said
//Publisher: Levine Systems Inc.© 

import java.util.Arrays;
import java.math.BigInteger;


@SuppressWarnings("unused")
public class SHA256
{
	private byte[] message;
	private int NUM_BLOCKS;
	
	private int[] h = {0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19};
	
	private int[] k = {(int)0x428a2f98, (int)0x71374491, (int)0xb5c0fbcf, (int)0xe9b5dba5, (int)0x3956c25b, (int)0x59f111f1, (int)0x923f82a4, (int)0xab1c5ed5,
					   (int)0xd807aa98, (int)0x12835b01, (int)0x243185be, (int)0x550c7dc3, (int)0x72be5d74, (int)0x80deb1fe, (int)0x9bdc06a7, (int)0xc19bf174,
					   (int)0xe49b69c1, (int)0xefbe4786, (int)0x0fc19dc6, (int)0x240ca1cc, (int)0x2de92c6f, (int)0x4a7484aa, (int)0x5cb0a9dc, (int)0x76f988da,
					   (int)0x983e5152, (int)0xa831c66d, (int)0xb00327c8, (int)0xbf597fc7, (int)0xc6e00bf3, (int)0xd5a79147, (int)0x06ca6351, (int)0x14292967,
					   (int)0x27b70a85, (int)0x2e1b2138, (int)0x4d2c6dfc, (int)0x53380d13, (int)0x650a7354, (int)0x766a0abb, (int)0x81c2c92e, (int)0x92722c85,
					   (int)0xa2bfe8a1, (int)0xa81a664b, (int)0xc24b8b70, (int)0xc76c51a3, (int)0xd192e819, (int)0xd6990624, (int)0xf40e3585, (int)0x106aa070,
					   (int)0x19a4c116, (int)0x1e376c08, (int)0x2748774c, (int)0x34b0bcb5, (int)0x391c0cb3, (int)0x4ed8aa4a, (int)0x5b9cca4f, (int)0x682e6ff3,
					   (int)0x748f82ee, (int)0x78a5636f, (int)0x84c87814, (int)0x8cc70208, (int)0x90befffa, (int)0xa4506ceb, (int)0xbef9a3f7, (int)0xc67178f2};
	
	private final long MOD = 1L << 32;
	
	public SHA256()
	{
		super();
	}

	public String digest(byte[] a)
	{
		//initalization
		/////////////////////////////////////////////////////////////
		message = padding(a);
		int[] m = toIntegerArray(message);
		NUM_BLOCKS = m.length / 16;
		/////////////////////////////////////////////////////////////
		int[] registers = new int[8];
		int[] w = new int[64];
		int[][] H = new int[NUM_BLOCKS + 1][8];
		
		for(int i = 0; i < 8; i++)
			H[0][i] = h[i];
		
		for(int i = 1, pos = 0; i < NUM_BLOCKS + 1; i++)
		{

			//intializing registers
			for(int j = 0; j < 8; j++)
				registers[j] = H[i - 1][j];
			
			
			for(int j = 0; j < 64; j++)
			{
				//message schedule
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(j >= 0 && j < 16)
				{
					w[j] = m[pos];
					pos++;
				}
				else
				{
					int w2 = (Integer.rotateRight(w[j-2], 17) ^ Integer.rotateRight(w[j-2], 19) ^ (w[j-2] >>> 10));
					int w15 = (Integer.rotateRight(w[j-15], 7) ^ Integer.rotateRight(w[j-15], 18) ^ (w[j-15] >>> 3));
					w[j] =  (int)(((((((long)w2 + (long)w[j - 7]) % MOD) + (long)w15) % MOD) + (long)w[j - 16]) % MOD);
				}

				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				//compression function

				//compute variables
			    int ch = (registers[4] & registers[5]) ^ (~registers[4] & registers[6]);
				int maj = (registers[0] & registers[1]) ^ (registers[0] & registers[2]) ^ (registers[1] & registers[2]);
				int sigma0 = Integer.rotateRight(registers[0], 2) ^ Integer.rotateRight(registers[0], 13) ^ Integer.rotateRight(registers[0], 22);
				int sigma1 = Integer.rotateRight(registers[4], 6) ^ Integer.rotateRight(registers[4], 11) ^ Integer.rotateRight(registers[4], 25);
				
				int t1 = (int)(((((((((long)registers[7] + (long)sigma1) % MOD) + (long)ch) % MOD) + (long)k[j]) % MOD) + (long)w[j]) % MOD);
				int t2 = (int)((((long)sigma0 + (long)maj)) % MOD);
				
				registers[7] = registers[6];
				registers[6] = registers[5];
				registers[5] = registers[4];
				registers[4] = (int)(((long)registers[3] + t1) % MOD);
				registers[3] = registers[2];
				registers[2] = registers[1];
				registers[1] = registers[0];
				registers[0] = (int)((t1 + t2) % MOD);
					
			}

			//intermediate hash vlaue
			for(int j = 0; j < 8; j++)
			{
				H[i][j] = (int)(((long)registers[j] + (long)H[i - 1][j]) % MOD);				
			}			
		}
		
		
		//concatenates the hashes together
		StringBuilder hashDigest = new StringBuilder();
		
		for(int i = 0; i < 8; i++)
		{
			StringBuilder temp = new StringBuilder();
			temp.append(String.format("%x", (H[NUM_BLOCKS][i])));
			int pad = 8 - temp.length();
			
			temp.reverse();
			for(int j = 0; j < pad; j++)
				temp.append("0");
			temp.reverse();
			
			hashDigest.append(temp.toString());
		}

		return hashDigest.toString();
	}
	
	private byte[] padding(byte[] a)
	{
		int pad;
        if(((a.length % 64) >= 56 &&  (a.length % 64) < 64))
		{
			pad = (64 - ((a.length) % 64)) + 64;
			System.out.println("The padding size is: " + pad);
		}
        else if(((a.length % 64) == 0 && a.length == 64))
        {
        	pad = 64;
        }
		else
		{
			pad = (64 - ((a.length) % 64));
		}

		byte[] paddedMessage = new  byte[a.length + pad]; 
 		byte[] temp2 = new byte[8];
 		
		//copies the contents of the message into the new array
		int i;
		for(i = 0; i < a.length; i++)
		{	
			paddedMessage[i] = a[i];
		}
		
		
		//this turns on the bit after the message ends 
		paddedMessage[i] = (byte)0x80;
		
		//this addes the 0 padding to the array
		int n;
		for(i = i + 1, n = 0; n < pad - 1; i++, n++)
		{		 
				paddedMessage[i] = 0;
		}
		
		//gets the last 64 bits from a bigineger byte array
		BigInteger middleMan = BigInteger.valueOf(a.length * 8);
		byte[] temp = middleMan.toByteArray();
				
		int pad2 = 8 - temp.length;
		
		int j;
		for(j = 0; j < pad2; j++)
			temp2[j] = 0;
		
		for(int k = 0; k < temp.length; k++, j++)
			temp2[j] = temp[k];
		
		for(int k = paddedMessage.length - 8, m = 0; k < paddedMessage.length; k++, m++)
			paddedMessage[k] = temp2[m];
		
		return paddedMessage;		
	}
	
	private int[] toIntegerArray(byte[] a)
	{
		int[] b = new int[a.length/4];

		for(int j = 0, i = 0; j < b.length; j++)
		{
			for(int k = 0; k < 4; k++, i++)
			{
				b[j] <<= 8;
				b[j] |= (int)a[i] & 0xFF;
			}
		}
		
		return b;
	}	
}
