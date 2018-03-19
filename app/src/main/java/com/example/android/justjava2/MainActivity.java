/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
       boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox=(CheckBox)findViewById(R.id.chocolate_checkbox);
       boolean hasChocolate=chocolateCheckBox.isChecked();


        EditText txt = (EditText)findViewById(R.id.name_field);
        String name = txt.getText().toString();

        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage=createOrderSummary( name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view) {
        quantity=quantity-1;
        display(quantity);

    }

    private String createOrderSummary(String name,int price,boolean addWhippedCream,boolean addChocolate){
        String priceMessage="Name:" + name;
        priceMessage=priceMessage + "\n add whipped cream?" + addWhippedCream;
        priceMessage=priceMessage + "\n add chocolate?" + addChocolate;
        priceMessage=priceMessage + "\n Quantity: " + quantity;
        priceMessage=priceMessage + "\n Total: $" +(price);
        priceMessage= priceMessage + "\n thank you!";

        return priceMessage;

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
    /**
     * Calculates the price of the order.
          */
    private int calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        int basePrice=5;

        if(addWhippedCream){
            basePrice = basePrice+1;
        }
        if(addChocolate){
            basePrice = basePrice+2;
        }
        return quantity * basePrice;
    }
}