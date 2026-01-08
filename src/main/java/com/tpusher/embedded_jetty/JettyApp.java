package com.tpusher.embedded_jetty;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.IOException;

public class JettyApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(HelloServlet.class, "/hello");

        server.start();

        server.join();

    }

    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/plain");
            try {
                resp.getWriter().println("Welcome to the Jetty world!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
