package com.blogapplication.project.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.blogapplication.project.entity.Post;

public class FileUtils {

	public static String uploadImage(Post post) throws IOException {
		
		  String filePath="/opt/blogappImages/"+post.getFile().getOriginalFilename();
           if (!post.getFile().isEmpty()) {
       		  Files.copy(post.getFile().getInputStream(), 
       				  Paths.get(filePath),
       				  StandardCopyOption.REPLACE_EXISTING);

		   }
           
           return filePath;
	}
}
