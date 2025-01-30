import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ButtonByTemplate {
    private final String template;
    private final Rect searchArea;
    private final String pathName;

    public ButtonByTemplate(Rect searchArea, String template, String pathname) {
        this.searchArea = searchArea;
        this.template = template;
        this.pathName = pathname;
    }

    public Rect getButton() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String screenFilePath = new Screenshot(this.searchArea, this.pathName).capture();
        Mat img = Imgcodecs.imread(screenFilePath);
        Mat templ = Imgcodecs.imread(template);

        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_CCORR_NORMED);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        org.opencv.core.Point matchLoc = mmr.maxLoc;
        if (mmr.maxVal < 0.9991) {
            throw new Exception(template + " Not found! The best result is: " + (mmr.maxVal * 100));
        }
        return new Rect((int) matchLoc.x, (int) matchLoc.y, templ.cols(), templ.rows());
    }


}
