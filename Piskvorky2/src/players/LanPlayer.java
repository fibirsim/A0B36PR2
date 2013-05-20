package players;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import main.Const;
import main.Point;
import main.Value;

public class LanPlayer extends Player {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public LanPlayer(String name, Value mark, Socket socket) {
        super(name, mark);
        playerType = Const.LAN;
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Point play() {
        try {
            int y = in.readInt();
            int x = in.readInt();
            return new Point(x, y);
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }

    }

    @Override
    public void close() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException ex) {
        }
    }
}
