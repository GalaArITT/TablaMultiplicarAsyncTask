package com.example.oliver.tablamultiplicarasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;
    int leer;
    AsyncTask_load hiloConectar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leer = parseInt(editText.getText().toString());
                hiloConectar = new AsyncTask_load(textView,leer);
                hiloConectar.execute();


            }
        });
    }

    private void UnSegundo() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  class AsyncTask_load extends AsyncTask<Void,Integer, Boolean> {
        int valorMaximo;
        TextView textView;

        public AsyncTask_load(TextView textView,int valorMaximo) {
            this.valorMaximo =valorMaximo;
            this.textView=textView;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i=1; i<=10; i++){
                UnSegundo();
                publishProgress(leer*i);
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.append("\n"+values[0].intValue());
        }


    }
}
