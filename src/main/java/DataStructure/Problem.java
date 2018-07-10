package DataStructure;

public class Problem {
    public static void main(String[] args) {
        System.out.println(sol2());
    }

    private static int sol(int n, int m){
        int s1 = 0, s2 = 0;
        int i = 1;
        for (; i <= m; i++){
            s1 += (i + (i + ((n / (2 * m)) - 1) * (2 * m))) * (n / (2 * m)) / 2;
        }
        for (; i <= (2 * m); i++){
            s2 += (i + (i + ((n / (2 * m)) - 1) * (2 * m))) * (n / (2 * m)) / 2;
        }
        return s2 - s1;
    }

    private static int sol2(){
        int sum = 0;
        bt(sum, 5, 2, 2, 1, 3, 3, 0, 3);
        System.out.println(sum);
        return sum;
    }

    private static void bt(int sum, int target,int cnum, int i1,int ci1, int l1, int i2, int ci2, int l2){
        target -= cnum;
        if (target == 0){
            int z = 0;
            int s1 = 1,s2 = 1;
            for (int i = 1; i <= ci1; i++){
                s1 *= i;
                s2 *= l1 - i + 1;
            }
            z = s2 / s1;
            for (int i = 1; i <= ci2; i++){
                s1 *= i;
                s2 *= l2 - i + 1;
            }
            sum += z * (s2 / s1);
        }else if (target > 0){
            if (ci1 < l1)bt(sum, target, i1, i1, ci1 + 1, l1, i2, ci2, l2);
            else if (ci2 < l2)bt(sum, target, i2, i1, ci1, l1, i2, ci2 + 1, l2);
            else return;
        }else {
            if (cnum == i1)bt(sum, target + cnum, i2, i1, ci1 - 1, l1, i2, ci2 + 1, l2);
        }
    }
}
