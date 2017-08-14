package src;

import com.mysql.jdbc.ResultSetImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jeremy on 2017/8/3.
 */
public class UserDaoImpl implements UserDao {

    static String sql = null;
    static MySqlHelper db = null;
    static User userResult = null;

    @Override
    public void save(User user) throws SQLException {

        sql = "insert into userInfo(username,password) values('"+user.getName()+"','"+user.getPassword()+"')";
        db = new MySqlHelper(sql);
        try {
           db.pst.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public int queryUserName(String username) throws SQLException {
        sql="select * from userInfo where username = '"+username+"'";
        db = new MySqlHelper(sql);
        int resultI = 0;
        ResultSet rs=null;
        try {
        rs=db.pst.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (rs.next()){
            resultI =-1;
        }
        db.close();
        rs.close();
        return resultI;

    }

    @Override
    public int queryPassword(String username,String password) throws SQLException {
        sql="select * from userInfo where username = '"+username+"'";
        db = new MySqlHelper(sql);
        int resultI = 0;
        ResultSet rs = null;
        try {
            rs=db.pst.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        while (rs.next()){
           if (!rs.getString("password").equals(password)){
               resultI=-1;
           }
        }
        db.close();
        rs.close();
        return resultI;
    }
}
