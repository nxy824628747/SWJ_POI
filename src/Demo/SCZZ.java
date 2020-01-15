package Demo;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;

public class SCZZ {
    public static void ma1in(String args[]){
        try{
            JdbcTemplate jdt = CurrencyTools.getOracleJdbcTemplate("", "", "");
            String getNos="select distinct license_number from license_content where license_type_name='营业执照'";

        }catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
