package DataStructure;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午7:36 17-10-23
 * 杨辉三角，借助队列来实现，在各行之间插入0，通过i行系数来获得i+1行是系数。
 */
public class YANGVI {

    public static void main(String[] args){
        method(10);
        PriorityQueue<String> pq = new PriorityQueue<>();

    }

    public static void method(int n ){
        Queue<Integer> queue = new LinkedList<>();
        int i = 1,j,s = 0,k = 0,t,u;
        queue.add(i);
        queue.add(i);//第一行系数入队1,1
        for (i = 1; i <= n; i++){//循环处理各行
            System.out.println();
            queue.add(k);//各行之间插入0隔开
            for (j = 1; j <= i+2; j++){//处理第i行的i+2个系数
                t = queue.remove();
                u = s + t;//计算i+1行的第j个系数
                queue.add(u);//入队
                s = t;
                if (j != i+2){//i+2为0
                    System.out.print(s + " ");
                }
            }
        }
    }
}
