package toast;


import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 默认的Toast
 *  新版本改动：
 *      支持建造者模式
 *      UI圆角增大，使其更加圆润
 *      <strong>支持根据文本内容自适应调节大小</strong>
 *      <strong>跟随主窗口移动</strong>
 * @author callback
 * @version 1.0.1
 */
public class DefaultToast extends BaseToast
{

    private static final Font contentFont = new Font(Font.MONOSPACED, Font.PLAIN, 15);

    private static final int lineWrapperThreshold = 16; /* 默认15字符换行 */
    private static final int defaultLineHeight = 30; /* 默认行高为30px */

    private final JLabel contentArea = new JLabel();


    public DefaultToast(){ this(null, "", ToastStatus.INFO); }

    public DefaultToast(String text){ this(null, text, ToastStatus.INFO); }

    public DefaultToast(Frame owner, String text, ToastStatus status)
    {
        super(owner);

        /* 设置文本显示域样式 */
        autoAdjustSizeRefText(text); /* 根据文本自适应大小，并设置文本内容 */
        contentArea.setOpaque(true);
        contentArea.setFont(contentFont);
        contentArea.setBackground(status.background);
        contentArea.setForeground(status.foreground);
        contentArea.setHorizontalAlignment(SwingConstants.CENTER);
        /* 添加组件 */
        add(contentArea, BorderLayout.CENTER);
        autoAdjustSizeRefText(text);

//        /* 设置窗体边框圆角 */
//        AWTUtilities.setWindowShape(this,
//                new RoundRectangle2D.Double(0, 0, getWidth(),
//                        getHeight(), 30, 30
//                )
//        );
    }

    public DefaultToast setContent(String text)
    {
        autoAdjustSizeRefText(text);
        return this;
    }

    public DefaultToast setDimension(int width, int height)
    {
        setSize(width, height);
        return this;
    }

    public DefaultToast setContentFont(Font font)
    {
        contentArea.setFont(font);
        return this;
    }


    /**
     * 根据文本内容的长度，自适应调节大小，并设置文本
     *  使用HTML控制格式也换行
     * @param text 文本
     */
    private void autoAdjustSizeRefText(String text)
    {
        StringBuilder builder = new StringBuilder();
        char[] chars = Optional.ofNullable(text)
                .orElseGet(new Supplier<String>() {
					@Override
					public String get() {
						return "";
					}
				})
                .toCharArray();
        if (chars.length == 0) return;

        /* 计算行数 */
        builder.append("<html><body><p align=\"center\">"); /* 换行BUG FIXED */
        int lineCharacterCnt = 0, lineCnt = 1;
        for (char ch : chars) {
            builder.append(ch); lineCharacterCnt++;
            if ((lineCharacterCnt %= lineWrapperThreshold) == 0 ) lineCnt++;
        }
        builder.append("</p></body></html>");
        System.out.println(builder.toString());
        setSize(getWidth(), lineCnt * defaultLineHeight); /* 调整窗口大小 */
        contentArea.setText(builder.toString());
    }

}