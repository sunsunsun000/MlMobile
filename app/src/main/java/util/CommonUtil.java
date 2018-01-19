package util;

import android.widget.EditText;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class CommonUtil {
    public final static String FIRSTTAG="FITSTTAG";
    public static boolean isEmpty(EditText editText){
        return editText.getEditableText().toString().trim().equals("");
    }
}
