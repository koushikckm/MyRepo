package  com.mazda.common.utility.mail;

import java.io.Serializable;

public class MailTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Subject and body will be set by the caller of the mail service. It can be
	 * arabic or english. The Layer will going to consume mail Service will be responsible to 
	 * set the subject and body in vendor specifc language. This is the reason, I haven't 
	 * kept subject_en or subject_ar in MailVO.
	 */
	private String subject;
	private String body;
	private String to;
	
	private Long dataContainerId;
	
	public MailTO(){
		
	}
	public MailTO(String subject, String body, String to ){
		this.subject 	= subject;
		this.body 		= body;
		this.to 		= to;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public Long getDataContainerId() {
		return dataContainerId;
	}
	public void setDataContainerId(Long dataContainerId) {
		this.dataContainerId = dataContainerId;
	}

}
