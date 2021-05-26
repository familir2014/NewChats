import java.sql.*;

public class SQLusers {
    private static PreparedStatement getNickNameUs;
    private static PreparedStatement usRegistrationUs;
    private static Connection connection;
    private static PreparedStatement uschangeNickUs;

    public static boolean connects(){
        try{
            Class.forName("Sqlite");
            connection = DriverManager.getConnection("Sqlite:main.db");
            prepareStUs();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }




    private static void prepareStUs() throws SQLException {
        getNickNameUs = connection.prepareStatement("Select nickname From users Where login = ? And password = ?;");
        usRegistrationUs = connection.prepareStatement("Insert INTO users(login,password,nickname) VALUES(?,?,?);");
        uschangeNickUs = connection.prepareStatement("Update users Set nick = ? Where nick = ?");

    }


    public static String getNickLogPass(String login, String password){
        String nick = null;
        try {
            getNickNameUs.setString(1,login);
            getNickNameUs.setString(2,password);
            ResultSet rs = getNickNameUs.executeQuery();
            if (rs.next()){
                nick = rs.getString(1);
            }
            rs.close();

        }catch (SQLException e){
            e.printStackTrace();

        }
        return nick;
    }

    public static boolean registation(String login, String password, String nick){
        try {
            usRegistrationUs.setString(1, login);
           usRegistrationUs.setString(2, password);
            usRegistrationUs.setString(3,nick);
            usRegistrationUs.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
//


    public static boolean uschangeNickUs(String backNick, String newNick) {try {
        uschangeNickUs.setString(1, newNick);
        uschangeNickUs.setString(2, backNick);
        uschangeNickUs.executeUpdate();
        return true;

    }catch (SQLException e){
//        e.printStackTrace();
        return false;
        //наверно смена ника так же?)
    }
    }
    public static void disconnets(){
        try {
            usRegistrationUs.close();
            uschangeNickUs.close();
            getNickNameUs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
