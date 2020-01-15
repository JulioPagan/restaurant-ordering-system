import java.util.Scanner;

public class Password {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userpw="", syspw="admin123";
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter your now, please!");
		userpw = s.nextLine();
		int tries = 1;
		while(tries<=3) {
		if (userpw.equals(syspw))
		{
			System.out.println("Password is correct");
			break;
		}
			else{
		
			System.out.println("Password incorrect" + 
		                " please try again- \nyou got " +
					    (3-tries) + " more time(s) to get it" +
					    " right!");
			userpw = s.nextLine();
			tries++; //increment count by 1 up to 3
		}
		
		}
	}

}
