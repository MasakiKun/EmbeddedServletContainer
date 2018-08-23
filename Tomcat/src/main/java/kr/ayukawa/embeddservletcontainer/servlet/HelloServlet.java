package kr.ayukawa.embeddservletcontainer.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns={"/hello.do"})
public class HelloServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();
		out.write("<h1>Hello world! with Servlet</h1>".getBytes());
		out.flush();
		out.close();
	}
}
