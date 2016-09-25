package com.finderlo.weixzz.utility.callbackListener;

import android.graphics.Bitmap;

/**
 * Created by Finderlo on 2016/8/3.
 */
public interface HttpLoadPicCallbackListener {
    void onComplete(Bitmap bitmap);
    void onError(Exception exception);
}
