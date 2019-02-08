import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Decryption {

	static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) throws IOException {
		
		/*
		 * IN THAT PART, YOU MAY GET WRONG A AND B. BUT IT TRANSFORM'S THE STRING FINE.
		 */

		String paragraf = getCrypted();

		System.out.println(paragraf);

		List<String> words = new ArrayList<String>();
		words = getDictionary();

		int valueA = 1;
		int valueB = 0;

		boolean bir = false, iki = false, uc = false, dort = false;

		String[] splited = paragraf.split(" ");

		outerloop: for (int a = 1; a < 26; a++) {
			for (int b = 0; b < 26; b++) {
				for (int i = 0; i < words.size(); i++) {
					if (words.get(i).equals(wordDecrypt(a, b, splited[0])))
						bir = true;

					if (words.get(i).equals(wordDecrypt(a, b, splited[1])))
						iki = true;

					if (words.get(i).equals(wordDecrypt(a, b, splited[2])))
						uc = true;

					if (words.get(i).equals(wordDecrypt(a, b, splited[3])))
						dort = true;
				}
				if ((bir && iki && uc) || (iki && uc && dort) || (bir && iki && dort) || (bir && uc && dort)) {
					valueA = a;
					valueB = b;
					break outerloop;
				}
				bir = false;
				iki = false;
				uc = false;
				dort = false;

			}

		}

		System.out.println("a: " + valueA);
		System.out.println("b: " + valueB);

		for (int i = 0; i < splited.length; i++) {
			splited[i] = wordDecrypt(valueA, valueB, splited[i]);
			System.out.print(splited[i] + " ");
		}

	}

	static List<String> getDictionary() throws IOException {
		File dictionary = new File("/home/anil/Desktop/HW4_dictionary.dat");
		BufferedReader br2 = new BufferedReader(new FileReader(dictionary));

		List<String> words = new ArrayList<String>();
		String st;
		while ((st = br2.readLine()) != null)
			words.add(st);

		br2.close();

		return words;
	}

	static String getCrypted() throws IOException {
		File file = new File("/home/anil/Desktop/crypted.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		String paragraf = "";
		while ((st = br.readLine()) != null)
			paragraf = paragraf.concat(st);

		br.close();

		return paragraf;
	}

	static int gcd(int x, int y) {
		if (y == 0)
			return x;
		else
			return gcd(y, x % y);
	}

	static int number(char a) {
		for (int i = 0; i < alphabet.length; i++) {
			if (a == alphabet[i])
				return i;
		}
		return 0;
	}

	static String wordDecrypt(int a, int b, String cryptedWord) {
		StringBuilder decryptedWord = new StringBuilder(cryptedWord);

		for (int i = 0; i < cryptedWord.length(); i++) 
			if (Character.isLetter(decryptedWord.charAt(i)))
				decryptedWord.setCharAt(i, alphabet[(number(decryptedWord.charAt(i)) * a + b) % 26]);
		

		return decryptedWord.toString();
	}

}
