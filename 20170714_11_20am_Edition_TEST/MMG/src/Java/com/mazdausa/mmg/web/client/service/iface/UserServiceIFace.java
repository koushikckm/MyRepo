/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.service.iface;

import java.text.ParseException;

import com.mazdausa.mmg.web.client.request.vo.UserGetDetailRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserProfileRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserUpdateDetailsRequestVO;
import com.mazdausa.mmg.web.client.response.vo.UserGetDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserProfileResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserUpdateDetailsResponseVO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * This is the Service layer interface, that will contain all the functionalities related to User functions.
 * @author PankajB
 * @version 1.0
 */
public interface UserServiceIFace {


    /**
     * This Function will perform the User Registration.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO performRegistration(UserProfileRequestVO userProfileReqeustVO,HttpServletRequest request,String guid);

    /**
     * This Function will perform the login in the Mazda Web Services.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO performLogin(UserProfileRequestVO userProfileReqeustVO,HttpServletRequest request,String guid);


    /**
     * This function will perform forgot password function for Users.
     * @param userId
     * @return
     */
    public UserProfileResponseVO performForgotPassword(String userId);

    /**
     * This is the function, which will be responsible for getting the Customer Details by providing a set of parameters encapsulated in the object.
     * @param userDetailRequestVO
     * @return
     */
    public UserGetDetailResponseVO getUserDetails(UserGetDetailRequestVO userDetailRequestVO, @Context HttpHeaders headers);


    /**
     * This Function will update the User Details in the System.
     * @param userUpdateDetailsRequestVO
     * @return
     */
    public UserUpdateDetailsResponseVO updateUserDetails(UserUpdateDetailsRequestVO userUpdateDetailsRequestVO, @Context HttpHeaders headers);

    /**
     * This function will perform change password function for Users.
     * @param userId
     * @return
     */
    public UserProfileResponseVO performChangePassword(UserProfileRequestVO userProfileRequestVO);

    /**
     * This function will perform forgot password implementation for Users.
     * @param userId
     * @return
     */
    public UserProfileResponseVO forgotPassword(UserProfileRequestVO userProfileRequestVO);

    /**
     * This function will validate the reset password link.
     * @param encryptedVal
     * @return
     * @throws ParseException 
     */
        
    public UserProfileResponseVO verifyResetPasswordLink(String  encryptedVal) throws ParseException;

    /**
     * This function will update the new password.
     * @param userId
     * @return
     */
    public UserProfileResponseVO updatePassword(UserProfileRequestVO userProfileRequestVO);

    /**
     * This function will update vehicle name for he vin.
     * @return
     */
    public UserProfileResponseVO addOrUpdateVinCarlineName(UserProfileRequestVO userProfileReqeustVO,HttpServletRequest request);
    
    /**
     * This function will fetch vehicle name for he vin.
     * @param vin
     * @return
     */
    public UserProfileResponseVO getVinCarlineName(String vin);
}
