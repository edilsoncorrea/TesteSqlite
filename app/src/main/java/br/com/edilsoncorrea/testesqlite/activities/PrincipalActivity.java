package br.com.edilsoncorrea.testesqlite.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.edilsoncorrea.testesqlite.R;
import br.com.edilsoncorrea.testesqlite.dao.WaypointDAO;
import br.com.edilsoncorrea.testesqlite.modelo.Waypoint;


public class PrincipalActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button bInserir = (Button) findViewById(R.id.btInserirData);
        bInserir.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                inserirDataCorrente();
            }
        });

        Button bAgrupar = (Button) findViewById(R.id.btAgrupar);
        bAgrupar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                agruparPorData();

            }

        });
    }

    private void agruparPorData() {
        WaypointDAO dao;
        List<Waypoint> lista;

        dao = new WaypointDAO(this);

        lista = dao.getListaAgrupada();

        for (Waypoint item : lista) {

            Log.i("Teste", "Distancia: "+ item.distancia);

        }
    }

    private void inserirDataCorrente() {
        WaypointDAO dao;

        dao = new WaypointDAO(this);

        Waypoint waypoint = new Waypoint();

        Date data = new Date();

        SimpleDateFormat ftData = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = ftData.format(data);

        SimpleDateFormat ftHora = new SimpleDateFormat("HH:mm:ss");
        String horaStr = ftHora.format(data);

        try {
            waypoint.data = ftData.parse(dataStr);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {
            waypoint.hora = ftHora.parse(horaStr);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        waypoint.distancia = 100;

        dao.insere(waypoint);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
