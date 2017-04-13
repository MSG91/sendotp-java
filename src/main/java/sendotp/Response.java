package sendotp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author gauravmahawar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
  private String message;
  private String type;
  
  public Response() {
    
  }
  
  public Response(String message, String type) {
    this.message = message;
    this.type = type;
  }
  
  public String getMessage() {
    if(this == null) {
      return null;
    }
    return this.message;
  }
  
  public String getType() {
    if(this == null) {
      return null;
    }
    return this.type;
  }
}
