package com.hyungjin.webscan;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.HashSet;

/**
 *
 * @author Madalin Ilie
 */
public class WebScannerForkJoin implements LinkHandler {

    private final Collection<String> visitedLinks = new HashSet<String>();
    //private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
//    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<>());
    private String url;
    private ForkJoinPool mainPool;

    public WebScannerForkJoin(String startingURL, int maxThreads) {
        this.url = startingURL;
        mainPool = new ForkJoinPool(maxThreads);
    }

    private void startWebScanning() {
        mainPool.invoke(new LinkFinderAction(this.url, this));
    }

    public void queueLink(String link) throws Exception {
		
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new WebScannerForkJoin("http://www.javaworld.com", 64).startWebScanning();
    }
}