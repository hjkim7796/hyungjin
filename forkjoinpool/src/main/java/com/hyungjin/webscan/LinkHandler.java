package com.hyungjin.webscan;

public interface LinkHandler {
	final static int MAX_LINK = 500;

	void queueLink(String link) throws Exception;

	int size();

	boolean visited(String link);

	void addVisited(String link);
}