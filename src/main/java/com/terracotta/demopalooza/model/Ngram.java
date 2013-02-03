package com.terracotta.demopalooza.model;

import java.io.Serializable;


public class Ngram implements Serializable {
  private String word;
  private int year;
  private long match_count;
  private long page_count;
  private long volume_count;

  public Ngram() {
  }

  public Ngram(String word, int year, long match_count, long page_count, long volume_count) {
    this.word = word;
    this.year = year;
    this.match_count = match_count;
    this.page_count = page_count;
    this.volume_count = volume_count;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public long getMatch_count() {
    return match_count;
  }

  public void setMatch_count(long match_count) {
    this.match_count = match_count;
  }

  public long getPage_count() {
    return page_count;
  }

  public void setPage_count(long page_count) {
    this.page_count = page_count;
  }

  public long getVolume_count() {
    return volume_count;
  }

  public void setVolume_count(long volume_count) {
    this.volume_count = volume_count;
  }

  @Override
  public String toString() {
    return word + ", " + year + ", " + match_count + ", " + page_count + ", " + volume_count;
  }
}
