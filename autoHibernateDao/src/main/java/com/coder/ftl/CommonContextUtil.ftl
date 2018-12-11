package ${packagepath};


import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.InputStreamResource;

public class CommonContextUtil { 
  private static ApplicationContext context=null;
  /**
   * ���ϵͳӦ�û�������Ӧ�û�����Ҫ������ϵͳ�ڵ�Facade���ã�Dao����
   * �����õ������ļ��� /application.xml;  /*-context.xml
   * @return ϵͳӦ�û���
   */
  public synchronized static ApplicationContext getApplicationContext(){
    if(context==null){
      try{
        String path = System.getProperty("user.dir")  + "\\src\\";
        System.out.println("Load Application Config:"+path+"\\application.xml");
      //ApplicationContext root             = new FileSystemXmlApplicationContext("file:"+config_home+"/application.xml");
        ApplicationContext root             = new ClassPathXmlApplicationContext(new String[]{"/application.xml"});
        ApplicationContext transation       = new ClassPathXmlApplicationContext(new String[]{"/transaction.xml"},root);
        ApplicationContext dao_atom         = new ClassPathXmlApplicationContext(new String[]{"/dao_context.xml"},transation);
        context = dao_atom;
      }catch(Exception e){
        e.printStackTrace();
      }
    }
    return context;
  }
  public static void main(String []a) throws Exception{
    System.out.println("Start Time:"+System.currentTimeMillis());
    System.out.println(CommonContextUtil.getApplicationContext().getBean("Decoder"));
    System.out.println("Finished Time:"+System.currentTimeMillis());
  }
}
