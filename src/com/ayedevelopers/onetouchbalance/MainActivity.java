package com.ayedevelopers.onetouchbalance;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends ActionBarActivity {


    String mCodecb;
    String mCodesb;
    String mCodedb;
    String mCodecc;
    String mCoderc;
    int backButtonCount;

    EditText medit1;
    String medit;





    /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }



    public void rc(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        medit1=null;
        medit1 = (EditText) findViewById(R.id.editText);
        medit=null;
        medit = medit1.getText().toString();
        //String meditn = null;
        String ussdCode = "*" + mCoderc + "*" + medit + Uri.encode("#");

        if (isNullnum(medit)) {
            medit1.setError("Invalid Number");
        }
        else
        {
            intent.setData(Uri.parse("tel:" + ussdCode));
            startActivity(intent);
        }

    }

    private boolean isNullnum(String num) {
        if (num != null && num.length()==0) {
            return true;

        }

        else {
            String rc_pattern="[0-9]+";
            Pattern pattern=Pattern.compile(rc_pattern);
            Matcher matcher=pattern.matcher(num);
            return !matcher.matches();
           // return false;
        }
    }



    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String ussdCode = mCodecb;
        intent.setData(Uri.parse("tel:" + ussdCode));
        startActivity(intent);
    }

    public void sms(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String ussdCode = mCodesb;
        intent.setData(Uri.parse("tel:" + ussdCode));
        startActivity(intent);

    }

    public void data(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String ussdCode = mCodedb;
        intent.setData(Uri.parse("tel:" + ussdCode));
        startActivity(intent);
    }

    public void care(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(mCodecc));
        startActivity(callIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageview network logo
        ImageView img= (ImageView) findViewById(R.id.carrierLogo);
        //Get System TELEPHONY service reference
        TelephonyManager tManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        //Get carrier name (Network Operator Name)
        String carrierName = tManager.getNetworkOperatorName();
        //Toast network selection
        Context context = getApplicationContext();
        CharSequence text = carrierName + " network detected.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Get country
        //*String countryCode = tManager.getSimCountryIso();



        //Carrier name on Textview
        TextView carrierView = (TextView)findViewById(R.id.carrierView);
        carrierView.setText(carrierName);


        if(carrierName.equals("IDEA")||carrierName.equals("!dea")){
            mCodecb = "*" + "121" + Uri.encode("#");
        mCodesb =  "*" + "167" +"*"+"01"+ Uri.encode("#");
        mCodedb = "*" + "125" + Uri.encode("#");
        mCodecc=  "tel:198";
        mCoderc="457";
        img.setImageResource(R.drawable.idea);
        }

        else if (carrierName.equals("Airtel")||carrierName.equals("AIRTEL")||carrierName.equals("airtel")) {
            mCodecb =   "*" + "123" + Uri.encode("#");
            mCodesb =  "*" + "123" + "*" + "7" +Uri.encode("#");
            mCodedb = "*" + "123" + "*" + "10" +Uri.encode("#");
            mCodecc=  "tel:198";
            mCoderc="101";
            img.setImageResource(R.drawable.airtel);

        }
        else if (carrierName.equals("Vodafone IN")||carrierName.equals("Hutch")||carrierName.equals("Vodafone")){
            mCodecb =   "*" + "111" + "*" + "2" + Uri.encode("#");
            mCodesb =  "*" + "142" + Uri.encode("#");
            mCodedb = "*" + "111" + "*" + "6" + "*" + "2" + Uri.encode("#");
            mCodecc=  "tel:199";
            mCoderc="140";
            img.setImageResource(R.drawable.vodafone);
        }
        else if (carrierName.equals("BSNL")||carrierName.equals("BSNL MOBILE")||carrierName.equals("CellOne")){
            mCodecb = "*" + "123" + Uri.encode("#");
            mCodesb = "*" + "123" + "*" + "1" +Uri.encode("#");
            mCodedb = "*" + "124" + "*" + "4" +Uri.encode("#");
            mCodecc = "tel:198";
            mCoderc="123";
            img.setImageResource(R.drawable.bsnl);
        }
        else if (carrierName.equals("Tata Docomo")||carrierName.equals("TATA DOCOMO")){
            mCodecb = "*" + "111" + Uri.encode("#");
            mCodesb = "*" + "111" + "*" + "1" +Uri.encode("#");
            mCodedb = "*" + "111" + "*" + "1" + Uri.encode("#");
            mCodecc = "tel:198";
            mCoderc="135"+"*"+"2";
            img.setImageResource(R.drawable.docomo);
        }
        else if (carrierName.equals("reliance")||carrierName.equals("Reliance")||carrierName.equals("RELIANCE")){
            mCodecb = "*" + "367" + Uri.encode("#");
            mCodesb = "*" + "367" + "*" + "2" +Uri.encode("#");
            mCodedb = "*" + "367" + "*" + "3" + Uri.encode("#");
            mCodecc = "tel:198";
            mCoderc="368";
            img.setImageResource(R.drawable.reliance);
        }

        else if(carrierName.equals("Aircel")||carrierName.equals("aircel")||carrierName.equals("AIRCEL")){
            mCodecb = "*" + "125" + Uri.encode("#");
            mCodesb = "*" + "126" + "*" + "2" +Uri.encode("#");
            mCodedb = "*" + "126" + "*" + "1" + Uri.encode("#");
            mCodecc = "tel:198";
            mCoderc="124";
            img.setImageResource(R.drawable.aircel);
        }

       else if (carrierName.equals("du")||carrierName.equals("DU")||carrierName.equals("Du")){
            mCodecb = "*" + "135" + Uri.encode("#");
            mCodesb = "*" + "135" + Uri.encode("#");
            mCodedb = "*" + "135" + Uri.encode("#");
            mCodecc = "tel:155";
            mCoderc ="135";
            img.setImageResource(R.drawable.du);
        }
        else if (carrierName.equals("etisalat")||carrierName.equals("Etisalat")||carrierName.equals("ETISALAT")){
            mCodecb = "*" + "121" + Uri.encode("#");
            mCodesb = "*" + "121" + Uri.encode("#");
            mCodedb = "*" + "170" + Uri.encode("#");
            mCodecc = "tel:101";
            mCoderc="121";
            img.setImageResource(R.drawable.ets);
        }
        else{
            Toast.makeText(this, "Sorry, Network not in db.Please mail us your network details.", Toast.LENGTH_LONG).show();
            img.setImageResource(R.drawable.blnk);
        }







    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this,SecActivity.class));



        }

        return super.onOptionsItemSelected(item);
    }
}
