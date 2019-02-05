package streamio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import streamio.evillair.EvilLairServer;

public class StreamLab {
  private PasswordCracker passwordCracker;

  public static void main(String[] args) throws Exception {
    String secretPhrase = "u1136324";

    EvilLairServer.startEvilLairServer();
    GuyOnTheInside guyOnTheInside = GuyOnTheInside.makeContact(secretPhrase);

    StreamLab lab = new StreamLab();

    lab.getPasswordFrom(guyOnTheInside);

    lab.getPasswordFrom(new File("deaddrop.txt"));

    lab.getPasswordFromEvilLair();
    System.out.println("Your password is: " + lab.crackPassword());
  }

  public StreamLab() throws Exception {

    passwordCracker = new PasswordCracker();
  }

  private void getPasswordFrom(GuyOnTheInside guyOnTheInside) throws IOException {
    String passwordPart = guyOnTheInside.getPasswordPart();
    InputStream streamFromString = new ByteArrayInputStream(passwordPart.getBytes());
    passwordCracker.updatePassword(streamFromString);
  }

  private void getPasswordFrom(File file) throws FileNotFoundException, IOException {
    //TODO: get InputStream for a File! Make sure you close any opened resources!
    try (InputStream targetStream = new FileInputStream(file)) {
      passwordCracker.updatePassword(targetStream);
    }
  }

  private void getPasswordFromEvilLair() throws UnknownHostException, IOException {
    try (Socket evilLairHack = new Socket("localhost", 8080);
        PrintWriter out = new PrintWriter(evilLairHack.getOutputStream(), true);
        InputStream inputStream = evilLairHack.getInputStream()) {
      //Send secret phrase to the Evil Lair Server.
      out.println("u1136324");
      //TODO: read response from Evil Lair Server! Then update the password cracker
      passwordCracker.updatePassword(inputStream);
    }
  }

  public String crackPassword() {
    return passwordCracker.crackPassword();
  }
}
