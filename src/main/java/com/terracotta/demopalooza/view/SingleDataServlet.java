package com.terracotta.demopalooza.view;

import com.terracotta.demopalooza.data.ServiceLocator;
import com.terracotta.demopalooza.model.Ngram;
import com.terracotta.demopalooza.service.NgramService;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This service gets a NGram. it illustrates the behaviour of loading an instance of data.
 * This is the simpler exercise
 */

public class SingleDataServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String source = req.getParameter("source");
    Integer year = Integer.valueOf(req.getParameter("year"));
    PrintWriter out = res.getWriter();
    try {
      long start;
      long end;
      NgramService service = ServiceLocator.getService(source);
      start = System.currentTimeMillis();
      service.findMostPopularNgramsForYear(year);
      end = System.currentTimeMillis();
      out.println(end - start);
    } catch (Exception e) {
      e.printStackTrace();  // do not send back data if there is an error but log it
    }
    out.close();
  }
}