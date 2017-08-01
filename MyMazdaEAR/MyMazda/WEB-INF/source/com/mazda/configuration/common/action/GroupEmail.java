/*package com.mazda.configuration.common.action;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mazda.common.utility.Utility;
import com.sun.mail.smtp.SMTPTransport;
class ApplicationConfig {
	static Properties properties 			= Utility.getProperties("env.properties");
	static String adminId 					= properties.getProperty("admin.id");
	static String adminEmail				= properties.getProperty("admin.pass");	
	
	 static final String EMAIL_SERVER  		= "gmail-smtp-msa.l.google.com";
	 static final String EMAIL_PROTOCOL  	= "smpts";
	 static final boolean EMAIL_DEBUG  		= true;
	 static final boolean EMAIL_SECURE  	= true;
	 static final String EMAIL_USERNAME  	= adminId;//yourusername
	 static final String EMAIL_PASSWORD  	= adminEmail;//yourpassword
	 static final String EMAIL_FROM  		= adminId;//<rick@.com>
	 static final String EMAIL_REPLYTO  	= adminId;//<rick@.com>
		
		
	}


public class GroupEmail {
	
		 
		 @SuppressWarnings("finally")
		public static synchronized String sendEmail(String to, String subject , String body) {
			 String mailhost = ApplicationConfig.EMAIL_SERVER;
			 //##("++++++++"+ApplicationConfig.EMAIL_DEBUG);		
			 String mailer 					= "GroovySend";
			 String protocol 				= ApplicationConfig.EMAIL_PROTOCOL;
			 boolean debug 					= ApplicationConfig.EMAIL_DEBUG;;
			 boolean auth 					= false;
			 boolean ssl 					= false;
			 boolean tls 					= false;
			 SMTPTransport t				= null;
			 String from 					= ApplicationConfig.EMAIL_FROM;
			 String replyto 				= ApplicationConfig.EMAIL_REPLYTO;
			 String cc 						= null;
			 //String[] bcc 					= to; 
			 String user 					= ApplicationConfig.EMAIL_USERNAME;
			 String password 				= ApplicationConfig.EMAIL_PASSWORD;

			  if (ApplicationConfig.EMAIL_SECURE)
			    {
			     auth = true;
			     ssl = true;
			     tls = true;
			    }

			    //##("sendEmail");
			   Properties props = System.getProperties();
			    //##("mailhost: " + mailhost);
			  props.put("mail.smtp.host", mailhost);

			    protocol = ssl ? "smtps" : "smtp";
			   if (auth)
			      {
			       //##("auth: true protocol: " + protocol);
			       props.put("mail." + protocol + ".auth", "true");
			      }
			   // Get a Session object
			   Session session = Session.getInstance(props, null);
			   if (debug) session.setDebug(true);

			   // construct the message
			   Message msg = new MimeMessage(session);

			    //##("from: " + from);
			    try {
			    	 msg.setFrom(new InternetAddress(from));
			    	 msg.setReplyTo(InternetAddress.parse(replyto, false));
			    	 for(int i=0; i<to.size(); i++)
			    	 {
			    		 msg.setRecipient(Message.RecipientType.TO,new InternetAddress((to.get(i)).toString())); // line no. 74
			    	 }
			    	 msg.setRecipient(Message.RecipientType.TO,new InternetAddress(to.toString()));
			    	 msg.setSubject(subject);
			    	 msg.setText(body);
					 msg.setHeader("X-Mailer", mailer);
					 //##("msg.setSendDate");
					 msg.setSentDate(new Date());
					 t= (SMTPTransport)session.getTransport(ssl ? "smtps" :	"smtp");
					   
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				 
			    //##("ssl: " + ssl);

			    if (tls) t.setStartTLS(true);  // MUST BE HERE to activate TLS
			    //##("getStartTLS: " + t.getStartTLS());

			   try
			      {
			    if (auth)
			          {
			    	System.out.print("+++++++++++++++");
			          //##("connecting with auth mailhost: " + mailhost +" user: " + user + " password: " + password);
			          System.out.print("--------------------");
			        t.connect(mailhost, user, password);
			        System.out.print("+++++++++++++++");
			        }
			    else{
			    	System.out.print("----------------------");
			        t.connect();
			        }
			    t.sendMessage(msg, msg.getAllRecipients());
			    System.out.print("=======================");
			      //##("\nMail was sent successfully.");
			      String a="email sent successfully!";
			      return a;
			      }
			    catch (Exception e)
			      {
			     //##("Send failed: " + e.toString());
			     }
			    finally
			      {
			      //##("Response: " + t.getLastServerResponse());
			      String v="Error sending email!";
			      return v;
			    }}
		 
		 
}


*/