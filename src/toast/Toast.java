package toast;

import java.awt.*;
import java.util.Timer;

/**
 * Swing Toast工具类
 * @author callback
 * @version 1.0.0
 */
public class Toast
{
    /* Toast出现位置（相对于Toast的owner） */
    public static final int LEFT_TOP = 0;
    public static final int LEFT_BOTTOM = 1;
    public static final int RIGHT_TOP = 2;
    public static final int RIGHT_BOTTOM = 3;
    public static final int CENTER = 4;
    public static final int BOTTOM = 5;
    public static final int TOP = 6;

    private static final int GAP = 15;
    private static final int DECORATE_GAP = 25;

    private static final Timer toastTimer = new Timer(); /* toast关闭调度器 */

    private Toast(){}


    /**
     * 展示一个Toast, 适用于用户自定义的Toast
     * @param toast Toast对象
     * @param close Toast关闭方式
     * @param duration Toast显示持续时间
     */
    public static void show(BaseToast toast, ToastClose close, long duration)
    {
        toast.setVisible(true);
        toastTimer.schedule(close, duration);
    }

	 /**
     * 展示一个Toast
     * @param owner Toast出现的父窗口（DefaultToast必须依赖于JFrame而存在）
     * @param location 出现在父窗口的位置
     * @param text 展示的文本
     */
    public static void showToast(Frame owner, int location, String text)
    {
        showToast(owner, location, ToastStatus.INFO ,text, 1500L);
    }

     /**
     * 展示一个Toast
     * @param owner Toast出现的父窗口（DefaultToast必须依赖于JFrame而存在）
     * @param location 出现在父窗口的位置
     * @param text 展示的文本
     * @param duration Toast的持续时间
     */
    public static void showToast(Frame owner, int location, String text, long duration)
    {
        showToast(owner, location, ToastStatus.INFO, text, duration);
    }

    /**
     * 展示一个Toast
     * @param owner Toast出现的父窗口（DefaultToast必须依赖于JFrame而存在）
     * @param location 出现在父窗口的位置
     * @param status Toast的状态，INFO，ERROR， SUCCESS
     * @param text 展示的文本
     * @param duration Toast的持续时间
     */
    public static void showToast(Frame owner, int location, ToastStatus status ,String text, long duration)
    {
        DefaultToast toast = new DefaultToast(owner,text, status);

        Window frame = toast.getOwner();

        final int frameX = frame.getX();
        final int frameY = frame.getY();
        final int frameWidth = frame.getWidth();
        final int frameHeight = frame.getHeight();

        /* 默认为居中显示 */
        int x = frameX + ( frameWidth - toast.getWidth() ) / 2;
        int y = frameY + ( frameHeight - toast.getHeight() ) / 2;

        if (RIGHT_BOTTOM == location) {
            y = frameY + ( frameHeight - toast.getHeight() - GAP );
            x = frameX + ( frameWidth - toast.getWidth() - GAP );
        } else if (LEFT_BOTTOM == location) {
            y = frameY + ( frameHeight - toast.getHeight() - GAP );
            x = frameX + GAP;
        } else if (RIGHT_TOP == location) {
            y = frameY + GAP + DECORATE_GAP; /* Frame顶部装饰栏缘故 */
            x = frameX + ( frameWidth - toast.getWidth() - GAP );
        } else if (LEFT_TOP == location) {
            y = frameY + GAP + DECORATE_GAP; /* Frame顶部装饰栏缘故 */
            x = frameX + GAP;
        } else if (BOTTOM == location) {
            y = frameY + (frameHeight - toast.getHeight() - GAP * 2);
        } else if (TOP == location) {
            y = frameY + GAP + DECORATE_GAP;
        }

        toast.setLocation(x, y);
        show(toast, new FadeClose(toast), duration);
    }
    
}

