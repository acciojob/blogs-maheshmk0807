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
    BlogRepository blogRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        //WHAT TO DO IF USER NOT FOUND

        try {
            User user = userRepository.findById(userId).get();
            Blog blog = new Blog(title, content, user);
            user.getBlogList().add(blog);
            userRepository.save(user);
            return blog;
        }
        catch (Exception e){
            //User mot found
            return null;
        }
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        //UNVERIFIED
        for(Image i : blogRepository.findById(blogId).get().getImageList())
        {
            imageRepository.deleteById(i.getId());
        }
        User user = blogRepository.findById(blogId).get().getUser();
        blogRepository.deleteById(blogId);
        for(Blog b : user.getBlogList()){
            if(b.getId()==blogId)
                user.getBlogList().remove(b);
        }
        userRepository.save(user);
    }
}

