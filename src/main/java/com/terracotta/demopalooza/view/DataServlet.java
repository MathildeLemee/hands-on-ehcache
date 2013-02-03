package com.terracotta.demopalooza.view;

import com.terracotta.demopalooza.data.ServiceLocator;
import com.terracotta.demopalooza.service.NgramService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String source = req.getParameter("source");
    Integer year = Integer.valueOf(req.getParameter("year"));
    PrintWriter out = res.getWriter();
    try {
      if ("ehcache".equals(source))
        out.println(new Random().nextInt(100));
      else if ("sql".equals(source))
        out.println(new Random().nextInt(1000));

/*
    NORMALAMENT l'appel aux services se fait ainsi:
      long start;
      long end;
      NgramService service = ServiceLocator.getService(source);
      start = System.currentTimeMillis();
      long match_count = service.findMostPopularNgramsForYear(year).getMatch_count();
      end = System.currentTimeMillis();
      out.println(end - start);
*/
    } catch (Exception e) {
      e.printStackTrace();  // do not send back data if there is an error but log it
    }
    out.close();
  }
}