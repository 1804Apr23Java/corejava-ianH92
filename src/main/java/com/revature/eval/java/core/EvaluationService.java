package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		if(phrase == null) return "";
		
		phrase = phrase.toUpperCase();
		int len = phrase.length();
		StringBuilder acronym = new StringBuilder();
		
		int index = 0;
		char curr = 'a'; //temp
		char prev = ' ';
		while(index < len) {
			curr = phrase.charAt(index);
			
			if(curr != ' ') {
				if((prev == ' ' || prev == '-') && (curr >= 'A' && curr <= 'Z'))
					acronym.append(curr);
			}
			
			prev = curr;
			index++;
		}
		
		return acronym.toString();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (this.sideOne == this.sideTwo && this.sideOne == this.sideThree);
		}

		public boolean isIsosceles() {
			return (this.sideOne == this.sideTwo || this.sideOne == this.sideThree || this.sideTwo == this.sideThree);
		}

		public boolean isScalene() {
			return (this.sideOne != this.sideTwo && this.sideOne != this.sideThree && this.sideTwo != this.sideThree);
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		string = string.toUpperCase();
		int[] vals = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
		
		int score = 0;
		for(int i = 0; i < string.length(); i++) {
			
			if(string.charAt(i) < 'A' || string.charAt(i) > 'Z') { //invalid input
				continue;
			}
			
			score += vals[string.charAt(i) - 'A'];
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		if(string == null) throw new IllegalArgumentException("Null input invalid");
		
		StringBuilder phoneNumber = new StringBuilder();
		int digitCount = 0;
		
		for(int i = 0; i < string.length(); i++) {
			char num = string.charAt(i);
			if(num >= '0' && num <= '9') {
				phoneNumber.append(num);
				digitCount++;
			}
		}
		
		if(digitCount == 10) {
			return phoneNumber.toString();
		} else if(digitCount == 11) {
			if(phoneNumber.toString().charAt(0) == '1') {
				return phoneNumber.toString().substring(1);
			} 
		}
		
		throw new IllegalArgumentException("Invalid Input");
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if(string == null || string.length() == 0) return map;
		
		int len = string.length();
		char[] c = string.toCharArray();
		int p1 = 0;
		int p2 = 0;
		
		while(p2 < len) {
			while(p1 < len && (c[p1] == ' ' || c[p1] == ',' || c[p1] == '\n' || c[p1] == '\t')) {
				p1++;
			}
			p2 = p1;
			
			while(p2 < len && c[p2] != ' ' && c[p2] != ',' && c[p2] != '\n' && c[p2] != '\t') {
				p2++;
			}
			
			if(p2 <= len) {
				String sub = string.substring(p1, p2);
				Integer count = map.get(sub);
				count = (count == null) ? new Integer(1) : new Integer(count.intValue() + 1);
				map.put(sub, count);
			}
			p1 = p2;
		}
		
		return map;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int high = sortedList.size() - 1;
			int low = 0;
			
			while(low <= high) {
				int middle = (high + low) / 2;
				
				int comparison =  ((Comparable<T>) this.sortedList.get(middle)).compareTo(t);
				
				if(comparison == 0) {
					return middle;
				} else if(comparison == 1) {
					high = middle - 1;
				} else {
					low = middle + 1;
				}
			}
			
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		if(string == null || string.length() == 0) return null;
		
		string = string.trim();
		String[] l = string.split(" ");
		
		String result = "";
		for(int i = 0; i < l.length; i++) {
			String s = l[i];
			
			char f = s.charAt(0);
			if(f == 'A' || f == 'a' || f == 'E' || f == 'e' || f == 'I' || f == 'i' || f == 'O' || 
			   f == 'o' || f == 'U' || f == 'u') {
				
				result += (s + "ay");
			} else {
				int j = 0;
				
				if(f == 'q' || f == 'Q') {
					while(j < s.length() && f != 'A' && f != 'a' && f != 'E' && f != 'e' && f != 'I' && f != 'i' && f != 'O' && 
							   f != 'o') {
						f = s.charAt(j);
						j++;
					}
				} else {
					while(j < s.length() && f != 'A' && f != 'a' && f != 'E' && f != 'e' && f != 'I' && f != 'i' && f != 'O' && 
							   f != 'o' && f != 'U' && f != 'u') {
						f = s.charAt(j);
						j++;
					}
				}
				j--;
				result += (s.substring(j) + s.substring(0, j) + "ay");
			}
			
			if(i < l.length - 1) result += " ";
		}
		
		return result;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		if(input == 0) return true;
		if(input < 0) return false;
		
		int valueOfInput = input;
		int[] digits = new int[10];
		int numDigits = 0;
		
		while(valueOfInput > 0) {
			digits[ numDigits++ ] = valueOfInput % 10;
			valueOfInput /= 10;
		}

		int sum = 0;
		for(int i = 0; i < numDigits; i++) {
			sum += (int) Math.pow(digits[i], numDigits);
		}
		
		return (sum == input) ? true : false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> list = new ArrayList<>();
		if(l <= 1) return list;
	
		long i = 2;
		
		while(l != 1) {
			
			if(l % i == 0 && (i == 2 || i % 2 != 0)) {
				
				//Test if i is prime
				boolean isPrime = true;
				for(int j = 2; j <= (i / 2); j++) {
					if(i % j == 0) isPrime = false;
				}
				if(isPrime) list.add(i);
				
				l = l / i;
				i = 1;
			}
			i++;
		}
		
		return list;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			char[] cipherText = new char[string.length()];
			
			for(int i = 0; i < string.length(); i++) {
				char curr = string.charAt(i);
				
				if(curr >= 'a' && curr <= 'z') {
					cipherText[i] = (curr + this.key <= 'z') ? (char) (curr + this.key) : (char) (((curr + this.key) % 'z') + ('a' - 1));
				} else if(curr >= 'A' && curr <= 'Z') {
					cipherText[i] = (curr + this.key <= 'Z') ? (char) (curr + this.key) : (char) (((curr + this.key) % 'Z') + ('A' - 1));
				} else {
					cipherText[i] = curr;
				}
			}
			
			return new String(cipherText);
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		if(i <= 0) throw new IllegalArgumentException(i + "th prime is not defined\n");
		
		int numPrimes = 1;
		int curr = 2;
		
		while(numPrimes != i) {
			curr++;
			if(curr % 2 != 0) {
				
				boolean isPrime = true;
				for(int j = 2; j < curr; j++) {
					if(curr % j == 0) isPrime = false;
				}
				if(isPrime) numPrimes++;
				
			}
		}
		return curr;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			char[] lowerCase = {'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 
								'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'};
			
			StringBuilder encodedString = new StringBuilder();
			int groupingCount = 0;
			
			for(int i = 0; i < string.length(); i++) {
				char curr = string.charAt(i);
				
				if(groupingCount == 5) {
					encodedString.append(" ");
					groupingCount = 0;
				}
				
				if(curr >= 'a' && curr <= 'z') {
					encodedString.append(lowerCase[curr - 'a']);
					groupingCount++;
				} else if(curr >= 'A' && curr <= 'Z') {
					encodedString.append(lowerCase[curr - 'A']);
					groupingCount++;
				} else if(curr >= '0' && curr <= '9') {
					encodedString.append(curr);
					groupingCount++;
				}
			}
			
			//Remove extra space at end if necessary
			String result = encodedString.toString();
			return (result.charAt(result.length() - 1) == ' ') ? result.substring(0, result.length() - 1) : result;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			char[] lowerCase = {'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 
					'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'};
			
			StringBuilder plain = new StringBuilder();
			
			for(int i = 0; i < string.length(); i++) {
				char curr = string.charAt(i);
				if(curr >= 'a' && curr <= 'z')  {
					plain.append(lowerCase[curr - 'a']);
				} else if(curr >= '0' && curr <= '9') {
					plain.append(curr);
				}
			}
			
			return plain.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		int j = 10;
		int sum = 0;
		for(int i = 0; i < string.length(); i++) {
			char curr = string.charAt(i);
			if(j < 1) return false;
			
			if(curr >= '0' && curr <= '9') {
				sum += ((curr - '0') * j);
				j--;
			} else if (curr == '-') {
				continue;
			} else if(curr == 'X') {
				sum += (10 * j);
				j--;
			} else {
				return false;
			}
 		}
		
		return (sum % 11 == 0) ? true : false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: Ï€Î±Î½ Î³Ï�Î¬Î¼Î¼Î±, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		int lettersTracker = 0;
		
		for(int i = 0; i < string.length(); i++) {
			char curr = string.charAt(i);
			
			if(curr >= 'a' && curr <= 'z') {
				lettersTracker |= (1 << (curr - 'a'));
			} else if(curr >= 'A' && curr <= 'Z') {
				lettersTracker |= (1 << (curr - 'A'));
			}
		}
		
		return(lettersTracker == 67108863) ? true : false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		if(given instanceof LocalDateTime) { 
			return ((LocalDateTime) given).plusSeconds(1000000000);
		} else {
			LocalDate g = (LocalDate) given;
			LocalDateTime m = LocalDateTime.of(g.getYear(), g.getMonth(), g.getDayOfMonth(), 0, 0, 0);
			return (Temporal) m.plusSeconds(1000000000);
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		
		for(int j = 0; j < set.length; j++) {
			int curr = set[j];
			int multiple = 1;
			int val = curr * multiple;
			
			while(val < i) {
				Integer check = map.get(val);
				sum += (check == null) ? val : 0;
				map.put(val, 1);
				
				
				multiple++;
				val = curr * multiple;
			}
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 *
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		int len = string.length();
		if(len <= 1) return false;
		
		int[] val = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int isEven = 1;
		int sum = 0;
		
		for(int i = (len - 1); i >= 0; i--) {
			char curr = string.charAt(i);
			
			if(curr >= '0' && curr <= '9') {
				if(isEven % 2 == 0) {
					sum += ((val[curr - '0'] * 2) > 9) ?  ((val[curr - '0'] * 2) - 9) : (val[curr - '0'] * 2);
					isEven++;
				} else {
					sum += val[curr - '0'];
					isEven++;
				}
			} else if (curr == ' ') {
				//skip
			} else {
				return false;
			}
		}
		
		return (sum % 10 == 0) ? true : false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		int len = string.length();
		
		int num1 = 0;
		int num2 = 0;
		
		int operationVal = -1;
		
		boolean num1Assigned = false;
		boolean num2Assigned = false;
		boolean extraNums = false;
		boolean operationAssigned = false;
		
		try {
			for(int i = 0; i < len; i++) {
				char curr = string.charAt(i);
				
				if(curr >= '0' && curr <= '9') {
					boolean negative = (i > 0 && string.charAt(i - 1) == '-') ? true : false;
					
					int index = i;
					while(i <  len && (string.charAt(i) >= '0' && string.charAt(i) <= '9')) {
						i++;
					}
					
					if(!num1Assigned){
						num1 = Integer.parseInt(string.substring(index, i));
						num1 = (negative) ? (-1 * num1) : num1;
						num1Assigned = true;
					} else if(num1Assigned && !num2Assigned) {
						num2 = Integer.parseInt(string.substring(index, i));
						num2 = (negative) ? (-1 * num2) : num2;
						num2Assigned = true;
					} else {
						extraNums = true;
					}
				}
				
				if(curr == 'm' || curr == 'M' || curr == 't' || curr == 'T' || curr == 's' || curr == 'S' || curr == 'a' || curr == 'A' || 
				   curr == 'p' || curr =='P' || curr == 'd' || curr == 'D') {
					
					int index = i;
					while(i <  len && ((string.charAt(i) >= 'a' && string.charAt(i) <= 'z') || (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z'))) {
						i++;
					}
					
					if(i < len && !operationAssigned) {
						String operation = string.substring(index, i);
						
						if(operation.equalsIgnoreCase("subtracted") || operation.equalsIgnoreCase("minus")) {
							operationVal = 0;
							operationAssigned = true;
						} else if(operation.equalsIgnoreCase("multiplied") || operation.equalsIgnoreCase("times")) {
							operationVal = 1;
							operationAssigned = true;
						} else if(operation.equalsIgnoreCase("added") || operation.equalsIgnoreCase("plus")) {
							operationVal = 2;
							operationAssigned = true;
						} else if(operation.equalsIgnoreCase("divided")) {
							operationVal = 3;
							operationAssigned = true;
						} else {
							//Skip
						}
					}
				}
			}
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Number in expression is to large for Java int");
		}
		
		if(num1Assigned && num2Assigned && !extraNums && operationVal >= 0 && operationVal <=3) {
			switch(operationVal) {
				case 0: long result = (long) num1 - (long) num2;
						if(result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
							throw new IllegalArgumentException("Result of subtraction = " + result + ", wont fit in Java int");
						} else {
							return(num1  - num2);
						}
				case 1: result = (long) num1 * (long) num2;
						if(result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
							throw new IllegalArgumentException("Result of multiplication = " + result + ", wont fit in Java int");
						} else {
							return(num1  * num2);
						}
				case 2: result = (long) num1 + (long) num2;
						if(result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
							throw new IllegalArgumentException("Result of addition = " + result + ", wont fit in Java int");
						} else {
							return(num1  + num2);
						}
				case 3: if(num2 == 0) {
							throw new IllegalArgumentException("Division by zero not allowed");
						} else {
							return(num1 / num2);
						}
			}
		} 
		
		String msg = "";
		if(operationVal == -1) msg += "Invalid input, no operation specified\n";
		if(!num1Assigned) msg += "No numbers in expression\n";
		if(!num2Assigned) msg += "Only one number in expression\n";
		if(extraNums) msg += "More than two numbers in expression\n";
		throw new IllegalArgumentException(msg);
	}

}
