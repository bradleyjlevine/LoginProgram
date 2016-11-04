import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SecureSystem
{
  public static void main(String[] args)
  {
    Scanner reader = new Scanner(System.in);
    Random gen = new Random();
    boolean human = false;
    String[] captchas = { "9 + one = (answer in word-form): ", 
      "Which of the following is a plumber? Donkey Kong, Mario, Peach: ", 
      "What is the second number in this sequence? 19, three, 2, fifteen (answer in digits): ", 
      "What is the course code for this class? ", 
      "Two raised to the power of 3 is (in digits): ", 
      "Which of the following is a dorm on this campus? Schlosser, Hamburglarville, Gazorpazorpfield: ", 
      "Which of the following is more secure? AES or DES: ", 
      "Which of these is worn on your feet? Pancakes, Scarf, Socks: ", 
      "How many words are in this sentence (in word-form)? ", 
      "Pants, Wombat, Eggplant, Diarrhea: The word starting with 'E' is? " };
    String[] answers = { "ten", 
      "Mario", 
      "3", 
      "cs363", 
      "8", 
      "Schlosser", 
      "AES", 
      "socks", 
      "seven", 
      "eggplant" };
    String username = "";
    String password = "";
    SHA256 hash = new SHA256();
    AES cipherText = new AES();
    boolean legalUser = false;
    boolean legalPass = false;
    int index = -1;
    int choice = 0;
    String[] users = { "divinss", 
      "gallopd", 
      "guest", 
      "hartranfte", 
      "iacobaccia", 
      "kenneyw", 
      "lefeverb", 
      "levineb", 
      "manningd", 
      "mirandat", 
      "schubertr", 
      "schulteiss", 
      "weisk", 
      "wittmanb" };
    
    String[] passwords = { "f5cbafeaddd9d750dd05abdca2cd8418c084703d6cc4028b5a455a02ce99d8bb", 
      "0a9f19529003feb37b36ac42a747b17ac0fef053a23fafc15185ecf377febdbd", 
      "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", 
      "f3ec3a8732dc7a5c868b08bee7cb97845838862ab9dd1ae191184d4591619965", 
      "50ed69f699c2daf6d3815fa05ebad19ae67563ab172f1beac39caa0718e03e28", 
      "e85e8201f4f20240247e733e2e33b748b454973334e456227e6ef85b9366ac41", 
      "8adc002a42e15313d7e5ecee08288414e476bd56088d812ee2bd2269a1575b7c", 
      "9d067067a0691c4e547e0fa8fadcea8bb932d90724875bffeab2d16ac3be2374", 
      "5c5666b6c4481e22b2504011bd97a49490eb6a1b09e3e47f94fb60718f0fb868", 
      "5a66b11f69fa153adc01fde0dd68c0b008aa20c27811e7ce58e77aadb30ef9f0", 
      "830c4ebeb9330aabc45a482662361e43cc246444ca762c02ce18fc7c5acc8654", 
      "d8d69469ad1b3e4bfb68f2b8434177a8cd419491a619449f94f862720082e287", 
      "75837ed75e6b29da7bc904363c098218723eb1312d878c775432fafa585c81cc", 
      "153d0c71148d3efb6f2017ad19ccf3726bc4ed06c50f80913692c33807c76f39" };
    
    ArrayList<short[]> messages = new ArrayList();
    
    short[] temp = { 244, 94, 36, 40, 226, 132, 37, 127, 163, 92, 230, 67, 21, 243, 101, 216, 245, 4, 210, 210, 193, 88, 87, 38, 97, 85, 185, 67, 75, 62, 188, 86, 45, 206, 165, 183, 67, 65, 95, 133, 176, 121, 43, 58, 78, 236, 199, 132, 200, 106, 108, 15, 123, 8, 162, 182, 206, 144, 115, 133, 52, 41, 148, 195, 50, 251, 94, 189, 193, 4, 195, 155, 112, 79, 106, 247, 229, 93, 229, 211, 215, 191, 145, 12, 241, 217, 161, 248, 147, 104, 238, 67, 233, 169, 35, 117 };
    messages.add(temp);
    
    short[] temp2 = { 64, 222, 27, 171, 208, 97, 83, 175, 31, 214, 246, 53, 124, 105, 62, 65, 90, 219, 36, 6, 226, 193, 209, 130, 200, 228, 141, 86, 19, 83, 154, 6, 117, 107, 133, 51, 207, 119, 81, 73, 85, 22, 133, 150, 232, 39, 0, 199, 98, 93, 210, 164, 190, 107, 90, 30, 190, 204, 136, 143, 31, 192, 164, 38, 159, 206, 233, 209, 64, 202, 99, 221, 250, 2, 170, 35, 148, 89, 21, 22, 83, 233, 100, 233, 68, 144, 127, 222, 89, 71, 192, 24, 222, 182, 13, 103, 109, 248, 200, 233, 219, 153, 245, 10, 230, 38, 96, 210, 199, 143, 188, 83, 73, 203, 182, 184, 167, 78, 195, 163, 223, 9, 18, 26, 61, 52, 92, 33, 157, 148, 26, 35, 96, 157, 200, 165, 139, 235, 201, 84, 212, 101, 247, 222 };
    messages.add(temp2);
    
    short[] temp3 = { 70, 8, 68, 32, 80, 243, 246, 127, 173, 31, 248, 68, 199, 148, 60, 5, 65, 152, 151, 31, 232, 234, 252, 56, 144, 25, 104, 9, 189, 130, 46, 102, 96, 66, 64, 130, 27, 179, 214, 207, 132, 176, 128, 49, 25, 189, 217, 12 };
    messages.add(temp3);
    
    short[] temp4 = { 188, 53, 196, 224, 102, 69, 19, 157, 51, 183, 174, 235, 164, 196, 54, 248, 167, 140, 218, 212, 251, 81, 11, 45, 220, 33, 175, 72, 221, 25, 135, 170, 230, 121, 223, 124, 16, 92, 190, 24, 131, 57, 44, 34, 253, 30, 87, 191, 31, 48, 202, 184, 248, 193, 96, 107, 6, 206, 205, 106, 171, 72, 234, 177 };
    messages.add(temp4);
    
    short[] temp5 = { 195, 143, 5, 178, 211, 225, 111, 194, 118, 250, 50, 254, 236, 99, 1, 225, 135, 29, 7, 68, 176, 51, 71, 29, 196, 225, 37, 142, 83, 17, 170, 208, 56, 204, 56, 131, 188, 255, 235, 236, 252, 72, 63, 214, 170, 148, 207, 166 };
    messages.add(temp5);
    
    short[] temp6 = { 146, 131, 102, 199, 85, 255, 80, 223, 130, 192, 106, 246, 83, 19, 153, 57, 224, 134, 134, 242, 94, 32, 110, 162, 111, 124, 58, 252, 228, 175, 93, 174, 177, 21, 94, 197, 245, 77, 27, 49, 114, 227, 201, 74, 182, 100, 160, 222 };
    messages.add(temp6);
    
    short[] temp7 = { 235, 232, 223, 136, 162, 210, 85, 78, 250, 190, 233, 53, 129, 197, 255, 194, 44, 15, 232, 135, 112, 140, 66, 11, 237, 205, 174, 112, 250, 137, 223, 178, 227, 41, 103, 44, 201, 103, 150, 22, 221, 210, 235, 154, 100, 130, 23, 46 };
    messages.add(temp7);
    
    short[] temp8 = { 156, 223, 232, 48, 106, 222, 69, 92, 27, 85, 28, 230, 139, 27, 230, 120, 147, 251, 30, 48, 106, 12, 57, 95, 185, 218, 26, 225, 129, 250, 90, 198, 149, 110, 108, 39, 199, 112, 173, 106, 184, 112, 76, 3, 170, 249, 54, 98, 49, 189, 96, 137, 45, 23, 82, 234, 139, 43, 47, 73, 89, 59, 150, 237 };
    messages.add(temp8);
    
    short[] temp9 = { 42, 91, 17, 145, 54, 168, 185, 87, 163, 66, 172, 243, 187, 90, 162, 147, 236, 215, 26, 97, 148, 104, 129, 2, 200, 61, 206, 248, 43, 163, 68, 76, 183, 243, 106, 35, 157, 33, 30, 112, 144, 95, 150, 111, 4, 153, 16, 148, 120, 135, 246, 187, 60, 242, 126, 123, 35, 165, 27, 53, 174, 220, 111, 129 };
    
    messages.add(temp9);
    
    short[] temp10 = { 85, 73, 189, 253, 52, 6, 100, 62, 6, 139, 176, 112, 216, 32, 25, 185, 76, 104, 200, 227, 140, 234, 98, 112, 18, 218, 142, 148, 194, 183, 7, 73, 164, 191, 99, 148, 130, 100, 122, 129, 55, 218, 51, 242, 105, 33, 62, 102, 38, 105, 26, 151, 168, 17, 98, 196, 194, 15, 96, 83, 80, 188, 170, 8, 224, 44, 124, 193, 59, 125, 194, 106, 221, 56, 161, 61, 122, 225, 212, 188, 8, 163, 82, 246, 40, 126, 31, 156, 119, 114, 220, 12, 225, 141, 89, 114, 138, 65, 218, 245, 86, 154, 110, 115, 60, 239, 165, 20, 162, 173, 235, 160, 238, 148, 42, 3, 212, 179, 177, 119, 205, 13, 111, 163, 241, 146, 22, 190, 172, 100, 151, 29, 237, 193, 182, 180, 33, 67, 116, 161, 89, 201, 100, 83, 0, 170, 1, 240, 42, 112, 13, 221, 157, 137, 28, 220, 126, 14, 235, 238, 192, 111, 184, 142, 82, 189, 210, 204, 242, 65, 63, 145, 126, 124, 127, 211, 82, 176, 14, 15, 41, 71, 125, 197, 116, 146, 224, 216, 237, 210, 53, 36, 241, 222, 20, 77, 83, 96, 189, 167, 222, 11, 0, 180, 102, 190, 64, 37, 245, 220, 107, 34, 4, 15, 236, 8, 254, 129, 170, 105, 205, 97, 226, 89, 84, 99, 38, 16, 3, 149, 5, 157, 188, 59, 184, 185, 36, 28, 134, 231, 46, 240, 99, 3, 255, 232, 215, 43, 194, 180, 98, 7, 38, 177, 35, 167, 253, 184, 219, 29, 80, 32, 20, 33, 253, 238, 12, 183, 175, 46, 224, 220, 12, 158, 79, 245, 130, 147, 144, 61, 216, 135, 254, 24, 18, 232, 48, 92, 86, 173, 150, 34, 170, 116, 178, 180, 69, 5, 103, 146, 243, 112, 127, 145, 94, 126, 116, 87, 63, 70, 74, 225, 208, 2, 73, 150, 164, 148, 132, 161 };
    
    messages.add(temp10);
    
    short[] temp11 = { 0, 158, 185, 187, 76, 214, 9, 25, 238, 92, 50, 23, 61, 100, 47, 100, 212, 109, 21, 157, 196, 45, 145, 57, 197, 64, 134, 150, 18, 190, 251, 104, 147, 146, 205, 163, 35, 167, 110, 121, 1, 247, 120, 27, 66, 135, 139, 138, 84, 162, 36, 186, 101, 71, 240, 207, 184, 141, 207, 34, 159, 121, 168, 29, 110, 76, 62, 252, 175, 98, 179, 46, 32, 221, 98, 91, 195, 128, 217, 54 };
    
    messages.add(temp11);
    
    short[] temp12 = { 83, 132, 135, 237, 5, 85, 151, 136, 161, 49, 149, 48, 73, 72, 27, 128, 119, 16, 17, 69, 102, 164, 90, 181, 171, 143, 216, 43, 5, 245, 150, 75, 121, 199, 184, 188, 15, 49, 133, 7, 134, 224, 212, 236, 255, 213, 169, 21, 33, 209, 77, 45, 60, 58, 53, 62, 182, 56, 179, 132, 202, 237, 101, 76 };
    
    messages.add(temp12);
    
    short[] temp13 = { 9, 198, 192, 26, 130, 237, 210, 60, 137, 190, 175, 143, 186, 54, 6, 23, 49, 253, 100, 130, 137, 105, 78, 204, 52, 96, 141, 117, 135, 28, 203, 232, 134, 56, 148, 68, 159, 135, 248, 200, 39, 120, 228, 224, 84, 242, 207, 250, 9, 145, 20, 236, 96, 110, 23, 138, 180, 22, 248, 249, 148, 53, 155, 190 };
    
    messages.add(temp13);
    
    short[] temp14 = { 13, 250, 202, 175, 196, 224, 170, 245, 43, 154, 214, 75, 200, 189, 138, 145, 3, 166, 220, 76, 234, 169, 198, 231, 225, 3, 224, 210, 141, 116, 18, 176, 132, 115, 192, 68, 98, 135, 144, 139, 8, 105, 168, 65, 126, 223, 67, 64, 227, 113, 26, 37, 157, 15, 171, 70, 57, 65, 72, 5, 194, 41, 221, 161, 110, 47, 185, 54, 98, 11, 212, 99, 192, 1, 193, 70, 153, 173, 76, 201 };
    
    messages.add(temp14);
    int[] logins = new int[14];
    int[] failed = new int[14];
    while (choice != 3)
    {
      System.out.println("Welcome to Levine Systems Inc. Please select an option:");
      System.out.println("1) Login");
      System.out.println("2) List Users");
      System.out.println("3) Quit");
      choice = reader.nextInt();
      System.out.println();
      switch (choice)
      {
      case 1: 
        int num = gen.nextInt(10);
        String response = "";
        System.out.println("To verify that you are human, please answer the following captcha:");
        reader.nextLine();
        System.out.println(captchas[num]);
        response = reader.nextLine();
        if (answers[num].equalsIgnoreCase(response)) {
          human = true;
        } else {
          human = false;
        }
        if (human)
        {
          System.out.print("Enter username: ");
          username = reader.next();
          System.out.print("Enter password: ");
          password = reader.next();
          for (int i = 0; i < users.length; i++)
          {
            if (users[i].equals(username))
            {
              legalUser = true;
              index = i;
              break;
            }
            legalUser = false;
          }
          if ((legalUser) && (passwords[index].equals(hash.digest(password.getBytes())))) {
            legalPass = true;
          } else {
            legalPass = false;
          }
          if ((legalUser) && (legalPass))
          {
            StringBuilder padder = new StringBuilder();
            padder.append(password);
            int pad = 16 - padder.length();
            for (int k = 0; k < pad; k++) {
              padder.append("x");
            }
            while (padder.length() > 16) {
              padder.deleteCharAt(padder.length() - 1);
            }
            password = padder.toString();
            
            System.out.println("Secret message: " + cipherText.decrypt(password, (short[])messages.get(index)));
            logins[index] += 1;
          }
          if (!legalUser) {
            System.out.println("Incorrect username or password. Please try again.");
          }
          if ((legalUser) && (!legalPass))
          {
            System.out.println("Incorrect username or password. Please try again.");
            failed[index] += 1;
          }
        }
        else
        {
          System.out.println("Error: Captcha failed. Please try again.");
        }
        System.out.println();
        break;
      case 2: 
        System.out.printf("User                    Successful Logins       Unsuccessful Logins\n", new Object[0]);
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < users.length; i++) {
          System.out.printf("%-15s   %15d          %15d\n", new Object[] { users[i], Integer.valueOf(logins[i]), Integer.valueOf(failed[i]) });
        }
        System.out.println();
        break;
      case 3: 
        break;
      default: 
        System.out.println("Invalid choice. Please try again.");
        System.out.println();
      }
    }
    reader.close();
  }
}
