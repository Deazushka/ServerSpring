package by.itsm.server.web;

import by.itsm.server.model.Request;
import by.itsm.server.model.Response;
import by.itsm.server.service.RequestProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RootEndpoint implements Runnable {
    private final int port;
    private final int threadCount;
    private final ObjectMapper objectMapper;
    private final RequestProcessor processor;
    private ServerSocket serverSocket;
    private ExecutorService executorService;


    public RootEndpoint(int port,
                        int threadCount,
                        ObjectMapper objectMapper,
                        RequestProcessor processor) {

        this.port = port;
        this.threadCount = threadCount;
        this.objectMapper = objectMapper;
        this.processor = processor;
    }

    @PostConstruct
    public void init() throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdownNow();
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            accept(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void accept(Socket socket) {
        executorService.submit(() -> {
            try (InputStream is = socket.getInputStream();
                 OutputStream os = socket.getOutputStream();
                 DataInputStream reader = new DataInputStream(is);
                 DataOutputStream writer = new DataOutputStream(os)) {

                String requestString = reader.readUTF();
                Request request = objectMapper.readValue(requestString, Request.class);

                Response response = null;

                if (processor.accept(request)) {
                    response = processor.process(request);
                }

                String responseString = objectMapper.writeValueAsString(response);

                writer.writeUTF(responseString);
                writer.flush();

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
