/**
 * 
 */
package  com.mazda.common.utility;

import com.mazda.common.transferobject.BusinessErrorTO;
import com.mazda.exception.BusinessException;

/**
 * @author Vibhor.Gupta
 * @since 20-07-2012
 * @version 1.0
 * Business exception utility used to throw exception according to business rules applied
 */
public class ExceptionUtil {
	
	/**
	 * @author Vibhor.Gupta
	 * @since 20-07-2012
	 * @version 1.0
	 * @param exceptionMessage
	 * @param errorCode
	 * @param errorMessage
	 * @return
	 */
	public static BusinessException getBusinessException(String exceptionMessage,String errorCode,String errorMessage)
	{
		BusinessException businessException  = new BusinessException(exceptionMessage);
		BusinessErrorTO error = new BusinessErrorTO();
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		businessException.addError(error);
		return businessException;
	}
	/**
	 * @author Vibhor.Gupta
	 * @since 20-07-2012
	 * @version 1.0
	 * @param errorCode
	 * @param errorMessage
	 * @return
	 */
	public static BusinessException getBusinessException(String errorCode,String errorMessage)
	{
		BusinessException businessException  = new BusinessException();
		BusinessErrorTO error = new BusinessErrorTO();
		error.setCode(errorCode);
		error.setMessage(errorMessage);
		businessException.addError(error);
		return businessException;
	}

}
