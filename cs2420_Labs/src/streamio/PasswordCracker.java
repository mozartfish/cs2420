package streamio;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordCracker {

  private MessageDigest hasher;

  public PasswordCracker() throws NoSuchAlgorithmException {
    hasher = MessageDigest.getInstance("SHA-256");
  }

  public void updatePassword(InputStream inputStream) throws IOException {
    //TODO: read from the inputstream and update the hasher.
//    hasher.update(new byte[0], 0, 0);
    int bytes = 0;
    byte[] buffer = new byte[1024];
    while ((bytes = inputStream.read(buffer, 0, buffer.length)) > 0) {
      hasher.update(buffer, 0, bytes);
    }
    
  }

  public String crackPassword() {
    return new String(Base64.getEncoder().encode(hasher.digest()));
  }
}
