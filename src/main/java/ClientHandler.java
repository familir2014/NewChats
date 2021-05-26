import sun.applet.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket server;
    private String nick;


    String getNick() {
        return nick;
    }
//    }
//    public ClientHandler(Server server, Socket socket) {
//
//    }
//
//    void sendMsg(String msg) {
//    }

    boolean checkBlackList(String _blacklist_nick) {
        int nickId = AuthService.getIdByNick(_blacklist_nick);
        int blacklistId = AuthService.getBlackListUserById(nickId);
        return blacklistId > 0;
    }

    ClientHandler(Server socket, Socket server) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = null;
                            try {
                                str = in.readUTF();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    if (!server.isBound(newNick)) {
                                        sendMsg("/ok");
                                        nick = newNick;
                                        server.isBound(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Учетная запись уже используется");
                                    }
                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }


