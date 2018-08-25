package kr.ayukawa.embeddservletcontainer.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

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

/*
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);
*/
/*
		StandardJarScanner scanner = (StandardJarScanner)ctx.getJarScanner();
		scanner.setScanClassPath(true);
		scanner.setScanBootstrapClassPath(true);
		scanner.setScanAllDirectories(true);
		scanner.setScanAllFiles(true);
*/

		// Servlet 등록
		tomcat.addServlet(ctx, "helloServlet", new kr.ayukawa.embeddservletcontainer.servlet.HelloServlet());

		// Servlet에 URL Mapping
		ctx.addServletMapping("/hello", "helloServlet");

		tomcat.start();
		tomcat.getServer().await();
	}
}
