package com.com.haiwar.tinkerpop;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjl198435 on 7/9/2017.
 */
public class GremlinWithRemote {
    private static final Logger LOGGER = LoggerFactory.getLogger(GremlinWithRemote.class);
    static Graph graph = EmptyGraph.instance();
    static GraphTraversalSource g = null;
    static String remoteGraph="remote-graph.properties";


    public static GraphTraversalSource getInstance() {

        if (g == null) {
            try {
                 g = graph.traversal().withRemote(remoteGraph);
            } catch (Exception e) {
                g=null;
                LOGGER.error("get gremlin remote failed!!!");
                e.printStackTrace();
            }
        }
        return g;
    }
}
