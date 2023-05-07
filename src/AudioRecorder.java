import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class AudioRecorder extends JFrame {

  private JButton startButton;
  private JButton stopButton;
  private AudioFormat audioFormat;
  private TargetDataLine targetDataLine;
  private JLabel recordingLabel;

  public static void main(String[] args) {
    new AudioRecorder();
  }

  public AudioRecorder() {
    setTitle("Audio Recorder");
    setLayout(new GridLayout(1, 3));

    startButton = new JButton("Start");
    this.add(startButton);

    stopButton = new JButton("Stop");
    this.add(stopButton);

    recordingLabel = new JLabel("Recording");
    recordingLabel.setForeground(Color.RED);
    recordingLabel.setPreferredSize(new Dimension(20, 20));
    recordingLabel.setVisible(false);
    this.add(recordingLabel);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 100);
    setVisible(true);

    audioFormat = getAudioFormat();
    addListeners();
  }

  public void addListeners() {
    startButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          startRecording();
        }
      }
    );
    stopButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          stopRecording();
        }
      }
    );
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
            recordingLabel.setVisible(true);
      
            // the AudioInputStream that will be used to write the audio data to a file
            AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
      
            // the file that will contain the audio data
            File audioFile = new File("recording.wav");
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
            recordingLabel.setVisible(false);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    );
    t.start();
  }

  public void stopRecording() {
    targetDataLine.stop();
    targetDataLine.close();
  }
}