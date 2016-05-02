package rainey.id;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class ObserverActivity extends AppCompatActivity implements View.OnClickListener, Observer {

    Button increase;
    Button decrease;
    TextView status;
    TextView temperature;

    AirConditioner airConditioner = new AirConditioner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        increase = (Button) findViewById(R.id.increase);
        decrease = (Button) findViewById(R.id.decrease);
        status = (TextView) findViewById(R.id.status);
        temperature = (TextView) findViewById(R.id.temperature);

        //add observer
        //if any update, method public(Observerable,Object) will called
        airConditioner.addObserver(this);

        increase.setOnClickListener(this);
        decrease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(increase)) {
            airConditioner.increaseTemperature();
            return;
        }
        airConditioner.decreaseTemperature();
    }

    @Override
    public void update(Observable observable, Object data) {
        int temparatureNumber = (int) data;
        temperature.setText(""+temparatureNumber);
        if (temparatureNumber <= 19) {
            status.setText("Cold");
            return;
        }
        if (temparatureNumber <= 25) {
            status.setText("Warm");
            return;
        }
        status.setText("Hot");
    }

    private class AirConditioner extends Observable {

        private int temperatureCelcius = 21;

        public void increaseTemperature() {
            if (temperatureCelcius < 31) {
                temperatureCelcius++;
                setChanged();
                notifyObservers(temperatureCelcius);
            }
        }

        public void decreaseTemperature() {
            if (temperatureCelcius > 17) {
                temperatureCelcius--;
                setChanged();
                notifyObservers(temperatureCelcius);
            }
        }
    }
}
