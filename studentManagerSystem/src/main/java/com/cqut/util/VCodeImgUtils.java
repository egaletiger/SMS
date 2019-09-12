package com.cqut.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description 生产验证码
 * @Date 2019/7/24 13:59
 * @Created by ZFQ
 */
public class VCodeImgUtils {
    private  static final int HEIGHT = 40;
    private  static final int WIDTH = 100;
    private  static final int FONT_SIZE = 20;
    private static Random ran = new Random();

    /**
     * 获取随机验证码
     * @return
     */
    private static int[] getVCode(){
        int[] vcode = new int[4];
        for(int i = 0; i < 4; i++){
            vcode[i] = (int) Math.floor(Math.random() * 10);
        }
        return vcode;
    }

    /**
     * 获取随机颜色
     * @return
     */
    private static Color getRanColor(){
        return new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
    }

    /**
     * 获取验证码图片以及验证码
     * @return
     */
    public static Map<String,Object> getVCodeMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        BufferedImage vcodeImg = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = vcodeImg.getGraphics();
        Color c = g.getColor();
        drawBackground(vcodeImg,g);
        int[] vcode = getVCode();
        drawVCode(vcodeImg,g,vcode);

        //封装验证码图片和验证码
        String vcodeStr = "";
        for(int i = 0; i < vcode.length; i++){
            vcodeStr += ""+vcode[i];
        }
        map.put("vcodeImg",vcodeImg);
        map.put("vcode", vcodeStr);
        return map;
    }

    /**
     * 填充验证码
     * @param vcodeImg
     * @param g
     * @param vcode
     */
    private static void drawVCode(BufferedImage vcodeImg, Graphics g, int[] vcode) {
        g.setFont(new Font("黑体", Font.BOLD, FONT_SIZE));
        for(int i = 0; i < vcode.length; i++){
            g.setColor(getRanColor());
            g.drawString("" + vcode[i], 10+ran.nextInt(10) +i*20, 15+ ran.nextInt(10)+i*5);
        }
    }

    /**
     * 设置验证码图片背景
     * @param vcodeImg
     * @param g
     */
    private static void drawBackground(BufferedImage vcodeImg, Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);

        //绘制干扰点
        for(int i = 0; i < 100; i++){
            int x = ran.nextInt(30);
            int y = ran.nextInt(HEIGHT);
            g.setColor(getRanColor());
            g.fillOval(x,y,2,2);
        }
        for(int i = 0; i < 100; i++){
            int x = 30 + ran.nextInt(30);
            int y = ran.nextInt(HEIGHT);
            g.setColor(getRanColor());
            g.fillOval(x,y,2,2);
        }
        for(int i = 0; i < 100; i++){
            int x = 60 + ran.nextInt(40);
            int y = ran.nextInt(HEIGHT);
            g.setColor(getRanColor());
            g.fillOval(x,y,2,2);
        }
    }
}
