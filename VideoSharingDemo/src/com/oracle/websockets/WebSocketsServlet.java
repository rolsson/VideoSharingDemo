
package com.oracle.websockets;

import com.sun.grizzly.websockets.WebSocketEngine;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WebSocketsServlet extends HttpServlet {

    private final VideoSharingApplication app = new VideoSharingApplication();

    @Override
    public void init(ServletConfig config) throws ServletException {
        WebSocketEngine.getEngine().register(
            config.getServletContext().getContextPath() + "/videosharing", app);
    }

}
