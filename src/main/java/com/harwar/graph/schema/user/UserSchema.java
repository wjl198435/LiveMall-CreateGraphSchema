package com.harwar.graph.schema.user;

/**
 * Created by wjl198435 on 7/9/2017.
 */
import org.janusgraph.core.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Created by wjl198435 on 5/9/2017.
 */
public  class UserSchema {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSchema.class);

    public static void Create(JanusGraph graph){
        wxUserData.VertexLabel(graph);
        wxUserData.Properties(graph);
    }


    public static void main(String[] args) {

        if(args.length<1){
            LOGGER.error("UserSchema *.properties ");
            System.exit(1);
        }

        ///Users/wjl198435/Downloads/Graph/janusgraph-0.1.1-hadoop2/conf/GremlinWithRemote-server/janusgraph-cassandra-es-server-user.properties
        ///data/graph/janusgraph-cassandra-es.properties
        String conf = args[0];
        LOGGER.info(conf);

        JanusGraph graph=JanusGraphFactory.open(conf);

        Create(graph);

        graph.close();
    }

}

