package com.bm.word.controller;

import com.bm.word.service.WordService;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.Map;


/**
 * Swagger接口文档API转Word
 *
 * @author QINGUOQING
 */
@Api(tags = "Swagger接口文档API转Word")
@Controller
@PropertySource("classpath:config.properties")
public class WordController {

    @Value("${swagger_url}")
    private String swaggerUrl;

    @Autowired
    private WordService tableService;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    /**
     * 将 swagger 文档转换成 html 文档，可通过在网页上右键另存为 xxx.doc 的方式转换为 word 文档
     *
     * @param model
     * @param url   需要转换成 word 文档的资源地址
     * @return
     */
    @Deprecated
    @ApiOperation(value = "将 swagger 文档转换成 html 文档，可通过在网页上右键另存为 xxx.doc 的方式转换为 word 文档")
    @RequestMapping(value = "/toWord", method = {RequestMethod.GET})
    public String getWord(Model model,
                          @ApiParam(value = "资源地址", required = false)
                          @RequestParam(value = "url", required = false) String url,
                          @ApiParam(value = "是否下载", required = false)
                          @RequestParam(value = "download", defaultValue = "1") Integer download) {
        generateModelData(model, url, download);
        return "word";
    }

    private void generateModelData(Model model, String url, Integer download) {
        url = StringUtils.defaultIfBlank(url, swaggerUrl);
        Map<String, Object> result = tableService.tableList(url);
        model.addAttribute("url", url);
        model.addAttribute("download", download);
        model.addAllAttributes(result);
    }

    /**
     * 将 swagger 文档一键下载为 doc 文档
     *
     * @param model
     * @param url      需要转换成 word 文档的资源地址
     * @param response
     */
    @ApiOperation(value = "将 swagger 文档一键下载为 doc 文档")
    @RequestMapping(value = "/downloadWord", method = {RequestMethod.GET})
    public void word(Model model,
                     @ApiParam(value = "资源地址", required = false) @RequestParam(required = false) String url,
                     HttpServletResponse response) {
        generateModelData(model, url, 0);
        writeContentToResponse(model, response);
    }

    private void writeContentToResponse(Model model, HttpServletResponse response) {
        Map<String, String> info = (Map<String, String>) model.getAttribute("info");
        String title = MapUtils.getString(info, "title");
        Context context = new Context();
        context.setVariables(model.asMap());
        String content = springTemplateEngine.process("word", context);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            response.setHeader("Content-disposition", "attachment;filename="
                    + URLEncoder.encode(String.format("%s" + ".doc", title), "utf-8"));
            byte[] bytes = content.getBytes();
            bos.write(bytes, 0, bytes.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 swagger json文件转换成 word文档并下载
     *
     * @param model
     * @param jsonFile 需要转换成 word 文档的swagger json文件
     * @param response
     * @return
     */
    @ApiOperation(value = "将 swagger json文件转换成 word文档并下载")
    @RequestMapping(value = "/fileToWord", method = {RequestMethod.POST})
    public void getWord(Model model,
                        @ApiParam("swagger json file") @Valid @RequestPart("jsonFile") MultipartFile jsonFile,
                        HttpServletResponse response) {
        generateModelData(model, jsonFile);
        writeContentToResponse(model, response);
    }

    /**
     * 将 swagger json字符串转换成 word文档并下载
     *
     * @param model
     * @param jsonStr  需要转换成 word 文档的swagger json字符串
     * @param response
     * @return
     */
    @ApiOperation(value = "将 swagger json字符串转换成 word文档并下载")
    @RequestMapping(value = "/strToWord", method = {RequestMethod.POST})
    public void getWord(Model model, @ApiParam("swagger json string") @Valid @RequestParam("jsonStr") String jsonStr,
                        HttpServletResponse response) {
        generateModelData(model, jsonStr);
        writeContentToResponse(model, response);
    }

    /**
     * 生成markdown接口文档
     *
     * @param url
     * @throws MalformedURLException
     */
    @ApiOperation(value = "将 swagger json字符串转换成Markdown文档下载")
    @ResponseBody
    @GetMapping("/getMarkdown")
    public String getMarkdown(@RequestParam(value = "url", required = false) String url) {
        url = StringUtils.defaultIfBlank(url, swaggerUrl);
        try {
            //    输出Markdown到单文件
            Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                    .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                    .withOutputLanguage(Language.ZH)
                    .withPathsGroupedBy(GroupBy.TAGS)
                    .withGeneratedExamples()
                    .withoutInlineSchema()
                    .build();

            Swagger2MarkupConverter.from(new URL(url))
                    .withConfig(config)
                    .build()
                    .toFile(Paths.get("src/main/resources/docs/markdown"));
        } catch (MalformedURLException e) {
            return "请检查服务是否正常启动，Swagger是否正常运行 ： " + url;
        }

        return "生成markdown接口文档完成";
    }

    private void generateModelData(Model model, String jsonStr) {
        Map<String, Object> result = tableService.tableListFromString(jsonStr);
        model.addAttribute("url", "http://");
        model.addAttribute("download", 0);
        model.addAllAttributes(result);
    }

    private void generateModelData(Model model, MultipartFile jsonFile) {
        Map<String, Object> result = tableService.tableList(jsonFile);
        model.addAttribute("url", "http://");
        model.addAttribute("download", 0);
        model.addAllAttributes(result);
    }
}
