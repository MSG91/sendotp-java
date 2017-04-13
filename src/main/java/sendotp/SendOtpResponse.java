package sendotp;

/**
 *
 * @author gauravmahawar
 */
public class SendOtpResponse {
  private String message;
  private int statusCode;
  
  public SendOtpResponse(String message, int statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public int getStatusCode() {
    return this.statusCode;
  }
}
