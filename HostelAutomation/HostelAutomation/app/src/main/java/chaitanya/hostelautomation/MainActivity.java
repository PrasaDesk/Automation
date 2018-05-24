package chaitanya.hostelautomation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    String ServerHost=null,ServerPort=null,ClientRequest;
    Integer Port;
    Boolean Flag = false;

    public boolean Validate(String IPAddress,String PortNum)
    {
        if(IPAddress.isEmpty() || PortNum.isEmpty())
        {
            return false;
        }
        else
        {
            Flag = true;
            return true;
        }
    }

    /*===========================================================================*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);


      Button ConnectBtn=(Button)findViewById(R.id.button);
      ConnectBtn.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View view)
        {
          EditText RpiIpAddress=(EditText)findViewById(R.id.editText);
          EditText PortNumber=(EditText)findViewById(R.id.editText2);
          if(Validate(RpiIpAddress.getText().toString(),PortNumber.getText().toString()))
          {
            ServerHost = RpiIpAddress.getText().toString();
            ServerPort = PortNumber.getText().toString();
            Port = Integer.parseInt(ServerPort);
          }
          else
          {
            Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
          }
        }
      });

      /*===========================================================================*/

      final Switch CoridorLight =(Switch)findViewById(R.id.switch1);
      CoridorLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b)
        {
          if(b == true)
          {
            if(false == Flag)
            {
              CoridorLight.setChecked(false);
              Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
              return;
            }
            ClientRequest="on1";
            Thread t1 = new NetworkActivity(ServerHost, Integer.parseInt(ServerPort), ClientRequest);
            t1.start();
            Toast.makeText(getApplicationContext(), "Switching On Coridor Light", Toast.LENGTH_SHORT).show();
          }
          else if(b == false)
          {
            if(false == Flag)
            {
              CoridorLight.setChecked(true);
              Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
              return;
            }
            ClientRequest="off1";
            Thread t2 = new NetworkActivity(ServerHost, Integer.parseInt(ServerPort), ClientRequest);
            t2.start();
            Toast.makeText(getApplicationContext(), "Switching Off Coridor Light", Toast.LENGTH_SHORT).show();
          }
        }
      });

      /*===========================================================================*/

      final Switch WaterMotor =(Switch)findViewById(R.id.switch2);
      WaterMotor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b)
        {
          if(true == b)
          {
            if(false == Flag)
            {
              WaterMotor.setChecked(false);
              Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
              return;
            }
            ClientRequest="on2";
            Thread t3 = new NetworkActivity(ServerHost, Integer.parseInt(ServerPort), ClientRequest);
            t3.start();
            Toast.makeText(getApplicationContext(), "Switching On Water Motor", Toast.LENGTH_SHORT).show();
          }
          else if(false == b)
          {
            if(false == Flag)
            {
              WaterMotor.setChecked(true);
              Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
              return;
            }
            ClientRequest="off2";
            Thread t4 = new NetworkActivity(ServerHost, Integer.parseInt(ServerPort), ClientRequest);
            t4.start();
            Toast.makeText(getApplicationContext(), "Switching Off Water Motor", Toast.LENGTH_SHORT).show();
          }
        }
      });

      /*===========================================================================*/

      final Switch CameraStreaming =(Switch)findViewById(R.id.switch3);
      CameraStreaming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b)
        {
          if(true == b)
          {
            if(false == Flag)
            {
              CameraStreaming.setChecked(false);
              Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
              return;
            }
            ClientRequest="on3";
            Toast.makeText(getApplicationContext(), "Switching On Camera Streaming", Toast.LENGTH_SHORT).show();
            String url = "http://"+ServerHost+":"+"8080";
            // String url = "http://www.google.co.in";
            //Intent intent = new Intent (MainActivity.this,WebActivity.class);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

          }
          else if(false == b)
          {
            if(false == Flag)
            {
              CameraStreaming.setChecked(true);
              Toast.makeText(getApplicationContext(),"Please fill all the deatils",Toast.LENGTH_SHORT).show();
              return;
            }
            ClientRequest="off3";
            Toast.makeText(getApplicationContext(), "Switching Off Camera Streaming", Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
}
