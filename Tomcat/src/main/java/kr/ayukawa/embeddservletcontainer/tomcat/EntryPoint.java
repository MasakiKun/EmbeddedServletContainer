package kr.ayukawa.embeddservletcontainer.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EntryPoint {
	public static final String WEBAPP_DIR = "webapp/";

	public static void main(String[] args) throws Exception {
		String portNo = System.getProperty("tomcat.port");
		int port;

		try {
			port = Integer.valueOf(portNo);
		} catch(Exception e) {
			port = 8080;				// default port
		}

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(port);

		// ContextPath 등록
		Context ctx = tomcat.addWebapp("/", new File(WEBAPP_DIR).getAbsolutePath());

		// Servlet 등록
		tomcat.addServlet(ctx, "helloServlet", new kr.ayukawa.embeddservletcontainer.servlet.HelloServlet());

		// Servlet에 URL Mapping
		ctx.addServletMapping("/hello", "helloServlet");

		tomcat.start();
		tomcat.getServer().await();
	}
}
