package freemaker;

import freemaker.util.FreemakerRoot;
import freemaker.util.log.LogKit;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 11723
 * @date 2017/5/7
 */
public class XmlService {
    //model.java存放的相对路径
    public static final String modelPath = ".\\src\\main\\java\\org\\nix\\model\\";
    //model所在的包名（实体model必须以xxxModel的类名）
    public static final String packageName = "org.nix.model";
    //生成mapping的路径
    public static final String mappingPath = ".\\src\\main\\java\\org\\nix\\dao\\mapping\\";
    public static final String modelSuffix = "Model";


    /**
     * 执行还main方法会自动生成mapping.xml
     * */
    public static void main(String[] args) {
        new XmlService().startGenerateMappingXml();
    }

    public void startGenerateMappingXml(){
        FreemakerRoot freemaker = new FreemakerRoot();
        List<Class<?>> classes = getClasses(getModelFileName());
        for (Class<?> clazz : classes) {
            try {
                long start = System.currentTimeMillis();
                LogKit.info(this.getClass(),"开始自动写入" + clazz.getSimpleName() + "的mapping.xml内容");
                freemaker.createMapperXml(clazz);
                LogKit.info(this.getClass(),"写入" + clazz.getSimpleName() + "的mapping.xml文件成功");
                LogKit.info(this.getClass(),"耗时：" + (System.currentTimeMillis() - start)/1000 + "毫秒");
            } catch (IOException e) {
                LogKit.error(this.getClass(),"文件操作异常！！！");
                e.printStackTrace();
            } catch (TemplateException e) {
                LogKit.error(this.getClass(),"ftl格式错误！！！");
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取model路径下的model.java文件名  （类名）
     * */
    private List<String> getModelFileName(){
        File dir = new File(modelPath);
        File[] dirFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // 接受dir目录
                boolean acceptDir = file.isDirectory();
                // 接受class文件
                boolean acceptClass = file.getName().endsWith(".java");
                return acceptDir || acceptClass;
            }
        });
        List<String> javaFileNames = new ArrayList<>();
        for (File file:dirFiles){
            if (isModelFile(file.getName())){
                LogKit.info(this.getClass(),"获取到文件" + file.getName());
                javaFileNames.add(file.getName());
            }
        }
        return javaFileNames;
    }
    /**
     * 根据类名获取packageName包下的所有类类型
     * */
    private List<Class<?>> getClasses(List<String> fileNames){
        List<Class<?>> classes = new ArrayList<>();
        for (String fileName:fileNames) {
            try {
                classes.add(Class.forName(packageName + "." + fileName.replaceAll(".java", "")));
                LogKit.info(this.getClass(),"获取到" + fileName.replaceAll(".java", "") + "的类类型");
            } catch (ClassNotFoundException e) {
                LogKit.error(this.getClass(),"获取类类型失败！！！");
                e.printStackTrace();
            }
        }
        return classes;
    }
    /**
     * 判断fileName时候是Model实体类
     * */
    private boolean isModelFile(String fileName){
        return fileName.matches(".*" + modelSuffix + ".java");
    }
}
