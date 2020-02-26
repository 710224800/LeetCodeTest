package lyhao.java.lib;

import java.util.HashMap;

/**
 * Created by luyanhao on 2020/2/26.
 */
public class Functions_Middle {
    /**
     * 字符串转换整数(atoi)
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }
        HashMap<String, Integer> numMap = new HashMap<>();
        for(int i = 0; i < 10; i++){
            numMap.put("" + i, i);
        }
        int position = 0;
        while(position < str.length() && str.charAt(position) == ' '){
            position ++;
        }
        if(position >= str.length()){
            System.out.println("position >= str.length()");
            return 0;
        }
        String s = str.substring(position, position + 1);
        if(!numMap.containsKey(s) && !s.equals("+") && !s.equals("-")){
            return 0;
        }
        int sign = 1;
        if(s.equals("-")){
            sign = -1;
            position ++;
        } else if (s.equals("+")){
            position ++;
        }
        long sum = 0;
        long boundry = sign < 0 ? 1 << 31 : (1 << 31) - 1;
        System.out.println("boundry = " + boundry);

        for(; position < str.length(); position++){
            String key = str.substring(position, position + 1);
            if(!numMap.containsKey(key)){
                break;
            }
            Integer value = numMap.get(key);
            if(value == null){
                break;
            }
            System.out.println("key = " + key);
            System.out.println("value = " + value);
            sum = sum * 10 + value;
            System.out.println("sum = " + sum);
            if(sign > 0) {
                if (sum * sign >= boundry) {
                    return (int) boundry;
                }
            } else {
                if(sum * sign <= boundry){
                    return (int) boundry;
                }
            }
        }
        return (int) (sum * sign);
    }
}
