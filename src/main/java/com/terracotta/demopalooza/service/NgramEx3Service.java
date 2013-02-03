package com.terracotta.demopalooza.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terracotta.demopalooza.model.Ngram;

import java.security.SecureRandom;
import java.util.Set;

/**
 * Exmaple service using a cache as cache-through
 *
 * @author Aurelien Broszniowski
 */
public class NgramEx3Service implements NgramService {
  final static Logger logger = LoggerFactory.getLogger(NgramEx3Service.class);

  public void setup() throws Exception {
  }

  public void teardown() throws Exception {
  }

  public Set<Ngram> findNgramsForWord(final String word) throws Exception {
    return null;
  }

  public Ngram findMostPopularNgramsForYear(final int year) throws Exception {
    return null;
  }

}
