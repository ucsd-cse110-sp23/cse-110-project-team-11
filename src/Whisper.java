import java.io.*;
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

    /*
     * Returns generatedText when success
     */
    private static String handleSuccessResponse(HttpURLConnection connection)
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

        // if(response.toString().length() == 0) {
        //     generatedText = "invalid";
        // }
        //print transcription result
        //System.out.println("Transcription Result: " + generatedText);

        return generatedText;
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

    /*
     * Uses connection and gets the transcript of the audio file
     */
    public String getTranscript(String filePath) throws IOException, JSONException {
        //create file object
        File file = new File(filePath);

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

        //write closing bonudary to request body
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());

        //flush and close output stream
        outputStream.flush();
        outputStream.close();

        //get response code
        int responseCode = connection.getResponseCode();

        //check response code and handle response accordingly
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String successReturn = handleSuccessResponse(connection);
            connection.disconnect();
            return successReturn; //method returns generatedText
        } else {
            handleErrorResponse(connection);
            connection.disconnect();
            return null;
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        Whisper whisper = new Whisper();
        whisper.getTranscript(FILE_PATH);
    }
}