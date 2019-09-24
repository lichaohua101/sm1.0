package com.entor.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entor.dao.StudentDao;
import com.entor.dao.impl.StudentDaoImpl;
import com.entor.entity.Student;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 下载文件
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//导出数据时候生成的临时文件存放路径
		String path = request.getServletContext().getRealPath("/download");
		System.out.println("临时文件存放路径"+path);
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + ".xls";
		//上传文件的路径地址
		File downloadFile = new File(path+"/"+fileName);
		//创建工作簿，指定文件路径
		WritableWorkbook wwb = Workbook.createWorkbook(downloadFile);
		//创建工作表
		WritableSheet sheet = wwb.createSheet("学生列表",0);
		//获取数据库数据
		StudentDao dao = new StudentDaoImpl();
		List<Student> list = dao.queryAll();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//excel表头内容
			String[] title = {"编号","姓名","性别","年龄","账号","密码","出生日期","创建时间"};
			for(int i=0;i<title.length;i++) {
				sheet.addCell(new Label(i, 0, title[i]));
			}
			//excel数据内容
			for(int i=0;i<list.size();i++) {
				Student s = list.get(i);
				sheet.addCell(new Label(0, i+1, s.getId()+""));
				sheet.addCell(new Label(1, i+1, s.getName()));
				sheet.addCell(new Label(2, i+1, s.getSex()+""));
				sheet.addCell(new Label(3, i+1, s.getAge()+""));
				sheet.addCell(new Label(4, i+1, s.getUsername()));
				sheet.addCell(new Label(5, i+1, s.getPassword()));
				sheet.addCell(new Label(6, i+1, sdf.format(s.getBrithday())));
				sheet.addCell(new Label(7, i+1, sdf2.format(s.getCreateTime())));
			}
			//往文件里写入数据
			wwb.write();
			//关闭工作簿
			wwb.close();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*************************************以下代码是下载文件************************************/
		//根据下载的文件打开输入流对象
		FileInputStream fis = new FileInputStream(downloadFile);
		 //设置返回消息头部信息
		response.setContentType("application/force-download");
		//设置下载文件大小
		response.setHeader("Content-Length",String.valueOf(fis.available()));
		//设置文件下载框
		response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
		//通过response对象打开文件输出流
		OutputStream os = response.getOutputStream();
		byte[] b = new byte[1024];
		int len;
		while((len=fis.read(b))!=-1) {
			os.write(b, 0, len);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
