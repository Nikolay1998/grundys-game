package Server;


import State.State;
import com.google.gson.Gson;

public class Server {
    public static void main(String[] args) {
        MySocket MS1 = new MySocket(3222);
        MySocket MS2 = new MySocket(2015);

        State state = new State();

        Gson g = new Gson();

        while (true){
            MS1.sendMessage(g.toJson(state));
            MS1.recieveMessage();
            state = g.fromJson(MS1.getMessage(), State.class);
            System.out.println(state);
            if(state.isGameOver()) break;

            MS2.sendMessage(g.toJson(state));
            MS2.recieveMessage();
            state = g.fromJson(MS2.getMessage(), State.class);
            System.out.println(state);
            if(state.isGameOver()) break;
        }
    }
}
