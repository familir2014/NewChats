public class DataBAuthService implements AuthService {


    @Override
    public String getNickandLoginPass(String login, String password) {
        return SQLusers.getNickLogPass(login,password);
    }

    @Override
    public boolean registration(String login, String password, String nick) {
        return SQLusers.registation(login,password,nick);
    }

    @Override
    public boolean changNick(String backNick, String newNick) {
        return SQLusers.uschangeNickUs(backNick,newNick);
    }
}
