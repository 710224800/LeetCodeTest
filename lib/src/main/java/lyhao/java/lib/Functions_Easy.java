package lyhao.java.lib;


import java.util.Arrays;

/**
 * Created by luyanhao on 2020/1/19.
 */
public class Functions_Easy {

    public static int strStr(String haystack, String needle){

        return -1;
    }

    /**
     * 多数元素--给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);  //排序后返回 n/2个元素，必定是多数元素
        return nums[nums.length / 2];
//        int majorCount = nums.length / 2;
//        for(int item : nums){
//            if(itemCount(item, nums) > majorCount){
//                return item;
//            }
//        }
//        return 0;
    }

//    private static int itemCount(int item, int[] nums){
//        int count = 0;
//        for(int ite : nums){
//            if(ite == item){
//                count ++;
//            }
//        }
//        return count;
//    }



    /**
     * 反转字条串
     * @param s
     * @return
     */
    public static char[] reverseString(char[] s) {
        if(s == null || s.length == 0){
            return s;
        }
        int start = 0;
        int end = s.length - 1;
        while(start <= end){
            char tempC = s[start];
            s[start] = s[end];
            s[end] = tempC;
            start ++;
            end --;
        }
        return s;
    }

    /**
     * 链表反转（双指针，也可以叫三指针）
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;

        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    /**
     * 链表反转（递归）
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverseList(head.next); // 先反转后边的
        head.next.next = head; //最后反转头节点
        head.next = null;
        return node;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 反转二进制位
     *
     * 1 取当前 n 的最后一位：n & 1
     * 2 将最后一位移动到对应位置，第一次为 将最后一位左移 31 位，第二次是 30 位，即：31、30、29... 1、0，写作代码 bit << 31;
     * 3 退出条件，二进制反转时，如果剩余位数全位 0，则可以不用再反转。
     *
     * @param n
     * @return
     */
    public static int reverseBits(int n) {
        StringBuilder before = new StringBuilder(Integer.toBinaryString(n));
        int bLen = before.length();
        for (int i = 0; i < 32 - bLen; i++){ //算上符号位是32位
            before.insert(0, "0");
        }
        System.out.println("reverse Before=" + before.toString());

        int ans = 0;
        for (int bitsSize = 31; n != 0; n = n >>> 1, bitsSize--) { //左移31位即能将最后一位移动到第一位
            ans += (n & 1) << bitsSize;

            StringBuilder result = new StringBuilder(Integer.toBinaryString(ans));
            int rLen = result.length();
            for (int i = 0; i < 32 - rLen; i++){ //算上符号位是32位
                result.insert(0, "0");
            }
            System.out.println("reverse  After=" + result.toString());
        }
        return ans;
    }

    /**
     * 快乐数
     * 快慢指针（找环形链表）的思想，找结果有没有重合 如果重合，看是不是等于1，如果等于1则是快乐数，反之不是
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        int slow = cal(n);
        int fast = cal(n); fast = cal(fast);
        while (slow != fast && slow != 1 && fast != 1) {
            slow = cal(slow);
            fast = cal(fast);
            fast = cal(fast);
        }
        if(slow == 1 || fast == 1){
            return true;
        } else {
            return false;
        }
    }

    private static int cal(int n){
        int result = 0;
        do{
            int a = n % 10;
            result += a * a;
            n = n/10;
        } while (n > 0);
        return result;
    }

    /**
     * 找字符串数组，的最大公共前缀 NO.1
     * @param strs
     * https://leetcode-cn.com/problems/longest-common-prefix/
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0]; // 设置第0个字符串为公共前缀
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) { // 等于0说明正好匹配上，是前缀，否则就不是前缀 (返回-1 正数 都说明不是前缀，-1是没匹配上，正数是匹配在中间)
                prefix = prefix.substring(0, prefix.length() - 1); //去掉最后一个字符，再匹配一次，直到匹配成功
                if (prefix.isEmpty()) return ""; // 如果为空了，则说明没有公共前缀
            }
        }
        return prefix;
    }

    /**
     * 找字符串数组，的最大公共前缀 NO.2
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    System.out.println(i);
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 辗转相除求最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int zhanzhuanXiangchu(int a, int b){
        int temp;
        while (b != 0){
            temp = a % b;
            a = b;
            b = temp;
            System.out.println("a=" + a + " b=" + b);
        }
        return a;
    }
}
