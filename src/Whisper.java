//javac -cp ../lib/json-20230227.jar:. Whisper.java
//java -cp ../lib/json-20230227.jar:. Whisper

import java.io.*;
import java.lang.reflect.Parameter;
import java.net.*;
import org.json.*;

public class Whisper {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/audio/transcriptions";
    private static final String TOKEN = "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";
    private static final String MODEL = "whisper-1";
    private static final String FILE_PATH = "myAudio.mp3";

    private static void writeParameterToOutputStream(
        OutputStream outputStream,
        String parameterName,
        String parameterValue,
        String boundary
    ) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
            (
                "Content-Disposition: form-data; name=\"" + parameterName + "\"\r\n\r\n"
            ).getBytes()
        );
        outputStream.write((parameterValue + "\r\n").getBytes());
    }

    private static void writeFileToOutputStream(
        OutputStream outputStream,
        File file,
        String boundary
    ) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
            (
                "Content-Disposition: form-data; name=\"file\"; filename=\"" +
                file.getName() +
                "\"\r\n"
            ).getBytes()
        );
        outputStream.write(("Content-Type: audio/mpeg\r\n\r\n").getBytes());

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        fileInputStream.close();
    }

    private static void handleSuccessResponse(HttpURLConnection connection)
        throws IOException, JSONException {

        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject responseJson = new JSONObject(response.toString());
        String generatedText = responseJson.getString("text");

        //print transcription result
        System.out.println("Transcription Result: " + generatedText);

        //create and write transcription result to txt file for use
        File whisperResult = new File("whisperResult.txt");

        try {
            FileWriter myWriter = new FileWriter(whisperResult);
            myWriter.write(generatedText);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    private static void handleErrorResponse(HttpURLConnection connection)
        throws IOException, JSONException {
        
        BufferedReader errorReader = new BufferedReader(
            new InputStreamReader(connection.getErrorStream())
        );

        String errorLine;
        StringBuilder errorResponse = new StringBuilder();
        while ((errorLine = errorReader.readLine()) != null) {
            errorResponse.append(errorLine);
        }
        errorReader.close();
        String errorResult = errorReader.toString();
        System.out.println("Error result: " + errorResult);
    }

    public static void main(String[] args) throws IOException {
        //create file object
        File file = new File(args[0]);

        //set up http connection
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        //set up request headers
        String boundary = "Boundary-" + System.currentTimeMillis();
        connection.setRequestProperty(
            "Content-Type",
            "multipart/form-data; boundary=" + boundary
        );
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN);

        //set up output stream to write request body
        OutputStream outputStream = connection.getOutputStream();

        //write model parameter to request body
        writeParameterToOutputStream(outputStream, "model", MODEL, boundary);

        //write file parameter to request body
        writeFileToOutputStream(outputStream, file, boundary);

        //write closing boundary to request body
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());

        //flush and close output stream
        outputStream.flush();
        outputStream.close();

        //get response code
        int responseCode = connection.getResponseCode();

        //check response code and handle response accordingly
        if (responseCode == HttpURLConnection.HTTP_OK) {
            handleSuccessResponse(connection);
        } else {
            handleErrorResponse(connection);
        }

        //disconnect connection
        connection.disconnect();
    }
}