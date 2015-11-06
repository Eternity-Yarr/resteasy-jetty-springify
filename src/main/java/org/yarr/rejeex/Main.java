package org.yarr.rejeex;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Component
public class Main
{
    public static void main(String... args) throws Exception {
        Server server = new Server(8080);
        ServletHolder holder = new ServletHolder(new HttpServletDispatcher());
        holder.setInitParameter("javax.ws.rs.Application", "org.yarr.rejeex.RejeexApplication");

        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(holder, "/*");
        context.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        context.addEventListener(new ResteasyBootstrap());
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfiguration.class);
        context.addEventListener(new MyContextLoaderListener(ctx));
        server.setHandler(context);
        server.start();
        server.join();
    }
}