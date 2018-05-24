package chaitanya.hostelautomation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.lang.Thread;

/**
 * Created by Chaitanya on 24/10/2017.
 */

public class NetworkActivity extends Thread
{
  private static String ServerHost;
  private static Integer Port;
  private static String ClientRequest;

  public static String ErrorMsg;
  private static Socket ClientSocket = null;
  private static DataInputStream DataIn = null;
  private static DataOutputStream DataOut = null;
  private byte[] RequestBuff;

  public NetworkActivity(String ServerIP, Integer SertverPort, String ClientMsg)
  {
    ServerHost = ServerIP;
    Port = SertverPort;
    ClientRequest = ClientMsg;
  }

   /*=========================================================================*/
  public void run()
  {
    try
    {
      ClientSocket = new Socket(ServerHost, Port);
      DataIn = new DataInputStream(ClientSocket.getInputStream());
      DataOut = new DataOutputStream(ClientSocket.getOutputStream());
      RequestBuff = ClientRequest.getBytes("UTF-8");
      DataOut.write(RequestBuff, 0, RequestBuff.length);
      ClientSocket.close();
    }
    catch(java.lang.NullPointerException NullPointer)
    {
      setErrorMsg("Please fill all the details");
      NullPointer.printStackTrace();
    }
    catch(java.net.PortUnreachableException PortUnreach)
    {
      setErrorMsg("Port Unreachable");
      PortUnreach.printStackTrace();
    }
    catch(java.net.NoRouteToHostException NoRouteToHost)
    {
      NoRouteToHost.printStackTrace();
      setErrorMsg("No Route To Host");
    }
    catch(SocketTimeoutException SocketTimeout)
    {
      SocketTimeout.printStackTrace();
      setErrorMsg("Socket Connection Timed Out");
    }
    catch(java.net.ConnectException ConnExcep)
    {
      ConnExcep.printStackTrace();
      setErrorMsg("Failed To Connect");
    }
    catch(SocketException SocketExcep)
    {
      SocketExcep.printStackTrace();
      setErrorMsg("Socket Exception");
    }
    catch(UnknownHostException UnknownHost)
    {
      UnknownHost.printStackTrace();
      setErrorMsg("Unknown Host");
    }
    catch(IOException IO)
    {
      setErrorMsg("I/O Exception");
      IO.printStackTrace();
    }
    catch(Exception e)
    {
      setErrorMsg("Exception occured but not listed");
      e.printStackTrace();
    }
  }//void run()

  /*=========================================================================*/

  public void setErrorMsg(String Messege)
  {
    ErrorMsg=Messege;
  }

  /*=========================================================================*/

  public static boolean getErrorMsg()
  {
    if(ErrorMsg.isEmpty()) {
      return false;
    }
    else
    {
      return true;
    }
  }

  /*=========================================================================*/

}//NetworkActivity