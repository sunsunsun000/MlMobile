package ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zw.mlmobile.R;

/**
 * Created by zhongwang on 2018/1/24.
 */

public class SeetingItemView extends LinearLayout {
    private TextView tvItem;
    public SeetingItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.myview_seetitem, this);
        tvItem = findViewById(R.id.tvItem);
    }
public void setItemName(String itemName){
        tvItem.setText(itemName);
}
    public void setItemName(int resId){
        tvItem.setText(resId);
    }
    
    
}
