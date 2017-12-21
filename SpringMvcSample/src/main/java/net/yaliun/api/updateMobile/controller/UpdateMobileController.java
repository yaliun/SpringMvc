package net.yaliun.api.updateMobile.controller;

import javax.validation.Valid;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yaliun.api.updateMobile.model.UpdateMobileReqVO;
import net.yaliun.api.updateMobile.model.UpdateMobileResVO;

@RestController
public class UpdateMobileController {

	@RequestMapping(value="/api/updateMobile", method=RequestMethod.POST)
	public UpdateMobileResVO updateMobile(@RequestBody @Valid UpdateMobileReqVO reqVo){

		UpdateMobileResVO resVo = new UpdateMobileResVO();
		resVo.setSessionId("session12345");
		resVo.setMsisdn("821043400369");
		
		return resVo;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public void method(){
		System.out.println("MethodArgumentNotValidException");
	}
}
