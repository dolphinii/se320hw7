
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea();
 
	public static void main(String[] args) 
	{
		new Server();
    }

  public Server() 
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
      Socket socket = serverSocket.accept();
      DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
      DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

      while (true) 
      {
        double height = inputFromClient.readDouble();
        double weight = inputFromClient.readDouble();
        double bmi = weight / (height*height);

        outputToClient.writeDouble(bmi);

        jta.append("Height received from client: " + height + '\n');
        jta.append("Weight received from client: " + weight + '\n');
        jta.append("BMI found: " + bmi + '\n');
      }
    }
    catch(IOException ex)
    {
      System.err.println(ex);
    }
  }
}