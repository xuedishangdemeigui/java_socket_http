package com.zc.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                // OutputStream stream = socket.getOutputStream();
                socketHandler(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void socketHandler(Socket socket) {

        try (
                // InputStream is = socket.getInputStream();
                // Reader reader = new InputStreamReader(is);
                // BufferedReader br = new BufferedReader(reader);
                BufferedReader br =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));) {

            String line;
            while ((line = br.readLine()) != null && line.length() != 0) {
                System.out.println(line);
            }
            System.out.println("read finished");

            // parseRequest(is);
            System.out.println(socket.isClosed());

            pw.write("HTTP/1.1 200 \r\n");
            // pw.write("Transfer-Encoding: chunked");
            pw.write("Content-Type: text/html;charset=UTF-8\r\n");
            pw.write("\r\n");
            pw.write("Hello World!\r\n");
            System.out.println("handler finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseRequest(InputStream inputStream) {
        try (
                //
                Reader reader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(reader);
        // BufferedReader br =
        // new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String line;
            while ((line = br.readLine()) != null && line.length() != 0) {
                System.out.println(line);
            }
            System.out.println("read finished");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
