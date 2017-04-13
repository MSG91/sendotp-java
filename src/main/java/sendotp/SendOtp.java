package sendotp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author gauravmahawar
 */
public class SendOtp {
  
  private static final String MSG91_URL = "https://control.msg91.com/api/";
  private static final String SEND_PATH = "sendotp.php";
  private static final String RETRY_PATH = "retryotp.php";
  private static final String VERIFY_PATH = "verifyRequestOTP.php";
  
  private String authKey;
  private String messageTemplate;
  
  public SendOtp(String authKey, String messageTemplate) {
    this.authKey = authKey;
    if(messageTemplate == null || messageTemplate.isEmpty()) {
      this.messageTemplate = "Your otp is {{otp}}. Please do not share it with anybody";
    } else {
      this.messageTemplate = messageTemplate;
    }
  }
  
  public SendOtp(String authKey) {
    this.authKey = authKey;
    this.messageTemplate = "Your otp is {{otp}}. Please do not share it with anybody";
  }
  
  public String getAuthKey() {
    return this.authKey;
  }
  
  public String getMessageTemplate() {
    return this.messageTemplate;
  }
  
  public void send(String contactNumber, String senderId, String otp) {
    if(otp == null) {
      send(contactNumber, senderId);
    }
    Map<String, String> params = getSendParams(contactNumber, senderId, otp);
    post(SEND_PATH, params, "");
  }
  
  private Map<String, String> getSendParams(String contactNumber, String senderId, String otp) {
    Map<String, String> params = new HashMap<>();
    params.put("authKey", this.authKey);
    params.put("mobile", contactNumber);
    params.put("sender", senderId);
    params.put("message", this.messageTemplate.replace("{{otp}}", otp));
    params.put("otp", otp);
    return params;
  }
  
  private Map<String, String> getRetryParams(String contactNumber, boolean retryVoice) {
    String retryType = "voice";
    if(!retryVoice) {
      retryType = "text";
    }
    Map<String, String> params = new HashMap<>();
    params.put("authKey", this.authKey);
    params.put("mobile", contactNumber);
    params.put("retryType", retryType);
    return params;
  }
  
  private Map<String, String> getVerifyParams(String contactNumber, String otp) {
    Map<String, String> params = new HashMap<>();
    params.put("authKey", this.authKey);
    params.put("mobile", contactNumber);
    params.put("otp", otp);
    return params;
  }
  
  public void send(String contactNumber, String senderId) {
    String otp = generateOtp(6);
    Map<String, String> params = getSendParams(contactNumber, senderId, otp);
    post(SEND_PATH, params, "");
  }
  
  public void retry(String contactNumber, boolean retryVoice) {
    Map<String, String> params = getRetryParams(contactNumber, retryVoice);
    post(RETRY_PATH, params, "");
  }
  
  public void verify(String contactNumber, String otp) {
    Map<String, String> params = getVerifyParams(contactNumber, otp);
    post(VERIFY_PATH, params, "");
  }
  
  public String generateOtp(int len) {
    String chars = "0123456789";
    String randomString = "";
    int length = chars.length();
    for (int i = 0; i < len; i++) {
      randomString += chars.split("")[(int) (Math.random() * (length - 1))];
    }
    return randomString;
  }
  
  private int post(final String path, final Map<String, String> params, String userAgent) {
    String url = MSG91_URL + path;
    PostMethod pm = new PostMethod(url);
    pm.setRequestHeader("User-Agent", userAgent);
    params.entrySet().forEach((param) -> {
      pm.addParameter(param.getKey(), param.getValue());
    });
    HttpClient hc = new HttpClient(); 
    try {
      hc.executeMethod(pm);
    } catch (IOException ex) {
    }
    int statusCode = pm.getStatusCode();
    pm.releaseConnection();
    return statusCode;
  }
}
