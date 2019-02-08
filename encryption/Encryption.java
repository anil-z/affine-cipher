import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Random;

public class Encryption {

	static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) throws IOException {
		File file = new File("/home/anil/Desktop/HW4_history of istanbul.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		String paragraf = "";
		while ((st = br.readLine()) != null)
			paragraf = paragraf.concat(st);
		br.close();
		paragraf = paragraf.toLowerCase();
		System.out.println(paragraf);

		//Random rand = new Random();
		int a = 1;
		while (gcd(a, 26) != 1)
			a = 1;
		System.out.println("a is: " + a);

		int b = 1;
		System.out.println("b is: " + b);

		String cryptedString = "";

		for (int i = 0; i < paragraf.length(); i++)
			cryptedString = cryptedString.concat(String.valueOf(charChanger(paragraf.charAt(i), a, b)));

		System.out.println("---------------------------");
		System.out.println(cryptedString);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		File newFile = new File("/home/anil/Desktop/crypted.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
		
		bw.write(cryptedString);
		bw.close();

	}

	static int gcd(int x, int y) {
		if (y == 0)
			return x;
		else
			return gcd(y, x % y);
	}

	static char charChanger(char x, int a, int b) {
		int number = 0;
		boolean isLetter = false;

		for (int i = 0; i < alphabet.length; i++) {
			if (x == alphabet[i]) {
				number = i;
				isLetter = true;
			}
		}

		if (!isLetter)
			return x;

		int cryptedInt = (a * number + b) % 26;
		char cryptedChar = alphabet[cryptedInt];

		return cryptedChar;
	}

}
