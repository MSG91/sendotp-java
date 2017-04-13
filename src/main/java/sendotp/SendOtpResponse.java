package sendotp;

import com.fasterxml.jackson.core.JsonProcessingException;
import sendotp.util.JsonUtil;

/**
 *
 * @author gauravmahawar
 */
public class SendOtpResponse extends Response {
  private String otp;
  
  public SendOtpResponse(String otp, String message, String type) {
    super(message, type);
    this.otp = otp;
  }
  
  public String asString() {
    try {
      return JsonUtil.toJsonAsString(this);
    } catch (JsonProcessingException ex) {
      return null;
    }
  }
}
