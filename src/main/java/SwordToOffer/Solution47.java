package SwordToOffer;

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 这道题就想到用递归来做，但是递归条件终止要用到if，还是不知道怎么弄，
 * 看到答案使用&&的短路性来完成的，厉害，如果n <= 0的话，&&后的条件就不会执行了，可以成功返回。
 */
public class Solution47 {
    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
}
