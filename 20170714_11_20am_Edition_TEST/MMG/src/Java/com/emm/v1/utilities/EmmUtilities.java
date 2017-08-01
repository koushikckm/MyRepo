/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emm.v1.utilities;

import org.springframework.scheduling.annotation.Async;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.emm.v1.constants.DeviceType;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import com.emm.v1.constants.EMMConstants;
import com.emm.v1.mobile.vo.EmmSessionTokenVO;
import com.emm.v1.mobile.vo.MobileCharacteristicsVO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.wurfl.wurflapi.CapabilityMatrix;
import net.sourceforge.wurfl.wurflapi.ListManager;
import net.sourceforge.wurfl.wurflapi.ObjectsManager;
import net.sourceforge.wurfl.wurflapi.UAManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.stereotype.Component;
import static java.lang.Math.round;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.min;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

/**
 * This Class is the responsible for performing all the EMM Utilities Functions.
 * @author PankajB
 */
@Component
public class EmmUtilities {

    private static final Logger logger = LoggerFactory.getLogger(EmmUtilities.class);
    private static UAManager uaManager = null;
    private static ListManager listManager = null;
    private static CapabilityMatrix capabilityMatrix = null;
    
    @Autowired
    EMMConstants emmConstants;
    /**
     * ThIs is the variable used for generating the  Secured Random Number, as suggested by Danny.
     */
    private static SecureRandom secureRandomNumberGenerator = null;

    /**
     * Initializing the Secure Random Number.
     */
    static {
        try {
            secureRandomNumberGenerator = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            logger.error("An Error has generatedw while generated the Secure Random Number. ");
        }
    }

    @PostConstruct
    public void init() {
        try {
            logger.info(">> ***** Initializing the WURFL Utilities with the WURFL XML File ***** ");

            ObjectsManager.getWurflInstance(emmConstants.EMM_WURFL_LOCATION);
            uaManager = ObjectsManager.getUAManagerInstance();
            listManager = ObjectsManager.getListManagerInstance();
            capabilityMatrix = ObjectsManager.getCapabilityMatrixInstance();
            logger.info("********* Completed the WURFL Initialization *********** ");

            // Prepare the parameters to the cipthers
            Authenticator.setDefault(
                    new Authenticator() {

                        @Override
                        public PasswordAuthentication getPasswordAuthentication() {
                            logger.info("******** Initializing the Network/Proxy Settings. ");
                            return new PasswordAuthentication(System.getProperty("http.proxyUser").trim(), System.getProperty("http.proxyPassword").trim().toCharArray());

                        }
                    });

            // Jersey uses java.util.logging - bridge to slf4
            // The Below code has been used just to Redirect all the Logging from the JERSEY FILTERS TO THE LOG FILES TO SLF4j.
            java.util.logging.Logger rootLogger = LogManager.getLogManager().getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (int i = 0; i < handlers.length; i++) {
                rootLogger.removeHandler(handlers[i]);
            }
            SLF4JBridgeHandler.install();
            logger.info("************* COMPLETING THE LOGGER REDIRECTION ******************* ");


        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("AN Runtime Exception has been occured, while Loading the WURFL with XML File");
        }
    }

    /**
     * This is the function, which will be responsible for getting the MObile Phone Characteristics
     * @param userAgent
     * @return
     */
    public static MobileCharacteristicsVO getMobilePhoneCharacteristics(String userAgent, String requestorIp) {

        MobileCharacteristicsVO mobileCharacteristicsVO = new MobileCharacteristicsVO();
        mobileCharacteristicsVO.setUserAgent(userAgent);
        mobileCharacteristicsVO.setRequestorIp(requestorIp);

        String deviceId = uaManager.getDeviceIDFromUALoose(userAgent);
        HashMap<String, String> listOfCapabilities = listManager.getCapabilitiesForDeviceID(deviceId);

        mobileCharacteristicsVO.setPhoneBrand(listOfCapabilities.get(EMMConstants.BRAND_NAME));
        mobileCharacteristicsVO.setPhoneType(listOfCapabilities.get(EMMConstants.MODEL_NAME));

        return mobileCharacteristicsVO;


    }

    /**
     * This is the function, which will be responsible for generating the Session and a Token
     * @param request
     * @return
     */
    public static EmmSessionTokenVO getSessionAndToken(HttpServletRequest request) {
        EmmSessionTokenVO sessionTokenVO = new EmmSessionTokenVO();
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null & request.isRequestedSessionIdValid()) {
            httpSession.invalidate();      // Invalidate the HttpSession and create a new Token and Session.
        }

