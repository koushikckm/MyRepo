/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emm.v1.session.listeners;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.session.vo.ClientSessionVO;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the Listener class, which will receive the Notifications once the Session has been created or the
 * Session has been timed out
 * @author PankajB
 */
public class ClientHttpSessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(ClientHttpSessionListener.class);

    /**
     * This is the function which gets Notified once a new Session is being created.
     * @param sessionEvent
     */
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        // Nothing Needs to be done Here.
    }

    /**
     * This is the function which is being notified, once the Session has been destroyed by the Servlet COntainer.
     * @param sessionEvent
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {

        HttpSession session = sessionEvent.getSession();
        // Find out the COrressponding Entry in the HashTable and delete it.
        ClientSessionVO clientSessionVO = EMMConstants.getTOKENTABLE().remove(session.getId());
        if (clientSessionVO != null) {
            logger.debug("Session TimeOut: GUID ={}  Session ID={}", new Object[]{clientSessionVO.getGuid(), session.getId()});
        }
        clientSessionVO = null;
        session = null;

    }
}
