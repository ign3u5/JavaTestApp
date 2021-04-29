import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JLabel;

public class MonitorFunctionality {
    private final String CLIENTTYPE = "monitor";
    private int _port;
    private JLabel _label;

    public MonitorFunctionality(int port) {
        _port = port;
    }

    public void setLabelForData(JLabel label) {
        _label = label;
    }

    public void run() throws IOException {

        _label.setText("Going to sleep for 3 seconds");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        _label.setText("Waking up...");

        while (true) {
            //connecting to server
            try {
                    //connect to server
                    Socket s = new Socket("localhost", _port);
                    System.out.println("Connection Established.");
                    
                    //set up input and output streams
                    DataInputStream input = new DataInputStream(s.getInputStream());
                    DataOutputStream output = new DataOutputStream(s.getOutputStream());
                    Map<String, String> Info = new HashMap<>();

                    //send over a string to the server to determine clienttype
                    output.writeUTF(CLIENTTYPE);

                    //TEMPORARY UNTIL GUI IS INTRODUCED
                    String ServerRequest = input.readUTF();
                    System.out.println(ServerRequest);

                    while (true) {
                        try {
                            String readings = input.readUTF();
                            String ID = readinput(readings, Info);
                            dataupdate(ID, Info);

                            // TODO Update updateData function
                            _label.setText("Your new data");

                        } catch (IOException e) {

                        }
                    }
                
            } catch (ConnectException e) {
                System.out.println("Connection Error - Server Unavailable");
                System.exit(0);
            }

        }
    }
    
    //takes input from the data stream and overwrites in into a HashMap
    static public String readinput(String input, Map Info) {
        String in = input;
        String[] sections = in.split(":");
        String ID = sections[0];
        String data = sections[1];
        Info.put(ID, data);
        return ID;
    }

    //overwrites previous readings array with up to date readings
    static public void dataupdate(String ID, Map Info) {
        String data = String.valueOf(Info.get(ID));
        String[] readings = data.split(";");
        String gps = readings[0];
        String crop = readings[1];
        String temp1 = readings[2];
        String humid1 = readings[3];
        String windspeed1 = readings[4];
        String temp2 = readings[5];
        String humid2 = readings[6];
        String windspeed2 = readings[7];
        String temp3 = readings[8];
        String humid3 = readings[9];
        String windspeed3 = readings[10];

        weathercheck(temp1, temp2, temp3, humid1, humid2, humid3, windspeed1, windspeed2, windspeed3);

        System.out.println("ID : " + ID);
        System.out.println("GPS Location : " + gps);
        System.out.println("Crop Grown : " + crop + "\n");
        System.out.println("Sensor 1");
        System.out.println("temp : " + temp1);
        System.out.println("humidity : " + humid1);
        System.out.println("windspeed : " + windspeed1 + "\n");
        System.out.println("Sensor 2");
        System.out.println("temp : " + temp2);
        System.out.println("humidity : " + humid2);
        System.out.println("windspeed : " + windspeed2 + "\n");
        System.out.println("Sensor 3");
        System.out.println("temp : " + temp3);
        System.out.println("humidity : " + humid3);
        System.out.println("windspeed : " + windspeed3 + "\n");

    }

    //check all weather values are within normal ranges
    static public void weathercheck(String temp1, String temp2, String temp3, String humid1, String humid2, String humid3, String wspeed1, String wspeed2, String wspeed3) {
        tempcheck(temp1, temp2, temp3);
        humidcheck(humid1, humid2, humid3);
        windspeedcheck(wspeed1, wspeed2, wspeed3);

    }
    
    //provides an alert if temperature is too high or too low
    static void tempcheck(String temp1, String temp2, String temp3) {
        double t1 = Double.valueOf(temp1);
        double t2 = Double.valueOf(temp2);
        double t3 = Double.valueOf(temp3);
        double temp[] = {t1, t2, t3};

        for (int i = 0; i < 3; i++) {
            if (temp[i] > 26) {
                System.out.println("Temperature is very high");
            } else if (temp[i] < 7) {
                System.out.println("Temperature is very low");
            }
        }

    }

    //provides an alert if humidity is too high or too low
    static void humidcheck(String humid1, String humid2, String humid3) {
        double h1 = Double.valueOf(humid1);
        double h2 = Double.valueOf(humid2);
        double h3 = Double.valueOf(humid3);
        double humid[] = {h1, h2, h3};

        for (int i = 0; i < 3; i++) {
            if (humid[i] > 80) {
                System.out.println("This area is very humid");
            } else if (humid[i] < 20) {
                System.out.println("The area is incredibly dry");
            }
        }
    }

    //provides an alert if windspeed is too high
    static void windspeedcheck(String wspeed1, String wspeed2, String wspeed3) {
        double ws1 = Double.valueOf(wspeed1);
        double ws2 = Double.valueOf(wspeed2);
        double ws3 = Double.valueOf(wspeed3);
        double windspeed[] = {ws1, ws2, ws3};

        for (int i = 0; i < 3; i++) {
            if (windspeed[i] > 400) {
                System.out.println("The wind is very strong");
            }

        }
    }
}
