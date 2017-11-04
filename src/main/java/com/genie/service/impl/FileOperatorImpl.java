package com.genie.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.genie.service.FileOperator;

/*author:Genie
 *date:2017年3月15日
 *文件操作实现类
**/
@Service("fileOperator")
public class FileOperatorImpl implements FileOperator {

	@Override
	public void upload(String uploadPath, HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> fileNamesIter = multiRequest.getFileNames();

			while (fileNamesIter.hasNext()) {
				MultipartFile multiFile = multiRequest.getFile((String) fileNamesIter.next());
				if (multiFile != null) {
					String fileName = multiFile.getOriginalFilename();
					String path;
					if (uploadPath == null) {
						path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "files"
								+ File.separator;
					} else {
						path = uploadPath;
					}
					File localFile = new File(path + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName);
					
					try {
						multiFile.transferTo(localFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			System.out.println("upload finished.");
		} else {
			System.out.println("upload faild.");
		}
		
	}

	@Override
	public void download(String fileName, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		try {
			/*String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "files";*/
			String path = "D:/Server/filesForTest/";
			InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));

			OutputStream outputStream = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;

			while ((length = inputStream.read(b)) > 0) {
				outputStream.write(b, 0, length);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("download finished.");
	}

}
