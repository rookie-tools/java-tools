package stringprocess;

/**
 * Created by hecz on 2018/6/1.
 */
public class CharUtils {
    public static boolean isAsciiAlphanumeric(char c)
    {
        // 0x2E80-0x9FFF 东亚文字范围
        return (c>=0x2E80&&c<=0x9FFF);
    }
    public static void main(String[] args)
    {
        System.out.println("ADADADASD");
    }
}
