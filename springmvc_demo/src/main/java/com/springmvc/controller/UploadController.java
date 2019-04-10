package com.springmvc.controller;


import com.springmvc.entity.User;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController{

    @RequestMapping("/file")
    @ResponseBody
    public String upload(@RequestParam("upload") MultipartFile file, Model model) {
        if(file==null) {
            return "2";
        }
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        InputStream inputStream = null;
        try {
            inputStream= file.getInputStream();
            IOUtils.copy(inputStream,new FileOutputStream("D:/A.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<User> userList = new ArrayList<User>();
        try {
            //打开工作簿（97格式）
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            //在工作簿中打开工作表
            XSSFSheet sheet = workbook.getSheetAt(0);
            //一行一行读
            for (Row row : sheet) {
                //一般第一行跳过
                if(row.getRowNum() == 0) {
                    continue;
                }

                //行内一格一格读getStringCellValue()只能读取字符类型
//                String id1 = row.getCell(0).getStringCellValue();
                Cell c1 =  row.getCell(0);
                c1.setCellType(CellType.NUMERIC);
                double id = c1.getNumericCellValue();

                Cell c2 = row.getCell(1);
                c2.setCellType(CellType.STRING);
                String username = c2.getStringCellValue();

                Cell c3 = row.getCell(2);
                c3.setCellType(CellType.NUMERIC);
                double age = c3.getNumericCellValue();

                Cell c4 = row.getCell(3);
                c4.setCellType(CellType.STRING);
                String password = c4.getStringCellValue();
//                int id = Integer.parseInt(id1);
//                int age = Integer.parseInt(age2);
                //汉字转拼音
//                String provinceStr = StringUtils.substring(province, 0, -1);
//                String cityStr = StringUtils.substring(city, 0, -1);
//                String districtStr = StringUtils.substring(district, 0, -1);
//				System.out.println(provinceStr);
//				System.out.println(cityStr);
//				System.out.println(districtStr);
//                String shortcode = PinyinHelper.getShortPinyin(provinceStr+cityStr+districtStr).toUpperCase();
//                String citycode = PinyinHelper.convertToPinyinString(cityStr, "", PinyinFormat.WITHOUT_TONE);
                //封装成对象
                User user = new User();
                user.setId(id);
                user.setAge(age);
                user.setUsername(username);
                user.setPassword(password);

                //放入集合中
                userList.add(user);
                System.out.println(user);
            }
            System.out.println(userList);
//            areaService.save(user);
            return  "1";
        } catch (Exception e) {
            e.printStackTrace();
            return  "2";
        }
    }

    @RequestMapping("/file2")
    @ResponseBody
    public String upload2(Model model) throws IOException {
        System.out.println("this is upload2");

        return "1";
    }


  /*  //下面这三个api是struts2里fileupload拦截器里的
    //真实的文件对象，注意该名字必须和页面上file元素的name的名字一样！！！！
    private File upload;
    //文件的mime类型，name+ContentType
    private String uploadContentType;
    //真实的文件名,name+FileName
    private String uploadFileName;
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    @Autowired
    private AreaService areaService;
    //定义一个属性放入值栈中
//	private Map<String,Object> map = new HashMap<String,Object>();
    private String result;
    @Action(value="area_importData",results={@Result(name="success",type="json")})
    public String importData() {
//		System.out.println("上传的文件是："+upload);
//		System.out.println("上传的文件类型是："+uploadContentType);
//		System.out.println("上传的文件名字是："+uploadFileName);
        ArrayList<Area> arealist = new ArrayList<Area>();

        try {
            //打开工作簿（97格式）
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(upload));
            //在工作簿中打开工作表
            HSSFSheet sheet = workbook.getSheetAt(0);
            //一行一行读
            for (Row row : sheet) {
                //一般第一行跳过
                if(row.getRowNum() == 0) {
                    continue;
                }
                //行内一格一格读getStringCellValue()只能读取字符类型
                String id = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String postcode = row.getCell(4).getStringCellValue();
                //汉字转拼音
                String provinceStr = StringUtils.substring(province, 0, -1);
                String cityStr = StringUtils.substring(city, 0, -1);
                String districtStr = StringUtils.substring(district, 0, -1);
//				System.out.println(provinceStr);
//				System.out.println(cityStr);
//				System.out.println(districtStr);
                String shortcode = PinyinHelper.getShortPinyin(provinceStr+cityStr+districtStr).toUpperCase();
                String citycode = PinyinHelper.convertToPinyinString(cityStr, "", PinyinFormat.WITHOUT_TONE);
                //封装成对象
                Area area = new Area();
                area.setId(id);
                area.setProvince(province);
                area.setCity(city);
                area.setDistrict(district);
                area.setPostcode(postcode);
                area.setShortcode(shortcode);
                area.setCitycode(citycode);
                //放入集合中
                arealist.add(area);
            }
            System.out.println(arealist);
            areaService.save(arealist);
            result = "1";
        } catch (Exception e) {
            e.printStackTrace();
            result = "0";
        }
        ActionContext.getContext().getValueStack().push(result);
        return SUCCESS;
    }

    */

}
