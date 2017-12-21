package net.yaliun.api.updateMobile.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="UpdateMobileResponse")
@XmlAccessorType(XmlAccessType.FIELD)

public class UpdateMobileResVO {
	
	@XmlElement(name="session-id")
	private String sessionId;
	
	private String msisdn;
}
