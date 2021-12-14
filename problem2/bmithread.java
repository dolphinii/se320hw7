import java.io.*;
import java.net.*;

public class bmithread extends Thread
{
	protected Socket socket;
	
	public bmithread(Socket clientSocket)
	{
		socket=clientSocket;
	}
	
	public void run()
	{
		DataInputStream i = null;
		DataOutputStream o = null;
		try 
		{
			i = new DataInputStream(socket.getInputStream());
			o = new DataOutputStream(socket.getOutputStream());
		} 
		catch (IOException e) {e.printStackTrace();}
		while(true)
		{
	      double height;
	      double weight;
		  try 
		  {
			  height = i.readDouble();
			  weight = i.readDouble();
			  double bmi = weight / (height*height);
			  o.writeDouble(bmi);
		  }
		  catch(IOException e) {return;}
		  try 
		  {
			socket.close();
		  } 
		  catch (IOException e) {e.printStackTrace();}
		  return;
		}
	}
}
