import java.io.*;

public class BufferedVsUnBufferedCopy {
    private static final int BUFFER_SIZE = 4096; 

    public static void main(String[] args) {
        String sourcePath = "largefile.txt";           
        String destUnbuffered = "copy_unbuffered.txt"; 
        String destBuffered = "copy_buffered.txt";     

       
        long startUnbuffered = System.nanoTime();
        copyUsingUnbufferedStreams(sourcePath, destUnbuffered);
        long endUnbuffered = System.nanoTime();
        System.out.println("Unbuffered copy time: " + (endUnbuffered - startUnbuffered) / 1_000_000 + " ms");

       
        long startBuffered = System.nanoTime();
        copyUsingBufferedStreams(sourcePath, destBuffered);
        long endBuffered = System.nanoTime();
        System.out.println("Buffered copy time: " + (endBuffered - startBuffered) / 1_000_000 + " ms");
    }

    public static void copyUsingUnbufferedStreams(String source, String dest) {
        try (
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(dest)
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Unbuffered copy failed:");
            e.printStackTrace();
        }
    }

    public static void copyUsingBufferedStreams(String source, String dest) {
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Buffered copy failed:");
            e.printStackTrace();
        }
    }
}
