package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstString = in.readLine();
                    if (firstString.contains("msg=Bye") || firstString.contains("msg=Exit")) {
                        out.write("Server closed".getBytes());
                        socket.close();
                        server.close();
                    } else if (firstString.contains("msg=Hello")) {
                        out.write("Hello, dear friend.\r\n".getBytes());
                    } else {
                        String[] split1 = firstString.split("=");
                        String[] split2 = split1[1].split(" ");
                        out.write(split2[0].getBytes());
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}