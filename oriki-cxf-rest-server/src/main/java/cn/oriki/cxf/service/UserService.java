package cn.oriki.cxf.service;

import cn.oriki.cxf.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface UserService {

    /**
     * 查询方法
     *
     * @return
     */
    @Path("/users")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll();

    /**
     * 保存用户的方法
     *
     * @param user
     */
    @Path("/users")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void save(User user);

    /**
     * 更新用户的方法
     *
     * @param user
     */
    @Path("/users")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(User user);

    /**
     * 删除用户的方法
     *
     * @param id
     */
    @Path("/users/{id}")
    @DELETE
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void delete(@PathParam("id") Integer id);


    /**
     * 根据id查询用户的方法
     *
     * @param id
     * @return
     */
    @Path("/users/{id}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findById(@PathParam("id") Integer id);

}
