import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
    private JTextField jtf = new JTextField(10);
    private JTextField jtf2 = new JTextField(10);
    private JTextField bmi = new JTextField(10);
    private JButton submit = new JButton("Submit");
    private JTextArea jta = new JTextArea();
    private DataOutputStream outputToServer;
    private DataInputStream inputFromServer;

  public static void main(String[] args) 
  {
    new Client();
  }

  public Client() 
  {
	JFrame f = new JFrame("BMI Calculator");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    JPanel p = new JPanel();
    p.setLayout(new GridLayout(2,2));
    p.add(new JLabel("Enter height"));
    p.add(jtf);
    jtf.addActionListener(this);
    p.add(new JLabel("Enter weight"));
    p.add(jtf2);
    jtf2.addActionListener(this);
    f.add(p, BorderLayout.NORTH);
    
    JPanel sp = new JPanel();
    sp.setLayout(new FlowLayout());
    sp.add(new JLabel("Press to communicate with Server"));
    submit.addActionListener(this);
    sp.add(submit);
    f.add(sp,BorderLayout.CENTER);
    
    bmi.setEditable(false);
    JPanel op = new JPanel();
    op.setLayout(new GridLayout(1,2));
    op.add(new JLabel("BMI: "));
    op.add(bmi);
    f.add(op, BorderLayout.SOUTH);

    f.setTitle("Client");
    f.pack();
    f.setVisible(true);

    try 
    {
      Socket socket = new Socket("localhost", 8000);
      inputFromServer = new DataInputStream(socket.getInputStream());
      outputToServer = new DataOutputStream(socket.getOutputStream());
    }
    catch (IOException ex) 
    {
      jta.append(ex.toString() + '\n');
    }
  }

  public void actionPerformed(ActionEvent e) 
  {
    String actionCommand = e.getActionCommand();
    if (e.getSource() instanceof JButton) 
    {
      try 
      {
        double height = Double.parseDouble(jtf.getText().trim());
        double weight = Double.parseDouble(jtf2.getText().trim());
        outputToServer.writeDouble(height);
        outputToServer.writeDouble(weight);
        outputToServer.flush();
        bmi.setText(""+(inputFromServer.readDouble()));
      }
      catch (IOException ex) 
      {
        System.err.println(ex);
      }
    }
  }
}