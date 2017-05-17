package effat;

//importing packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
 
@SuppressWarnings("serial")
public class SwingSearchApp extends JFrame implements ActionListener 
{
 
//Initializing Components
    JLabel lb, lb1, lb2, lb3, lb4, lb5;
    JTextField tf1, tf2, tf5;
    JButton btn;
    Container c;
   
   //prescreption text
	JTextArea tf3;
 

 //middlePanel.setBorder ( new TitledBorder ( new EtchedBorder () ) );
    
    //Creating Constructor for initializing JFrame components
    SwingSearchApp() {
        //Providing Title
        super("Fetching Medical Information");
        lb5 = new JLabel("Enter Disease:");
        lb5.setBounds(20, 20, 100, 20);
        tf5 = new JTextField(20);
        tf5.setBounds(130, 20, 200, 20);
 
        btn = new JButton("Submit");
        btn.setBounds(50, 50, 100, 20);
        btn.addActionListener(this);
 
        lb = new JLabel("Fetching Medical Information From Database");
        lb.setBounds(30, 80, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        
      
  
        lb1 = new JLabel("FileID");
        lb1.setBounds(20, 120, 100, 20);
        tf1 = new JTextField(50);
        tf1.setBounds(130, 120, 200, 20);
        lb2 = new JLabel("Disease");
        lb2.setBounds(20, 150, 100, 20);
        tf2 = new JTextField(100);
        tf2.setBounds(130, 150, 200, 20);
        lb3 = new JLabel("Prescreption");
        lb3.setBounds(20, 280, 100, 20);
        tf3 = new JTextArea(750,720);
       // textArea.append(text + newline);
        tf3.setBounds(130, 180, 740, 420);
        
        setLayout(null);
 
        //Add components to the JFrame
        add(lb5);
        add(tf5);
        add(btn);
 
        add(lb);
        add(lb1);
        add(tf1);
        add(lb2);
        add(tf2);
        add(lb3);
        add(tf3);
      
        //Set TextField Editable False
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
      
    }
 
    public void actionPerformed(ActionEvent e) {
        //Create DataBase Coonection and Fetching Records
 
        try {
            String str = tf5.getText();
 
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String userName = "sa";
            String password = "a";
            String url = "jdbc:sqlserver://localhost:1433"+";databaseName=mjcetDB";
            Connection con = DriverManager.getConnection(url, userName, password);
            
                   
            PreparedStatement st = con.prepareStatement("select * from docs where disease=?");
            st.setString(1, str);
 
            //Excuting Query
            ResultSet rs = st.executeQuery();
 
            if (rs.next()) {
                String s = rs.getString(1);
                String s1 = rs.getString(3);
                String s2 = rs.getString(2);
               // String s3 = rs.getString(4);
 
                //Sets Records in TextFields.
                tf1.setText(s);
                tf2.setText(s1);
                tf3.setText(s2);
               // tf4.setText(s3);
            } else {
                JOptionPane.showMessageDialog(null, "Name not Found");
            }
 
            //Create Exception Handler
        } catch (Exception ex) {
 
            System.out.println(ex);
        }
    }
//Running Constructor
    public static void main(String args[]) {
        new SwingSearchApp();
    }
} 
