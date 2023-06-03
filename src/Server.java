import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;



public class Server {


 // initialize server port and hostname
 private static final int SERVER_PORT = 8100;
 private static final String SERVER_HOSTNAME = "localhost";
 private static final String uri = "mongodb+srv://Cluster95779:YV50WnpyZEp9@cluster95779.lfr31so.mongodb.net/?retryWrites=true&w=majority";


 public static void main(String[] args) throws IOException {
    // create a thread pool to handle requests
   ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);


   // connect with database
   MongoClient mongoClient = MongoClients.create(uri);


   
   MongoDatabase database = mongoClient.getDatabase("cse110");
   

   // create a server
   HttpServer server = HttpServer.create(
     new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
     0
   );

   // Create a context
    server.createContext("/", new RequestHandler(database));

    // Set the executor
    //server.setExecutor(threadPoolExecutor);


    // Start the server
    server.start();


    System.out.println("Server started on port " + SERVER_PORT);

 }
}
