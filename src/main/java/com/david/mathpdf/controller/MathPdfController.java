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
@RequestMapping("/math")
public class MathPdfController {
    //生成九九乘法表随机题
    @RequestMapping("/multiplySheet")
    public String multiplySheet(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "九九乘法表随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成乘法口诀表
        list = createMultiplySheet();
        Collections.shuffle(list);
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
            j++;

            if (j < list.size()) {
                firstContent = list.get(i) + "□";
                secondContent = list.get(j) + "□";
            }

            if (j % 2 != 0 && j < list.size()) {
                 lastContent = firstContent + "  " + secondContent;
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

    //生成乘法口诀表
    private static ArrayList<String> createMultiplySheet() {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 1; i <10 ; i++) {
            for (int j = 1; j <10 ; j++) {
                tempList.add(i + "×" + j + "=");
            }
        }

        return tempList;
    }

    //生成两位数加两位数随机题
    @RequestMapping("/doublePlusDouble")
    public String doublePlusDouble(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数加两位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数加两数随机题
        list = createDoublePlusDouble();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数加两位数
    private static ArrayList<String> createDoublePlusDouble() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]
            int firstPlus = r1.nextInt(99 - 10 + 1) + 10;
            int lastPlus = r2.nextInt(99 - 10 + 1) + 10;
            result = firstPlus + lastPlus;
            if (result < 110) {
                expression = firstPlus + "+" + lastPlus + "=";
                tempList.add(expression);

                count++;
            }

            if (count == 100) {
                break;
            }
        }

