package freemaker.util;
import freemaker.model.FreeParam;
import freemaker.model.Model;
import freemaker.model.Param;
import freemaker.util.log.LogKit;
import freemaker.XmlService;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;


import org.nix.model.base.BaseModel;



import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by 11723 on 2017/5/5.
 */
public class FreemakerRoot {
    private final static int NO_COLUMN = 0;
    //pojo存在一对一的关系
    private final static int ONE_ONT_ONE = 1;
    //pojo存在一对多的关系
    private final static int ONE_TWO_MONY = 2;
    //替换的xml文件关键字段  自动生成的代码会替换在原文件里的有该第一次字符串的地方
    private final String keyWord = "<!--ftl_contrnt-->";
    //需要修改的mapping.xml是否需要mapper节点之外的头部
    private final String ftl_have_header = "mapping_have_header.ftl";

    private final String ftl_no_header = "mapping_no_header.ftl";

    private final static Configuration CONFIGURATION = new Configuration();



    //需要生成xml的实体类的类类型
    private Class baseModelClazz;
    /**
     * 获取项目运行路径
     * 类实例化的加载freemaker工作目录并初始化
     *
     * */
    public FreemakerRoot(){
        try {
            CONFIGURATION.setDirectoryForTemplateLoading(new File(this.getClass().getResource("./").getFile().toLowerCase().replaceFirst("util","ftl")));
            CONFIGURATION.setObjectWrapper(new DefaultObjectWrapper());
            CONFIGURATION.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据pojo实体类自动生成mybatis的mapping.xml的基本增删改查四个方法和pojo数据库映射map
     * <br />
     * 如果该pojo的mapping.xml映射文件已经存在 请在源文件里添加"<!--ftl_contrnt-->"字符串便于生成的内容替换进去
     * @param baseModelClazz 需要生成pojo的类类型
     *
     * */
    public void createMapperXml(Class baseModelClazz) throws IOException, TemplateException {
        this.baseModelClazz = baseModelClazz;
        if (!xmlIsHavaKeyword(getMappingXmlPath())){
            LogKit.info(this.getClass(),"文件校验失败，退出代码生成");
            return;
        }
        LogKit.info(this.getClass(),"文件校验通过 开始代码生成");
        Template template = CONFIGURATION.getTemplate(isHavaFile(getMappingXmlPath()) ? ftl_no_header : ftl_have_header);
        Map<String, Object> paramMap = generateInsertContent();
        Writer writer = new OutputStreamWriter(new FileOutputStream("./mapping.xml"),"UTF-8");
        template.process(paramMap, writer);
        changProjectXml();
    }
    /**
     * 获取本次操作的pojo实体的字段映射关系
     * @return map
     * <br />
     *      map里包含键值
     *      <br/>
     *      model_name 本次操作实体的名称首字母大写<br/>
     *      oneself 本次操作实体字段类<br/>
     *      oneself 本次操作实体包含其他pojo的字段类list
     *
     * */
    private Map<String,Object> generateInsertContent(){
        Map<String,Object> map = new HashMap<>();
        FreeParam freeParam = getFreeParam(baseModelClazz,true);
        map.put("model_name",freeParam.getModel().getModel_name().toLowerCase());
        map.put("oneself",freeParam.getModel());
        map.put("others",freeParam.getModels());
        return map;
    }
    /**
     * 获取本次操作实体类的freeParam对象
     * @param type 需要操作的实体类的类类型
     * @param isRoot 本次操作的类类型时候为更pojo
     * */
    private FreeParam getFreeParam(Class<?> type, boolean isRoot) {
        FreeParam freeParam = new FreeParam();
        Model model = new Model();
        model.setModel_name(type.getSimpleName().replace("model",""));
        List<Param> paramList = new ArrayList<>();
        Param param1 = new Param("id","java.lang.Integer");
        paramList.add(param1);
        for (Field field:type.getDeclaredFields()) {
            if (BaseModel.class.isAssignableFrom(field.getType()) && isRoot){
                Model m = getFreeParam(field.getType(),false).getModel();
                m.setColumnType(ONE_ONT_ONE);
                m.setName(field.getName());
               freeParam.getModels().add(m);
            } else if ((field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Set.class)) && isRoot){
                Model m = getFreeParam((Class<?>) (((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]),false).getModel();
                m.setColumnType(ONE_TWO_MONY);
                m.setName(field.getName());
                freeParam.getModels().add(m);
            } else if (TypeUtil.getJdbcType(field.getType()) == null) continue;
            else {
                Param param = new Param(field.getName(),TypeUtil.getJdbcType(field.getType()).name());
                paramList.add(param);
            }
        }
        model.setParams(paramList);
        model.setName(model.getModel_name().toLowerCase() + "_id");
        model.setType(type.getName());
        freeParam.setModel(model);
        return freeParam;
    }
    /**
     * 根据模本生成的四种基本方法xml内容后再原先的xml内容的添加进去
     * <br />
     * 如果源文件不存在  将会新创建一个mapping.xml文件并附上头节点
     * */
    private void changProjectXml(){
        try {
            String orignalPath = getMappingXmlPath();
            String ftlContent = readFile( "./mapping.xml");
            String orignal = isHavaFile(orignalPath) ? readFile(orignalPath) : null;
            orignal = orignal == null ? ftlContent : orignal.replaceAll(keyWord,ftlContent);
            orignal = escape(orignal);
            LogKit.info(this.getClass(),"开始修改文件" + getMappingXmlPath());
            generateXml(orignalPath,orignal);
            LogKit.info(this.getClass(),"修改文件" + getMappingXmlPath() + "成功");
        }catch (IOException e){
            LogKit.error(this.getClass(),"修改/src/main/java" + getMappingXmlPath() + "失败!!!");
            e.printStackTrace();
        }
    }
    private boolean xmlIsHavaKeyword(String filePath){
        if (filePath.matches(".*erssion.*")){
            System.out.println();
        }
        try {
            String orignal = isHavaFile(filePath) ? readFile(filePath) : null;
            return orignal == null || orignal.matches("[\\s\\S]*" + keyWord + "[\\s\\S]*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private String readFile(String filePath) throws IOException {
        FileReader reade = null;
        BufferedReader bufferedReader = null;
        try {
            reade = new FileReader(filePath);
             bufferedReader = new BufferedReader(reade);
            StringBuffer buffer = new StringBuffer();
            String str= null;
            while ((str = bufferedReader.readLine()) != null){
                buffer.append(str + "\r\n");
            }
            return buffer.toString();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }finally {
            reade.close();
            bufferedReader.close();
        }
    }
    private void generateXml(String filePath,String content) throws IOException{
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogKit.error(this.getClass(),filePath + "文件写入失败！！！");
        }finally {
            writer.close();
        }
    }
    private boolean isHavaFile(String filePath){
        return new File(filePath).exists();
    }

    private String escape(String content){
        return content.replace('@','$');
    }

    private String getMappingXmlPath(){
        return XmlService.mappingPath +baseModelClazz.getSimpleName().replaceFirst("Model","Mapping.xml");
    }
}
