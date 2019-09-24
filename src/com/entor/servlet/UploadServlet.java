package com.entor.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.entor.dao.StudentDao;
import com.entor.dao.impl.StudentDaoImpl;
import com.entor.entity.Student;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取服务器存放上传文件的路径地址
		String path = request.getServletContext().getRealPath("/upload");
		File file = new File(path);
		//路径不存在则创建路径
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("服务器上传文件存放的路径地址："+path);
		//获取文件上传表单中name属性对应的值
		Part part = request.getPart("file");
		//获取上传文件名称
		String fileName = part.getSubmittedFileName();
		//获取上传文件类型
		String contentType = part.getContentType();
		//获取上传文件大小
		long size = part.getSize();                                       
		System.out.println("文件名称："+fileName);
		System.out.println("文件类型："+contentType);
		System.out.println("文件大小："+size);
		//上传文件后缀
		String regx = fileName.substring(fileName.lastIndexOf("."));
		//通过时间戳生成新的文件名，避免上传文件重名
		String newFileName = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+regx;
		//上传文件的路径地址
		String uploadPath = path+"/"+newFileName;
		//上传文件
		part.write(uploadPath);
		
		/*********************************以下代码是excel文件内容读取************************************/
		try {
			//读取excel文件内容
			Workbook wb = Workbook.getWorkbook(new File(uploadPath));
			Sheet sheet = wb.getSheet(0);
			int rows = sheet.getRows();
			List<Student> list = new ArrayList<>();
			//从第二行开始读取，第一行表头内容跳过
			for(int i=1;i<rows;i++) {
				Student s = new Student();
				s.setName(sheet.getCell(0, i).getContents());
				s.setSex(Integer.parseInt(sheet.getCell(1, i).getContents()));
				s.setAge(Integer.parseInt(sheet.getCell(2, i).getContents()));
				s.setUsername(sheet.getCell(3, i).getContents());
				s.setPassword(sheet.getCell(4, i).getContents());
				s.setBrithday(((DateCell)sheet.getCell(5, i)).getDate());
				list.add(s);
			}
			wb.close();
			//批量保存数据
			StudentDao dao = new StudentDaoImpl();
			dao.addMore(list);
			//请求转发到列表页面
			request.getRequestDispatcher("/StudentServlet?opType=queryByPage").forward(request, response);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
