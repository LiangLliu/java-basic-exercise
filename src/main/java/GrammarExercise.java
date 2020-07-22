import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {

        firstWordList = firstWordList.toUpperCase();
        secondWordList = secondWordList.toUpperCase();

        // 解析字符串
        String[] firstSplitString = firstWordList.split(",");
        String[] secondSplitString = secondWordList.split(",");


        Stream.of(firstSplitString).forEach(GrammarExercise::checkString);


        Stream.of(secondSplitString).forEach(GrammarExercise::checkString);

        Set<String> firstStringSet = Stream.of(firstSplitString).collect(Collectors.toSet());
        Set<String> secondStringSet = Stream.of(secondSplitString).collect(Collectors.toSet());


        firstStringSet.retainAll(secondStringSet);


        //在这编写实现代码
        return firstStringSet.stream().sorted().map(GrammarExercise::addSpace).collect(Collectors.toList());
    }

    private static String addSpace(String s) {
        char[] chars = s.toCharArray();

        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < chars.length - 1; i++) {
            stringBuffer.append(chars[i]).append(" ");
        }
        stringBuffer.append(chars[chars.length - 1]);
        return stringBuffer.toString();
    }

    private static void checkString(String string) {
        if ("".equals(string) || !checkIllegal(string)) {
            throw new RuntimeException("input not valid");
        }

    }

    private static boolean checkIllegal(String string) {
        String pattern = "^[A-Z]+$";
        return Pattern.matches(pattern, string);
    }


}
