import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class man <T>{
   public T a;
   public ArrayList<String> ma;

    public static void main(String[] args) throws NoSuchFieldException {
        man<String> man=new man<>();
        Field field=man.class.getFields()[0];
        System.out.println(field.getType());
        System.out.println(field.getGenericType());
        Field file=man.class.getFields()[1];
        System.out.println(file.getType());
        System.out.println(file.getGenericType());

//        Field field=man.class.getField("ma");
//        System.out.println(field.getType());
//        System.out.println(field.getGenericType());
//        Field field1=man.class.getField("a");
//        System.out.println(field1.getType());
//        System.out.println(field1.getGenericType());




    }
}
