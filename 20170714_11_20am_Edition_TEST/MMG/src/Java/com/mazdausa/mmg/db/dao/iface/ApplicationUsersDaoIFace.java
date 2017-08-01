/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.UserVO;
import java.util.List;

/**
 * This is the interface, which will list all the functions related to the Users.
 * @author PankajB
 * @version 1.0
 */
public interface ApplicationUsersDaoIFace {


    /**
     * This Function will be responsible for returning the USeR by User Id.
     * @param userId UserId
     * @return
     */
    public UserVO getUser(int userId);


    /**
     * This Function will return the User By its Mail ID
     * @param userMailId
     * @return
     */
    public UserVO getUserByMailId(String userMailId);

    /**
     * This Function will return the User by its Customer Id
     * @param mazdaCustId Customer Id
     * @return
     */
    public UserVO getUserByCustomerId(String mazdaCustId);

    /**
     * This Function will add the User in the database.
     * @param userVO
     */
    public void addUser(UserVO userVO);

    /**
     * This Function will update the details of the User in the Database.
     * @param userVO
     */
    public void updateUser(UserVO userVO);

    /**
     * This Function will mark the User deleted in the database.
     * @param userVO
     */
    public void deleteUser(UserVO userVO);

    /**
     * This Function will return the list of all the User present in the database. 
     */
    public List<UserVO> findAllUsers();

    /**
     * This is the function, which is responsible for returning a list of active Users maintained in the database.
     * @param noOfUsersRequired
     * @param startingFrom
     * @return
     */
    public List<UserVO> getAllActiveUsers(int noOfUsersRequired,int startingFrom);




}
