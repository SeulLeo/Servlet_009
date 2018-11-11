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
		
		
		//获取客户端保存的最后访问时间
		Cookie[] cookies=req.getCookies();//获取客户端的所有cookie对象
		for(int i=0;cookies!=null&&i<cookies.length;i++)
		{
			if("lastAccessTime".equals(cookies[i].getName()))//判断当前cookie中的name是否是想要的cookie
			{
				long l=Long.parseLong(cookies[i].getValue());//如果是想要的cookie,则把cookie输出
				out.print("你的最后访问时间是:"+new Date(l).toLocaleString());//yyyy-MM-dd
			}
		}
		//删除cookie后qq浏览器关闭重启，则cookie消失
		out.print("<a href='"+req.getContextPath()+"/servlet/clear'>clear</a>");
		//创建Cookie,并把信息写回客户端
		Cookie ck=new Cookie("lastAccessTime", System.currentTimeMillis()+"");
		//设置cookie的有效时间，单位是秒
		ck.setMaxAge(60*1);//保存时间为5分钟
		//把cookie信息写回客户端浏览器
		resp.addCookie(ck);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

}
