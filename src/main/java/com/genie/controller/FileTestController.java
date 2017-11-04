package com.genie.controller;
/*author:Genie
 *date:2017年3月19日
**/

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.genie.service.FileOperator;

@Controller
@RequestMapping(value = "/fileTest")
public class FileTestController {

	@Autowired
	FileOperator fileOperator;

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String upload(HttpServletRequestWrapper request) {
		System.out.println("test upload");
		fileOperator.upload("D:/Server/filesForTest/", request);
		return "uploadSuccess";
	}

	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public void download(@RequestParam(value = "fileName") String fileName, HttpServletResponse response) {
		System.out.println("test download");
		fileOperator.download(fileName, response);
		
	}
}
