package com.zk.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		
		
		//��ȡ�ͻ��˱����������ʱ��
		Cookie[] cookies=req.getCookies();//��ȡ�ͻ��˵�����cookie����
		for(int i=0;cookies!=null&&i<cookies.length;i++)
		{
			if("lastAccessTime".equals(cookies[i].getName()))//�жϵ�ǰcookie�е�name�Ƿ�����Ҫ��cookie
			{
				long l=Long.parseLong(cookies[i].getValue());//�������Ҫ��cookie,���cookie���
				out.print("���������ʱ����:"+new Date(l).toLocaleString());//yyyy-MM-dd
			}
		}
		//ɾ��cookie��qq������ر���������cookie��ʧ
		out.print("<a href='"+req.getContextPath()+"/servlet/clear'>clear</a>");
		//����Cookie,������Ϣд�ؿͻ���
		Cookie ck=new Cookie("lastAccessTime", System.currentTimeMillis()+"");
		//����cookie����Чʱ�䣬��λ����
		ck.setMaxAge(60*1);//����ʱ��Ϊ5����
		//��cookie��Ϣд�ؿͻ��������
		resp.addCookie(ck);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

}
