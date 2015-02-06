package com.hyungjin.webscan;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

@SuppressWarnings("serial")
public class LinkFinderAction extends RecursiveAction {
    private String url;
    transient private LinkHandler cr;
    /**
     * Used for statistics
     */
    private static final long t0 = System.nanoTime();

    public LinkFinderAction(String url, LinkHandler cr) {
        this.url = url;
        this.cr = cr;
    }

    @Override
    public void compute() {
        if (cr.size() <= LinkHandler.MAX_LINK && !cr.visited(url)) {
            try {
                if (cr.size() == LinkHandler.MAX_LINK) {
                    System.out.println("Time to visit " + LinkHandler.MAX_LINK + " distinct links = " + (System.nanoTime() - t0));
                    return;
                }
                
                
                
                List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

                for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.extractLink().isEmpty()
                            && !cr.visited(extracted.extractLink())) {

                        actions.add(new LinkFinderAction(extracted.extractLink(), cr));
                    }
                }
                cr.addVisited(url);


                //invoke recursively
                invokeAll(actions);
            } catch (Exception e) {
                //ignore 404, unknown protocol or other server errors
            }
        }
    }
}
