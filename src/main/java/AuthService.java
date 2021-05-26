public interface AuthService {

    static int getIdByNick(String blacklist_nick) {
        return 0;
    }

    static int getBlackListUserById(int nickId) {
        return 0;
    }

    static String getNickByLoginAndPass(String token, String token1) {
        return null;
    }

    String getNickandLoginPass(String login, String password);
    boolean registration(String login, String password, String nick);
    boolean changNick(String backNick, String newNick);
}

