package com.harwar.graph.schema.user;

import org.apache.janusgraph.CreateSchema;
import org.janusgraph.core.Cardinality;
import org.janusgraph.core.JanusGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjl198435 on 7/9/2017.
 */
public class wxUserData {

    private static final Logger LOGGER = LoggerFactory.getLogger(wxUserData.class);

    /*******************用户注册标签******************************/
    static private final String labVertexUser = "wxUser";

    static private String[] vertexLabel = {labVertexUser};


    /*******************微信注册用户信息*********************/

    //注册时间
    static private final String time = "time";

    //static  private final String lab_Vertex_wx_reg="wx_register";

    //微信用户在本小程序openId
    static private final String openId = "openId";

    //微信号
    static private final String account = "account";

    //微信开发者appId(公众号、小程序)通用唯一id
    static private final String unionId = "unionId";

    //微信用户小程序呢称
    static private final String nickName = "nickName";

    //微信用户头像
    static private final String avatarUrl = "avatarUrl";

    //微信用户登记性别
    static private final String gender = "gender";

    //微信用户登记所在国家
    static private final String country = "country";
    //微信用户登记所在省份
    static private final String province = "province";
    //微信用户登记所在城市
    static private final String city = "city";


    static public String[] PropertyKey = {time, account, openId, unionId,
            nickName, avatarUrl, gender, country, province, city};

    static void VertexLabel(JanusGraph jGraph) {
        for (int i = 0; i < vertexLabel.length; i++) {
            LOGGER.info(vertexLabel[i]);

            switch (vertexLabel[i]) {
                case labVertexUser: {
                    CreateSchema.makeVertexLabel(jGraph, vertexLabel[i], true);
                    LOGGER.info(vertexLabel[i]);
                    break;
                }
                default:
                    ;
            }
        }
    }

    static void Properties(JanusGraph jGraph) {

        for (int i = 0; i < PropertyKey.length; i++) {
            //LOGGER.info(wxUserInfoArray[i]);

            switch (PropertyKey[i]) {
                case openId: {
                    LOGGER.info("" + PropertyKey[i]);
                    //CreateSchema.makePropertyKey(jGraph,PropertyKey[i],String.class, Cardinality.SINGLE);
                    CreateSchema.makeVertexPropertyKeyIndex(jGraph, PropertyKey[i] + "s", PropertyKey[i], String.class, true);
                    break;
                }

                case time: {
                    CreateSchema.makePropertyKey(jGraph, PropertyKey[i], Long.class, Cardinality.SINGLE);
                    break;
                }

                case unionId: {
                    LOGGER.info("" + PropertyKey[i]);
                    CreateSchema.makeVertexPropertyKeyIndex(jGraph, PropertyKey[i] + "s", PropertyKey[i], String.class, true);
                    break;
                }
                default: {
                    LOGGER.info("" + PropertyKey[i]);
                    CreateSchema.makePropertyKey(jGraph, PropertyKey[i], String.class, Cardinality.SINGLE);
                }
            }

        }

    }

    public static String getLabVertexUser() {
        return labVertexUser;
    }

    public static String[] getVertexLabel() {
        return vertexLabel;
    }

    public static void setVertexLabel(String[] vertexLabel) {
        wxUserData.vertexLabel = vertexLabel;
    }

    public static String getTime() {
        return time;
    }

    public static String getOpenId() {
        return openId;
    }

    public static String getAccount() {
        return account;
    }

    public static String getUnionId() {
        return unionId;
    }

    public static String getNickName() {
        return nickName;
    }

    public static String getAvatarUrl() {
        return avatarUrl;
    }

    public static String getGender() {
        return gender;
    }

    public static String getCountry() {
        return country;
    }

    public static String getProvince() {
        return province;
    }

    public static String getCity() {
        return city;
    }

    public static String[] getPropertyKey() {
        return PropertyKey;
    }

    public static void setPropertyKey(String[] propertyKey) {
        PropertyKey = propertyKey;
    }
}
