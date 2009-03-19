
package com.act.util;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;

import java.awt.image.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;
import java.io.*;
import javax.imageio.*;

/**
 * Created by IntelliJ IDEA. User: Hugo Date: 2005-6-3 Time: 14:24:25 To change
 * this template use File | Settings | File Templates.
 */
public class ImageUtil {

	public final static String[] EXTENSIONS={"GIF","JPG","PNG","gif","jpg","png"};
	
	public static Dimension getDimension(InputStream in) {
		try {
			ImageInfo ii = new ImageInfo();
			ii.setInput(in);
			if (ii.check()) {
				int width = ii.getWidth();
				int height = ii.getHeight();
				if (width > 0 && height > 0) {
					int format = ii.getFormat();
					if (format == ii.FORMAT_GIF || format == ii.FORMAT_JPEG || format == ii.FORMAT_PNG || format == ii.FORMAT_SWF) {
						return new Dimension(width, height);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	static {
		String property = System.getProperty("java.awt.headless");
		if (property == null || !property.equals("true")) {
			System.setProperty("java.awt.headless", "true");
		}
	}

	public static void applyWatermark(File input, File output, File watermark) throws IOException {
		BufferedImage source = ImageIO.read(input);
		addWaterMark(3, source, watermark);
		saveAsJPEG(source, output);
	}

	public static boolean cropAndScaleImage(File input, File output, int width, int height) throws IOException {
		BufferedImage source = ImageIO.read(input);
		if (source == null) {
			return false;
		}
		BufferedImage thumbImage = getCropedAndScaledImage(source, width, height);
		if (thumbImage == null || thumbImage.getWidth() == 0) {
			return false;
		}
		saveAsJPEG(thumbImage, output);
		return true;
	}

	public static boolean scaleImage(File input, File output, int width, int height) throws IOException {
		BufferedImage source = ImageIO.read(input);
		if (source == null) {
			return false;
		}
		BufferedImage thumbImage = getScaledImage(source, width, height);
		if (thumbImage == null || thumbImage.getWidth() == 0) {
			return false;
		}
		saveAsJPEG(thumbImage, output);
		return true;
	}

	public static boolean scaleImage(File input, File output, double scale) throws IOException {
		BufferedImage source = ImageIO.read(input);
		if (source == null) {
			return false;
		}
		BufferedImage thumbImage = getScaledImage(source, (int) (source.getWidth() * scale), (int) (source.getHeight() * scale));
		if (thumbImage == null || thumbImage.getWidth() == 0) {
			return false;
		}
		saveAsJPEG(thumbImage, output);
		return true;
	}

	public static boolean cropAndScaleImage(InputStream input, File output, int width, int height) throws IOException {
		BufferedImage source = ImageIO.read(input);
		if (source == null) {
			return false;
		}
		BufferedImage thumbImage = getCropedAndScaledImage(source, width, height);
		if (thumbImage == null || thumbImage.getWidth() == 0) {
			return false;
		}
		saveAsJPEG(thumbImage, output);
		return true;
	}

	public static BufferedImage getScaledImage(BufferedImage source, int width, int height) {
		int[] pixels = new int[width * height]; // RGB pixels.
		try {
			ImageProducer scaledImageProducer = new FilteredImageSource(source.getSource(), new AreaAveragingScaleFilter(width, height));
			PixelGrabber pg = new PixelGrabber(scaledImageProducer, 0, 0, width, height, pixels, 0, width);
			pg.grabPixels();
		} catch (InterruptedException e) {
			return null;
		}
		BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		thumbImage.setRGB(0, 0, width, height, pixels, 0, width);
		return thumbImage;
	}

	/**
	 * Crop the image in the middel and center
	 * 
	 * @param source
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getCropedAndScaledImage(BufferedImage source, int width, int height) {
		int oriWidth = source.getWidth();
		int oriHeight = source.getHeight();
		double rate = (double) width / (double) height;
		int desiredHeight = (int) (oriWidth / rate);
		int fixedWidth = oriWidth;
		int fixedHeight = desiredHeight;
		if (desiredHeight > oriHeight) {
			fixedHeight = oriHeight;
			fixedWidth = (int) (oriHeight * rate);
		}

		int x = 0;
		int y = 0;
		if (fixedWidth == oriWidth) {
			y = (oriHeight - fixedHeight) / 2;
		} else if (fixedHeight == oriHeight) {
			x = (oriWidth - fixedWidth) / 2;
		}

		int[] pixels = new int[width * height]; // RGB pixels.
		try {
			ImageProducer producer = new FilteredImageSource(source.getSource(), new CropImageFilter(x, y, fixedWidth, fixedHeight));
			ImageProducer scaledImageProducer = new FilteredImageSource(producer, new AreaAveragingScaleFilter(width, height));
			PixelGrabber pg = new PixelGrabber(scaledImageProducer, 0, 0, width, height, pixels, 0, width);
			pg.grabPixels();
		} catch (InterruptedException e) {
			return null;
		}
		BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		thumbImage.setRGB(0, 0, width, height, pixels, 0, width);

		return thumbImage;
	}

	public static void addWaterMark(int location, BufferedImage thumbImage, String txt) {
		Font font = new Font("Arial", Font.BOLD, 128);
		Graphics2D g2 = thumbImage.createGraphics();
		FontRenderContext fc = g2.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(txt, fc);
		g2.setFont(font);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
		g2.drawString(txt, (thumbImage.getWidth() - (int) bounds.getWidth()) / 2 - 2, (thumbImage.getHeight() - (int) bounds.getHeight() / 2) - 2);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		g2.drawString(txt, (thumbImage.getWidth() - (int) bounds.getWidth()) / 2, (thumbImage.getHeight() - (int) bounds.getHeight() / 2));
	}

	final static int LEFT_TOP = 0;

	final static int LEFT_BOTTOM = 1;

	final static int RIGHT_BOTTOM = 3;

	final static int RIGHT_TOP = 4;

	final static int CENTER = 2;

	public static void addWaterMark(int location, BufferedImage thumbImage, File image) throws IOException {
		BufferedImage watermark = ImageIO.read(image);
		if (watermark == null) {
			throw new IllegalArgumentException("Sorry, " + image.getAbsolutePath() + " is not a valid watermark image.");
		}
		int x = 0, y = 0;
		double m = thumbImage.getWidth() * 1.0d / watermark.getWidth();
		if (m > 1) {
			m = 1;
		} else if (m < 0.1) {
			m = 0.1;
		}
		switch (location) {
			case LEFT_BOTTOM:
				y = (int) (thumbImage.getHeight() - watermark.getHeight() * m);
				break;
			case RIGHT_BOTTOM:
				y = (int) (thumbImage.getHeight() - watermark.getHeight() * m) - 8;
				x = (int) (thumbImage.getWidth() - watermark.getWidth() * m) - 8;
				break;
			case RIGHT_TOP:
				x = (int) (thumbImage.getWidth() - watermark.getWidth() * m);
				break;
			case CENTER:
				x = (int) ((thumbImage.getWidth() - watermark.getWidth() * m) / 2);
				y = (int) ((thumbImage.getHeight() - watermark.getHeight() * m) / 2);
				if (y < 0) {
					y = 0;
				}
				break;

		}

		Graphics2D g2 = thumbImage.createGraphics();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.16f));
		AffineTransform t = new AffineTransform(m, 0, 0, m, 0, 0);
		AffineTransformOp xform = new AffineTransformOp(t, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		g2.drawImage(watermark, xform, x, y);

		// thumbImage.getGraphics().drawImage(watermark,x,y,null);
	}

	public static void saveAsJPEG(BufferedImage thumbImage, File output) throws IOException {
		FileOutputStream out = new FileOutputStream(output);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		int quality = 90;
		param.setQuality((float) quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(thumbImage);
		out.close();
	}

	/**
	 * Read the input image file, scaled then write the output image file.
	 * 
	 * @param input the input image file
	 * @param output the output image file
	 * @param width the output image's width
	 * @param height the output image's height
	 * @return true for sucessful,
	 * @deprected false if no appropriate reader or writer is found.
	 */
	public static boolean scaleImage2(File input, File output, int width, int height) throws IOException {
		BufferedImage image = ImageIO.read(input);
		if (image == null) {
			return false;
		}
		image = getCropedAndScaledImage(image, width, height);
		String name = output.getName();
		String format = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
		return ImageIO.write(image, format, output);
	}

	public static BufferedImage getScaledImage2(BufferedImage source, int width, int height) {
		int oriWidth = source.getWidth();
		int oriHeight = source.getHeight();
		double rate = (double) width / (double) height;
		int desiredHeight = (int) (oriWidth / rate);
		int fixedWidth = oriWidth;
		int fixedHeight = desiredHeight;
		if (desiredHeight > oriHeight) {
			fixedHeight = oriHeight;
			fixedWidth = (int) (oriHeight * rate);
		}
		ImageProducer producer = new FilteredImageSource(source.getSource(), new CropImageFilter(0, 0, fixedWidth, fixedHeight));
		Image cropedSource = Toolkit.getDefaultToolkit().createImage(producer);
		BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(cropedSource, 0, 0, width, height, null);
		return thumbImage;
	}

	public static BufferedImage getScaledImage3(BufferedImage source, int width, int height) {
		int oriWidth = source.getWidth();
		int oriHeight = source.getHeight();
		double rate = (double) width / (double) height;
		int desiredHeight = (int) (oriWidth / rate);
		int fixedWidth = oriWidth;
		int fixedHeight = desiredHeight;
		if (desiredHeight > oriHeight) {
			fixedHeight = oriHeight;
			fixedWidth = (int) (oriHeight * rate);
		}
		ImageProducer producer = new FilteredImageSource(source.getSource(), new CropImageFilter(0, 0, fixedWidth, fixedHeight));
		Image cropedSource = Toolkit.getDefaultToolkit().createImage(producer);
		BufferedImage image = new BufferedImage(width, height, source.getType());
		image.createGraphics().drawImage(cropedSource, 0, 0, width, height, null);
		return image;
	}
}
