import com.sun.corba.se.impl.orbutil.ObjectWriter;
import org.junit.Test;
import sun.util.resources.ga.LocaleNames_ga;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class TestIOStream {
    static final String DIR_PATH = "/Users/leon_chiang/Study/Github/Programming/Java/JavaSE/第13章_IO流";
    static File file = new File(DIR_PATH + File.separator + "file.txt");
    static String str = "是否真的爱我\n" +
            "\n" +
            "是否真的爱我\n" +
            "别对我沉默\n" +
            "这月色美丽的夜晚\n" +
            "你在想什么\n";

    @Test
    public void testFile() throws IOException {
        /** 构造器 */
        File file1 = new File(DIR_PATH + File.separator + "file1");
        File file2 = new File(DIR_PATH + File.separator + "file1", "file2");
        File file3 = new File(file1, "file3.txt");

        /** File类的创建功能 */
        // 1.创建文件目录。如果此文件目录存在，或者此文件目录的上层目录不存在，就不创建。
        System.out.println(file1.mkdir());
        // 2.创建文件目录。如果上层文件目录不存在，一并创建
        //   注意事项：如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下。
        System.out.println(file2.mkdirs());
        // 3.创建文件。若文件存在，则不创建，返回false
        System.out.println(file3.createNewFile());

        /** File类的获取功能 */
        // 1.获取绝对路径
        System.out.println(file1.getAbsolutePath());
        // 2.获取路径
        System.out.println(file1.getPath());
        // 3.获取名称
        System.out.println(file1.getName());
        // 4.获取上层文件目录路径。若无，返回null
        System.out.println(file1.getParent());
        // 5.获取文件长度（即：字节数）。不能获取目录的长度，返回 0
        System.out.println(file3.length());
        // 6.获取最后一次的修改时间，毫秒值
        System.out.println(file1.lastModified());
        // 7.获取指定目录下的所有文件或者文件目录的名称 String数组
        System.out.println(Arrays.toString(file1.list()));
        // 8.获取指定目录下的所有文件或者文件目录的 File数组
        System.out.println(Arrays.toString(file1.listFiles()));

        /** File类的重命名功能 */
        // 1.把文件重命名为指定的文件路径，相当于移动文件
        System.out.println(file2.renameTo(new File(DIR_PATH + File.separator + "file222")));

        /** File类的判断功能 */
        // 1.判断是否是文件目录
        System.out.println(file1.isDirectory());
        // 2.判断是否是文件目录
        System.out.println(file1.isFile());
        // 3.判断是否存在
        System.out.println(file1.exists());
        // 4.判断是否可读
        System.out.println(file1.canRead());
        // 5.判断是否可写
        System.out.println(file1.canWrite());
        // 6.判断是否隐藏
        System.out.println(file1.isHidden());

        /** File类的删除功能 */
        // 1.删除文件或者文件夹
        // 删除注意事项：Java中的删除不走回收站。
        System.out.println(file1.delete());  // 删除文件目录，里面不能包含文件或者文件目录，否则会失败
        System.out.println(file3.delete());
        System.out.println(file1.delete());
        System.out.println(file2.delete()); // 该操作不能删除flie222，因为名称还是 /Users/leon_chiang/Study/Github/Programming/Java/JavaSE/第13章_IO流/file1/file2
        System.out.println(new File(DIR_PATH + File.separator + "file222").delete());
    }

    @Test
    public void testOutputStream() {
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 标准OutputStream流程 */
        OutputStream outputStream1 = null;
        try {
            outputStream1 = new FileOutputStream(file, true);
            // 1.将指定的字节写入此输出流
            outputStream1.write(1024);
            outputStream1.write("\n---------\n".getBytes(StandardCharsets.UTF_8));
            // 2.将指定 byte 数组中从偏移量 0 开始的 10 个字节写入此输出流
            outputStream1.write(str.getBytes(StandardCharsets.UTF_8), 0, 10);
            outputStream1.write("\n---------\n".getBytes(StandardCharsets.UTF_8));
            // 3.将指定的 byte 数组写入此输出流
            outputStream1.write(str.getBytes(StandardCharsets.UTF_8));

            // 4.刷新此输出流并强制写出所有缓冲的输出字节，调用此方法指示应将这些字节立即写入它们预期的目标
            outputStream1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream1 != null) {
                    // 5.关闭此输出流并释放与该流关联的所有系统资源
                    outputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /** JDK8优化关闭流 */
        try(OutputStream outputStream = new FileOutputStream(file, true)){
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriter() {file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 标准Writer流程 */
        Writer writer1 = null;
        try{
            writer1 = new FileWriter(file);
            writer1.write(1024);
            writer1.write("\n---------\n");
            writer1.write(str.toCharArray(), 0, 5);
            writer1.write("\n---------\n");
            writer1.write(str.toCharArray());
            writer1.write("\n---------\n");
            writer1.write(str, 0, 5);
            writer1.write("\n---------\n");
            writer1.write(str);

            writer1.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer1 != null) {
                    writer1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /** JDK8优化关闭流 */
        try(Writer writer = new FileWriter(file, true)){
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInputStream() {
        /** 标准InputStream流程 */
        byte[] bytes = new byte[1024];
        StringBuffer str = new StringBuffer();
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            // 1.从输入流中读取数据的下一个字节
            while(inputStream.read() != -1){
                break;
            }
            // 2.将输入流中最多 len 个数据字节读入 byte 数组
            while(inputStream.read(bytes, 0, 100) != -1){
                str.append(bytes);
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            }
            System.out.println(str);
            System.out.println("\n-----------\n");

            // 3.从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中
            while(inputStream.read(bytes) != -1){
                str.append(bytes);
            }
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null) {
                    // 4.关闭此输入流并释放与该流关联的所有系统资源
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /** JDK8优化关闭流 */
        try(BufferedInputStream inputStream1 = new BufferedInputStream(new FileInputStream(file))){
            while (inputStream1.read(bytes) != -1) {
                str.append(new String(bytes, "utf-8"));
            }
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReader(){
        /** 标准Reader写法 */
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = new char[1024];
        Reader reader1 = null;
        try{
            reader1 = new FileReader(file);
            while(reader1.read() != -1){
                break;
            }
            while (reader1.read(chars, 0 , 20) != -1){
                stringBuffer.append(chars);
            }
            while (reader1.read(chars) != -1){
                stringBuffer.append(chars);
            }
            System.out.println(stringBuffer);
        }catch (IOException exception){
            exception.printStackTrace();
        }finally {
            try{
                if(reader1 != null){
                    reader1.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }


        /** JDK8优化关闭流 */
        chars = new char[1024];
        try(Reader reader = new FileReader(file)){
            while(reader.read(chars) != -1){
                stringBuffer.append(chars);
            }
            System.out.println(stringBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileCopy(){
        File desFile = new File(DIR_PATH + File.separator + "desFile.txt");
        byte[] bytes = new byte[1024];
        try(InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = new FileOutputStream(desFile)){
            desFile.createNewFile();
            while(inputStream.read(bytes) != -1){
                outputStream.write(bytes);
            }
            outputStream.flush();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testBufferStream(){
        File desBufFile = new File(DIR_PATH + File.separator + "desBufFile.txt");
        String temp;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(desBufFile, true))){
            // 1.按照每一行读取
            while((temp = bufferedReader.readLine()) != null){
                bufferedWriter.write(temp + "\n");
            }
            bufferedWriter.flush();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testConvertStream() throws IOException {
        // 1.将InputStream转换为Reader
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
        // 2.将OutputStream转换为Writer
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
    }

    /***
     * 标准输入输出流
     * 当输入quit、exit时，退出程序（要用main函数进行测试，用@Test不行）
     * @throws IOException
     */
    @Test
    public void testSystemInOut() throws IOException {
//    public static void main (String[] args) throws IOException, ClassNotFoundException {
        try(InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String str;
            while (true) {
                System.out.println("请输入：");
                str = bufferedReader.readLine();
                if("exit".equals(str) || "quit".equals(str)) break;
                System.out.println(str);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testPrintStream(){
        PrintStream ps =  null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换行
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Test
    public void testDataStream(){
        DataOutputStream dos = null;
        try { // 创建连接到指定文件的数据输出流对象
            dos = new DataOutputStream(new FileOutputStream("destData.dat"));
            dos.writeUTF("我爱北京天安门"); // 写UTF字符串
            dos.writeBoolean(false); // 写入布尔值
            dos.writeLong(1234567890L); // 写入长整数
            System.out.println("写文件成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 关闭流对象
            try {
                if (dos != null) {
                    // 关闭过滤流时,会自动关闭它包装的底层节点流
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("destData.dat"));
            String info = dis.readUTF();
            boolean flag = dis.readBoolean();
            long time = dis.readLong();
            System.out.println(info);
            System.out.println(flag);
            System.out.println(time);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试 ObjectOutputStream
     */
    @Test
    public void testObjectOutputStream() throws IOException {
        File file = new File(DIR_PATH + File.separator + "testObjectStream.txt");
        // 如果某个类的属性不是基本数据类型或 String 类型，而是另一个引用类型，那么这个引用类型必须是可序列化的，否则拥有该类型的Field 的类也不能序列化
        Man man = new Man(23, "leon", 175, new AAAAA());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(man);
            outputStream.flush();
        }
    }

    /**
     * 测试 ObjectInputStream
     */
    @Test
    public void testObjectInputStream() throws IOException, ClassNotFoundException {
        File file = new File(DIR_PATH + File.separator + "testObjectStream.txt");
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
            Object man = inputStream.readObject();
            System.out.println(man);
        }
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        byte [] bytes = new byte[1024];
        /** 读取文件 */
        try(RandomAccessFile randomAccessFile1 = new RandomAccessFile(file, "r")){
            // 设置读取开始位置：第6个字节开始
            randomAccessFile1.seek(6);
            randomAccessFile1.read(bytes);
            System.out.println(new String(bytes));
            System.out.println(randomAccessFile1.getFilePointer());
        }

        /** 写入文件 */
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")){
            // 设置写入开始位置
            randomAccessFile.seek(6);
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.write("开始写入".getBytes(StandardCharsets.UTF_8));
        }
    }

    @Test
    public void testPaht(){
        Path path = Paths.get(DIR_PATH);
        System.out.println(path.getName(6));
    }

    @Test
    public void testFiles(){
        // 检测文件路径是否存在。
//        Files.exists();
//        Files.createFile()：创建文件。
//        Files.createDirectory()：创建文件夹。
//        Files.delete()：删除一个文件或目录。
//        Files.copy()：复制文件。
//        Files.move()：移动文件。
//        Files.size()：查看文件个数。
//        Files.read()：读取文件。
//        Files.write()：写入文件。
    }
}


class Man implements Serializable{

    public Integer age;
    public String name;
    public Integer height;
    public AAAAA aaaaa;

    public Man(Integer age, String name, Integer height, AAAAA aaaaa) {
        this.age = age;
        this.name = name;
        this.height = height;
        this.aaaaa = aaaaa;
    }

    @Override
    public String toString() {
        return "Man{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", aaaaa=" + aaaaa +
                '}';
    }
}
class AAAAA implements Serializable{

}