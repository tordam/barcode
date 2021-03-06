package src.main.java.org.appcore;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarcodeGenerator {

	public static String requestToken(int number) {
		
		int tokenLength = 9;
		
		// Generate random token 9 digits
		//ArrayList<String> tokens = generateRandomToken(tokenLength, number);
		
		//File file = generateToken(token.toString(), path);
		//return token;
		return null;
	}

	public static File generateToken(String msg, String path) {
		File file = new File(path);
		try {
			generate(msg, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return file;
	}

	public static byte[] generate(String msg) {
		ByteArrayOutputStream ous = new ByteArrayOutputStream();
		generate(msg, ous);
		return ous.toByteArray();
	}

	public static void generate(String msg, OutputStream ous) {
		if (StringUtils.isEmpty(msg) || ous == null) {
			return;
		}

		Code39Bean bean = new Code39Bean();

		// accuracy
		final int dpi = 150;
		// module width
		final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

		// configuration object
		bean.setModuleWidth(moduleWidth);
		bean.setWideFactor(3);
		bean.doQuietZone(false);

		String format = "image/png";

		try {

			// output to the stream
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY,
					false, 0);

			// Generate a barcode
			bean.generateBarcode(canvas, msg);

			// end drawing
			canvas.finish();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String generateRandomToken(int digits, int numberOfTokens) {
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		for (int i = 0; 0 < numberOfTokens; i++) {
			
		}
		
		int m = (int) Math.pow(10, digits - 1);
		return Integer.toString(m + new Random().nextInt(9 * m));
	}

	public static void main(String[] args) {
		//requestToken(1);
	}

}
