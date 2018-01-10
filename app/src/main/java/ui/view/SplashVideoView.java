package ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class SplashVideoView extends VideoView{
    public SplashVideoView(Context context) {
        super(context);
    }

    public SplashVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplashVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public SplashVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    /**
     * 播放视频
     *
     * @param uri 播放地址
     */
    public void playVideo(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri can not be null");
        }
        /**设置播放路径**/
        setVideoURI(uri);
        /**开始播放**/
        start();
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                /**设置循环播放**/
                mp.setLooping(true);
            }
        });
        setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
    }
}
