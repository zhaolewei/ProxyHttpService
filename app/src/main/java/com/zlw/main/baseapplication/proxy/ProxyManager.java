package com.zlw.main.baseapplication.proxy;

import android.content.Context;

/**
 * 代理
 * 1.音乐播放代理，用于边听边下，缓存模块见 MusicCacheManager.class
 * Created by zlw on 2016/12/17.
 */

public class ProxyManager {

    public static final int PROXY_PORT = 8080;
    private static final String TAG = ProxyManager.class.getSimpleName();
    private static volatile ProxyManager instance = null;
    private MyWebServer mProxy;

    private ProxyManager(Context context) {
        mProxy = new MyWebServer(PROXY_PORT);
    }

    public static ProxyManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ProxyManager.class) {
                if (instance == null) {
                    instance = new ProxyManager(context);
                }
            }
        }
        return instance;
    }

    public static String getProxyAddress(String target, String mid) {
        return String.format(
                "http://127.0.0.1:%d?target=%s&mid=%s",
                PROXY_PORT,
                target,
                mid);
    }

    public void startProxy() {
        try {
            if (!mProxy.isAlive()) {
                mProxy.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopProxy() {
        if (null != mProxy) {
            mProxy.stop();
        }
    }
}
