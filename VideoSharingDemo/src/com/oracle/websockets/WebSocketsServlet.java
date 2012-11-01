
package com.oracle.websockets;

import com.sun.grizzly.websockets.WebSocketEngine;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WebSocketsServlet extends HttpServlet {

    /**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;
	
	private final VideoSharingApplication app = new VideoSharingApplication();

    @Override
    public void init(ServletConfig config) throws ServletException {
        WebSocketEngine.getEngine().register(app);
    }

}
