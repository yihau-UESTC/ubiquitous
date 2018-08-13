package SwordToOffer;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * pow = (base*base)^exponent/2 if exponent % 2 == 0
 * pow = base *(base*base)^exponent/2 if exponent % 2 == 1
 * pow = base if exponent = 1
 * 由上面递推式，可以使用递归求解。
 * 复杂度log(exponent)
 */
public class Solution12 {
    public double Power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        boolean isNagative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNagative = true;
        }
        double res = Power(base * base, exponent / 2);
        if (exponent % 2 != 0) {
            res = base * res;
        }
        return isNagative ? 1 / res : res;
    }
}
