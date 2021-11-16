package com.bayee.political.utils;
import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Word2Pdf {

    /* 转PDF格式值 */
    private static final int wdFormatPDF = 17;
    /**
     * Word文档转换
     *
     * @param inputFile
     * @param pdfFile
     */
    public static boolean word2PDF(String inputFile, String pdfFile) {
        ComThread.InitMTA(true);
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");// 创建一个word对象
            app.setProperty("Visible", new Variant(false)); // 不可见打开word
            app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch docs = app.getProperty("Documents").toDispatch();// 获取文挡属性

            System.out.println("打开文档 >>> " + inputFile);
            // Object[]第三个参数是表示“是否只读方式打开”
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
            System.out.println("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");
            // 调用Document对象的SaveAs方法，将文档保存为pdf格式
            // word保存为pdf格式宏，值为17
            Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17

            long end = System.currentTimeMillis();

            System.out.println("用时：" + (end - start) + "ms.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            Dispatch.call(doc, "Close", false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
            // 如果没有这句话,winword.exe进程将不会关闭
            ComThread.Release();
            ComThread.quitMainSTA();
        }
        return false;
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            String rootPath = Word2Pdf.class.getClassLoader().getResource("license.xml").getPath();;
            System.out.println(rootPath);
            File file = new File(rootPath); // 新建一个空白pdf文档
            InputStream is = new FileInputStream(file); // license.xml找个路径放即可。
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doc2pdf(String inPath, String outPath, String url) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return null;
        }
        try {

            long old = System.currentTimeMillis();
            File file = new File(url + outPath); // 新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            FontSettings.setFontsFolder("/usr/share/fonts", false);
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            // System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
            return outPath;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

/*    public static void main(String args[]) {
        ActiveXComponent app = null;
        String wordFile = "E:\\document\\重大事项API.docx";
        String pdfFile = "E:\\document\\重大事项API.pdf";
        Word2Pdf.doc2pdf(wordFile,pdfFile);
//        boolean res = Word2Pdf.word2PDF(wordFile, pdfFile);
    }*/

}
