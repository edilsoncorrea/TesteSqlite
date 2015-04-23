package br.com.edilsoncorrea.testesqlite.modelo;

import com.j256.ormlite.field.DatabaseField;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edilson on 14/03/2015.
 */
public class Waypoint {
    @DatabaseField(generatedId = true)
    public long id;

    @DatabaseField(index = true)
    public Date data;

    @DatabaseField
    public Date hora;

    @DatabaseField
    public double latitude;

    @DatabaseField
    public double longitude;

    @DatabaseField
    public double velocidade;

    @DatabaseField
    public int  distancia;

    @DatabaseField
    public  boolean calculado;

    public Waypoint() {
        // needed by ormlite
    }

    //Overload do metodo toString
    @Override
    public String toString() {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        return  formatoHora.format(data) + "  " + String.format("%.4f", latitude) + "/" + String.format("%.4f", longitude) + "  " + String.format("%d", distancia);
    }

}
