
package com.huawei.cdsdemo.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(DownloadServlet.class);
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        
        try{        	                
            String fileName = request.getParameter("fileName");
            if (fileName == null || fileName.isEmpty())
	        {
	            return;
	        }

	        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
	    	if (null == contextClassLoader) 
	    	{
	    		return;
			}
	    	URL url = contextClassLoader.getResource("");
	    	if (null == url) 
	        {
	   			return;
	   		}

	        String filePaths = url.getPath().substring(0, url.getPath().lastIndexOf("/classes") + 1);

	        try
	        {
	            filePaths = URLDecoder.decode(filePaths, "UTF-8") + "/audio/";
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            filePaths = "";
	        }
	        
	        String filePath = filePaths + fileName;
	        filePath = Normalizer.normalize(filePath, Form.NFKC);
        
            //检查文件路径start
	        try 
	        {
	        	 Path path = new File(filePath).toPath();
	             if (!isInSecureDir(path))
	             {
	                 log.error("File not in secure directory");
	                 return;
	             }
	             BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
	             
	             if (!attr.isRegularFile())
	             {
	                 log.error("Not a regular file");
	                 return;
	             }
			} 
	        catch (IOException e) 
	        {
	        	log.error("Not a regular file");
	        	return;
			}
            //检查文件路径end
            
	        File file = new File(filePath);
	        if (!file.exists())
	        {
	        	log.info("file is not faile");
	        }
            response.setContentType("application/x-download");
            response.setContentType("application/octet-stream");
            response.setHeader("Expires", "-1");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-control", "no-cache");
            fileName = URLEncoder.encode(file.getName(), "UTF-8");
            
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            
            FileInputStream fs = null;
            BufferedInputStream bis = null;
            OutputStream out = response.getOutputStream();
            BufferedOutputStream bos = null;
   
            try
            {
                fs = new FileInputStream(file);
                bis = new BufferedInputStream(fs);
                bos = new BufferedOutputStream(out);
                           
                byte[] data = new byte[1000];
                int readNum = 0;
                while (true)
                {
                    readNum = bis.read(data);
                    if (readNum == -1)
                    {
                        break;
                    }
                    bos.write(data, 0, readNum);                
                }
                
            } 
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Exception e)
            {
                log.error("PrintWriter println Error");
            }
            finally
            {
                if (null != bos)
                {
                    try
                    {
                        bos.close();
                    }
                    catch (Exception e)
                    {
                        log.error("Close BufferedOutputStream error. The error is {}",
                                e.getMessage());
                    }
                }
                
                if (null != bis)
                {
                    try
                    {
                        bis.close();
                    }
                    catch (Exception e)
                    {
                        log.error("Close BufferedInputStream error. The error is {}", 
                                e.getMessage());
                    }
                }
                if (null != fs)
                {
                    try
                    {
                        fs.close();
                    }
                    catch (Exception e)
                    {
                        log.error("Close FileInputStream error. The error is {}", 
                                e.getMessage());
                    }
                }
               
            }

        }
        catch (Throwable throwable)
        {
            log.error("failed",throwable);
        }
     }
    
    
    public static boolean isInSecureDir(File f)
        throws IOException
    {
        Path path = Paths.get(f.getCanonicalPath());
        return isInSecureDir(path);
    }
    
    private static boolean isInSecureDir(Path path)
    {
        if (!path.isAbsolute())
        {
            path = path.toAbsolutePath();
        }
        
        for (int i = 1; i <= path.getNameCount(); i++)
        {
            Path partialPath = Paths.get(path.getRoot().toString(), path.subpath(0, i).toString());
            try
            {
                if (Files.isSymbolicLink(partialPath))
                {
                    PosixFileAttributes attr = Files.readAttributes(partialPath, PosixFileAttributes.class);
                    Set<PosixFilePermission> perms = attr.permissions();
                    if (perms.contains(PosixFilePermission.GROUP_WRITE)
                        || perms.contains(PosixFilePermission.OTHERS_WRITE))
                    {
                        return false;
                    }
                }
            }
            catch (IOException x)
            {
                return false;
            }
        }
        return true;
    }
}