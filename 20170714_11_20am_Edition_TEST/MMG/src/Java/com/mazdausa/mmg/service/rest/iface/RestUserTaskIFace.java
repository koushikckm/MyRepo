/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.iface;

import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailsUpdateRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserTaskRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailsUpdateResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserTaskResponseVO;

/**
 * This is an REST based Client User INterface, which will contain a list of all the REST Based user functionalities provided by MAZDA.
 * @author PankajB
 * @version 1.0
 */
public interface RestUserTaskIFace {


    /**
     * REST Based function to register the User.
     * @param userRegistrationVO
     * @return
     */
    public RestUserTaskResponseVO registerUser(RestUserTaskRequestVO userTaskRequestVO);

    /**
     * This Function will perform the login on to the Mazda User Services
     * @param userTaskRequestVO
     * @return
     */
    public RestUserTaskResponseVO loginUser(RestUserTaskRequestVO userTaskRequestVO);

    /**
     * This Function will invoke the Forgot Password function at MAZDA Web Services.
     * @param userTaskRequestVO
     * @return
     */
    public RestUserTaskResponseVO invokeForgotPassword(RestUserTaskRequestVO userTaskRequestVO);


    /**
     * This Function will perform the change password on to the Mazda User Services
     * @param userTaskRequestVO
     * @return
     */
    public RestUserTaskResponseVO performChangePassword(RestUserTaskRequestVO userTaskRequestVO);

    /**
     * This Function will return the details of the customer, given the parameters that are being passed.
     * @param userDetailRequestVO
     * @return
     */
    public RestUserDetailResponseVO getUserDetails(RestUserDetailRequestVO userDetailRequestVO);

    /**
     * This Function will update the user details in the Mazda System.
     * @param userDetailsUpdateRequestVO
     * @return
     */
    public RestUserDetailsUpdateResponseVO updateUserDetails(RestUserDetailsUpdateRequestVO userDetailsUpdateRequestVO);
    
}
