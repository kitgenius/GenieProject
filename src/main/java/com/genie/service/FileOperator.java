package com.genie.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*author:Genie
 *date:2017年3月12日
 *文件操作接口
**/
public interface FileOperator {
	//实现上传、下载
	void upload(String uploadPath,HttpServletRequest request);
	void download(String fileName,HttpServletResponse response);
}
