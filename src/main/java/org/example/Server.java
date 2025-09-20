package org.example;


import com.fastcgi.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.logging.Logger;

public class Server {
    // потом сделать логгер)))))))))))
    private final Logger logger;

    public Server() {
        Locale.setDefault(Locale.US);
        this.logger = ServerLogger.getInstance();
    }

    public void run() throws IOException {
        var fcgiInterface = new FCGIInterface();
        logger.info("Сервер стартует");
        while (fcgiInterface.FCGIaccept() >= 0) {

            long start = System.nanoTime();
            var method = fcgiInterface.request.params.getProperty("REQUEST_METHOD");
            logger.info("Сейчас начнем что-то обрабатывать с методом "+ (String) method);
            if (!method.equals("POST")) {
                System.out.println(errorResult("Invalid request method"));
            } else {
                var contentType = FCGIInterface.request.params.getProperty("CONTENT_TYPE");
                if (!contentType.equals("application/x-www-form-urlencoded")) {
                    System.out.println(errorResult("Content-Type is not supported"));
                    continue;
                }
                SimpleFormUrlencodedParser parser = new SimpleFormUrlencodedParser();
                ServerMath serverMath = new ServerMath();
                var requestBody = parser.parseInput(readRequestBody());
                var xStr = requestBody.get("x");
                var yStr = requestBody.get("y");
                var rSrt = requestBody.get("r");
                if (!serverMath.checkResults((String) xStr, (String) yStr, (String) rSrt)) {
                    System.out.println(errorResult("Invalid request parameters"));
                }
                if (serverMath.checkHit((String) xStr, (String) yStr, (String) rSrt)) {
                    System.out.println(hitResult(makeJsonResponse(Float.parseFloat((String) xStr),
                            Float.parseFloat((String) yStr),
                            Integer.parseInt((String) rSrt),
                            true,
                            System.nanoTime()- start)));
                    logger.info("У нас победитель!");


                } else {
                    System.out.println(hitResult(makeJsonResponse(Float.parseFloat((String) xStr),
                            Float.parseFloat((String) yStr),
                            Integer.parseInt((String) rSrt),
                            false,
                            System.nanoTime()- start)));
                    logger.info("Не попал мужик, мда))))");
                }


            }

        }
    }

    private static String errorResult(String error) {
        return """
                HTTP/1.1 400 Bad Request
                Content-Type: text/html
                Content-Length: %d
                
                
                %s
                """.formatted(error.getBytes(StandardCharsets.UTF_8).length, error);
    }

    private static String hitResult(String hit) {
        return """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length: %d
                
                
                %s
                """.formatted(hit.getBytes(StandardCharsets.UTF_8).length, hit);
    }

    private static String makeJsonResponse(float x, float y, int r, boolean tFHit, long time) {
        double ms = time / 1_000_000d;
        String json = """
                         {
                         "x": %.2f,
                         "y": %.1f,
                         "r": %d,
                         "execution_time": "%.5f",
                         "result": "%s"
                         }
                """;
        return json.formatted(x, y, r, ms, tFHit ? "true" : "false");
    }

    private static String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();

        var contentLength = FCGIInterface.request.inStream.available();
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);

        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();

        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}
