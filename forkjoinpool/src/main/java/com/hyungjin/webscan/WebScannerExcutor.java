package com.hyungjin.webscan;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashSet;

/**
 *
 * @author Madalin Ilie
 */
public class WebScannerExcutor implements LinkHandler {

	//private final Collection<String> visitedLinks = new HashSet<String>();
    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
//    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<String>());    
    private String url;
    private ExecutorService execService;

    public WebScannerExcutor(String startingURL, int maxThreads) {
        this.url = startingURL;
        execService = Executors.newFixedThreadPool(maxThreads);
    }

    public void queueLink(String link) throws Exception {
        startNewThread(link);
    }

    public int size() {
        return visitedLinks.size();
    }

    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }
    
    public boolean visitedAndAddVisited(String s) {
    	synchronized (visitedLinks) {
			if(!visited(s))
			{
				addVisited(s);
				return true;
			}
			else
			{
				return false;
			}
    	}
    }

    private void startNewThread(String link) throws Exception {
        execService.execute(new LinkFinder(link, this));
    }

    private void startCrawling() throws Exception {
        startNewThread(this.url);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new WebScannerExcutor("http://www.javaworld.com", 64).startCrawling();
    }
}