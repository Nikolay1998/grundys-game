package Client;

import State.State;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер порта 3222 или 2015:");
        int port = scanner.nextInt();

        Gson g = new Gson();

        Socket socket;
        socket = new Socket("localhost", port);

        InputStream sin = socket.getInputStream();
        OutputStream stout = socket.getOutputStream();

        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(stout);

        State state;

        while (true) {
            state = g.fromJson(in.readUTF(), State.class);
            if(state.isGameOver()){
                System.out.println("You lose!");
                break;
            }
            System.out.println(state);
            while (!state.crackHeap(0,0,0)) {
                System.out.println("какую кучку вы хотите разбить?");
                int heapNumber = scanner.nextInt();
                System.out.println("укажите кол-во камней в каждой кучке");
                int first = scanner.nextInt();
                int second = scanner.nextInt();
            }
            out.writeUTF(g.toJson(state));
            if(state.isGameOver()){
                System.out.println("You win!");
                break;
            }
        }
    }

}
