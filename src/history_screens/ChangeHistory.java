package history_screens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//After changing the item data, related use case should create a Change History object with data and then call
//the save_history_change method

//could be split into data model,use case,and gateway writer but this use case is not too long.
public class ChangeHistory {
    public String time;
    public String username;
    public String action;
    //or make this the item object
    public String item;
    public String quantity;


//GET all the history data we need when constructing ChangeHistory object

    public ChangeHistory(String username, String action,String item,String quantity)  {
        //get current time and save it as a String arribute
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.time = dateFormat.format(date);
        this.username = username;
        this.action = action;
        this.item = item;
        this.quantity = quantity;


    }

    //gateway writer to write new line of history data to the file "history.csv"
    public void save_history_change(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("history.csv", true));
            String line = String.format("%s,%s,%s,%s,%s",
                    this.time, this.username, this.action, this.item, this.quantity);
            writer.write(line);
            writer.newLine();


        }catch (IOException e) {
            System.err.format("%s%n",e);
        }
    }

}
