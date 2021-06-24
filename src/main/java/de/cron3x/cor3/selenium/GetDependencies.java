package de.cron3x.cor3.selenium;

import de.cron3x.cor3.Cor3;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GetDependencies {

    String link;
    File out;

    public GetDependencies(String link, File out) {
        out.getParentFile().mkdirs();
        this.out = out;
        this.link = link;
        StartDownload();
    }

    public void StartDownload(){
        new BukkitRunnable(){
            @Override
            public void run(){
                try {
                    URL url = new URL(link);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    double filesize = (double) http.getContentLength();
                    BufferedInputStream inputStream = new BufferedInputStream(http.getInputStream());
                    FileOutputStream outputStream = new FileOutputStream(out);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);

                    byte[] buffer = new byte[1024];
                    double downloaded = 0.00;
                    int read = 0;
                    double percentDownloaded = 0.00;

                    while ((read = inputStream.read(buffer, 0,1024)) >= 0){
                        bufferedOutputStream.write(buffer,0,read);
                        downloaded += read;
                        percentDownloaded = (downloaded*100)/filesize;
                        String percent = String.format("%.4f", percentDownloaded);
                        Cor3.getInstance().log("Downloaded " + percent + "%");
                    }
                    bufferedOutputStream.close();
                    inputStream.close();
                    Cor3.getInstance().log("Downloaded is complete");
                    unZip();
                    out.delete();
                }
                catch (IOException exception){
                    exception.printStackTrace();
                }
                this.cancel();
            }
        }.runTaskTimerAsynchronously(Cor3.getInstance(),0, 1000);
    }
    private void unZip() throws IOException{
        String fileZip = out.toString();
        File destDir = out.getParentFile();
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

}

