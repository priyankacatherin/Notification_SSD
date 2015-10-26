package com.example.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.google.api.client.util.DateTime;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.makeText;

public class SocialActivity extends Activity {
        private RadioGroup radioSocialGroup;
        private RadioGroup radioconversationgroup;
        private CheckBox checksituation;
        public RadioButton radioSocialButton;
        public RadioButton radioSocialButton2;
        private Button btnDisplay;
        private TextView Time;
    ArrayList<String> selectedItem =new ArrayList<String>();
    String situation;
    String unix_time;
  //  String final_fruit_selection="";
    String final_text;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_social);
            situation="";
            Time = (TextView) findViewById(R.id.time);
            String timestamp = MainActivity.formattedDate;
            String split[]=timestamp.split("split");
            timestamp=split[0];
            System.out.println("TIME : "+split[0]);
            System.out.println("TIME : " + split[1]);
             unix_time=split[1];
            Time.setText(timestamp);
            addListenerOnButton();

        }

    public void selectItem(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.a2:
                if (checked)
                    selectedItem.add("Am in a Conversation");
                else {
                    selectedItem.remove("Am in a Conversation");
                }
                break;
            case R.id.b2:
                if (checked)
                    selectedItem.add("Nobody is talking");
                else {
                    selectedItem.remove("Nobody is talking");
                }
                break;
            case R.id.c2:
                if (checked)
                    selectedItem.add("Other people around me are talking");
                else {
                    selectedItem.remove("Other people around me are talking");
                }
                break;
            case R.id.d2:
                if (checked)
                    selectedItem.add("Noise from the environment");
                else {
                    selectedItem.remove("Noise from the environment");
                }
                break;
        }
        String final_selection="";
        for(String Selections:selectedItem)
        {
            final_selection=final_selection + Selections +";";
        }

       final_text=final_selection;

        Toast.makeText(this,final_selection, Toast.LENGTH_LONG).show();
       // return final_text;
    }











        public void addListenerOnButton() {

            radioSocialGroup = (RadioGroup) findViewById(R.id.radioSocial);
           // radioconversationgroup = (RadioGroup) findViewById(R.id.radioconversation);


            btnDisplay = (Button) findViewById(R.id.btnDisplay);

            btnDisplay.setOnClickListener(new View.OnClickListener() {



          /*  public void selectItem(View view)
            {
                String check_text=final_text;
            }*/

                public void onClick(View v) {


                  //  System.out.println("check :" + final_text);
                    // get selected radio button from radioGroup
                    int selectedId = radioSocialGroup.getCheckedRadioButtonId();
                   // int selectedId2 = radioconversationgroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioSocialButton = (RadioButton) findViewById(selectedId);
                   // radioSocialButton2 = (RadioButton) findViewById(selectedId2);


                    makeText(SocialActivity.this,
                            radioSocialButton.getText(), Toast.LENGTH_SHORT).show();
                    makeText(SocialActivity.this,
                            final_text, Toast.LENGTH_SHORT).show();
                   // makeText(SocialActivity.this,
                     //       radioSocialButton2.getText(), Toast.LENGTH_SHORT).show();


                    String radioval = String.valueOf(radioSocialButton.getText());
                    //String radiocon = String.valueOf(radioSocialButton2.getText());


                    ContentValues new_data = new ContentValues();
                    new_data.put(SocialEsmProvider.Esm_Data.DEVICE_ID, Aware.getSetting(getApplicationContext(), Aware_Preferences.DEVICE_ID));
                  //  new_data.put(SocialEsmProvider.Esm_Data.TIMESTAMP, System.currentTimeMillis());
                    new_data.put(SocialEsmProvider.Esm_Data.TIMESTAMP, unix_time);
                    // new_data.put(EsmProvider.Esm_Data.EMAIL, true);
                    System.out.println("final_text: " + final_text);
                    new_data.put(SocialEsmProvider.Esm_Data.LOCATION, String.valueOf(radioSocialButton.getText()));
                    new_data.put(SocialEsmProvider.Esm_Data.SITUATION,final_text.toString());
                  //  new_data.put(SocialEsmProvider.Esm_Data.CONVERSATIONTYPE, String.valueOf(radioSocialButton2.getText()));
                    getContentResolver().insert(SocialEsmProvider.Esm_Data.CONTENT_URI, new_data);
                    //  Toast.makeText(getApplicationContext(), "You have been saved!", Toast.LENGTH_LONG).show();
                    // finish();


                    Intent calendar_intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    calendar_intent.putExtra("Radio Value", radioval);
                   // calendar_intent.putExtra("Radio Con", radiocon);
                    calendar_intent.putExtra("Check Con", final_text);

                    startActivity(calendar_intent);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.cancel(023);
                    //   notificationManager.cancel(1);



                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());


                    finish();
                }

            });

          //  Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

           /* Bundle extras = getIntent().getExtras();
            Date date = new Date(extras.getLong("start_time"));*/

/*            Calendar c = Calendar.getInstance();
            Intent get_Intent = getIntent();
            formattedDate = get_Intent.getStringExtra("date");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //formattedDate= DateTime.parseRfc3339(get_Intent.getStringExtra("date"));
            formattedDate = df.format(c.getTime());
// Now formattedDate have current date/time
            Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
            //Log.d("date", formattedDate);*/

        }
    }




