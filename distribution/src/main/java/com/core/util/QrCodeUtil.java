package com.core.util;

import com.iboot.weixin.client.HttpClientFactory;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import org.apache.http.client.HttpClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by core on 15/11/28.
 */
public class QrCodeUtil {
    public static String DecoderQRCode(String path){
       String result="";
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        BufferedImage bufImg = null;
        try {
            url = new URL(path);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bufImg = ImageIO.read(bis);
            QRCodeDecoder decoder = new QRCodeDecoder();
            result = new String(decoder.decode(new CodeImage(bufImg)));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (DecodingFailedException dfe) {
            System.out.println("Error: " + dfe.getMessage());
            dfe.printStackTrace();
        }
        return result;


    }


    public static void main(String[] args) {
        System.out.println(QrCodeUtil.DecoderQRCode("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQG87zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3FUbXU5YXJsSjAxZXNJWUZ3UlVXAAIEbL5SVgMEAAAAAA%3D%3D"));
    }
}
 class CodeImage implements QRCodeImage {
    BufferedImage bufImg;
    public CodeImage(BufferedImage bufImg){
        this.bufImg=bufImg;
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