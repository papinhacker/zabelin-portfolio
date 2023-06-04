//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.util;

public class CommonUtil {
    public CommonUtil() {
    }

    public static String filterInvalidChars(String msg) {
        if (TextUtil.hasValue(msg)) {
            msg = msg.replaceAll("[^\\n\\x20-\\x7e]", "");
        }

        return msg;
    }
}
