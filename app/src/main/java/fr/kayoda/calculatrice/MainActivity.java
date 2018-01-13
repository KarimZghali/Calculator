package fr.kayoda.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jeval.*;

public class MainActivity extends AppCompatActivity {

    Evaluator eve = new Evaluator();

    private TextView displayResult;
    private static boolean lastNumber = true;
    private static boolean point = false;
    private static boolean convertDevisePossible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayResult = findViewById(R.id.displayResult);

    }

    ////////////////////////////////////////////////
    ///////// T O U C H E S    C H I F F R E //////
    ///////////////////////////////////////////////

    public void b0 (View v) {
        Addtxt("0");
    }

    public void b1 (View v) {
        Addtxt("1");
    }

    public void b2 (View v) {
        Addtxt("2");
    }

    public void b3 (View v) {
        Addtxt("3");
    }

    public void b4 (View v) {
        Addtxt("4");
    }

    public void b5 (View v) {
        Addtxt("5");
    }

    public void b6 (View v) {
        Addtxt("6");
    }

    public void b7 (View v) {
        Addtxt("7");
    }

    public void b8 (View v) {
        Addtxt("8");
    }

    public void b9 (View v) {
        Addtxt("9");
    }

    ////////////////////////////////////////////////////
    ///////// T O U C H E S    O P E R A T I O N //////
    //////////////////////////////////////////////////

    public void bPlus (View v) {
        oneSigle('+', "+");
    }

    public void bMoins (View v) {
        oneSigle('-', "-");
    }

    public void bx (View v) {
        oneSigle('*', "*");
    }

    public void bDiv (View v) {
        oneSigle('/', "/");
    }

    public void btnPoint (View v) {

        if (lastNumber == true && point == false) {
            displayResult.setText(displayResult.getText()+".");
            point = true;
        }
    }

    /////////////////////////////////////////////////
    ///////// T O U C H E S   D E V I S E  /////////
    ///////////////////////////////////////////////


    public void btnEuro (View v) {

        convDevise(1,'€');

    }
    public void btnDollar (View v) {

        convDevise(1.185, '$');

    }

    public void btnLivre (View v) {

        convDevise(1.88, '£');

    }

    public void btnBtc (View v) {

        convDevise(0.001, 'Ƀ');

    }
// AAAAAAAAAAAAAAA
    private void convDevise(double tx, char Symb) {

       if (checkSigle()) {

           double resultConvert = convertDouble() * tx;

           displayResult.setText(String.valueOf(resultConvert)+Symb);
       }

    }

    public double convertDouble() {

        return Double.parseDouble(displayResult.getText().toString());

    }

    public void convRad(View v) {

        if (checkSigle()) {

            double resultConvert = (3.14/180) * convertDouble();

            displayResult.setText(String.valueOf(resultConvert));
        }

    }

    public void convGrad(View v) {


        if (checkSigle()) {

            double resultConvert = (convertDouble() * 100/90) ;

            displayResult.setText(String.valueOf(resultConvert));

        }

    }

    public boolean checkSigle() {

        int mult=displayResult.getText().toString().indexOf('*');
        int div=displayResult.getText().toString().indexOf('/');
        int add=displayResult.getText().toString().indexOf('+');
        int sous=displayResult.getText().toString().indexOf('-');


        if (mult >= 0 || div >= 0 || add >= 0 || sous >= 0) {
            Toast.makeText(this, "Ceci n'est pas convertissable, désolé !", Toast.LENGTH_LONG).show();

            return false;

        } else {

            return true;
        }

    }

    /////////////////////////////////////////////////////////////
    ///////// T O U C H E   C L E A R   E T   R E S U L T //////
    ///////////////////////////////////////////////////////////


    public void btnC(View v) {

        if (!displayResult.getText().equals("0")) {

            int widthString = displayResult.getText().length();

            StringBuffer buffer = new StringBuffer( displayResult.getText());

            buffer.delete(widthString-1, widthString);

            displayResult.setText(buffer.toString());

            if (displayResult.getText().equals("")) {

                displayResult.setText("0");
                point = false;

            }
        }
    }


    public void btnClear(View v) {

       displayResult.setText("0");
    }

    public void btnResult(View v) {

        try {
            displayResult.setText(eve.evaluate(displayResult.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this, "Calcul impossible !", Toast.LENGTH_LONG).show();
        }

    }


    /////////////////////////////////////////////////
    ///////// F U N C T I O N   D I S P L A Y //////
    ///////////////////////////////////////////////


    public void Addtxt (String txt) {
        clearZero();
        lastNumber = true;
        displayResult.setText(displayResult.getText()+txt);
    }

    public void addsigle(String sigle) {
        lastNumber = false;
        displayResult.setText(displayResult.getText()+sigle);
    }



    public void oneSigle (char sigle, String sigletoAdd) {

        if (lastNumber == false) {

            int widthString = displayResult.getText().length();

            StringBuffer buffer = new StringBuffer( displayResult.getText());

            buffer.setCharAt(widthString-1, sigle);

            displayResult.setText(buffer.toString());
            point = false;

        } else {
            addsigle(sigletoAdd);
            point = false;
        }
    }


    public void clearZero() {

        if (displayResult.getText().equals("0")) {
            displayResult.setText("");
        }

    }

}
