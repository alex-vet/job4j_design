package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String[] line;
                    String answer = "";
                    boolean serverOn = true;
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("?msg=")) {
                            line = str.split(" ");
                            if ("Exit".equals(line[1].substring(6))) {
                                answer = "GoodBye!";
                                serverOn = false;
                            } else if ("Hello".equals(line[1].substring(6))) {
                                answer = "Hello";
                            } else {
                                answer = "What";
                            }
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                    out.flush();
                    if (!serverOn) {
                        server.close();
                    }
                } catch (Exception e) {
                    LOG.error("IO error:", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Socket error: ", e);
        }
    }
}
