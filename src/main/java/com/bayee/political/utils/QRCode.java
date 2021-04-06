package com.bayee.political.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.awt.*;

/**
 * 二维码生成类
 */
public class QRCode {

	String logPath;

	public QRCode(String logPath) {
		this.logPath = logPath;
	}

	public QRCode() {

	}

	/**
	 * 生成二维码
	 * 
	 * @return 二维码图片路径
	 * @param content 二维码内容
	 * @param width   二维码宽度
	 * @param height  二维码高度
	 * @param path    二维码存放路径+图片名.后缀
	 */
	public void encodeQRCode(String content, int width, int height, String path) {
		QrConfig config = new QrConfig(width, height);
		// 设置二维码及背景之间的间距
		config.setMargin(2);
		// 二维码前景色
//        Color foreColor = new Color(58, 95, 205);
		Color foreColor = new Color(0, 0, 0);
		// 二维码后景色
		Color backColor = new Color(255, 255, 255);
		// 开始设置
		config.setForeColor(foreColor.getRGB()).setBackColor(backColor.getRGB());
		// log
		//config.setImg(logPath);
		QrCodeUtil.generate(content, config, FileUtil.file(path));
	}

	/**
	 * 解析二维码
	 * 
	 * @return 二维码内容
	 * @param path 二维码存放路径
	 */
	public String decodeQRCode(String path) {
		return QrCodeUtil.decode(FileUtil.file(path));
	}

}
