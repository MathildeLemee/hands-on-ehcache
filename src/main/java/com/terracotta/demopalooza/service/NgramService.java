package com.terracotta.demopalooza.service;

import com.terracotta.demopalooza.model.Ngram;

import java.util.Set;

public interface NgramService {

  void setup() throws Exception;

  void teardown() throws Exception;

  Set<Ngram> findNgramsForWord(String word) throws Exception;

  Ngram findMostPopularNgramsForYear(int year) throws Exception;

}
