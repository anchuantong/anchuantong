
package com.act.util.captcha;

// ///////////////////////////////////////////////////////////
// create the checkCode//
// Author:kingcl //
// Date:11/22/2005 //
// ///////////////////////////////////////////////////////////
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public class GetGraphic {

	public static final int IMAGE_WIDTH = 80;

	public static final int IMAGE_HEIGHT = 30;

	public static final int FONT_SIZE = 20;

	public static final String CHARS_LIB = "һ���Եľ��ڵع�����������ͬ�в�Ϊ�ѳ�Ҫ������������һ�����������鿪����ƽ��δ������æ����Ω�����ǻ�ǡ�����հ�����ʱ�ﱩ�Զ��Ϲ���Ͼ����ɹ���������Ӱ��Ы��ó�����ܻ��ֵ���������̨ӭ�԰����������ҽ������ұ���ͼ���ü������θ�����̹���׼����˼������������Ѱ�Ż�ɽ���˽�����Ⱥӡ���꿭��ת�Ļ������ּ�ı���Ŀ���������������ܸ��������޷�����ڵ�ǩȡ����Խ�����������ڸ�������༪��ǽ������������ĳ��ѿ��������������";

	private String result = "";// ����ַ���

	public BufferedImage getImage() {

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Random random = new Random();
		// ���ɱ���
		g.setColor(new Color(0xCCCCCC));
		g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		// ���ɱ߿�
		g.setColor(new Color(0xF4F4F4));
		g.fillRect(1, 1, IMAGE_WIDTH - 2, IMAGE_HEIGHT - 2);
		// ����
		for (int j = 1; j < 9; j++) {
			int r = 5 - random.nextInt(5);
			g.setColor(Color.gray);
			g.fillArc(random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT), r, r, 0, 360);
			g.drawLine(random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT), IMAGE_WIDTH - random.nextInt(50), IMAGE_HEIGHT - random.nextInt(30));
		}
		// ��������
		String fontStyle[] = { "����", "����", "����", "��Բ" };
		g.setFont(new Font(fontStyle[random.nextInt(4)], Font.BOLD, FONT_SIZE));

		int length = CHARS_LIB.length();
		int start = random.nextInt(length);
		String rand = CHARS_LIB.substring(start, start + 1);
		result = rand;
		g.setColor(Color.black);
		g.drawString(rand, 3, (IMAGE_WIDTH - FONT_SIZE - 10) / 2);
		for (int i = 1; i < 4; i++) {
			int nm = random.nextInt(9);
			g.setColor(Color.black);
			g.setFont(new Font(fontStyle[random.nextInt(4)], Font.BOLD, FONT_SIZE - random.nextInt(5)));
			g.drawString(Integer.toString(nm), FONT_SIZE * i + 8, FONT_SIZE - random.nextInt(5));
			result += Integer.toString(nm);
		}
		g.dispose();
		return image;
	}

	public String getStringCode() {
		return result;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
