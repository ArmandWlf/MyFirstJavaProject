
package MyFirstproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SecondEntry {

	private static class Word {
		String wordStr;
		int occurrence;

		public Word(String wordStr, int occurrence) {
			this.wordStr = wordStr;
			this.occurrence = occurrence;
		}

		public int compareTo(Word word) {
			return 0;
		}
	}

	public static <text> void main(String[] args) {
		String filePath = "";
		if (args.length > 0)
			filePath = System.getProperty("user.dir") + "/" + args[0];
		else
			filePath = System.getProperty("user.dir") + "/Hacker.txt";

		// First, we read the text from the file
		String text = readFile(filePath);

		// Then we split the text into words
		String[] words = getWords(text);

		// Print out each word
		// for (String word : words)
		// System.out.println("Test:" + word);

		HashMap<String, Integer> wordCount = countWords(words);

		printCount(wordCount);

		List<Word> topWords = getTopWords(wordCount);
		System.out.println("TOP 15 WORDS:");
		for (Word word : topWords) {
			System.out.println(word.wordStr + " : " + word.occurrence);
		}

		String compressedText = compressText(text, topWords);
		// System.out.println(compressedText);

		// Legend
		String legendLine = getLegend(topWords);

		System.out.println(legendLine);

		writeToFile("D:/out.txt", legendLine + "\n" + compressedText);

		processLegendLine(legendLine);
		
		HashMap<String, String> newMap = processLegendLine(legendLine);

		compressedText = readFile("D:/out.txt");

		writeToFileClean("D:/out_clean", legendLine + text);

	}

	public static HashMap<String, String> processLegendLine(String legendLine) {

		String[] splitLegend = legendLine.split(";");

		for (String s : splitLegend) {
			String[] newSplit = s.split("=");

			for (String ss : newSplit) {
				System.out.println(ss);
			}
		}

		System.out.println(legendLine);

	}
	
	
	public static void writeToFileClean(String string, String string3) {

		File file = new File("D:/out-decompressed.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(string3);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("PRINTED DECOMPRESSED IN NEW .TXT FILE");
	}

	public static void writeToFile(String string, String string2) {
		try {

			File file = new File("D:/out.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(string2);
			bw.close();

			System.out.println("PRINTED IN NEW .TXT FILE");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static java.lang.String getLegend(List<Word> topWords) {

		String line = "";

		for (int i = 0; i < topWords.size(); i++) {
			String word = topWords.get(i).wordStr;
			String replacementCode = "" + i;
			String pair = replacementCode + "=" + word;

			line += pair + ";";
		}

		return line;
	}

	public static String getLegend(List<Word> topWords, java.lang.String replacementCode) {
		String line = "";
	   // String replacementCode = i + "=" + word;

		line += replacementCode + ";";

		return line;
	}

	public static String compressText(String text, List<Word> topWords) {
		for (int i = 0; i < topWords.size(); i++) {
			String word = topWords.get(i).wordStr;
			String replacementCode = "@" + i;

			text = text.replaceAll(" " + word + " ", " " + replacementCode + " ");
		}

		return text;
	}

	/**
	 * This methods reads the contents of a text file
	 * 
	 * @param path
	 * @return
	 */
	public static String readFile(String path) {
		try {
			File file = new File(path);
			Scanner scanner = new Scanner(file);

			String result = "";
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result = result + line + "\n";
			}

			scanner.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String[] getWords(String text) {
		if (text == null || text.isEmpty())
			return null;

		String[] words = text.split("[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+");
		return words;

	}

	/**
	 * This methods creates a map with each word as a key and it's occurrence
	 * count as value
	 * 
	 * @param words
	 * @return
	 * @return
	 * @return
	 */

	public static HashMap<String, Integer> countWords(String[] words) {

		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		for (String string : words) {
			if (!countMap.containsKey(string)) {
				countMap.put(string, 1);
			} else {
				Integer count = countMap.get(string);
				count = count + 1;
				countMap.put(string, count);
			}
		}

		return countMap;
	}

	public static void printCount(HashMap<String, Integer> countMap) {
		Set<String> keySet = countMap.keySet();
		for (String word : keySet) {
			System.out.println(word + " : " + countMap.get(word));
		}
	}

	public static List<Word> getTopWords(HashMap<String, Integer> countMap) {
		List<Word> wordsList = new ArrayList<Word>();

		Set<String> keySet = countMap.keySet();
		for (String word : keySet) {
			Integer occurrence = countMap.get(word);

			if (word.length() < 3)
				continue;

			Word wordInstance = new Word(word, occurrence.intValue());
			wordsList.add(wordInstance);
		}

		System.out.println("SORTED WORDS:");
		List<Word> sortedWords = bubbleSort2(wordsList);
		// for(Word word : sortedWords){
		// System.out.println(word.wordStr + " : " + word.occurrence);
		// }

		return sortedWords.subList(0, 15);
	}

	public static List<Word> bubbleSort2(List<Word> words) {
		boolean swapped = false;
		Word[] wordsArr = new Word[words.size()];
		wordsArr = words.toArray(wordsArr);

		do {
			// browsing the vector then changing the element if needed

			swapped = false;
			for (int i = 0; i < wordsArr.length - 1; i++) {

				if (wordsArr[i].occurrence < wordsArr[i + 1].occurrence) {
					// if(wordsArr[i].wordStr.compareTo(wordsArr[i+1].wordStr) >
					// 0){ ( code for alphabetical sort)
					Word temp = wordsArr[i];
					wordsArr[i] = wordsArr[i + 1];
					wordsArr[i + 1] = temp;

					swapped = true;
				}

			}
		} while (swapped);

		words = new ArrayList<Word>();
		for (Word word : wordsArr) {
			words.add(word);
		}

		return words;
	}

	// This method sorts out the top 10 most frequent words.

	public static void bubbleSort(ArrayList<Word> list, int o) {
		Word temp;

		if (list.size() > 1) {
			for (int x = 0; x < list.size(); x++) {
				for (int i = o; i < list.size() - x - 1; i++) {
					if (list.get(i).compareTo(list.get(i + 1)) > 0) {
						temp = list.get(i);
						list.set(i, list.get(i + 1));
						list.set(i + 1, temp);
					}
				}
			}
		}
	}
}
