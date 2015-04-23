package br.com.edilsoncorrea.testesqlite.dao;

import android.content.Context;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.field.DataType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.edilsoncorrea.testesqlite.helper.DatabaseHelper;
import br.com.edilsoncorrea.testesqlite.modelo.Waypoint;


/**
 * Created by Edilson on 14/03/2015.
 */
public class WaypointDAO {

    private RuntimeExceptionDao<Waypoint, Integer> waypointDao;

    public WaypointDAO(Context contexto) {
        DatabaseHelper db = new DatabaseHelper(contexto);

        // get our dao
        waypointDao = db.getWaypointDao();
    }

    public void insere(Waypoint waypoint){
        waypointDao.create(waypoint);
    }


    public void delete(Waypoint waypoint) {
        waypointDao.delete(waypoint);
    }

    public void alterar(Waypoint waypoint) {
        waypointDao.update(waypoint);
    }

    public List<Waypoint> getLista() {
        return waypointDao.queryForAll();
    }

    public List<Waypoint> getListaAgrupada() {
        Waypoint item;
        List<Waypoint> lista = new ArrayList<Waypoint>();

        // return the orders with the sum of their amounts per account
        GenericRawResults<Object[]> rawResults =
                waypointDao.queryRaw("select data, sum(distancia) as disttotal from Waypoint group by data",
                        new DataType[]{DataType.DATE_LONG, DataType.INTEGER});
        // page through the results

        try {
            Object[] resultado = rawResults.getFirstResult();
            item = new Waypoint();
            item.data = (Date)resultado[0];
            lista.add(item);
        } catch (Exception E){
            E.printStackTrace();
        }

        /*for (Object[] resultArray : rawResults) {
            item = new Waypoint();

            item.data = (Date)resultArray[0];
            item.distancia = (Integer) resultArray[1];

           lista.add(item);
        }*/

        try {
            rawResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Waypoint> getListaNaoCalculados() {
        return waypointDao.queryForAll();
    }
}
