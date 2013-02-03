package com.terracotta.demopalooza.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Direction;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import com.terracotta.demopalooza.model.Ngram;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * This is for reference until the hands-on project is ready
 */

@Deprecated
public class NgramEhcacheService extends NgramJdbcService {
  private CacheManager manager;
  private Cache cache;
  
  //data source directory 
  private String CSV_DIRECTORY = "/fioa/bench/demo-test";

  @Override
  public void setup() throws Exception {
	  super.setup();
    
	  //initialize cache
      URL url = getClass().getResource("/ehcache-clustered.xml");
	  this.manager = CacheManager.create(url);
	  this.cache = manager.getCache("sampleCache");
  }

  @Override
 public void teardown() throws Exception {
    super.teardown();
    manager.shutdown();
  }

  public Set<Ngram> findNgramsForWord(final String word) {
	  Set<Ngram> ngrams = new HashSet<Ngram>();
	  Attribute<String> searchAttribute = cache.getSearchAttribute("word");
	  Query query = cache.createQuery();
	  query.includeValues();
	  query.addCriteria(searchAttribute.eq(word));
	  Results results = query.execute();
	  for (Result res: results.all()) {
		  ngrams.add((Ngram) res.getValue());
	  }
	  return ngrams;
  }

  public Ngram findMostPopularNgramsForYear(final int year) {
	  Attribute<Integer> searchAttribute = cache.getSearchAttribute("year");
	  Attribute<String> word = cache.getSearchAttribute("word");
	  Query query = cache.createQuery().includeValues()
        .addOrderBy(word, Direction.DESCENDING)
	      .maxResults(1).addCriteria(searchAttribute.eq(year));
	  Result result = query.execute().all().get(0);
    return (Ngram)result.getValue();
  }

  public void warmup() throws Exception {
    Statement stmt;
    Connection connection = cdps.getConnection();
    stmt = connection.createStatement();
    ResultSet rs;
    int nbRowsPerQuery = 1000;
    for (int i = 0; i < 3000000; i += nbRowsPerQuery) {
      rs = stmt.executeQuery("SELECT * FROM ngrams LIMIT " + nbRowsPerQuery + " OFFSET " + i);
      System.out.println("" + i);
      while (rs.next()) {
        String word = rs.getString(1);
        int year = rs.getInt(2);
        String key = word + Integer.toString(year);
        if (!cache.isKeyInCache(key)) {
          Ngram ngram = new Ngram(word, year, rs.getLong(3), rs.getLong(4), rs.getLong(5));
          Element elem = new Element(key, ngram);
          cache.put(elem);
        }
      }
    }
    stmt.close();
    connection.close();
  }
}
