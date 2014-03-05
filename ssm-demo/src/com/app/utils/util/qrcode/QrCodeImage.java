/**
 * @Filename：QrCodeImage.java
 * @author 汤建东
 * @Date：2013-12-11
 */
package com.app.utils.util.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 * @Class：QrCodeImage.java
 * @Description：二维码图片
 * @author 汤建东
 * @Date：2013-12-11
 */
public class QrCodeImage implements QRCodeImage {
	BufferedImage bufImg;

	public QrCodeImage(BufferedImage bufImg) {
		this.bufImg = bufImg;
	}

	@Override
	public int getHeight() {
		return bufImg.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return bufImg.getRGB(x, y);
	}

	@Override
	public int getWidth() {
		return bufImg.getWidth();
	}
}
