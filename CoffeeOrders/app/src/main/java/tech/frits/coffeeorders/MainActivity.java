package tech.frits.coffeeorders;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    double price_per_item = 1.99;
    int last_msg_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }

    /**
     * Increase quantity function
     */
    public void increaseQuantity(View view) {
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
        String toPay = NumberFormat.getCurrencyInstance().format(amount * price_per_item);

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

}
