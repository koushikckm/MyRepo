/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.impl;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailsUpdateRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserGetVehiclesRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleServiceHistoryRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.ClosedRecallsResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailsUpdateResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserGetVehiclesResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.service.soap.iface.UserWebServiceIFace;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserGetEgiftsRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.GetEgiftsDetailsVehicleVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserGetEgiftsResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

/**
 * This is an Implementation class of UserWebServiceIFace
 * @author PankajB
 * @version 1.0
 */
@Component
public class UserWebServiceImpl implements UserWebServiceIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserWebServiceImpl.class);
    @Autowired
    WebServiceTemplate webServiceTemplate;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    EMMConstants emmConstants;
    @Autowired
    RestTemplate restTemplate;
    
    
	public SOAPUserGetEgiftsResponseVO getUserEgifts(
			SOAPUserGetEgiftsRequestVO getEgiftsRequestVO) {
        logger.debug(">> Entering {} getUserEgifts() with CustomerID={}", this.getClass(), getEgiftsRequestVO.getCustomerId());
		
        SOAPUserGetEgiftsResponseVO getEgiftResponseVO = new SOAPUserGetEgiftsResponseVO();
        Object object = mmgUtilities.getWebServiceHeaderElement(appConstants.SERVICE_SOAP_GETEGIFTS_ACTION.trim(), appConstants);
        
        try {
            getEgiftResponseVO = (SOAPUserGetEgiftsResponseVO) webServiceTemplate.marshalSendAndReceive(appConstants.SERVICE_SOAP_GETEGIFTS.trim(), getEgiftsRequestVO, (WebServiceMessageCallback) object);

            List<GetEgiftsDetailsVehicleVO> listOfEgifts = getEgiftResponseVO.getEgifts().getEgiftsDetail().getEgiftList();

            getEgiftResponseVO.getEgifts().getEgiftsDetail().setCount(listOfEgifts.size());
            getEgiftResponseVO.getEgifts().getEgiftsDetail().setEgiftList(listOfEgifts);
            
            listOfEgifts = null;
            
        } catch (Exception ex) {
            logger.error("An Exception has occred, while getting the egift details of User Vehicles. ", ex);
        }
        
        logger.debug("<< Exiting getUserEgifts() with response = {} ", getEgiftResponseVO);
		return getEgiftResponseVO;
	}


    /*
    class MsgInterceptor implements ClientInterceptor {

        public boolean handleRequest(MessageContext mc) throws WebServiceClientException {
            return false;
        }

        public boolean handleResponse(MessageContext mc) throws WebServiceClientException {
            System.out.println("Start Handling the RESPONSE ");
          


            try {
                DOMResult rs = (DOMResult) mc.getResponse().getPayloadResult();
                System.out.println("Class = " + mc.getResponse().getPayloadResult().getClass());
                TransformerFactory transformerFactory =
                        TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource source = new DOMSource(rs.getNode());

                StreamResult result = new StreamResult(System.out);
                transformer.transform(source, result);
            } catch (Exception rEx) {

                System.out.println("Exception OCCURED, " + rEx.getMessage());
                rEx.printStackTrace();
            }
            System.out.println("******** COMPLETION *************** ");
            return true;
        }

        public boolean handleFault(MessageContext mc) throws WebServiceClientException {
            return false;
        }
    }  */
}
