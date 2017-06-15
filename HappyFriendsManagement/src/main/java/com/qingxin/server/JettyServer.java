package com.qingxin.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyServer {

	private void start() throws Exception {
		int port = 8888;
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.setResourceBase("./");
		server.setHandler(context);
		ServletHolder sh = new ServletHolder(ServletContainer.class);
		sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig");
		sh.setInitParameter("com.sun.jersey.config.property.packages", "com.qingxin.user.controller");
		sh.setInitParameter("jersey.config.server.provider.classnames","org.glassfish.jersey.moxy.json.MoxyJsonFeature");

		context.addServlet(sh, "/*");
		server.start();
	}

	public void stop() throws Exception {

	}

	public static void main(String[] args) throws Exception {
		JettyServer server = new JettyServer();
		server.start();
	}
}