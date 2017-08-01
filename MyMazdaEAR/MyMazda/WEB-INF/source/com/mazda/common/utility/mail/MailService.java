//package  com.mazda.common.utility.mail;
//
//import java.util.Properties;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.SendFailedException;
//import javax.mail.Session;
//import javax.mail.Store;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import com.mazda.common.utility.PropertyFileReader;
///**
// * @since 30-11-2012
// * @version 1.0
// * mail service to be used in the applications
// *
// */
//
//public class MailService {
//	public static MailService mailService = null;
//	public MailService(){		
//	}
//	
//	// based on double check mechanism.
//	public static MailService getInstance(){		
//			if(mailService == null){
//				synchronized(MailService.class){
//					if(mailService == null){
//						mailService = new MailService();
//					}
//				}
//			}
//		return mailService;
//	}
//	
//	
//	public Object clone() throws CloneNotSupportedException {
//		throw new CloneNotSupportedException();
//	}
//
//	
//	
//	/**
//	 * @author Pradeep.Sharma
//	 * @since 30-11-2012
//	 * @version 1.0
//	 * This function is used to create javax.mail.Session.    * 
//	 * @return javax.mail.Session;
//	 */
//	private Session getSession(Authenticator auth) {
//		Session session 			= null;
//		String host 				= null;
//		String mailSendingProtocol 	= null;
//		String mailSendingPort 		= null;
//		String mailReceivingProtocol= null;
//		Properties properties 		= null;
//		String mailReceivingPort 	= null;
//		properties 					= System.getProperties();
//		mailReceivingPort 			= PropertyFileReader.getProperty("mail.receiving.port");
//		mailReceivingProtocol 		= PropertyFileReader.getProperty("mail.receiving.protocol");		
//		mailSendingProtocol 		= PropertyFileReader.getProperty("mail.sending.protocol");
//		mailSendingPort 			= PropertyFileReader.getProperty("mail.sending.port");
//		host 						= PropertyFileReader.getProperty("mail.server.host");
//		properties.put("mail.store.protocol", mailReceivingProtocol);
//		properties.put("mail.imap.port", mailReceivingPort);
//		properties.put("mail.transport.protocol", mailSendingProtocol);
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.port", mailSendingPort);
//		session = Session.getInstance(properties, auth);
//		return session;
//	}
//	
//	public static void main(String[] args){
//		MailService mailService = new MailService();
//		MailTO mailTO = new MailTO();
//		mailTO.setTo("pradeep@saksham.com");
//		mailTO.setSubject("test mail");
//		mailTO.setBody("test");
//		try {
//			mailService.send(mailTO);
//		} catch (SendFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} 
//		private class SMTPAuthenticator extends javax.mail.Authenticator {
//        public PasswordAuthentication getPasswordAuthentication() {
//           String username = PropertyFileReader.getProperty("mail.add.admin");
//           String password = PropertyFileReader.getProperty("mail.pass.admin");;
//           return new PasswordAuthentication(username, password);
//        }
//    }
//	
//	 
//	/**
//	 * @author Pradeep.Sharma
//	 * @since 30-11-2012
//	 * @version 1.0
//	 * This function is used to create javax.mail.Store object by using 
//	 * 		  getSession() method of this class.
//	 * @return javax.mail.Store;
//	 */
//	public Store getStore(Session session) {
//		Store store = null;
//		String retrievingProtocol = null;
//		try {
//			retrievingProtocol = PropertyFileReader.getProperty("mail.receiving.protocol");
//			store = session.getStore("imap");
//		} catch (Exception e) {
//			//##(e);
//		}
//		return store;
//
//	}
//	
//	/**
//	 * @author Pradeep.Sharma
//	 * @since 30-11-2012
//	 * @version 1.0
//	 * MailVO comes as parameter to this function which contain the information of an email like subject, body, 
//	 * 		   comma separated email addresses.
//	 * @param mailVOList
//	 * @throws MessagingException
//	 * @throws SendFailedException
//	 */
//	public void send(MailTO mailTO)  throws MessagingException, SendFailedException{
//		Message msg 			= null;
//		InternetAddress[] to 	= null;		
//		Session session 		= null;		
//		String username			= null;		
//		String strTo			= null;
//		String subject			= null;
//		String message			= null;		
//		String messageFormat	= null;	
//		Authenticator auth		= null;
//		String separator 		= System.getProperty("file.separator");		
//		messageFormat 	= "text/html";
//		auth = new SMTPAuthenticator();
//		session = getSession(auth);
//		session.setDebug(false);
//		msg = new MimeMessage(session);	
//		
//		username = PropertyFileReader.getProperty("mail.add.admin");
//		if(mailTO != null){			
//			strTo 	= mailTO.getTo();			
//			subject = mailTO.getSubject();
//			message	= mailTO.getBody();
//			if (strTo != null && !"".equals(strTo)) {
//				to = InternetAddress.parse(strTo.trim());
//				msg.addRecipients(Message.RecipientType.TO, to);
//			}
//			if (subject != null && "".equals(subject)) {
//				msg.setSubject("[none]");
//			}
//			else {
//				msg.setSubject(subject);
//			}
//			msg.setContent(mailTO.getBody(), messageFormat);
//			msg.setSentDate(new java.util.Date());
//			msg.setFrom(new InternetAddress(username.trim()));
//			Transport.send(msg);
//		}
//	}
//
//	
//}
