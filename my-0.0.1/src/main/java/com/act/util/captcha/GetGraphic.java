
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

	public static final String CHARS_LIB = "一是以的经第地工上我这有来同中不为把成要和在了新人又一更动画顿已情开下性平怔未两事万忙于列惟悸天亚划恰三量日昂照旺时里暴显儿晗果明暇早易晒电旷晃逃昨影至蝎虫贸肆蜂蛙互乐到匪蛟蜥能台迎以蚌致蚂虬参蚓区医瓦圃四冶田国图凌冰眉冷男困胃回那忍固资准巴因思囡界决车理即舅瑞寻峙毁山暂退较琳现群印玉建岁凯出转幽环输轻策旨聊备眼目等筑处身联务条管各瞩罚置罗符过筹第瞪签取走者越超载老土塔圳赴表武赤青吉熬墙城起塘坏增教某草芽苗莲蓬著工范获";

	private String result = "";// 结果字符串

	public BufferedImage getImage() {

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Random random = new Random();
		// 生成背景
		g.setColor(new Color(0xCCCCCC));
		g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		// 生成边框
		g.setColor(new Color(0xF4F4F4));
		g.fillRect(1, 1, IMAGE_WIDTH - 2, IMAGE_HEIGHT - 2);
		// 画线
		for (int j = 1; j < 9; j++) {
			int r = 5 - random.nextInt(5);
			g.setColor(Color.gray);
			g.fillArc(random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT), r, r, 0, 360);
			g.drawLine(random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT), IMAGE_WIDTH - random.nextInt(50), IMAGE_HEIGHT - random.nextInt(30));
		}
		// 设置字体
		String fontStyle[] = { "黑体", "宋体", "楷体", "幼圆" };
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
