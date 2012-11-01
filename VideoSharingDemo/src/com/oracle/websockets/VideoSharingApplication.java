
package com.oracle.websockets;

import java.util.logging.Logger;

import com.sun.grizzly.tcp.Request;
import com.sun.grizzly.util.LoggerUtils;
import com.sun.grizzly.websockets.ProtocolHandler;
import com.sun.grizzly.websockets.WebSocket;
import com.sun.grizzly.websockets.WebSocketApplication;
import com.sun.grizzly.websockets.WebSocketListener;

/**
 * VideoSharingApplication class.
 *
 * @author Santiago.PericasGeertsen@oracle.com
 */
public class VideoSharingApplication extends WebSocketApplication {

	private static final Logger logger = LoggerUtils.getLogger();


	@Override
	public WebSocket createWebSocket(ProtocolHandler handler, WebSocketListener... listeners) {
        return new VideoSharingWebSocket(handler, listeners);
    }

    @Override
	public void onMessage(WebSocket socket, String text) {
        for (WebSocket webSocket : getWebSockets()) {
            if (socket != webSocket) {
                webSocket.send(text);
            }
        }
	}

    @Override
	public boolean isApplicationRequest(Request request) {
    	String uri = request.requestURI().toString();
    	logger.info("isApp Req: " + uri);
    	logger.info("isApp Req: " + uri.endsWith("/videosharing"));
    	return uri.endsWith("/videosharing");	
    }
}
