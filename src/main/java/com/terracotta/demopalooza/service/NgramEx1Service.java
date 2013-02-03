package com.terracotta.demopalooza.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terracotta.demopalooza.model.Ngram;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * Exmaple service connecting to a database
 *
 * @author Aurelien Broszniowski
 */
public class NgramEx1Service implements NgramService {
  final static Logger logger = LoggerFactory.getLogger(NgramEx1Service.class);

  SecureRandom rnd = new SecureRandom();

  public void setup() throws Exception {
    logger.info("We (falsely) setup the database connection");
  }

  public void teardown() throws Exception {
    logger.info("We (falsely) teardown the database connection");
  }

  public Set<Ngram> findNgramsForWord(final String word) throws Exception {
    //TODO for hands-on : actually create some stuff, either by a Builder class or from a real DB
    Thread.sleep(200 + rnd.nextInt(1000));
    return new HashSet<Ngram>();
  }

  public Ngram findMostPopularNgramsForYear(final int year) throws Exception {
    //TODO for hands-on : actually create some stuff, either by a Builder class or from a real DB
    Thread.sleep(200 + rnd.nextInt(300));
    return new Ngram();
  }

}
