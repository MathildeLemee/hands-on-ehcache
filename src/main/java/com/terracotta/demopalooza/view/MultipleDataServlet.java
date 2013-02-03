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

/**
 * This service gets a Set<NGram> it illustrates the behaviour of loading collections of data.
 * In term of cache fetching, that can be results in different optimizations:
 *   - put the whole colleciton in the cache
 *   - create the collection from the cache and the SOR
 */

public class MultipleDataServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String source = req.getParameter("source");
    String word = req.getParameter("word");
    PrintWriter out = res.getWriter();
    try {
      long start;
      long end;
      NgramService service = ServiceLocator.getService(source);
      start = System.currentTimeMillis();
      service.findNgramsForWord(word);
      end = System.currentTimeMillis();
      out.println(end - start);
    } catch (Exception e) {
      e.printStackTrace();  // do not send back data if there is an error but log it
    }
    out.close();
  }
}