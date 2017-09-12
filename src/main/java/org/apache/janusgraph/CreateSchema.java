package org.apache.janusgraph;


import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.elasticsearch.common.base.Preconditions;
import org.janusgraph.core.*;
import org.janusgraph.core.schema.ConsistencyModifier;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.graphdb.database.StandardJanusGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjl198435 on 7/9/2017.
 */
public interface CreateSchema {
    static final Logger LOGGER = LoggerFactory.getLogger(CreateSchema.class);

    public static void makeVertexLabel(JanusGraph jGraph, String vertexLabel, Boolean partition) {

        if (jGraph != null && !vertexLabel.isEmpty()) {
            JanusGraphManagement mgmt = jGraph.openManagement();
            if (mgmt != null) {

                if (mgmt.getVertexLabel(vertexLabel) == null) {
                    LOGGER.info("makeVertexLabel :" + vertexLabel + "as not existing");

                    if (partition == true) {
                        VertexLabel label = mgmt.makeVertexLabel(vertexLabel).partition().make();
                    } else {
                        VertexLabel label = mgmt.makeVertexLabel(vertexLabel).make();

                    }
                } else {

                    LOGGER.info("makeVertexLabel :" + vertexLabel + "is existing");
                }

                mgmt.commit();

            }

        } else {
            LOGGER.info("makeVertexLabel :" + vertexLabel + " is exsiting ,Nothing to do");
        }
    }


    public static void makeEdgeLabel(JanusGraph jGraph, String edgeLabelLabel, String sig, Multiplicity multi) {

        LOGGER.info(" Enter makeVertexPropertyKeyIndex create edgeLabelLabel: " + edgeLabelLabel + "--sig:" + sig + "--multi:" + multi);

        if (jGraph != null && !edgeLabelLabel.isEmpty()) {
            JanusGraphManagement mgmt = jGraph.openManagement();
            if (mgmt != null) {
                if (!mgmt.containsEdgeLabel(edgeLabelLabel)) {
                    if (sig != null) {
                        LOGGER.info("makeEdgeLabel :" + edgeLabelLabel + " as not existing" + "signature:" + sig + " Multiplicity:" + multi);
                        PropertyKey sigPropertyKey = mgmt.getOrCreatePropertyKey(sig);
                        EdgeLabel edgeLabel = mgmt.makeEdgeLabel(edgeLabelLabel).multiplicity(multi).signature(sigPropertyKey).make();
                        mgmt.buildEdgeIndex(edgeLabel, "battlesByTime", Direction.BOTH, Order.decr, sigPropertyKey);
                        LOGGER.info("makeEdgeLabel :" + edgeLabelLabel + " is ok");
                    } else {
                        EdgeLabel edgeLabel = mgmt.makeEdgeLabel(edgeLabelLabel).multiplicity(multi).make();
                    }
                } else {
                    LOGGER.info("makeEdgeLabel:" + edgeLabelLabel + " is exsiting");
                }
                mgmt.commit();
            }
        }
    }


    public static void makePropertyKey(JanusGraph jGraph, String propertyKey, Class<?> classType, Cardinality cardinality) {
        if (jGraph != null && !propertyKey.isEmpty()) {
            JanusGraphManagement mgmt = jGraph.openManagement();
            if (mgmt != null) {
                if (!mgmt.containsPropertyKey(propertyKey)) {
                    LOGGER.info("makePropertyKey:" + propertyKey + " as not existing");
                    final PropertyKey property = mgmt.makePropertyKey(propertyKey).dataType(classType).cardinality(cardinality).make();
                    //final PropertyKey property = mgmt.makePropertyKey(propertyKey).dataType(classType).make();
                    LOGGER.info("makePropertyKey:" + propertyKey + ":dataType:" + String.class + " ...is ok");
                } else {
                    LOGGER.info("makePropertyKey:" + propertyKey + " is existing");
                }
                mgmt.commit();
            }
        }
    }


    public static void makeVertexPropertyKeyIndex(JanusGraph jGraph, String IndexName, String propertyKey, Class<?> dataType,
                                                  Boolean uniqueNameCompositeIndex) {

        if (jGraph != null && !propertyKey.isEmpty()) {
            JanusGraphManagement mgmt = jGraph.openManagement();
            if (mgmt != null) {

                LOGGER.info(" Enter makeVertexPropertyKeyIndex create  IndexName " + IndexName + ":" + propertyKey);
                if (!mgmt.containsPropertyKey(propertyKey)) {
                    LOGGER.info("makeVertexPropertyKeyIndex create " + propertyKey + " as not existing");
                    PropertyKey name = mgmt.makePropertyKey(propertyKey).dataType(dataType).make();
                    LOGGER.info("makeVertexPropertyKeyIndex create " + propertyKey + "....... make is ok");

                    if (IndexName != null && IndexName.length() > 1 && !mgmt.containsGraphIndex(IndexName)) {
                        LOGGER.info("makeVertexPropertyKeyIndex:***************-index " + IndexName + " as not existing");
                        JanusGraphManagement.IndexBuilder nameIndexBuilder = mgmt.buildIndex(IndexName, Vertex.class).addKey(name);

                        if (uniqueNameCompositeIndex)
                            nameIndexBuilder.unique();
//
                        JanusGraphIndex namei = nameIndexBuilder.buildCompositeIndex();
                        mgmt.setConsistency(namei, ConsistencyModifier.LOCK);

                        LOGGER.info("makeVertexPropertyKeyIndex index  " + IndexName + ".......make is ok");
                    } else {
                        LOGGER.info("EXIT makeVertexPropertyKeyIndex:  " + IndexName + " is NULL OR EXISTING!!!!");
                    }
                } else {
                    LOGGER.info("EXIT makeVertexPropertyKeyIndex:" + propertyKey + "is existing");
                }
                mgmt.commit();
            }

        }
    }

}
