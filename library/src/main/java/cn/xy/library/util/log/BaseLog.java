/**
 * Function:TODO
 * @Author:Eric
 * @E-mail:2540458966@qq.com	
 * @Date：2020-4-13 下午6:39:01
 * @Verson:
 */
package cn.xy.library.util.log;

import android.util.Log;

/**
 * @author : Xy
 * TODO
 * 14:26 2020/12/10
 */
public class BaseLog {
	private static final int MAX_LENGTH = 4000;

    public static void printDefault(int type, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LENGTH);
                printSub(type, tag, sub);
                index += MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case XLog.V:
                Log.v(tag, sub);
                break;
            case XLog.D:
                Log.d(tag, sub);
                break;
            case XLog.I:
                Log.i(tag, sub);
                break;
            case XLog.W:
                Log.w(tag, sub);
                break;
            case XLog.E:
                Log.e(tag, sub);
                break;
            case XLog.A:
                Log.wtf(tag, sub);
                break;
        }
    }
}
