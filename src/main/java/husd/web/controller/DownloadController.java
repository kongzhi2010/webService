package husd.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import husd.framework.BooleanMessage;
import husd.framework.util.ExcelUtil;
import husd.framework.util.FileUtil;

@Controller
public class DownloadController {

    private Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

    @RequestMapping("/downloadTemplate/demo")
    @ResponseBody
    public String downLoadExcelTemplate(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "模板文件名称";
        String suffix = ".xls";
        File targetFile = null;
        try {
            targetFile = File.createTempFile(fileName, suffix);
            createExcelFile(targetFile);
            String filePath = targetFile.getAbsolutePath();
            FileUtil.download(request, response, filePath, fileName + suffix);
        } catch (Exception e) {
            LOGGER.error("下载模板异常", e);
        } finally {
            if (targetFile != null) {
                targetFile.delete();
            }
        }
        return null;
    }

    private BooleanMessage createExcelFile(File file) {
        List<Object[]> data = new ArrayList<Object[]>();
        String[] title = {"表头1", "表头2", "列2"};
        data.add(title);
        boolean status = ExcelUtil.createExcel2003(data, file);
        String info = status ? "生成excel模板成功" : "生成excel模板失败";
        return new BooleanMessage(status, info);
    }
}
