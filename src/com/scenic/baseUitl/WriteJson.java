package com.scenic.baseUitl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

@Repository
public class WriteJson {
	public static void writeToClient(String jsonData) {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");
		String callbackFunName = request.getParameter("callback");// 得到js函数名称
		try {
			response.getWriter().write(callbackFunName + "(" + jsonData + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
