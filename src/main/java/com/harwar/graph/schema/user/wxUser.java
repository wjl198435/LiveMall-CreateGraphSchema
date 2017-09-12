package com.harwar.graph.schema.user;

import com.alibaba.fastjson.JSONObject;
import com.com.haiwar.tinkerpop.GremlinWithRemote;
import com.com.haiwar.tinkerpop.IDAOVertex;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by wjl198435 on 7/9/2017.
 */
public class wxUser extends User implements IUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(wxUser.class);


    static GraphTraversalSource g = GremlinWithRemote.getInstance();

    private final String label = wxUserData.getLabVertexUser();

    static private String openId;
    static private String account;
    static private String unionId;
    static private String nickName;
    static private String avatarUrl;
    static private Boolean gender = false;
    static private String country;
    static private String province;
    static private String city;


    public String getLabel() {
        return label;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public List<User> findUsersByName(String uname) throws Exception {

        return null;
    }

    @Override
    public List<User> findAllUsers() throws Exception {
        return null;
    }

    @Override
    public User findUsersById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean UsersUpdate(User usr) throws Exception {
        return false;
    }


    @Override
    public boolean doInsert(User per) throws Exception {
        wxUser wxuser = (wxUser) per;

        GraphTraversal gt = g.addV(T.label, label);//wxUserData.getOpenId(), openId
//                 ,wxUserData.getAvatarUrl(),
//                        avatarUrl,wxUserData.getAccount(),
//                        account,wxUserData.getUnionId(), unionId,
//                        wxUserData.getNickName(), nickName,
//                        wxUserData.getGender(), gender,
//                        wxUserData.getCountry(), country,
//                        wxUserData.getProvince(), province,
//                        wxUserData.getTime(),time,
//                        wxUserData.getCity(), city).next();

        if (openId != null && !openId.isEmpty()) {
            gt.property(wxUserData.getOpenId(), openId);
        } else {
            return false;
        }

        if (avatarUrl != null && avatarUrl.isEmpty()) {
            gt.property(wxUserData.getAvatarUrl(), avatarUrl);
        }

        if (account != null && !account.isEmpty()) {
            gt.property(wxUserData.getAccount(), account);
        }

        if (unionId != null && !unionId.isEmpty()) {
            gt.property(wxUserData.getUnionId(), unionId);
        }

        if (nickName != null && !nickName.isEmpty()) {
            gt.property(wxUserData.getNickName(), nickName);
        }

        if (gender != null) {
            gt.property(wxUserData.getGender(), gender);
        }

        if (country != null && !country.isEmpty()) {
            gt.property(wxUserData.getCountry(), country);
        }

        if (province != null && !province.isEmpty()) {
            gt.property(wxUserData.getProvince(), province);
        }

        if (city != null && !city.isEmpty()) {
            gt.property(wxUserData.getCity(), city);
        }
        try {
            gt.next();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return false;
        }

        //LOGGER.info(wxuser.getOpenId());

        return true;
    }


    public boolean doInsert(String label, JSONObject property) throws Exception {

        if (g == null) {
            g = GremlinWithRemote.getInstance();
        }

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
    }

    @Override
    public boolean doUpdate(Person per) throws Exception {
        return false;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        return false;
    }

    @Override
    public Person findById(int id) throws Exception {
        return null;
    }

    public static void main(String[] args) {
        wxUser wxu = new wxUser();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {

            LOGGER.info("i:" + i);
            String label = wxUserData.getLabVertexUser();
            JSONObject jsonObj = new JSONObject();
            long time = i;
            //jsonObj.put(wxUserData.getAccount(), wxUserData.getAccount() + time);
            //jsonObj.put(wxUserData.getOpenId(), wxUserData.getOpenId() + i);
            //jsonObj.put(wxUserData.getAvatarUrl(), wxUserData.getAvatarUrl() + time);
            //jsonObj.put(wxUserData.getNickName(), wxUserData.getNickName() + time);
            jsonObj.put(wxUserData.getCity(), wxUserData.getCity() + time);
//            jsonObj.put(wxUserData.getProvince(), wxUserData.getProvince() + time);
//            jsonObj.put(wxUserData.getCountry(), wxUserData.getCountry() + time);
//            jsonObj.put(wxUserData.getUnionId(), wxUserData.getUnionId() + time);
//            jsonObj.put(wxUserData.getTime(), time);
//            jsonObj.put(wxUserData.getGender(), (System.currentTimeMillis() % 2 == 0 ? true : false));


            try {
                //LOGGER.info("doInsertVertex:" + IDAOVertex.doInsertVertex(label,jsonObj));


                JSONObject jsonObjSelect = new JSONObject();


                //jsonObjSelect.put("openId","openId"+i);
                //jsonObjSelect.put(wxUserData.getAvatarUrl(), wxUserData.getAvatarUrl() + time+100);

                jsonObjSelect.put(wxUserData.getNickName(), wxUserData.getNickName()+i);

                //LOGGER.info("doSelectVertex:"+ IDAOVertex.doDeleteVertex(jsonObjSelect));
                //LOGGER.info("doSelectVertex:"+ IDAOVertex.doSelectVertex(jsonObjSelect,wxUserData.getAvatarUrl()));

                JSONObject newjsonObjSelect = new JSONObject();

                newjsonObjSelect.put(wxUserData.getCity(), wxUserData.getCity() +i);

                //LOGGER.info("doUpdateVertex:"+ IDAOVertex.doUpdateVertex(jsonObjSelect,newjsonObjSelect));


            } catch (Exception e) {
                e.printStackTrace();
            }


//            wxu.setAccount("Account" + System.currentTimeMillis());
//            wxu.setOpenId("OpendId" + System.currentTimeMillis());
//            wxu.setAvatarUrl("Avatar" + System.currentTimeMillis());
//            wxu.setCity("City" + System.currentTimeMillis());
//
//            wxu.setCountry("Country" + System.currentTimeMillis());
//            wxu.setGender((System.currentTimeMillis()%2==0 ? true:false));
//            wxu.setNickName("NickName"+ System.currentTimeMillis());
//
//            wxu.setTime(i);
//
//            wxu.setProvince("Province"+ System.currentTimeMillis());
//            wxu.setUnionId("UnionId"+ System.currentTimeMillis());
//            try {
//                LOGGER.info("doInsertVertex:" + wxu.doInsertVertex(wxu));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        try {
            g.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        LOGGER.info("Spent:" + (endTime - startTime));
    }

    public String toString() {
        return "";
    }


}
