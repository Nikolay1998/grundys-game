package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocket {

    private int port; // Cлучайный порт от 1025 до 65535.
    private String message;

    // Создаем входной и выходной потоки для передачи данных примитивных типов.
    private DataInputStream in;
    private DataOutputStream out;

    // Приватный сокет сервера (сокращенно ss).
    MySocket(int newPort) {
        try {
            port = newPort; // Номер порта передается параметром при создании сокета.
            ServerSocket ss = new ServerSocket(port);   // Создаем сокет сервера и привязываем его переданному порту.
            System.out.println("Ожидание клиента...");
            Socket socket = ss.accept(); // Сервер ждет подключений, и следующая строчка кода выполнится только когда кто-то подключится.
            System.out.println("Клиент подключился ...");

            // Создаем входной и выходной потоки для передачи данных в виде байтов.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Окончательно завершаем создание ранее созданных потоков для передачи примитивных типов.
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    // Получение строки от клиента.
    public void recieveMessage() {
        try {
            message = in.readUTF();  // Считываем из потока строку в кодировке UTF-8 и записываем ее в переменную message.
            System.out.println("Получены данные от клиента: ");
        } catch (IOException ex) {
            System.out.print("Не удалось получить данные от клиента!");
        }
    }

    // Метод возвращает строку в формате JSON.
    public String getMessage() {
        return message;
    }

    // Функция записывает в переменную message (то есть в сокет M1 или M2) сообщение игроку, которое передается в параметре message при вызове этой функции.
    public void setMessage(String message) {
        this.message = message;
    }

    // Отправляем клиенту строку в кодировке UTF-8.
    public void sendMessage(String JSONmessage) {
        try {
            System.out.println("Отправка результата...");
            out.writeUTF(JSONmessage); // Отсылаем введенную строку текста клиенту (записываем в поток строку в кодировке UTF-8).
            out.flush(); // Очистка буферов вывода. Байты записываются в место назначения. Поток прекращает передачу данных и данные сбрасываются в поток.
            System.out.println("Готово!");
        } catch (IOException ex) {
            System.out.print("Не удалось отправить сообщение клиенту");
        }
    }

    // Метод для закрытия потоков.
    public void finishWork() throws IOException {
        in.close();
        out.close();
    }
}
