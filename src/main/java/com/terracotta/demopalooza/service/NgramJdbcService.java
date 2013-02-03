package com.terracotta.demopalooza.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.terracotta.demopalooza.model.Ngram;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * This is for reference until the hands-on project is ready
 */


@Deprecated
public class NgramJdbcService implements NgramService {

  protected ComboPooledDataSource cdps;

  public void setup() throws Exception {
    cdps = new ComboPooledDataSource();
    cdps.setDriverClass("com.mysql.jdbc.Driver");
    cdps.setJdbcUrl("jdbc:mysql://bigmemory02/demopalooza");
    cdps.setUser("root");
    cdps.setPassword("");
    cdps.setMaxPoolSize(20);
  }

  public void teardown() throws Exception {
    cdps.close();
  }

  public Set<Ngram> findNgramsForWord(final String word) throws Exception {
    Set<Ngram> ngrams = new HashSet<Ngram>();
    Connection conn = cdps.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT YEAR, MATCH_COUNT, PAGE_COUNT, VOLUME_COUNT FROM ngrams WHERE WORD = \''+word+ '\'");
    while (rs.next()) {
      Ngram ngram = new Ngram(word, rs.getInt(1), rs.getLong(2), rs.getLong(3), rs.getLong(4));
      ngrams.add(ngram);
    }
    stmt.close();
    conn.close();
    return ngrams;
  }

  public Ngram findMostPopularNgramsForYear(final int year) throws Exception {
    Connection conn = cdps.getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("select WORD, MATCH_COUNT, PAGE_COUNT, VOLUME_COUNT from ngrams where year=\'" + year + "\' order by match_count desc limit 1");
    Ngram ngram = null;
    if (rs.next()) {
      ngram = new Ngram(rs.getString(1), year, rs.getLong(2), rs.getLong(3), rs.getLong(4));
    }
    stmt.close();
    conn.close();
    return ngram;
  }

}
