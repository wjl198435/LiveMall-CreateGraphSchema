

import com.harwar.graph.schema.user.UserSchema;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
//import org.apache.tinkerpop.GremlinWithRemote.structure.util.empty.EmptyGraph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
//import org.janusgraph.core.JanusGraph;
//import org.janusgraph.core.JanusGraphFactory;
//import org.janusgraph.graphdb.database.StandardJanusGraph;
//import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjl198435 on 6/9/2017.
 */
public class TestConnectJanusGraphWithRemote {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestConnectJanusGraphWithRemote.class);

   // private static final Logger LOGGER = LoggerFactory.getLogger(TestConnectJanusGraphWithRemote.class);

    public static void main(String[] args) {

        long startTime=System.currentTimeMillis();

        Graph graph = EmptyGraph.instance();
        GraphTraversalSource g=null;
        // JanusGraph graph = JanusGraphFactory.open("/Users/wjl198435/Downloads/Graph/janusgraph-0.1.1-hadoop2/conf/GremlinWithRemote-server/janusgraph-cassandra-es-server-user.properties");

        try {
             g = graph.traversal().withRemote("remote-graph.properties");

            //Graph JGraph=(Graph)g.getGraph();

            //g.getGraph();
            for (int i = 0; i < 5000; i++) {
                //Vertex person=g.V().has( "name", "name" + i).next();
                //g.V().has( "name", "name" + i);
                try {
                    //LOGGER.info("name" + i + "  " + g.V().has("name", "name" + i).next());
                }catch (java.util.NoSuchElementException e) {
                    LOGGER.error(e.getMessage() + "IS NULL");
                }
                catch (Exception e){
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }

//                if(count>0) {
//                    LOGGER.info("test----:" + v.values("name") + "");
//                }else{
//                    LOGGER.info("name" + i+"Not Existing");
//                }
                // person.

//                  Set<String> keys= person.keys();
//
//                    Iterator it=keys.iterator();
//                     while(it.hasNext()){
//                         LOGGER.info("-------------"+person.value(it.next()+""));
//                     }
//
//                    LOGGER.info(g.V().has( "name", "name" + i).next().values() +"");
                LOGGER.info(g.addV("hello", "hello", "name", "name" + i).next() + "");

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "*****");

        }finally {
            try {
                g.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        long endTime=System.currentTimeMillis();
        LOGGER.info("Spend times"+(startTime-endTime)+"");

    }

}
