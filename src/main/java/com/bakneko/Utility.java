package com.bakneko;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Utility {

    public static String DownloadFile(String fileUrl, String saveUrl) {
        HttpURLConnection httpUrl = null;
        byte[] buf = new byte[1024];
        int size = 0;
        try {
            //下载的地址
            URL url = new URL(fileUrl);
            //支持http特定功能
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            //缓存输入流,提供了一个缓存数组,每次调用read的时候会先尝试从缓存区读取数据
            BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
            File file = new File(saveUrl);
            //判断文件夹是否存在
            if(!file.exists()){
                file.mkdir();//如果不存在就创建一个文件夹
            }
            //讲http上面的地址拆分成数组,
            String arrUrl[] = fileUrl.split("/");
            //输出流,输出到新的地址上面
            FileOutputStream fos = new FileOutputStream(saveUrl+"/"+arrUrl[arrUrl.length-1]);
            while ((size = bis.read(buf)) != -1){
                fos.write(buf, 0, size);
            }
            //记得及时释放资源
            fos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpUrl.disconnect();
        return null;
    }

    public static String GetHttpResponse(String url)
            throws IOException, InterruptedException {
        // 发送 https 请求
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Get the env of windir, such as "C:\WINDOWS".
     *
     * @return the env of windir value.
     */
    public static String getWindir() {
        return System.getenv("windir");
    }

    /**
     * Get file separator, such as "/" on unix.
     *
     * @return the separator of file.
     */
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * Get line separator, such as "\n" on unix.
     *
     * @return the separator of line.
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * Get programPath
     *
     * @return programPath
     */
    public static String getProgramPath() {
        Class<Utility> cls = Utility.class;
        ClassLoader loader = cls.getClassLoader();
        //
        // Get the full name of the class.
        //
        String clsName = cls.getName() + ".class";
        //
        // Get the package that include the class.
        //
        Package pack = cls.getPackage();
        String path = "";
        //
        // Transform package name to path.
        //
        if (pack != null) {
            String packName = pack.getName();
        //
        // Get the class's file name.
        //
            clsName = clsName.substring(packName.length() + 1);
        //
        // If package is simple transform package name to path directly,
        // else transform package name to path by package name's
        // constituent.
        //
            path = packName;
            if (path.indexOf(".") > 0) {
                path = path.replace(".", "/");
            }
            path = path + "/";
        }
        URL url = loader.getResource(path + clsName);
        //
        // Get path information form the instance of URL.
        //
        String retPath = url.getPath();
        //
        // Delete protocol name "file:" form path information.
        //
        try {
            int pos = retPath.indexOf("file:");
            if (pos > -1) {
                retPath = retPath.substring(pos + 5);
            }
        //
        // Delete the information of class file from the information of
        // path.
        //
            pos = retPath.indexOf(path + clsName);
            retPath = retPath.substring(0, pos - 1);
        //
        // If the class file was packageed into JAR e.g. file, delete the
        // file name of the corresponding JAR e.g..
        //
            if (retPath.endsWith("!")) {
                retPath = retPath.substring(0, retPath.lastIndexOf("/"));
            }
            retPath = URLDecoder.decode(retPath, "utf-8");
        } catch (Exception e) {
            retPath = null;
            e.printStackTrace();
        }
        return retPath.substring(1);
    }
}
