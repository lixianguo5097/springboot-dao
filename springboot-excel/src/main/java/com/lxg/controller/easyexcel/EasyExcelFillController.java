package com.lxg.controller.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.lxg.model.easyexcel.EasyUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 不要有返回值
 * @author LXG
 * @date 2020-4-30
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/easyexcel/fill")
public class EasyExcelFillController {


    /**
     * 可以填充对象，也可以填充Map
     */
    @GetMapping("/excelSimpleFill")
    public void simpleFill(HttpServletResponse response) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        try {
            String templateFileName = EasyExcelFillController.class.getResource("/").getPath()+"excel_template/simple_template.xlsx";
            // 根据对象填充
            // 这里 会填充到第一个sheet， 然后文件流会自动关闭
            EasyUser user = new EasyUser();
            user.setUserId("20190030");
            user.setName("张三");
            user.setAge(17);
            user.setSex("男");

            String fileName = URLEncoder.encode("simple_file", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).sheet().doFill(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/excelListFill")
    public void complexFill(HttpServletResponse response) {
        try {
            // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
            // {} 代表普通变量 {.} 代表是list的变量
            String templateFileName = EasyExcelFillController.class.getResource("/").getPath()+"excel_template/list_template.xlsx";
            String fileName = URLEncoder.encode("list_file", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
            // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
            // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
            // 如果数据量大 list不是最后一行 参照下一个
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(getEasyUsers(), fillConfig, writeSheet);
            // 其他参数可以使用Map封装
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("remark", "这是一条测试备注信息");
            excelWriter.fill(map, writeSheet);
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EasyUser> getEasyUsers() {
        List<EasyUser> users = new ArrayList<>();
        Collections.addAll(users,
                new EasyUser("20180030", "张三", 18, "男"),
                new EasyUser("20180031", "李四", 17, "女"),
                new EasyUser("20180032", "王五", 16, "女"),
                new EasyUser("20180033", "赵六", 15, "男"),
                new EasyUser("20180034", "田七", 19, "女")
        );
        return users;
    }
}
