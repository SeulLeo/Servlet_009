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
		//获取客户端保存的最后访问时间
		Cookie[] cookies=request.getCookies();//获取客户端的所有cookie对象
		for(int i=0;cookies!=null&&i<cookies.length;i++)
		{
			if("lastAccessTime".equals(cookies[i].getName()))//判断当前cookie中的name是否是想要的cookie
			{
				long l=Long.parseLong(cookies[i].getValue());//如果是想要的cookie,则把cookie输出
				out.print("你的最后访问时间是:"+new Date(l).toLocaleString());//yyyy-MM-dd
			}
		}
		//创建Cookie,并把信息写回客户端
		Cookie ck=new Cookie("lastAccessTime", System.currentTimeMillis()+"");
		//设置cookie的有效时间，单位是秒
		ck.setMaxAge(60*1);//保存时间为5分钟
		//设置cookies的path
		//ck.setPath(request.getContextPath());
		//ck.setPath("/Servet_009");
		ck.setPath("/");
		//把cookie信息写回客户端浏览器
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
