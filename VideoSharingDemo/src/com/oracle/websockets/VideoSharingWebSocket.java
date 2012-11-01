
package com.oracle.websockets;

import com.sun.grizzly.websockets.DefaultWebSocket;
import com.sun.grizzly.websockets.ProtocolHandler;
import com.sun.grizzly.websockets.WebSocketListener;

/**
 * VideoSharingWebSocket class.
 * 
 * @author Santiago.PericasGeertsen@oracle.com
 */
public class VideoSharingWebSocket extends DefaultWebSocket {

    public VideoSharingWebSocket(ProtocolHandler handler, WebSocketListener... listeners) {
        super(handler, listeners);
    }

}
