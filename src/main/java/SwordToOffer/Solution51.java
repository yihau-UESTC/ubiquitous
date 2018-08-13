package SwordToOffer;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * 这题解法挺巧的，不能直接使用双重循环来做，一定会超出时间的
 * 正确的做法是先从左到右累乘，在这基础上然后从右到左累乘。
 */
public class Solution51 {
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, mul = 1; i < n; mul *= A[i], i++) {
            B[i] = mul;
        }
        for (int i = n - 1, mul = 1; i >= 0; mul *= A[i], i--) {
            B[i] *= mul;
        }
        return B;
    }
}
