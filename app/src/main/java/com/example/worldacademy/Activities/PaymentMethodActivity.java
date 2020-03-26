package com.example.worldacademy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldacademy.Common.SessionMangment;
import com.example.worldacademy.Fragments.Models.BuyModel;
import com.example.worldacademy.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PaymentMethodActivity extends AppCompatActivity {
    DatabaseReference mDatabaseReference;

    public static final String PAYPAL_CLIENT_ID = "AfBzX3f-uhEmNFtIQ_qSlRRiOBvz1xaowRnYzjuhSfWwtSdgP2FB_To6FbT6gSBT1O2hROXfPWxMnBfo";
    //Paypal  request code

    public static final int PAYPAL_REQUEST_CODE = 123;

    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration() // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PAYPAL_CLIENT_ID)
            .merchantName("TechNxt Code Labs")
            .merchantPrivacyPolicyUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/privacy-full"))
            .merchantUserAgreementUri(
                    Uri.parse("https://www.paypal.com/webapps/mpp/ua/useragreement-full"));  // or live (ENVIRONMENT_PRODUCTION)


    private String paymentAmount = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        getPayment();

    }


    private void getPayment() {
        Intent mIntent = getIntent();

        Log.e("Priceeeeeeeeeeeee", mIntent.getStringExtra("Price"));
        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new
                BigDecimal(mIntent.getStringExtra("Price")), "USD", "Total", PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(PaymentMethodActivity.this, PaymentActivity.class);
        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        //invoice number
        payment.invoiceNumber("321");

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //----------------------------------------------------------------------------------
                SessionMangment mangment = new SessionMangment(this);
                Intent mIntent = getIntent();

                //----------------------------------------------------------------------------------

                BuyModel mBuyModel = new BuyModel(mangment.getUserDetails().get(SessionMangment.KEY_ID));

                mDatabaseReference = FirebaseDatabase.getInstance().getReference("Courses").child(Levels.mItemId).child("Folders").child(mIntent.getStringExtra("Name")).child("Users Who Bought");
                mDatabaseReference.child(mangment.getUserDetails().get(SessionMangment.KEY_ID)).setValue(mBuyModel);

                Toast.makeText(this, "You Bought this Course",
                        Toast.LENGTH_LONG).show();


                //----------------------------------------------------------------------------------

                Intent mIntent2 = new Intent(this, Video.class);
                mIntent2.putExtra("Name",mIntent.getStringExtra("Name"));
                startActivity(mIntent2);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.d("paymentExample", paymentDetails);
                        Log.i("paymentExample", paymentDetails);
                        Log.d("Pay Confirm : ", String.valueOf(confirm.getPayment().toJSONObject()));
//                        Starting a new activity for the payment details and status to show

                        startActivity(new Intent(PaymentMethodActivity.this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred : ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("Faillllllllllll", "Failllllllllllllll");

                Toast.makeText(this, "You Need To Buy The Course",
                        Toast.LENGTH_LONG).show();
                finish();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.e("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, MainActivity.class));
        super.onDestroy();
    }
}
