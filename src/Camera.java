import org.opencv.core.Core; //functii principale OpenCv si NATIVE_LIBRARY_NAME
import org.opencv.core.Mat; //(Matrix) stocheaza imaginile
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture; //Clasa care se ocupa cu captura video
import org.opencv.highgui.HighGui; //clasele GUI

public class Camera {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        VideoCapture camera = new VideoCapture(0); //camera mea
        Mat frame = new Mat(); //va stoca frameul capturat

        try {
            if (!camera.isOpened()) {
                System.out.println("Nu poate fi deschisa!");
                return;
            }

            while (true) {
                if (camera.read(frame)) {
                    HighGui.imshow("Camera calculatorului", frame); //afiseaza ce se vede

                    int key = HighGui.waitKey(30);

                    if (key >= 0) {
                        if (key == 'p' || key == 'P') {
                            String filename = "poza.jpg";
                            Imgcodecs.imwrite(filename, frame);

                        }
                        break;

                    }
                }
            }
        } finally {
            camera.release();
            HighGui.destroyAllWindows();
            HighGui.waitKey(1);
        }
    }
}