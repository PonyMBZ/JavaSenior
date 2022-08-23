package com.zzuli.dao;

import com.zzuli.bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-25 17:33
 */
public interface UserDAO {
    /** @Author: pony
     * @Description: 将 user 对象添加到数据库中
     * @Date: 17:39 2022/4/25
     * @param: conn
     * @param: user
     * @return: void
     * @Version: 1.0
     */
    void insert(Connection conn, User user);

    /** @Author: pony
     * @Description: 针对指定的 id ,删除表中的一条记录
     * @Date: 18:01 2022/4/25
     * @param: conn
     * @param: id
     * @return: void
     * @Version: 1.0
     */
    void deleteById(Connection conn, int id);

    /** @Author: pony
     * @Description: 针对内存中的 user 对象，修改数据表中指定的记录
     * @Date: 18:12 2022/4/25
     * @param: conn
     * @param: user
     * @return: void
     * @Version: 1.0
     */
    void update(Connection conn, User user);

    /** @Author: pony
     * @Description: 针对指定的id查询得到对应的 User 对象
     * @Date: 18:22 2022/4/25
     * @param: conn
     * @param: id
     * @return: com.zzuli.bean.User
     * @Version: 1.0
     */
    User getUserById(Connection conn, int id);

    /**@Author: pony
     * @Description: 查询表中所有记录对应的集合
     * @Date: 18:04 2022/4/26
     * @param: conn
     * @return: java.util.List<com.zzuli.bean.User>
     * @Version: 1.0
     */
    List<User> getAll(Connection conn);

    /** @Author: pony
     * @Description: 返回数据表中的条目数
     * @Date: 18:05 2022/4/26
     * @param: conn
     * @return: java.lang.Long
     * @Version: 1.0
     */
    Long getCount(Connection conn);
    //返回数据表中最大的生日
    Date getMaxBirth(Connection conn);
}
