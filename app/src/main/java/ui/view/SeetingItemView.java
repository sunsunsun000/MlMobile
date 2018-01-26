package ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zw.mlmobile.R;

/**
 * Created by zhongwang on 2018/1/24.
 */

public class SeetingItemView extends LinearLayout {
    private RelativeLayout topView,bottomView;
    private TextView tvItem;
    public SeetingItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.myview_seetitem, this);
        tvItem = findViewById(R.id.tvItem);
        topView = findViewById(R.id.topView);
        bottomView = findViewById(R.id.bottomView);


    }
public void setItemName(String itemName){
        tvItem.setText(itemName);
}
    public void setItemName(int resId){
        tvItem.setText(resId);
    }
    public void setTopViewVisable(boolean isVisable){
        if(isVisable){
            topView.setVisibility(VISIBLE);
        }else{
            topView.setVisibility(GONE);
        }
    }
    public void setBottomViewVisable(boolean isVisable){
        if(isVisable){
            bottomView.setVisibility(VISIBLE);
        }else{
            bottomView.setVisibility(GONE);
        }
    }
}
