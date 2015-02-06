package com.hyungjin.webscan;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;


/**
 *
 * @author Madalin Ilie
 */
public class LinkFinder implements Runnable {

    private String url;
    private LinkHandler linkHandler;
    /**
     * Used fot statistics
     */
    private static final long t0 = System.nanoTime();

    public LinkFinder(String url, LinkHandler handler) {
        this.url = url;
        this.linkHandler = handler;
    }

    public void run() {
        getSimpleLinks(url);
    }

    private void getSimpleLinks(String url) {
        //if not already visited
    	if (linkHandler.size() <= LinkHandler.MAX_LINK && !linkHandler.visited(url)) {
            try {
                if (linkHandler.size() == LinkHandler.MAX_LINK) {
                    System.out.println("Time to visit " + LinkHandler.MAX_LINK + " distinct links = " + (System.nanoTime() - t0));
                    return;
                }
                
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
                List<String> urls = new ArrayList<String>();

                 for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.getLink().isEmpty()
                            && !linkHandler.visited(extracted.getLink())) {

                        urls.add(extracted.getLink());
                    }

                }
                //we visited this url
                linkHandler.addVisited(url);

                for (String l : urls) {
                    linkHandler.queueLink(l);
                }

             } catch (Exception e) {
                //ignore all errors for now
            }
        }
    }
}