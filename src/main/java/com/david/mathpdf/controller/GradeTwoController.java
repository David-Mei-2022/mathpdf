package com.david.mathpdf.controller;

import com.david.mathpdf.util.PdfUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@RestController
@RequestMapping("/gradetwo")
public class GradeTwoController {
    //生成一位数先乘后加混合随机题;
    @RequestMapping("/multiandplus")
    public String multiplyAndPlus(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "一位数先乘后加混合随机题-" + r.nextInt(99 - 10 + 1) + 10;
        // 设置下载格式为pdf
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".pdf");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());

        // 1. Document document = new Document();
        Document document = PdfUtil.createDocument();
        // 2. 获取writer
        PdfWriter.getInstance(document, os);
        // 3. open()
        document.open();

        //设置字体
        Font blackFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);
        Font blueFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLUE);
        Font bigFont = PdfUtil.createFont(58, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成一位数先乘后加混合随机题
        list = createMultiAndPlus();
        Collections.shuffle(list);
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            j++;

            if (j < list.size()) {
                firstContent = list.get(i) + "□";
                secondContent = list.get(j) + "□";
            }

            if (j % 2 != 0 && j < list.size()) {
                lastContent = firstContent + " " + secondContent;
                content = PdfUtil.createParagraph(lastContent, bigFont);
                // 4. 添加段落内容
                document.add(content);
            }
        }

        // 5. close()
        document.close();
        os.close();
        //return new Response().setContent("success");
        return "success";
    }

    //生成一位数先乘后加混合随机题
    private static ArrayList<String> createMultiAndPlus() {
        Random r = new Random();

        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int plus = r.nextInt(9) + 1;
                tempList.add(i + "×" + j + "+" + plus + "=");
            }
        }

        return tempList;
    }

    //生成一位数先乘后减混合随机题;
    @RequestMapping("/multiandsub")
    public String multiplyAndSub(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "一位数先乘后减混合随机题-" + r.nextInt(99 - 10 + 1) + 10;
        // 设置下载格式为pdf
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".pdf");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());

        // 1. Document document = new Document();
        Document document = PdfUtil.createDocument();
        // 2. 获取writer
        PdfWriter.getInstance(document, os);
        // 3. open()
        document.open();

        //设置字体
        Font blackFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);
        Font blueFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLUE);
        Font bigFont = PdfUtil.createFont(58, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成一位数先乘后减混合随机题
        list = createMultiAndSub();
        Collections.shuffle(list);
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            j++;

            if (j < list.size()) {
                firstContent = list.get(i) + "□";
                secondContent = list.get(j) + "□";
            }

            if (j % 2 != 0 && j < list.size()) {
                lastContent = firstContent + " " + secondContent;
                content = PdfUtil.createParagraph(lastContent, bigFont);
                // 4. 添加段落内容
                document.add(content);
            }
        }

        // 5. close()
        document.close();
        os.close();
        //return new Response().setContent("success");
        return "success";
    }

    //生成一位数先乘后减混合随机题
    private static ArrayList<String> createMultiAndSub() {
        Random r = new Random();

        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int sub = r.nextInt(9) + 1;
                if ((i * j - sub) > 0 ) {
                    tempList.add(i + "×" + j + "-" + sub + "=");
                }
            }
        }

        return tempList;
    }

    //生成一位数先加后乘混合随机题;
    @RequestMapping("/pulsandmulti")
    public String plusAndMulti(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "一位数先加后乘混合随机题-" + r.nextInt(99 - 10 + 1) + 10;
        // 设置下载格式为pdf
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".pdf");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());

        // 1. Document document = new Document();
        Document document = PdfUtil.createDocument();
        // 2. 获取writer
        PdfWriter.getInstance(document, os);
        // 3. open()
        document.open();

        //设置字体
        Font blackFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);
        Font blueFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLUE);
        Font bigFont = PdfUtil.createFont(58, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成一位数先加后乘混合随机题
        list = createPlusAndMulti();
        Collections.shuffle(list);
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            j++;

            if (j < list.size()) {
                firstContent = list.get(i) + "□";
                secondContent = list.get(j) + "□";
            }

            if (j % 2 != 0 && j < list.size()) {
                lastContent = firstContent + " " + secondContent;
                content = PdfUtil.createParagraph(lastContent, bigFont);
                // 4. 添加段落内容
                document.add(content);
            }
        }

        // 5. close()
        document.close();
        os.close();
        //return new Response().setContent("success");
        return "success";
    }

    //生成一位数先加后乘混合随机题
    private static ArrayList<String> createPlusAndMulti() {
        Random r = new Random();

        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int plus = r.nextInt(9) + 1;
                tempList.add(plus + "+" + i + "×" + j + "=");
            }
        }

        return tempList;
    }

    //生成一位数先减后乘混合随机题;
    @RequestMapping("/subandmulti")
    public String subAndMulti(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "一位数先减后乘混合随机题-" + r.nextInt(99 - 10 + 1) + 10;
        // 设置下载格式为pdf
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".pdf");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());

        // 1. Document document = new Document();
        Document document = PdfUtil.createDocument();
        // 2. 获取writer
        PdfWriter.getInstance(document, os);
        // 3. open()
        document.open();

        //设置字体
        Font blackFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);
        Font blueFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLUE);
        Font bigFont = PdfUtil.createFont(53, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成一位数先减后乘混合随机题
        list = createSubAndMulti();
        Collections.shuffle(list);
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size()-1; i++) {
            j++;

            if (j < list.size()) {
                firstContent = list.get(i) + "□";
                secondContent = list.get(j) + "□";
            }

            if (j % 2 != 0 && j < list.size()) {
                lastContent = firstContent + " " + secondContent;
                content = PdfUtil.createParagraph(lastContent, bigFont);
                // 4. 添加段落内容
                document.add(content);
            }
        }

        // 5. close()
        document.close();
        os.close();
        //return new Response().setContent("success");
        return "success";
    }

    //生成一位数先减后乘混合随机题
    private static ArrayList<String> createSubAndMulti() {
        Random r = new Random();

        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int sub = r.nextInt(99);
                if ((sub - i * j) > 0 ) {
                    tempList.add(sub + "-" + i + "×" + j + "=");
                }
            }
        }

        return tempList;
    }
}