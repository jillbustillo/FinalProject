package com.example.jillbustillo.bustillofinalproj;

/**
 * Created by Jill Bustillo on 10/4/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by androidstudio on 10/09/16.
 */
public class OnTouchActivity extends AppCompatActivity {
    ImageView image1;
    EditText x1y1, x2y2, motion, difff, quadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch);
        image1 = (ImageView) findViewById(R.id.imageView);
        x1y1 = (EditText) findViewById(R.id.editText4);
        x2y2 = (EditText) findViewById(R.id.editText5);
        difff = (EditText) findViewById(R.id.editText6);
        motion = (EditText) findViewById(R.id.editText7);
        quadd = (EditText) findViewById(R.id.editText8);
        image1.setOnTouchListener(new View.OnTouchListener() {
            float x1, x2, y1, y2;
            float diffx, diffy;
            String msg = " ", msg1 = " ", msg2 = " ", quad = " ";

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = motionEvent.getAction();
                switch (x) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        y1 = motionEvent.getY();
                        /*x1y1.setText(x1 + " , " + y1);
                        return true;**/
                        msg1 = x1 + " , " + y1;

                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = motionEvent.getX();
                        y2 = motionEvent.getY();
                        /*diffx = (x1 - x2);
                        diffy = (y1 - y2); **/
                        if (x1 > x2) {
                            diffx = x1 - x2;
                        } else {
                            diffx = x2 - x1;
                        }
                        if (y1 > y2) {
                            diffy = y1 - y2;
                            if (y2 < 0) {
                                diffy = y1 + y2;
                            }
                        } else {
                            diffy = y2 - y1;
                        }

                        if (diffy == 0.0 && diffx == 0.0) {
                            msg = "";
                            quad = "";
                        }

                        /*x2y2.setText(x2 + " , " + y2);**/
                        msg2 = x2 + " , " + y2;

                        if (y1 < y2) {
                        /*    motion.setText("Swiped Down"); **/
                            msg = "Swiped Down";
                            if (x1 < x2) {
                        /*    motion.setText("Swiped Right"); **/
                                msg = msg + "Swiped Right";
                                quad = "Quadrant IV";
                            }
                            if (x1 > x2) {
                        /*    motion.setText("Swiped Left");**/
                                msg = msg + "Swiped Left";
                                quad = "Quadrant III";
                            }

                        } else {
                            motion.setText("");
                        }
                        if (y1 > y2) {
                        /*    motion.setText("Swiped Up"); **/
                            msg = "Swiped Up";
                            if (x1 < x2) {
                        /*    motion.setText("Swiped Right"); **/
                                msg = msg + "Swiped Right";
                                quad = "Quadrant I";
                            }
                            if (x1 > x2) {
                        /*    motion.setText("Swiped Left");**/
                                msg = msg + "Swiped Left";
                                quad = "Quadrant II";
                            }

                        } else {
                            motion.setText("");
                        }

                        x1y1.setText(msg1);
                        x2y2.setText(msg2);
                        motion.setText(msg);
                        difff.setText(diffx + " , " + diffy);
                        quadd.setText(quad);
                        break;
                }
                return true;
            }

        });
    }
}