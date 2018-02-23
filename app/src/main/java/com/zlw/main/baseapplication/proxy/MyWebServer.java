package com.zlw.main.baseapplication.proxy;

import com.zlw.main.baseapplication.utils.Logger;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by admin on 2018/2/23.
 */

public class MyWebServer extends NanoHTTPD {
    private static final String TAG = MyWebServer.class.getSimpleName();

    public MyWebServer(int port) {
        super(port);
        Logger.d(TAG, "MyWebServer........");
    }

    public MyWebServer(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Logger.d(TAG, "serve........");
        Map<String, String> parms = session.getParms();
        Logger.d(TAG, "session getParms：%s ", parms);


        String targetUrl = parms.get("target");
        String type = parms.get("type");

        Logger.d(TAG, "targetUrl：%s ", targetUrl);
        Logger.d(TAG, "type：%s ", type);

        String msg = String.format("<html><body> <h1>Hello server</h1>\n <p>Test Proxy: </p>\n <p>targetUrl: %s</p>\n <p>type: %s</p>\n </body></html>\n", targetUrl, type);
        //TODO: 请求targetUrl
        return newFixedLengthResponse(msg);
    }
}
