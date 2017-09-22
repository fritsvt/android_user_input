package tech.frits.coffeeorders;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static tech.frits.coffeeorders.R.id.name_input;
import static tech.frits.coffeeorders.R.id.summary_text_view;
import static tech.frits.coffeeorders.R.id.top;
import static tech.frits.coffeeorders.R.id.topping_whipped_checkbox;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    double price_per_item = 1.99;
    double chocolate_topping_price = 1.99;
    double whipped_cream_topping_price = 0.99;
    int last_msg_index = 0;
    String name;
    String toPay;
    String summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();
    }

    /**
     * Increase quantity function
     */
    public void increaseQuantity(View view) {
        if (quantity >= 10) {
            Toast.makeText(getApplicationContext(), "You can only order 10 coffees at a time",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    /**
     * Decrease quantity function
     */
    public void decreaseQuantity(View view) {
        if (quantity <= 0) {
            Toast.makeText(getApplicationContext(), "One does not order less then 0 coffees...",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + number);
    }

    private void displayPrice(int amount) {
        this.SetName();

        if (name.equals("") || name == null) {
            Toast.makeText(getApplicationContext(), "Please input a name to order coffee",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        this.SetSummary(amount);

        String[] pleasePayMessages = {
                "That would be " + toPay + " please",
                "Hey! " + toPay + " please",
                "You owe me " + toPay,
                "That'd be " + toPay + " sir.",
                "Need to pay me " + toPay + " to get ur juice"
        };

        Random random = new Random();
        int randomMessageIndex = random.nextInt(pleasePayMessages.length);

        if (randomMessageIndex == last_msg_index) {
            if (randomMessageIndex == pleasePayMessages.length-1) {
                randomMessageIndex = randomMessageIndex - 1;
            } else {
                randomMessageIndex = randomMessageIndex + 1;
            }
        }
        last_msg_index = randomMessageIndex;

        setFriendlyMessage(pleasePayMessages[randomMessageIndex]);

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(toPay);
    }

    private void setFriendlyMessage(String message) {
        TextView friendly_message = (TextView) findViewById(R.id.friendly_message_text_view);

        friendly_message.setText(message);
    }

    private void SetName() {
        EditText txtname = (EditText)findViewById(R.id.editText2);
        name = txtname.getText().toString();

        //txtname.setText("");
    }

    private void SetSummary(int amount) {
        double total = amount * price_per_item;

        TextView summary_txt = (TextView) findViewById(R.id.summary_text_view);
        CheckBox whipped_checkBox = (CheckBox) findViewById(R.id.topping_whipped_checkbox);
        CheckBox chocalte_checkbox = (CheckBox) findViewById(R.id.topping_chocolate_checkbox);
        summary_txt.setVisibility(View.VISIBLE);

        String whipped_topping = "Add whipped cream? ";
        if (whipped_checkBox.isChecked()) {
            whipped_topping += "true";
            total+=whipped_cream_topping_price;
        } else {
            whipped_topping += "false";
        }
        String chocolate_topping = "Add chocolate? ";
        if (chocalte_checkbox.isChecked()) {
            chocolate_topping += "true";
            total+=chocolate_topping_price;
        } else {
            chocolate_topping += "false";
        }
        toPay = NumberFormat.getCurrencyInstance().format(total);

        summary = "Order summary:\nName: " + name + "\n" +
                whipped_topping + "\n" +
                chocolate_topping + "\n" +
                "Amount: " + quantity + "\n" +
                "Total: " + toPay + "\n" +
                "Thank you!";
        summary_txt.setText(summary);

        this.Share();
    }

    private void Share() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order summary for " + name + "'s coffee");
        emailIntent.putExtra(Intent.EXTRA_TEXT, summary);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

}
