/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emm.v1.session.filters;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.session.vo.ClientSessionVO;
import com.emm.v1.utilities.EmmUtilities;
import com.google.common.base.Strings;
import com.mazdausa.mmg.business.iface.ApplicationBusIFace;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.filter.GenericFilterBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is the filter, which is responsible for Verifying whether the Request that is coming from the Client is VALID or NOT.
 * @author PankajB
 * @version 1.0
 */
public class TokenVerifierFilter extends GenericFilterBean {

    private static Logger logger = LoggerFactory.getLogger(TokenVerifierFilter.class);
    @Autowired
    ApplicationBusIFace applicationIFace;
    @Autowired
    EmmUtilities EmmUtilities;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        logger.info(">> Checking Verification of an Token for forlling URI : " + ((HttpServletRequest) request).getRequestURI());
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String USER_AGENT = null;

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            USER_AGENT = httpRequest.getHeader(ApplicationConstants.USER_AGENT);
            String givenToken = httpRequest.getHeader(ApplicationConstants.OAUTH_TOKEN_HEADER);
            HttpSession httpSession = httpRequest.getSession(false);
            ClientSessionVO clientSessionVO = null;
            if (httpSession != null && httpRequest.isRequestedSessionIdValid()) {

                logger.debug("SESSION ID = {} ", httpSession.getId());
                clientSessionVO = EMMConstants.getTOKENTABLE().get(httpSession.getId());
                logger.debug("HTTP SESSION IS NOT NULL & LOOKING FOR CELINT SESSION {} ", clientSessionVO);
            }
            givenToken = EmmUtilities.getDecryptedData(givenToken, USER_AGENT);

            logger.info("Session is VALID or not : {} CLIENTSESSION VO = {}", httpRequest.isRequestedSessionIdValid(), clientSessionVO);

            // also check for the User ID.
            if (clientSessionVO != null && givenToken.trim().equalsIgnoreCase(clientSessionVO.getToken())) {
                // Now the Request Can proceed, if the tocken Matches.
                filterChain.doFilter(request, response);


            } else if (clientSessionVO != null && givenToken.trim().equalsIgnoreCase(clientSessionVO.getToken()) == false) {
                // The TOKEN does not match, so we have to return with an BAD Request or FORBIDDEN RESPONSE CODE.
                logger.info("The Token Does not Match, so sending CODE " + HttpServletResponse.SC_FORBIDDEN + " to client");
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else if (httpRequest.isRequestedSessionIdValid() == false) {

                String token = EmmUtilities.getToken();
                httpSession = httpRequest.getSession(true);
                clientSessionVO = new ClientSessionVO();
                clientSessionVO.setRequestorsIp(httpRequest.getRemoteAddr());
                clientSessionVO.setToken(token);
                String guid = EmmUtilities.getDecryptedData(httpRequest.getHeader(ApplicationConstants.GUID_HEADER), USER_AGENT);
                token = EmmUtilities.getEncryptedData(token, USER_AGENT);

                // NOw Check whether this GUID EXISTS or not.
                ApplicationInstallationDetailsVO appInstallationDetails = applicationIFace.getApplicationInstallationDetails(guid.trim());
                // If It Exists then Simple pass down the Values.
                if (appInstallationDetails != null && Strings.isNullOrEmpty(appInstallationDetails.getMobileImei()) == false) {
                    clientSessionVO.setGuid(guid);
                    logger.debug("Applications details are not NULL, so setting GUID & TOKEN IN REQUEST HEADER");
                    EMMConstants.getTOKENTABLE().put(httpSession.getId(), clientSessionVO); // Put the TOKEN Here.
                    httpResponse.addHeader(ApplicationConstants.OAUTH_TOKEN_HEADER, token);
                    httpResponse.addHeader(ApplicationConstants.JSESSION_HEADER, EmmUtilities.getEncryptedData(httpSession.getId(), USER_AGENT));
                    httpResponse.setStatus(HttpServletResponse.SC_GONE);
                } else {
                    logger.error("GUID Details does not exists on the Server, so sending the BAD Request . ");
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
                appInstallationDetails = null;
                clientSessionVO = null;
                token = null;

            } else {
                logger.info("Sending the BAD Request to the Client. ");
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
