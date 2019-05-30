package si.um.feri.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.dbcp2.BasicDataSource;
import razredi.JsonSupport;
import razredi.SeznamRacunov;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBHelper {
    private static   BasicDataSource dataSource;

    private static BasicDataSource getDataSource() throws IOException {
        if(dataSource==null){
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(getData("url"));
            ds.setUsername(getData("username"));
            ds.setPassword(getData("password"));

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource=ds;
        }
        return dataSource;
    }

    public static  Connection getConnection() throws SQLException, IOException {
        dataSource=getDataSource();
        return dataSource.getConnection();
    }


    static String getData(String value) throws IOException {
        String result="";
        InputStream inputstream;
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Properties prop = new Properties();
            try{

                String propFileName="login.properties";
                inputstream=cl.getResourceAsStream(propFileName);
                if(inputstream!= null){
                    prop.load(inputstream);
                }
                else {
                    throw new FileNotFoundException("Property file not found!");
                }
                result = prop.getProperty(value);
                inputstream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        return result;
    }

}
