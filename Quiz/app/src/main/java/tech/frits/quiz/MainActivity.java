package tech.frits.quiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

import static tech.frits.quiz.R.id.ratingBar;

public class MainActivity extends AppCompatActivity {

    String gender;
    String mood;
    boolean pizza;
    int age = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SubmitQuiz(View view) {
        EditText age_edit_text  = (EditText)findViewById(R.id.age_input);
        String age_string = age_edit_text.getText().toString();

        try {
            age = Integer.parseInt(age_string);
        } catch (Exception e) {
            this.ShowDialog("Error", "Age invalid", "back");
            return;
        }

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        float stars = ratingBar.getRating();

        String pizza_string = "No";
        if (pizza) {
            pizza_string = "True";
        }
        if (gender != null && mood != null && age != 0) {
            System.out.println("xd");
            this.ShowDialog("Success!", "Thanks for completing the quiz. \n\n" +
                    "your submission:\nMood: " + mood + "\n" +
                    "Gender: " + gender + "\n" +
                    "Age: " + age + "\n" +
                    "Pizza: " + pizza_string + "\n" +
                    "Rating: " + stars, "Okay!");
        } else {
            this.ShowDialog("Error", "Make sure to fill out all the fields", "back");
        }

    }

    public void onGenderRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                    break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                    break;
        }
    }

    public void onMoodRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_sad:
                if (checked)
                    mood = "Sad";
                break;
            case R.id.radio_meh:
                if (checked)
                    mood = "Meh";
                break;
            case R.id.radio_gud:
                if (checked)
                    mood = "Good";
                break;
        }
    }

    public void onPizzaRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_like_pizza:
                if (checked)
                    pizza = true;
                break;
            case R.id.radio_dislike_pizza:
                if (checked)
                    pizza = false;
                break;
        }
    }


    public void ShowDialog(String title_text, String message, String btn_text) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle(title_text);
        dialog.setMessage(message);
        dialog.setPositiveButton(btn_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }
}