       return tempList;
    }

    //生成两位数减两位数随机题
    @RequestMapping("/doubleSubDouble")
    public String doubleSubDouble(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数减两位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数加两数随机题
        list = createDoubleSubDouble();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数减两位数
    private static ArrayList<String> createDoubleSubDouble() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]
            int firstSub = r1.nextInt(99 - 10 + 1) + 10;
            int lastSub = r2.nextInt(99 - 10 + 1) + 10;

            if (firstSub > lastSub) {
                expression = firstSub + "-" + lastSub + "=";
                tempList.add(expression);

                count++;
            }

            if (count == 100) {
                break;
            }
        }

        return tempList;
    }

    //生成两位数加一位数随机题
    @RequestMapping("/doublePlusSingle")
    public String doublePlusSingle(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数加一位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数加一位数随机题
        list = createDoublePlusSingle();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数加一位数
    private static ArrayList<String> createDoublePlusSingle() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]
            int firstPlus = r1.nextInt(99 - 10 + 1) + 10;
            int lastPlus = r2.nextInt(9 - 1 + 1) + 1;
            result = firstPlus + lastPlus;
            if (result < 110) {
                expression = firstPlus + "+" + lastPlus + "=";
                tempList.add(expression);

                count++;
            }

            if (count == 100) {
                break;
            }
        }

        return tempList;
    }

    //生成两位数减一位数随机题
    @RequestMapping("/doubleSubSingle")
    public String doubleSubSingle(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数减一位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数减一位数随机题
        list = createDoubleSubSingle();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数减一位数
    private static ArrayList<String> createDoubleSubSingle() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]
            int firstSub = r1.nextInt(99 - 10 + 1) + 10;
            int lastSub = r2.nextInt(9 - 1 + 1) + 1;

            if (firstSub > lastSub) {
                expression = firstSub + "-" + lastSub + "=";
                tempList.add(expression);

                count++;
            }

            if (count == 100) {
                break;
            }
        }

        return tempList;
    }

    //生成两位数乘一位数随机题
    @RequestMapping("/doubleMultiSingle")
    public String doubleMultiSingle(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数乘一位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数乘一位数随机题
        list = createDoubleMultiSingle();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数乘一位数
    private static ArrayList<String> createDoubleMultiSingle() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]

            int firstTimes = r1.nextInt(99 - 10 + 1) + 10;
            int lastTimes = r2.nextInt(9 - 1 + 1) + 1;

            expression = firstTimes + "×" + lastTimes + "=";
            tempList.add(expression);

            count++;

            if (count == 100) {
                break;
            }
        }

        return tempList;
    }

    //生成两位数乘两位数随机题
    @RequestMapping("/doubleMultiDouble")
    public String doubleMultiDouble(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数乘两位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(60, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数乘两位数随机题
        list = createDoubleMultiDouble();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数乘两位数
    private static ArrayList<String> createDoubleMultiDouble() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]

            int firstTimes = r1.nextInt(99 - 10 + 1) + 10;
            int lastTimes = r2.nextInt(99 - 10 + 1) + 10;

            expression = firstTimes + "×" + lastTimes + "=";
            tempList.add(expression);

            count++;

            if (count == 100) {
                break;
            }
        }

        return tempList;
    }

    //生成一位数除一位数随机题
    @RequestMapping("/singleDivideSingle")
    public String singleDivideSingle(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "一位数除一位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数除一位数随机题
        list = createSingleDivideSingle();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //一位数除一位数
    private static ArrayList<String> createSingleDivideSingle() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]

            int firstDivide = r1.nextInt(9 - 1 + 1) + 1;
            int lastDivide = r2.nextInt(9 - 1 + 1) + 1;

            if (firstDivide > lastDivide) {
                if (firstDivide % lastDivide == 0) {
                    expression = firstDivide + "÷" + lastDivide + "=";
                    tempList.add(expression);

                    count++;

                    if (count == 100) {
                        break;
                    }
                }
            }
        }

        return tempList;
    }

    //生成两位数除一位数随机题
    @RequestMapping("/doubleDivideSingle")
    public String doubleDivideSingle(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "两位数除一位数随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> list = new ArrayList<>();
        //生成两位数除一位数随机题
        list = createDoubleDivideSingle();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < list.size() ; i++) {
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

    //两位数除一位数
    private static ArrayList<String> createDoubleDivideSingle() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]

            int firstDivide = r1.nextInt(99 - 10 + 1) + 10;
            int lastDivide = r2.nextInt(9 - 1 + 1) + 1;

            if (firstDivide > lastDivide && firstDivide <= 81 && lastDivide != 1) {
                if (firstDivide % lastDivide == 0) {
                    expression = firstDivide + "÷" + lastDivide + "=";
                    tempList.add(expression);

                    count++;

                    if (count == 100) {
                        break;
                    }
                }
            }
        }

        return tempList;
    }

    //生成一位数加或乘一位数混合随机题
    @RequestMapping("/singleMixSingle")
    public String singleMixSingle(HttpServletResponse response) throws Exception {
        Random r = new Random();
        String filename = "一位数加或乘一位数混合随机题-" + r.nextInt(99 - 10 + 1) + 10;
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
        Font bigFont = PdfUtil.createFont(65, Font.NORMAL, BaseColor.BLACK);
        Font littleFont = PdfUtil.createFont(10, Font.NORMAL, BaseColor.BLACK);

        Paragraph content = PdfUtil.createParagraph("", bigFont);
        content.setAlignment(Element.ALIGN_LEFT);

        ArrayList<String> listPlus = new ArrayList<>();
        ArrayList<String> listMulti = new ArrayList<>();
        //生成两位数除一位数随机题
        listPlus = createSinglePlusSingle();
        listMulti = createMultiplySheet();
        String firstContent = "";
        String secondContent = "";
        String lastContent = "";

        int j = 0;
        for (int i = 0; i < listMulti.size() -1; i++) {
            j++;
            firstContent = listPlus.get(i) + "□";
            secondContent = listMulti.get(i) + "□";

            if (j % 2 != 0 && j < listPlus.size()) {
                lastContent = firstContent + "   " + secondContent;
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

    //一位数加一位数
    private static ArrayList<String> createSinglePlusSingle() {
        ArrayList<String> tempList = new ArrayList<>();

        Random r1 = new Random();
        Random r2 = new Random();

        String expression;
        int result;
        int count = 0;
        while (true) {
            //生成10-99区间的随机整数
            // 区间分析：
            //(max-min) 表示 [0,max)—因为min=0,所以也可以直接写 (max)
            //(max-min+1) 表示 [0,max]—因为min=0,所以也可以写(max+1)
            //(max-min+1)+min 表示 [min,max]
            int firstPlus = r1.nextInt(9 - 1 + 1) + 1;
            int lastPlus = r2.nextInt(9 - 1 + 1) + 1;
            result = firstPlus + lastPlus;
            if (result < 110) {
                expression = firstPlus + "+" + lastPlus + "=";
                tempList.add(expression);

                count++;
            }

            if (count == 100) {
                break;
            }
        }

        return tempList;
    }
}
