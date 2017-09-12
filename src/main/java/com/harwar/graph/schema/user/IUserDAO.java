package com.harwar.graph.schema.user;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface IUserDAO {
    //按姓名查询
    public List<User> findUsersByName(String uname) throws Exception;
    //查询所有用户
    public List<User> findAllUsers() throws Exception;
    //按id查询
    public User findUsersById(int id)throws Exception;
    //修改操作
    public boolean UsersUpdate(User usr)throws Exception;

    //增加操作
    public boolean doInsert(User per)throws Exception;



    //增加操作
    public static boolean doInsert(String label,JSONObject data)throws Exception{
        return false;
    };

    //修改操作
    public boolean doUpdate(Person per)throws Exception;
    //删除操作
    public boolean doDelete(int id)throws Exception;
    //按id查询
    public Person findById(int id)throws Exception;
    //查询全部
//    public List<Product> findAllProduct() throws Exception;
//    //模糊查询
//    public List<Product> findByLike(String cond)throws Exception;
}