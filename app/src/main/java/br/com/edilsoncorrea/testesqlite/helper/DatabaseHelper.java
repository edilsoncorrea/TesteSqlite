package br.com.edilsoncorrea.testesqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.edilsoncorrea.testesqlite.modelo.Waypoint;


/**
 * Created by Edilson on 14/03/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELAWAYPOINT = "Waypoint";
    private static final String TABELADISTANCIADIARIA = "Distanciadiaria";
    private static final String DATABASE = "Odometro";

    // O objeto DAO usado para acessar a tabela Waypoint
    private Dao<Waypoint, Integer> waypointDao = null;
    private RuntimeExceptionDao<Waypoint, Integer> waypointRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Waypoint.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Não foi possível criar o bando de dados", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Waypoint.class, true);

            //Após haver excluído o data base antigo, cria um novo
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Não foi possível criar o banco de dados", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna o objeto (DAO) para a classe Waypoint. Ela será criada ou retornar o valor em cache.
     */
    public Dao<Waypoint, Integer> getDao() throws SQLException {
        if (waypointDao == null) {
            waypointDao = getDao(Waypoint.class);
        }
        return waypointDao;
    }

    /**
     * Retorna a versão RuntimeExceptionDao de um DAO para a classe Waypoint. Vai criar or apenas retornar o valor
     * em cache. RuntimeExceptionDao apenas através de RuntimeExceptions.
     */
    public RuntimeExceptionDao<Waypoint, Integer> getWaypointDao() {
        if (waypointRuntimeDao == null) {
            waypointRuntimeDao = getRuntimeExceptionDao(Waypoint.class);
        }
        return waypointRuntimeDao;
    }

    /**
     * Fecha o banco de dados e limpa qualquer DAO em cache.
     */
    @Override
    public void close() {
        super.close();
        waypointDao = null;
        waypointRuntimeDao = null;
    }

}
