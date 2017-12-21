package net.yaliun.api.updateMobile.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
@XmlRootElement(name="UpdateMobileRequest")
@XmlAccessorType(XmlAccessType.FIELD)

public class UpdateMobileReqVO {
	
	@XmlElement(name="session-id")
	@NotBlank
	private String sessionId;
}
