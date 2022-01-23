import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class CryptoMethods {
    public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзиклмнопрстуфхцчшщъыьэюя.,\":-!? ";

    public static String encoding(String message, int key) {
        StringBuilder result = new StringBuilder();
        int newPositionInAlphabet = 0;
        key = key % 73;
        for (int i = 0; i < message.length(); i++) {
            char symbol = message.charAt(i);
            if (ALPHABET.indexOf(symbol) > -1) {

                int positionInAlphabet = ALPHABET.indexOf(symbol);
                if (key > 0) {
                    key = Math.abs(key);
                    newPositionInAlphabet = positionInAlphabet + key;
                    if (newPositionInAlphabet >= 73) {
                        newPositionInAlphabet = newPositionInAlphabet % 73;
                    }
                } else {
                    newPositionInAlphabet = positionInAlphabet - Math.abs(key);
                    if (newPositionInAlphabet < 0) {
                        newPositionInAlphabet = newPositionInAlphabet + 73;
                    }
                }
                result.append(ALPHABET.charAt(newPositionInAlphabet));
            } else result.append(message.charAt(i));
        }
        return result.toString();
    }

    public static int brutForceAlgorithm(String text) {
        int key = -1;
        boolean flag = true;
        while (flag) {
            String temp = encoding(text, key);
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            if (temp.indexOf('.') > -1) {
                if ((temp.indexOf(".") == temp.length() - 1 ||
                        (temp.charAt(temp.indexOf(".") + 1) == ' ' && (temp.indexOf(".") + 1 < temp.length())))) {
                    flag1 = true;
                } else flag1 = false;
            } else flag1 = true;
            if (temp.indexOf('!') > -1) {
                if ((temp.indexOf("!") == temp.length() - 1 ||
                        (temp.charAt(temp.indexOf("!") + 1) == ' ' && (temp.indexOf("!") + 1 < temp.length())))) {
                    flag2 = true;
                } else flag2 = false;
            } else
                flag2 = true;
            if (temp.indexOf(',') > -1) {
                if ((temp.indexOf(",") < temp.length() - 2 && temp.charAt(temp.indexOf(",") + 1) == ' ')) {
                    flag3 = true;
                } else flag3 = false;
            } else flag3 = true;
            if (!flag1 || !flag2 || !flag3) {
                key--;
            } else flag = false;
        }
        return key;
    }

    public static String statisticalTextAnalysis(String encryptText, String bigText) {
        Map<Character, Double> encryptMap = new HashMap<>();
        Map<Character, Double> bigTextMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            encryptMap.put(ALPHABET.charAt(i), 0d);
            bigTextMap.put(ALPHABET.charAt(i), 0d);
        }
        double sumAddToEncryptMap = (1.0 * 100) / encryptText.length();
        for (int i = 0; i < encryptText.length(); i++) {
            char symbol = encryptText.charAt(i);
            if (ALPHABET.indexOf(symbol) > -1) {
                encryptMap.put(symbol, encryptMap.get(symbol) + sumAddToEncryptMap);
            }
        }
        double sumAddToBigTextMap = (1.0 * 100) / bigText.length();
        for (int i = 0; i < bigText.length(); i++) {
            char symbol = bigText.charAt(i);
            if (ALPHABET.indexOf(symbol) > -1) {
                bigTextMap.put(symbol, bigTextMap.get(symbol) + sumAddToBigTextMap);
            }
        }

        Map<Character, Double> sortedEncryptMap = encryptMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        Map<Character, Double> sortedBigTextMap = bigTextMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        List<Character> sortedEncryptList = new ArrayList<>();
        for (Map.Entry<Character, Double> entry : sortedEncryptMap.entrySet()) {
            sortedEncryptList.add(entry.getKey());
        }
        List<Character> sortedBigTextList = new ArrayList<>();
        for (Map.Entry<Character, Double> entry : sortedBigTextMap.entrySet()) {
            sortedBigTextList.add(entry.getKey());
        }

        Map<Character, Character> resultMap = new HashMap<>();
        for (int i = 0; i < sortedEncryptList.size(); i++) {
            resultMap.put(sortedEncryptList.get(i), sortedBigTextList.get(i));

        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encryptText.length(); i++) {
            char symbol = encryptText.charAt(i);
            if (resultMap.containsKey(symbol)) {
                result.append(resultMap.get(symbol));
            }

        }


        return result.toString();
    }

}
