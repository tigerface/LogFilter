package toast;

import java.awt.*;

/**
 * ToastStatus 枚举类
 *  - INFO #D9EDF7
 *  - ERROR #F2DEDE
 *  - SUCCESS #DFF0D8
 */
public enum ToastStatus
{
    INFO(
            new Color(0xD9, 0xED, 0xF7),
            Color.black
    ),
    ERROR(
            new Color(0xF2, 0xDE, 0xDE),
            Color.black
    ),
    SUCCESS(
            new Color(0xDF, 0xF0, 0xD8),
            Color.black
    );

    public final Color background;
    public final Color foreground;

    ToastStatus(Color bg, Color fg) {background = bg; foreground = fg;}


}