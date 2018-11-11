package com.zk.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo2 extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//��ȡ�ͻ��˱����������ʱ��
		Cookie[] cookies=request.getCookies();//��ȡ�ͻ��˵�����cookie����
		for(int i=0;cookies!=null&&i<cookies.length;i++)
		{
			if("lastAccessTime".equals(cookies[i].getName()))//�жϵ�ǰcookie�е�name�Ƿ�����Ҫ��cookie
			{
				long l=Long.parseLong(cookies[i].getValue());//�������Ҫ��cookie,���cookie���
				out.print("���������ʱ����:"+new Date(l).toLocaleString());//yyyy-MM-dd
			}
		}
		//����Cookie,������Ϣд�ؿͻ���
		Cookie ck=new Cookie("lastAccessTime", System.currentTimeMillis()+"");
		//����cookie����Чʱ�䣬��λ����
		ck.setMaxAge(60*1);//����ʱ��Ϊ5����
		//����cookies��path
		//ck.setPath(request.getContextPath());
		//ck.setPath("/Servet_009");
		ck.setPath("/");
		//��cookie��Ϣд�ؿͻ��������
		response.addCookie(ck);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
