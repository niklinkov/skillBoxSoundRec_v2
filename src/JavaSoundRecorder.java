
import javax.sound.sampled.*;
import java.io.*;


public class JavaSoundRecorder {

    // format of audio file
    private AudioFileFormat.Type fileType;
    private TargetDataLine line;
    private AudioFormat audioFormat;
    private DropBoxConnection connection;



    public JavaSoundRecorder(DropBoxConnection connection)
    {
        fileType = AudioFileFormat.Type.WAVE;
        this.connection=connection;
        audioFormat = getAudioFormat();

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    /**
     * Defines an audio format
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    public void recordSound (long durationMilliseconds, String fileName)
    {
        File file = new File(fileName);
        start(file);

        delaeydFinish(durationMilliseconds,file);
    }

    /**
     * Captures the sound and record into a WAV file
     */
    private void start(File file) {
        new Thread(() -> {
            try {
                line.open(audioFormat);
//                System.out.println("Старт записи");
                line.start();   // start capturing
                AudioInputStream ais = new AudioInputStream(line);
                // start recording
                AudioSystem.write(ais, fileType, file);

            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }).start();

    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    private void delaeydFinish(long durationMilliseconds, File file) {  // fileName) {
        new Thread(() -> {
            try {
                Thread.sleep(durationMilliseconds);
                line.stop();
                line.close();
//                DropBoxConnection connection = new DropBoxConnection();

                connection.uploadFile(file.getName());
                //System.out.println("File deleted"+file.getName())
                ;
                file.delete();
               // System.out.println("File deleted");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }).start();
    }

}

