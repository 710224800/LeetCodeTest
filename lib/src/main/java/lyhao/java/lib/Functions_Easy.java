package lyhao.java.lib;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

import javafx.util.Pair;

/**
 * Created by luyanhao on 2020/1/19.
 */
public class Functions_Easy {

    public static int strStr(String haystack, String needle){

        return -1;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     * 答：使用hashMap
     */
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] table = new int[26];
        for(int i = 0; i < s.length(); i++){
            int sKey = s.charAt(i) - 'a';
            table[sKey] ++;
        }
        for(int i = 0; i < t.length(); i++){
            int tKey = t.charAt(i) - 'a';
            table[tKey] --;
            if(table[tKey] < 0){
                return false;
            }
        }
        return true;
    }

        /**
         * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
         * 示例:
         * 输入: [0,1,0,3,12]
         * 输出: [1,3,12,0,0]
         * https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
         * @param nums
         */
    public static void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                if(i > j) { // i == j 时不需要交换
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
                j++;
            }
        }
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode cur = null;
        if(l1 != null && (l2 == null || l1.val < l2.val)){
            head = l1;
            l1 = l1.next;
        } else if(l2 != null){
            head = l2;
            l2 = l2.next;
        }
        cur = head;
        while(cur != null && (l1 != null || l2 != null)){
            if(l1 != null && (l2 == null || l1.val < l2.val)){
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 输出 杨辉三角 前numRows行
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows <= 0){
            return result;
        }
        List<Integer> row = new ArrayList<>();
        row.add(1);
        result.add(row);
        for (int i = 1; i < numRows; i++){
            List<Integer> listRow = new ArrayList<>();

            listRow.add(1);

            for(int j = 1; j < i; j ++){
                List<Integer> preRow = result.get(i - 1);
                if(preRow.size() >= 2) {
                    listRow.add(preRow.get(j - 1) + preRow.get(j));
                }
            }

            listRow.add(1);

            result.add(listRow);
        }
        return result;
    }


    /**
     概念
     如果我们对 0 和二进制位做 XOR 运算，得到的仍然是这个二进制位
        a⊕0=a
     如果我们对相同的二进制位做 XOR 运算，返回的结果是 0
        a⊕a=0
     XOR 满足交换律和结合律
        a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
     */
    public static int singleNumber2(int[] nums) {
        int result = 0;
        for(int i : nums){
            result = result ^ i;
        }
        return result;
    }

    public static int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int twoBeiCount = 0;
        int count = 0;
        for(int i : nums){
            count += i;
            if(!set.contains(i)){
                set.add(i);
                twoBeiCount += i;
            }
        }
        return twoBeiCount * 2 - count;
    }

    /**
     * 位1的个数
     *
     * 输入：00000000000000000000000000001011
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     *
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
//            System.out.println(Integer.toBinaryString(n));
//            System.out.println(Integer.toBinaryString(n-1));
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    /**
     * Excel表列序号
     * 示例 1:       示例 2:        示例 3:
     * 输入: "A"     输入: "AB"     输入: "ZY"
     * 输出: 1       输出: 28       输出: 701
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int result = 0;
        for(int index = 0; index < s.length(); index++){
            char c = s.charAt(index);
            System.out.println(c);
            int num = (c - 'A') + 1;
            result = result * 26 + num;  // 可以理解为26进制，但还不太一样
        }
        return result;
    }

    private static int[] nums;

    /**
     * 将有序数组转换为二叉搜索树  答案不唯一
     * @param left
     * @param right
     * @return
     */
    public static TreeNode helper(int left, int right){
        if(left > right){
            return null;
        }

        int indexP = (left + right) / 2; // 用如果是偶数个数，则用中间左边那个元素为根元素（选择不同，结果不同，所以答案不唯一）
        TreeNode root = new TreeNode(nums[indexP]);
        root.left = helper(left, indexP -1);
        root.right = helper(indexP + 1, right);
        return root;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        Functions_Easy.nums = nums;
        return helper(0, nums.length - 1);
    }

    /**
     * 递归求二叉树的深度,可能会爆栈
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int lDepth = 0, rDepth = 0;
        if (root.left != null){
            lDepth = maxDepth(root.left);
        }
        if (root.right != null){
            rDepth = maxDepth(root.right);
        }
        return Math.max(lDepth, rDepth) + 1;
    }

    /**
     * 宽度优先(层次遍历)，求深度，防止爆栈 用队列实现
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth = 0;
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(root, 1));
        while(!q.isEmpty()){
            Pair<TreeNode, Integer> pair = q.poll();
            TreeNode node = pair.getKey();
            depth = pair.getValue();
            if(node.left != null){
                q.add(new Pair<>(node.left, depth + 1));
            }
            if(node.right != null){
                q.add(new Pair<>(node.right, depth + 1));
            }
        }
        return depth;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 删除链表中的节点，（不要想head，直接传入了链表中的一个节点，删除即可。。。）
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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
