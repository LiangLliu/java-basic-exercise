import exception.GrammarExerciseException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {

    private final static String VERIFY_VALID_REGULAR = "^[A-Z]+$";

    private final static String SPLIT_STRING = ",";

    private final static String SPACE_STRING = " ";

    private final static String INPUT_NOT_VALID = "input not valid";

    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {

        // 解析字符串
        List<String> firstStream = getWordList(firstWordList);
        List<String> secondStream = getWordList(secondWordList);

        firstStream.retainAll(secondStream);

        //在这编写实现代码
        return firstStream.stream()
                .sorted()
                .map(GrammarExercise::addSpace)
                .collect(Collectors.toList());
    }

    private static List<String> getWordList(String wordList) {
        return Arrays.stream(wordList.split(SPLIT_STRING))
                .map(String::toUpperCase)
                .peek(GrammarExercise::checkString)
                .distinct()
                .collect(Collectors.toList());
    }

    private static String addSpace(String s) {
        char[] chars = s.toCharArray();

        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < chars.length - 1; i++) {
            stringBuffer.append(chars[i]).append(SPACE_STRING);
        }
        stringBuffer.append(chars[chars.length - 1]);
        return stringBuffer.toString();
    }

    private static void checkString(String string) {
        if ("".equals(string) || !checkIllegal(string)) {
            throw new GrammarExerciseException(INPUT_NOT_VALID);
        }
    }

    private static boolean checkIllegal(String string) {
        return Pattern.matches(VERIFY_VALID_REGULAR, string);
    }


}
