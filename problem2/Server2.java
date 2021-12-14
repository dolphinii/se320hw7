
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server2 extends JFrame 
{
	private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea();
 
  public static void main(String[] args) 
  {
    new Server2();
  }

  public Server2() 
  {
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    try 
    {
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("Server started at " + new Date() + '\n');
      Socket socket = null;
      while (true) 
      {
    	  socket=serverSocket.accept();
    	  new bmithread(socket).start();
      }
    }
    catch(IOException ex) 
    {
      System.err.println(ex);
    }
  }
}
