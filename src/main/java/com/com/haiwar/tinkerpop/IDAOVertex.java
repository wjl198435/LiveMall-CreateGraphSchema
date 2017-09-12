package com.com.haiwar.tinkerpop;

import com.alibaba.fastjson.JSONObject;
import com.harwar.graph.schema.user.wxUser;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wjl198435 on 8/9/2017.
 */
public interface IDAOVertex {

    public static final Logger LOGGER = LoggerFactory.getLogger(wxUser.class);


    //增加操作
    public static boolean doInsertVertex(String label, JSONObject property) throws Exception {

        GraphTraversalSource g = GremlinWithRemote.getInstance();

        if (label == null) {
            LOGGER.error("label : is null！->" + label);
            return false;
        }

        GraphTraversal gt = g.addV(T.label, label);

        for (Object key : property.keySet()) {
            Object val = property.get(key);

            if (val == null) {
                LOGGER.warn("value is null:" + val);
            }

            gt.property(key, val);
        }
        gt.next();

        return true;

    };


    //删除操作
    /**
     *  注意需要catch 异常 java.util.NoSuchElementException
     *  ***/
    public static boolean doDeleteVertex(JSONObject hasProperty) throws Exception {

        boolean res=false;
        GraphTraversalSource g = GremlinWithRemote.getInstance();

        GraphTraversal gt = g.V();

        for (String key : hasProperty.keySet()) {
            Object val = hasProperty.get(key);

            if (val == null) {
                LOGGER.warn("value is null:" + val);
            }

            gt.has(key, val);
        }
        gt.drop().next();

        res=true;
        return res;

    }

    ;


    //修改操作
    /**
     *  注意需要catch 异常 java.util.NoSuchElementException
     *  ***/
    public static boolean doUpdateVertex( JSONObject hasProperty, JSONObject newProperty) throws Exception {

        LOGGER.info("hasProperty:"+hasProperty);
        LOGGER.info("newProperty:"+newProperty);

        //avatarUrl:[avatarUrl6100

        boolean res=false;
        GraphTraversalSource g = GremlinWithRemote.getInstance();

        //g.V(299178).property("avatarUrl","update2").iterate();

        //g.V().has("city","city1").property("avatarUrl","update1"+100).iterate();   GOOD

        GraphTraversal gt = g.V();

        for (String key : hasProperty.keySet()) {
            Object val = hasProperty.get(key);

            if (val == null) {
                LOGGER.warn("value is null:" + val);
            }

            gt.has(key, val);
        }

       //LOGGER.info("gt has:"+gt.next());

        for (String key : newProperty.keySet()) {
            Object val = newProperty.get(key);

            if (val == null) {
                LOGGER.warn("value is null:" + val);
            }

            gt.property(key, val).iterate();
        }
//
//        //gt.iterate().next();

        res=true;

        return res;

    }

    ;



    //增加操作
    /**
     *  注意需要catch 异常 java.util.NoSuchElementException
     *  ***/
    public static ArrayList doSelectVertex( JSONObject hasProperty, String... keys) throws Exception {
        ArrayList<Map<String, Object>> l = new ArrayList<Map<String, Object>>();

        GraphTraversalSource g = GremlinWithRemote.getInstance();

        GraphTraversal gt = g.V();

        for (String key : hasProperty.keySet()) {
            Object val = hasProperty.get(key);

            if (val == null) {
                LOGGER.warn("value is null:" + val);
            }

            gt.has(key, val);
        }

        l = (ArrayList) gt.valueMap(keys).toList();

        return l;

    }

    ;




}
