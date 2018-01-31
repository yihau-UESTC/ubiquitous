package Concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午9:01 17-12-25
 * @Description: 一个java程序天生就是一个多线程程序
 */
public class MutiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo :threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());

        }
        System.out.println(threadMXBean.getDaemonThreadCount());

    }
}
