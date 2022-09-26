package toast;

import java.util.concurrent.TimeUnit;

/**
 * Toast,线性淡出式关闭
 *
 */
public class FadeClose extends ToastClose
{
    private static final int defaultLinearSteps = 30;
    private static final long defaultLinearTimeGap = 10;

    private BaseToast instance = null;

    /* linearSteps x linearTimeGap = fadeout time used */
    private int linearSteps = defaultLinearSteps; /* Toast淡出式关闭的步数 */
    private long linearTimeGap = defaultLinearTimeGap; /* Toast淡出式关闭的步数的时间间隔(ms) */

    public FadeClose(BaseToast toast)
    {
        instance = toast;
    }

    public FadeClose(BaseToast toast, int steps, int timeGap)
    {
        instance = toast;
        linearSteps = steps;
        linearTimeGap = timeGap;
    }

    @Override
    public void run()
    {
        if (instance == null) return;

        float s = instance.getOpacity() / linearSteps;

        for (int i = 0; i < linearSteps; ++i) {
            instance.setOpacity(instance.getOpacity() - s);
            try {
                TimeUnit.MILLISECONDS.sleep(linearTimeGap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        instance.dispose();

    }
}