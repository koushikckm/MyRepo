/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.controllers;

import com.mazdausa.mmg.business.iface.UserBusIFace;
import com.mazdausa.mmg.db.dao.iface.ApplicationDetailsDaoIFace;
import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerSearchResultVO;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This is a Simple Test ControllerI
 * @author pankajb
 */
@Component
@Path("/test")
public class TestController implements MessageSourceAware {

    private MessageSource messageSource;
    @Autowired
    ApplicationDetailsDaoIFace applicationDetailsDao;
    @Autowired
    UserBusIFace appUserDao;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    private Locale clientLocale = Locale.ENGLISH;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Job job;
    @Autowired
    JobLauncher jobLauncher;

    @GET
    @Produces("text/plain")
    @Path("/{categoryId}")
    public String returnText(@Context HttpServletRequest request, @Context HttpServletResponse response,
            @HeaderParam("user-agent") String userAgent/*,
            @PathParam("categoryId") String cateogryId*/) {

        System.out.println("Heloooooooooooooooo " + System.currentTimeMillis());
        ApplicationDetailsVO app = new ApplicationDetailsVO();
        app.setAddedBy("pankaj");
        app.setAddedDate(new Date());
        app.setAppDeleted(false);
        app.setAppFeatureList("pankasdfsd");
        app.setAppName("MMG");
        app.setAppPath("ww.yahoo.com");
        app.setClientOsMaxVersion("2.3");
        app.setClientOsMinVersion("1.1");
        app.setClientOsType("MAC");
        app.setIp("192");
        app.setAppVersion("1.0");
        app.setUpgradedVersion(false);

        //System.out.println("Rseult is = "+applicationDetailsDao.addApplicationDetails(app));

        RestDealerSearchResultVO searchResultVO = restTemplate.getForObject("http://www.mazdausa.com/webservices/mx/dealerLocateByZipLimited/{zipcode}", RestDealerSearchResultVO.class, "10001");
        System.out.println(searchResultVO.getDealerCount() + " is the Number of Dealer i Found ");
        return "Pankaj Bhatt";

    }

    @GET
    //@Path("/user/{id}{format:(/format/[^/]+?)}{encoding:(/encoding/[^/]+?)}")
    //@Path("/user/{id}{format: (/format/[^/]+?)}{encoding: (/encoding/[^/]+?)}")
    //@Path("/user{format : (/format)?}{encoding : (/encoding)?}")
    @Path("/user/{id}{format:(/format/[^/]+?)?}{encoding:(/encoding/[^/]+?)?}{name:(/[^/]+?)?}{class:(/[^/]+?)?}")
    @Produces("text/html")
    public String test(@PathParam("id") String id, @PathParam("format") String format, @PathParam("encoding") String encoding,
            @PathParam("name") String name,
            @PathParam("class") String classP) {
        String result = "id: " + id;
        if (format != null) {
            result += " Format: " + format;
        }
        if (encoding != null) {
            result += " Encoding: " + encoding;
        }

        result += " :" + name;
        result += " :" + classP;

        return result;
    }

    @GET
    @Produces("text/plain")
    @Path("/batch")
    public String testBatch(@Context HttpServletRequest request, @Context HttpServletResponse response,
            @HeaderParam("user-agent") String userAgent) {

        String result = "";

        /* List<UserVO> listOfUsers = appUserDao.getAllActiveUsers(0, 0);
        for (UserVO userVO : listOfUsers) {

        System.out.println("User ID =  " + userVO.getMailId() + " vehicles = " );
        if(userVO.getVehicles()!=null)
        for (UserVehicleVO vehicleVO : userVO.getVehicles()) {
        System.out.println("vehicle VIN = " + vehicleVO.getVehicleVIN());
        }
        } */




        try {

            JobParametersBuilder builder = new JobParametersBuilder();
            builder.addLong("currentTime", new Long(System.currentTimeMillis()));
            jobLauncher.run(job, builder.toJobParameters());


        } catch (JobExecutionAlreadyRunningException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JobRestartException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JobInstanceAlreadyCompleteException ex) {

            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }


        return result;



    }

    @GET
    @Produces("text/plain")
    @Path("/apns/{token}/{info}")
    public String testBatch(@Context HttpServletRequest request, @Context HttpServletResponse response,
            @HeaderParam("user-agent") String userAgent,
            @PathParam("token") String token,
            @PathParam("info") String info) {

        String result = "";

        try {

            // The Below Line Code needs to be modified, later on, when we have the certificate.
            ApnsService service = APNS.newService().withCert("g:\\APNS\\Certificates.p12", "interrait").withSandboxDestination().build();
            String payload = APNS.newPayload().alertBody(info).build();
            service.push(token, payload);
            result = "Data Successfully sent.";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "An Exception Occured, please try again.";
        }

        return result;
    }
}
