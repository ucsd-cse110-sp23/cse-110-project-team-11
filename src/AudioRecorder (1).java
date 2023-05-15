import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class AudioRecorder extends JFrame {

<<<<<<< HEAD:src/AudioRecorder.java
  private JButton startButton;
  public JButton stopButton;
  private AudioFormat audioFormat;
  private TargetDataLine targetDataLine;
  private JLabel recordingLabel;
  private boolean isRecording = false;
=======
  private AudioFormat audioFormat;
  private TargetDataLine targetDataLine;
>>>>>>> US4:src/AudioRecorder (1).java

  public static void main(String[] args) {
    new AudioRecorder();
  }

  public AudioRecorder() {
    audioFormat = getAudioFormat();
  }

  private AudioFormat getAudioFormat() {
    // the number of samples of audio per second.
    // 44100 represents the typical sample rate for CD-quality audio.
    float sampleRate = 44100;

    // the number of bits in each sample of a sound that has been digitized.
    int sampleSizeInBits = 16;

    // the number of audio channels in this format (1 for mono, 2 for stereo).
    int channels = 2;

    // whether the data is signed or unsigned.
    boolean signed = true;

    // whether the audio data is stored in big-endian or little-endian order.
    boolean bigEndian = false;

    return new AudioFormat(
      sampleRate,
      sampleSizeInBits,
      channels,
      signed,
      bigEndian
    );
  }

  public void startRecording() {
    Thread t = new Thread(
      new Runnable() {
        @Override
        public void run() {
          try {
            // the format of the TargetDataLine
            DataLine.Info dataLineInfo = new DataLine.Info(
              TargetDataLine.class,
              audioFormat
            );
            // the TargetDataLine used to capture audio data from the microphone
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            
      
            // the AudioInputStream that will be used to write the audio data to a file
            AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
      
            // the file that will contain the audio data
            File audioFile = new File("myAudio.mp3");
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    );
    t.start();
    isRecording = true;
  }

  public void stopRecording() {
    if (this.targetDataLine != null) {
      this.targetDataLine.stop();
      targetDataLine.close();
    }
<<<<<<< HEAD:src/AudioRecorder.java
    isRecording = false;
  }

  public boolean isRecording() {
    return isRecording;
=======
>>>>>>> US4:src/AudioRecorder (1).java
  }
}