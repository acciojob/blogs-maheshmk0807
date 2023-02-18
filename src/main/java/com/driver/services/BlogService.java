package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;
    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) throws Exception {
        //create a blog at the current time
        //WHAT TO DO IF USER NOT FOUND
        try {
            User user = userRepository1.findById(userId).get();
            Blog blog = new Blog(title, content, user);
            user.getBlogList().add(blog);
            userRepository1.save(user);
            return blog;
        }
        catch (Exception e){
            return new Blog();
        }
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        //UNVERIFIED
//        for(Image i : blogRepository1.findById(blogId).get().getImageList())
//        {
//            imageRepository1.deleteById(i.getId());
//        }
//        User user = blogRepository1.findById(blogId).get().getUser();
//        blogRepository1.deleteById(blogId);
//        for(Blog b : user.getBlogList()){
//            if(b.getId()==blogId)
//                user.getBlogList().remove(b);
//        }
//        userRepository1.save(user);
        blogRepository1.deleteById(blogId);
    }
}
