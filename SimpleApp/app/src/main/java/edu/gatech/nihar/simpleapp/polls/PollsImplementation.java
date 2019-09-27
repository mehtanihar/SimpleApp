package edu.gatech.nihar.simpleapp.polls;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.gatech.nihar.simpleapp.R;

public class PollsImplementation extends Activity implements  PollsInterface {

    CardView yes_button, no_button;
    int yes_number, no_number;
    TextView yes_count, no_count, title, description;
    String my_vote;
    ProgressBar yes_pb, no_pb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll);
        yes_number = 97;
        no_number = 3;
        my_vote = "null";
        yes_button =  (CardView) findViewById(R.id.yes_button);
        no_button = (CardView) findViewById(R.id.no_button);
        yes_count = (TextView) findViewById(R.id.yes_count);
        no_count = (TextView) findViewById(R.id.no_count);
        title = (TextView) findViewById(R.id.polls_title);
        description = (TextView) findViewById(R.id.poll_description);
        yes_pb = (ProgressBar) findViewById(R.id.yes_progressbar);
        no_pb = (ProgressBar) findViewById(R.id.no_progressbar);

        title.setText("Will you attend the COC Grad Mixer event?");
        description.setText(("Event details: Sep 26 5pm @ The Garage"));

        String yes_string = Integer.toString(yes_number) + " people voted this";
        String no_string = Integer.toString(no_number) + " people voted this";
        yes_count.setText(yes_string);
        no_count.setText(no_string);
        yes_button.setCardBackgroundColor(Color.BLUE);
        no_button.setCardBackgroundColor(Color.BLUE) ;
        yes_pb.setProgress(100*yes_number/(yes_number + no_number));
        no_pb.setProgress(100*no_number/(yes_number + no_number));

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_yes_clicked();
            }
        });

        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_no_clicked();
            }
        });



    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void on_yes_clicked() {
        if(my_vote == "null") {
            yes_number += 1;
            my_vote = "yes";
            yes_button.setCardBackgroundColor(Color.MAGENTA);
        }
        else if(my_vote == "yes"){
            yes_number -= 1;
            my_vote = "null";
            yes_button.setCardBackgroundColor(Color.BLUE);

        }
        else{
            yes_number += 1;
            no_number -= 1;
            my_vote = "yes";
            no_button.setCardBackgroundColor(Color.BLUE);
            yes_button.setCardBackgroundColor(Color.MAGENTA);
        }

        String yes_string = Integer.toString(yes_number) + " people voted this";
        String no_string = Integer.toString(no_number) + " people voted this";
        yes_count.setText(yes_string);
        no_count.setText(no_string);
        yes_pb.setProgress(100*yes_number/(yes_number + no_number));
        no_pb.setProgress(100*no_number/(yes_number + no_number));


    }

    @Override
    public void on_no_clicked() {

        if(my_vote == "null") {
            no_number += 1;
            my_vote = "no";
            no_button.setCardBackgroundColor(Color.MAGENTA);
        }
        else if(my_vote == "no"){
            no_number -= 1;
            my_vote = "null";
            no_button.setCardBackgroundColor(Color.BLUE);

        }
        else{
            no_number += 1;
            yes_number -= 1;
            yes_button.setCardBackgroundColor(Color.BLUE);
            no_button.setCardBackgroundColor(Color.MAGENTA);
            my_vote = "no";
        }

        String yes_string = Integer.toString(yes_number) + " people voted this";
        String no_string = Integer.toString(no_number) + " people voted this";
        yes_count.setText(yes_string);
        no_count.setText(no_string);
        yes_pb.setProgress(100*yes_number/(yes_number + no_number));
        no_pb.setProgress(100*no_number/(yes_number + no_number));


    }

    @Override
    public void onFailed() {

    }
}
