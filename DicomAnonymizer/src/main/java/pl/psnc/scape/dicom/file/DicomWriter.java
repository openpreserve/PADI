package pl.psnc.scape.dicom.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomOutputStream;


public abstract class DicomWriter {
    public static boolean write(DicomObject dicomObject, String filePath) {
        File f = new File(filePath);
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return false;
        }

        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DicomOutputStream dos = new DicomOutputStream(bos);

        try {
            dos.writeDicomFile(dicomObject);
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        } finally {
            try {
                dos.close();
            } catch (IOException ignore) {
                return false;
            }
        }

        return true;
    }
}
