/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_;

/**
 *
 * @author ahmad
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Key;
import static java.time.Instant.now;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Server_ {

    /**
     * @param args the command line arguments
     */
    // threads class
    static class clientHandler implements Runnable
    {
        Socket c;
        
        public clientHandler (Socket c)
        {
            this.c = c;        
        }
        
        @Override
        public void run()
        {       
                DataOutputStream dos = null;
            try {
                int client_resp, balance;
                dos = new DataOutputStream(c.getOutputStream());
                DataInputStream dis = new DataInputStream(c.getInputStream());
                //4.perform IO with client
                //here we know what is establishing connection with the server
                //"Hello" if it was a client
                //"I'm another server" if it was server
                String whoAmI = dis.readUTF();
                if(whoAmI.equalsIgnoreCase("Hello")){
                    dos.writeUTF("1.Login\n2.Create Account");
                    client_resp = dis.readInt();
                    switch(client_resp){
                        case 1://Login
                            Login(c);
                            if(Session_ID != 0){
                                dos.writeUTF("1.Show balance"
                                        + "\n2.Deposit"
                                        + "\n3.Withdraw"
                                        + "\n4.Transfer to another account"
                                        + "\n5.Transfer to another bank"
                                        + "\n6.Show history");
                                client_resp = dis.readInt();
                                switch (client_resp){
                                    case 1:
                                        balance = ShowAccountInfo(c);
                                        dos.writeUTF("Your balance is:" + balance);
                                        break;
                                    case 2:
                                        balance = Deposit(c);
                                        dos.writeUTF("Your new balance is:" + balance);
                                        break;
                                    case 3:
                                        balance = Withdraw(c);
                                        dos.writeUTF("Your new balance is:" + balance);
                                        break;
                                    case 4:
                                        balance = TransferToAnotherAccount(c);
                                        dos.writeUTF("Your new balance is:" + balance);
                                        break;
                                    case 5:
                                        balance = TransferToAnotherAccountInAnotherBank(c, Session_ID);
                                        dos.writeUTF("Your new balance is:" + balance);
                                        break;
                                    case 6:
                                        String History = ShowHistory(c);
                                        dos.writeUTF(History);
                                        break;
                                }
                            }
                            
                            else 
                            {
                                //do something
                            }
                            break;
                        case 2://create account
                            dos.writeUTF("Your ID is: " + CreateAccount(c));
                            break;
                        default:
                            break;
                    }
                    
                }
                else if(whoAmI.equalsIgnoreCase("I'm another server")){
                    System.out.println("I hear you loud and clear");
                    dos.writeUTF("1.Transfer to account that I have");
                    char serverChoise = dis.readChar();
                    System.out.println(serverChoise);
                    switch(serverChoise){
                        case '1':
                            System.out.println("the server chose transfer");
                            //getting account ID
                            int IDOfTargetAccount = dis.readInt();
                            System.out.println(IDOfTargetAccount);
                            //check data base for target ID
                            boolean targetIDFound = stmt.execute(" SELECT ID FROM client WHERE ID = '"+IDOfTargetAccount+"'");
                            //return true or false to the server
                            dos.writeBoolean(targetIDFound);
                            if(targetIDFound){
                                //read amount of money
                                int amountTransfered = dis.readInt();
                                System.out.println(amountTransfered);
                                //update data base with targetbalance += amount;
                                boolean error = stmt.execute("UPDATE client SET balance = balance+ '"+amountTransfered+"' WHERE ID = '"+IDOfTargetAccount+"' ");
                                String history_string = "Someone deposited " + amountTransfered + " LE, at " + now();
                                stmt.execute("INSERT INTO history (client_id,transaction) VALUES ('"+IDOfTargetAccount+"' ,'"+history_string+"')");                                         
                            }
                            else{
                                //do something
                            }
                            break;
                            
                    }
                }
                //5. close comm with client
                dos.close();
                dis.close();
                c.close();
            } catch (Exception ex) {
                Logger.getLogger(Server_.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    dos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server_.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
    
    
    
    
    
    
    
    //socket num of this server
    static int socketNumber;
    public static class Crypto {
    private static final String ALGO ="AES";
	private byte[] keyVal;

	public Crypto(String key){
		keyVal =key.getBytes();
	} 

	public String encrypt(String Data)throws Exception{
		Key key =generateKey();
		Cipher c=Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte [] encVal=c.doFinal(Data.getBytes());
		String encryptedValue =new BASE64Encoder().encode(encVal);	
		return encryptedValue;
	}

	public String decrypt(String encryptedData)throws Exception{
		Key key =generateKey();
		Cipher c=Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte [] decVal=c.doFinal(decordedValue);
		String decryptedValue =	new String(decVal);
		return decryptedValue;
	}



	private Key generateKey() throws Exception{
	Key key =new SecretKeySpec(keyVal,ALGO);
	return key;
	}
    }
    static int CreateAccount(Socket c)
    {
        String fullName = "" , password = "";
        int amount, ID = 0;
        try 
        {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());        
            //get user info
            dos.writeUTF("Enter your full name:");
            fullName = dis.readUTF();
            dos.writeUTF("Enter your password:");
            password = dis.readUTF();                    
            dos.writeUTF("Reenter your password:");
            if(!dis.readUTF().equals(password))
            {
                //do something about it
            }
            dos.writeUTF("Enter initial amount of money:");
            amount = dis.readInt();
            if(amount<0)
            {
                //do something about it
            }           
            //Encrypt password
            try{

                        Crypto AES = new Crypto("HAVANAOHNANANADA");
			String encdata =AES.encrypt(password);
                        password = encdata;
			/*System.out.println("encrypted Data - "+ encdata);
			String decdata=AES.decrypt(encdata);
			System.out.println("decrypted Data - "+ decdata);
*/
		}
		catch(Exception ex){
		}
            //save to data base
            boolean error = stmt.execute("INSERT INTO client (full_name, password,balance) VALUES ('"+fullName+"','"+password+"','"+amount+"')");
            if(!error)
            {
                rs = stmt.executeQuery("SELECT MAX(ID) FROM client");
                rs.next();
                ID = rs.getInt(1);
            }            
            else
            {
                //do something about it
            }
            String s = "Create account with initial amount " + amount + " LE, at " + now();
            stmt.execute("INSERT INTO history (client_id,transaction) VALUES ('"+ID+"' ,'"+s+"')");
        } 
        catch (Exception e) {}   
        
        return ID;
    }
    
    static int Session_ID = 0;
    static void Login(Socket c)
    {       
        try {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            dos.writeUTF("Enter your ID");
            String IDReadFromUser = dis.readUTF();
            dos.writeUTF("Enter your password:");
            String password = dis.readUTF();
            //Encrypt password
            try{

                        Crypto AES = new Crypto("HAVANAOHNANANADA");
			String encdata =AES.encrypt(password);
                        password = encdata;
			/*System.out.println("encrypted Data - "+ encdata);
			String decdata=AES.decrypt(encdata);
			System.out.println("decrypted Data - "+ decdata);
*/
		}
		catch(Exception ex){
		}
            //check database for login
            rs = stmt.executeQuery(" SELECT ID FROM client WHERE ID = '"+IDReadFromUser+"' AND password = '"+password+"'");
            rs.next();
            Session_ID = rs.getInt(1);
            System.out.println(Session_ID);
                                  
            //return 0 if not found
            //return ID if found                                 
        } 
        catch (Exception e) {}              
    }   
    
    static int ShowAccountInfo(Socket c)
    {
        int balance = 0;
        try 
        {
            //search in database by ID and return balance
            rs = stmt.executeQuery(" SELECT balance FROM client WHERE ID = '"+Session_ID+"'");
            rs.next();
            balance = rs.getInt(1);
        } 
        catch (SQLException ex){}
        return balance;
    }
    
    static int Deposit(Socket c)
    {
        int balance = 0;
        try {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            dos.writeUTF("Enter the amount of money to Deposit:");
            int amount = dis.readInt();
            if(amount<0)
            {
                //do something
            }
            else
            {
                //update the database with balance+=amount;
                boolean error = stmt.execute("UPDATE client SET balance = balance+ '"+amount+"' WHERE ID = '"+Session_ID+"' ");
                if(!error)
                {               
                    String transaction = "Deposit '"+amount+"' LE";
                    rs = stmt.executeQuery("SELECT balance FROM client WHERE ID = '"+Session_ID+"'");
                    rs.next();
                    balance = rs.getInt(1);
                    balance += amount;
                    String s = "Deposit " + amount + " LE, at " + now();
                    stmt.execute("INSERT INTO history (client_id,transaction) VALUES ('"+Session_ID+"' ,'"+s+"')");
                }
            }
        } 
        catch (Exception e) {}        
        return balance ;
    }
    
    static int Withdraw(Socket c){
        int balance = 0;
        try {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            dos.writeUTF("Enter the amount of money to Withdraw:");
            int amount = dis.readInt();
            
            rs = stmt.executeQuery("SELECT balance FROM client WHERE ID = '"+Session_ID+"'");
            rs.next();
            balance = rs.getInt(1);
            if(amount<0 || balance < amount)
            {
                //add condition to check if the amount is more than the initial balance
                //do something
            }
            else
            {
                //update the database with balance-=amount;
                boolean error = stmt.execute("UPDATE client SET balance = balance - '"+amount+"' WHERE ID = '"+Session_ID+"' ");
                
                if(!error)
                {
                    balance -= amount;
                    String s = "Withdraw " + amount + " LE, at " + now();
                    stmt.execute("INSERT INTO history (client_id,transaction) VALUES ('"+Session_ID+"' ,'"+s+"')");
                }
                
            }
        } 
        catch (Exception e) {}
        return balance;
    }
    
    static int TransferToAnotherAccount(Socket c)
    {
        int balance = 0;
        boolean error;
        try {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            dos.writeUTF("Enter the account ID you want to Transfer to:");
            String IDForTheTargetAccount = dis.readUTF();
            //check the data base for the target ID
            
            rs = stmt.executeQuery(" SELECT ID FROM client WHERE ID = '"+IDForTheTargetAccount+"'");
            
            if(rs.next())
            {
                dos.writeUTF("Enter the amount of money to Transfer to " + IDForTheTargetAccount + ":");
                int amount = dis.readInt();
                if(amount<0)
                {
                    //do something
                }
                else
                {
                    //check if the amount is more than the balance
                    rs = stmt.executeQuery("SELECT balance FROM client WHERE ID = '"+Session_ID+"'");
                    rs.next();
                    balance = rs.getInt(1);
                    
                    if (balance >= amount)
                    {
                        //update target data base with target balance+=amount;
                        error = stmt.execute("UPDATE client SET balance = balance+ '"+amount+"' WHERE ID = '"+IDForTheTargetAccount+"' ");
                        //update self data base with balance-=amount;
                        if(!error)
                            error = stmt.execute("UPDATE client SET balance = balance - '"+amount+"' WHERE ID = '"+Session_ID+"' ");

                        if(!error)
                        {
                            rs = stmt.executeQuery("SELECT balance FROM client WHERE ID = '"+Session_ID+"'");
                            rs.next();
                            balance = rs.getInt(1);
                            String s = "Transfer " + amount + " LE to Account ID : " + IDForTheTargetAccount + ", at " + now();
                            stmt.execute("INSERT INTO history (client_id,transaction) VALUES ('"+Session_ID+"' ,'"+s+"')");
                        }
                    }
                    
                    else
                    {
                        // do something
                    }
                }
            }
            else
            {
                //do something
            }    
        } 
        catch (Exception e) {}
        return balance;
    } 
    
    static int TransferToAnotherAccountInAnotherBank(Socket c, int ID){
        int balance = 0;
        try {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());
            dos.writeUTF("Enter the bank ID you want to Transfer to:");
            String IP = dis.readUTF();
            //establish connection with another server with the
            //IP address mentioned above
            Socket peepToPeerSocket = new Socket("127.0.0.1", Integer.valueOf(IP));
            //System.out.println("server.Server.TransferToAnotherAccountInAnotherBank()" + IP);
            DataOutputStream dosPTP = new DataOutputStream(peepToPeerSocket.getOutputStream());
            DataInputStream disPTP = new DataInputStream(peepToPeerSocket.getInputStream());
            dosPTP.writeUTF("I'm another server");
            //connection established *Dap meme*
            //list of options
            System.out.println(disPTP.readUTF());
            //choosing option 1, transfer
            dosPTP.writeChar('1');
            
            
            if(/*target bank exists*/ true){
                dos.writeUTF("Enter the account ID you want to Transfer to:");
                int IDForTheTargetAccount = dis.readInt();
                //sending ID of target to target bank(server)
                dosPTP.writeInt(IDForTheTargetAccount);
                //reading boolean if found or not
                boolean targetIDFound = disPTP.readBoolean();
                
                if(targetIDFound){
                    dos.writeUTF("Enter the amount of money to Transfer to:" + IDForTheTargetAccount);
                    int amount = dis.readInt();
                    
                    rs = stmt.executeQuery("SELECT balance FROM client WHERE ID = '"+Session_ID+"'");
                    rs.next();
                    balance = rs.getInt(1);
                    
                    if(amount<0 || balance < amount){
                        //add condition to check if the amount is more than the initial balance
                        //do something
                    }
                    else{
                        //send the amount of money to the other bank
                        dosPTP.writeInt(amount);
                        //update self data base with balance-=amount;
                        boolean error = stmt.execute("UPDATE client SET balance = balance - '"+amount+"' WHERE ID = '"+Session_ID+"' ");
                       
                        if(!error)
                        {
                            balance -= amount;
                            String s = "Transfer " + amount + " LE, at " + now();
                            stmt.execute("INSERT INTO history (client_id,transaction) VALUES ('"+Session_ID+"' ,'"+s+"')");
                        }
                    }
                    System.out.println(amount);
                }
                else{
                    //do something
                }    
            }
            
        } 
        catch (Exception e) {}
        return balance ;
    }
    
    static String ShowHistory(Socket c)
    {
        String History = "";
        try 
        {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());       
            //search the data base by ID and return the history formatted for
            rs = stmt.executeQuery("SELECT transaction FROM history WHERE client_id = '"+Session_ID+"'");
            
            while (rs.next())
                History += rs.getString(1) + "\n";
        }
        catch (Exception ex) {}
        
        return History;  
    }
    
    static String username = "root";
    static String password = "";
    static Connection conn;
    static String conn_string;
    static Statement stmt;
    static ResultSet rs;
    
    public static void main(String[] args) throws SQLException, IOException  {
        // TODO code application logic here             
        try
        {                            
            //1.Listen
            //2.accept
            //3.create socket (I/O) with client
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter Bank number");
            socketNumber = sc.nextInt();
            sc.nextLine();
            ServerSocket s = new ServerSocket(socketNumber);
            conn_string = "jdbc:mysql://localhost/banking_system_" + String.valueOf(socketNumber);
            // Create connection to database
            conn = DriverManager.getConnection(conn_string, username, password);
            stmt = conn.createStatement();
            

            while (true)
            {            
                Socket c = s.accept();
                System.out.println("Client Arrived");
                clientHandler ch = new clientHandler(c);
                Thread t = new Thread(ch);
                t.start();
                
            }

            //s.close();
        } 
        catch (Exception ex)
        {
            //ex.printStackTrace();
            System.out.println("Something went wrong");
        }

    }
   
    
    
}