        // Create a new Session and Token for the User.
        httpSession = request.getSession(true);
        sessionTokenVO.setSessionid(httpSession.getId());
        sessionTokenVO.setTokenid(EmmUtilities.getToken());

        return sessionTokenVO;
    }

    /**
     * This Function returns an GUID.
     * @return
     */
    public static String getGUID() {
        UUID guid = UUID.randomUUID();
        return guid.toString();
    }

    /**
     * An Utility function has been returned which is being responsible for returning a Ranom number either through
     * the Secure Random Number or through the Math class random Utility
     * @return
     */
    private static double getSecureRandomNumber() {
        double randomNumber = 0.0;
        try {

            randomNumber = secureRandomNumberGenerator.nextDouble();

        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
            // randomNumber = random();
            randomNumber = secureRandomNumberGenerator.nextDouble();
        }
        return randomNumber;
    }

    /**
     * This is the function which will be responsible for Generating the TOKEN.
     * @return
     */
    public static String getToken() {
        StringBuilder sb = new StringBuilder();
        int length = 48;
        for (int i = length; i > 0; i -= 12) {
            int n = min(12, abs(i));
            sb.append(StringUtils.leftPad(Long.toString(round(getSecureRandomNumber() * pow(36, n)), 36), n, '0'));
            //OLD CODE sb.append(StringUtils.leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
        }

        return sb.toString();
    }

    /**
     * This is the function, which will return Decrypted Data based upon the type of Client Device.
     * @param encryptedData
     * @param userAgent
     * @return
     */
    public String getDecryptedData(String encryptedData, String userAgentOfDevice) {

        if(userAgentOfDevice != null && !userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_ANDROID)){
            return new String(transform(false, Base64.decode(encryptedData)));
        }else if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_ANDROID)){
            String decryptedPassword = null;
            String passPhrase   = emmConstants.EMM_AES_ENCRYPTION_KEY;
            EmmUtilities emmUtilities = new EmmUtilities();
            decryptedPassword = emmUtilities.decrypt(encryptedData, passPhrase);
            logger.debug("decryptedPassword :: ", decryptedPassword);
            System.out.println("decryptedPassword :: "+ decryptedPassword);
            return decryptedPassword;
        }else{
            return null;
        }
        
    }

    /**
     * This is the function, which will be responsible for Encrypting the data based upon the Type of Client Device.
     * @param dataToBeEncrypted  Data to be encrypted
     * @param userAgentOfDevice  Type of User Agent.
     * @return
     */
    public String getEncryptedData(String dataToBeEncrypted, String userAgentOfDevice) {


        byte enc[] = transform(true, dataToBeEncrypted.getBytes());
        return new String(Base64.encode(enc));
        /* <!-- This block of code is for encryption(Android) based on device type type i.e., user agent-->
        String encryptedString = "";
        //String passPhrase   = emmConstants.EMM_AES_ENCRYPTION_KEY;
        String passPhrase = "m@zd@10017017Int33r@IT";
        EmmUtilities emmUtilities = new EmmUtilities();
        encryptedString = emmUtilities.encrypt(dataToBeEncrypted, passPhrase);
        System.out.println("encryptedString :: "+ encryptedString);
        return encryptedString;
         *
         */
    }

    /**
     * This is the general utility function, which will be responsible for writing the code for iPhone Android Notification.
     * @param data
     * @param iPhoneDeviceToken
     * @param deviceType
     * @param args
     */
    @Async
    public void sentNotification(String data, String iPhoneDeviceToken, DeviceType deviceType, String... args) {

        logger.debug("******** SENDING THE NOTIFICATION to TOKEN= {} ", iPhoneDeviceToken);
        //    if (deviceType == DeviceType.IPHONE) {
        try {

            // The Below Line Code needs to be modified, later on, when we have the certificate.
            //ApnsService service = APNS.newService().withCert("g:\\APNS\\mazdarajesh\\p12\\AppDevPUSHCertificates.p12", "mazda").withSandboxDestination().build();
            // ApnsService service = APNS.newService().withCert("g:\\APNS\\Certificates.p12", "interrait").withSandboxDestination().build();
            ApnsService service = APNS.newService().withCert(emmConstants.IPHONE_PUSH_APNS_CERTIFICATE_LOCATION, emmConstants.IPHONE_PUSH_APNS_CERTIFICATE_PASSWORD).withSandboxDestination().build();
            logger.info("Sending data \"{}\" to token={}", data, iPhoneDeviceToken);
            String payload = APNS.newPayload().alertBody(data).build();
            service.push(iPhoneDeviceToken, payload);
        } catch (Exception ex) {
            logger.error("An Exception has occured, while sending an IPHONE notifcation to device Token : {} ", iPhoneDeviceToken, ex);
        }
        //}*/
    }

    public static void main(String args[]) {
        //sentNotification("Hi Hello", "e42eeafc3d26e571fc7bada5ee0e037b76b57867609bc0fef3b82a5830a2247e", DeviceType.IPHONE, args);
        sentNotification1("You have a recall for VIN JM1BK32F881118468", "8735f030b0f47e45d2999d775d0f92e2d0dfcbf63f877bca6b9b6f110fb83b59", DeviceType.IPHONE, args);
        //sentNotification1("You have a recall for VIN JM1BK12FX71742372", "e3ce578f2097cac86efe3134a9731a47f8ed441d09249528624c4c807bf5c169", DeviceType.IPHONE, args);
        //String strTest = "+Z9WYTseEmm+csuc5kcUNg==";
        
        //String strTest = "mazdausa";
        //String encryptedStr = getEncryptedData(strTest,"");
        //getDecryptedData(encryptedStr,"");

        // sentNotification("Hi Hello, how ru .", "34e5fb20eb64f67cd8fdb1d4a3876d16c1c29eac358b1dc4685d69094873cced", DeviceType.IPHONE, args);
        //sentNotification("Sent sample data. .", "e3ce578f2097cac86efe3134a9731a47f8ed441d09249528624c4c807bf5c169", DeviceType.IPHONE, args);
        //  5cfc31cc82e119f2e19c565045bf9b053f868f402736ef1fd588251a9eb4dd8e
        // e42eeafc3d26e571fc7bada5ee0e037b76b57867609bc0fef3b82a5830a2247e

    }

    private byte[] transform(boolean encrypt, byte[] inputBytes) {
        try {
            byte[] key = DigestUtils.md5(emmConstants.EMM_AES_ENCRYPTION_KEY.getBytes("UTF-8"));

            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
            cipher.init(encrypt, new KeyParameter(key));

            ByteArrayInputStream input = new ByteArrayInputStream(inputBytes);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            int inputLen;
            int outputLen;

            byte[] inputBuffer = new byte[1024];
            byte[] outputBuffer = new byte[cipher.getOutputSize(inputBuffer.length)];

            while ((inputLen = input.read(inputBuffer)) > -1) {
                outputLen = cipher.processBytes(inputBuffer, 0, inputLen, outputBuffer, 0);
                if (outputLen > 0) {
                    output.write(outputBuffer, 0, outputLen);
                }
            }

            outputLen = cipher.doFinal(outputBuffer, 0);
            if (outputLen > 0) {
                output.write(outputBuffer, 0, outputLen);
            }

            return output.toByteArray();
        } catch (Exception ex) {
            logger.error("An Exception has occurd, while Encryption/Decryption ", ex);
        }
        return "".getBytes();
    }

    public static void sentNotification1(String data, String iPhoneDeviceToken, DeviceType deviceType, String... args) {

        logger.debug("******** SENDING THE NOTIFICATION to TOKEN= {} ", iPhoneDeviceToken);
        //    if (deviceType == DeviceType.IPHONE) {
        try {

            // The Below Line Code needs to be modified, later on, when we have the certificate.
            ApnsService service = APNS.newService().withCert("g:\\APNS\\mazdarajesh\\p12\\AppDevPUSHCertificates.p12", "mazda").withSandboxDestination().build();
            // ApnsService service = APNS.newService().withCert("g:\\APNS\\Certificates.p12", "interrait").withSandboxDestination().build();
            //ApnsService service = APNS.newService().withCert(emmConstants.IPHONE_PUSH_APNS_CERTIFICATE_LOCATION, emmConstants.IPHONE_PUSH_APNS_CERTIFICATE_PASSWORD).withSandboxDestination().build();

            String payload = APNS.newPayload().alertBody(data).build();
            service.push(iPhoneDeviceToken, payload);
        } catch (Exception ex) {
            logger.error("An Exception has occured, while sending an IPHONE notifcation to device Token : {} ", iPhoneDeviceToken, ex);
        }
        //}*/
    }
    /**
     * Takes a encrypted String as an argument, decrypts and returns the
     * decrypted String.
     * @param dataToBeDecrypt Encrypted String to be decrypted
     * @return decryptedData Decrypted version of the provided String
     */
    public static String decrypt(String dataToBeDecrypt, String keyPhrase) {
            Cipher cipher;
        try {
            // Decode base64 to get bytes
             byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
            };

            // Iteration count
            int iterationCount = 19;
            //String passPhrase   = "m@zd@10017017Int33r@IT";
            //String passPhrase   = emmConstants.EMM_AES_ENCRYPTION_KEY;


            KeySpec keySpec = new PBEKeySpec(keyPhrase.toCharArray(), salt, iterationCount);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            cipher = Cipher.getInstance(key.getAlgorithm());
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(dataToBeDecrypt);
            //byte[] dec = Base64Coder.decode(dataToBeDecrypt);
            //Decrypt
            //byte[] utf8 = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(dataToBeDecrypt));
            byte[] utf8 = cipher.doFinal(Base64Coder.decode(dataToBeDecrypt));

            // Decode using utf-8
            String decryptedData = new String(utf8, "UTF8");
            logger.debug("decryptedData in decrypt function :: ", decryptedData);
            return decryptedData;

        } catch (Exception e) {
            logger.error("An Exception occured while decrypting the encrypted string from Androd {}", e);
        }
        return "";
    }

    /**
  * Takes a single String as an argument and returns an Encrypted version
  * of that String.
  * @param str String to be encrypted
  * @return <code>String</code> Encrypted version of the provided String
  */
 public String encrypt(String str, String keyPhrase) {
     Cipher cipher;
     try {
         // Decode base64 to get bytes
            byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
            };

            // Iteration count
            int iterationCount = 19;
            //String passPhrase   = "m@zd@10017017Int33r@IT";
            //String passPhrase   = emmConstants.EMM_AES_ENCRYPTION_KEY;


            KeySpec keySpec = new PBEKeySpec(keyPhrase.toCharArray(), salt, iterationCount);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            cipher = Cipher.getInstance(key.getAlgorithm());
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
         // Encode the string into bytes using utf-8
         byte[] utf8 = str.getBytes("UTF8");

         // Encrypt
         byte[] enc = cipher.doFinal(utf8);

         // Encode bytes to base64 to get a string
         //return new sun.misc.BASE64Encoder().encode(enc);
         return new String(Base64Coder.encode(enc));

     }  catch (Exception e) {
            logger.error("An Exception occured while encrypting the string from Androd {}", e);
        }
     return "";
 }
 /**
  * Takes a single String as an argument and returns an Encrypted version
  * of that String.
  * @param str String to be encrypted
  * @return <code>String</code> Encrypted version of the provided String
  */
 public String encryptURLSafe(String str, String keyPhrase) {
     Cipher cipher;
     try {
         // Decode base64 to get bytes
            byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
            };

            // Iteration count
            int iterationCount = 19;
            //String passPhrase   = "m@zd@10017017Int33r@IT";
            //String passPhrase   = emmConstants.EMM_AES_ENCRYPTION_KEY;


            KeySpec keySpec = new PBEKeySpec(keyPhrase.toCharArray(), salt, iterationCount);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            cipher = Cipher.getInstance(key.getAlgorithm());
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
         // Encode the string into bytes using utf-8
         byte[] utf8 = str.getBytes("UTF8");

         // Encrypt
         byte[] enc = cipher.doFinal(utf8);

         // Encode bytes to base64 to get a string
         //return new sun.misc.BASE64Encoder().encode(enc);
         return new String(UrlBase64.encode(enc));

     }  catch (Exception e) {
            logger.error("An Exception occured while encrypting the string", e);
        }
     return "";
 }
 /**
  * Takes a encrypted String as an argument, decrypts and returns the
  * decrypted String.
  * @param dataToBeDecrypt Encrypted String to be decrypted
  * @return decryptedData Decrypted version of the provided String
  */
 public static String decryptURLSafe(String dataToBeDecrypt, String keyPhrase) {
         Cipher cipher;
     try {
         // Decode base64 to get bytes
          byte[] salt = {
         (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
         (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
         };

         // Iteration count
         int iterationCount = 19;
         //String passPhrase   = "m@zd@10017017Int33r@IT";
         //String passPhrase   = emmConstants.EMM_AES_ENCRYPTION_KEY;


         KeySpec keySpec = new PBEKeySpec(keyPhrase.toCharArray(), salt, iterationCount);

         SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

         cipher = Cipher.getInstance(key.getAlgorithm());
         AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
         cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

         //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(dataToBeDecrypt);
         //byte[] dec = Base64Coder.decode(dataToBeDecrypt);
         //Decrypt
         //byte[] utf8 = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(dataToBeDecrypt));
         byte[] utf8 = cipher.doFinal(UrlBase64.decode(dataToBeDecrypt));

         // Decode using utf-8
         String decryptedData = new String(utf8, "UTF8");
         logger.debug("decryptedData in decrypt function :: ", decryptedData);
         return decryptedData;

     } catch (Exception e) {
         logger.error("An Exception occured while decrypting the encrypted string ", e);
     }
     return "";
 }
}
