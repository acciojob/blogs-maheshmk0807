package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) throws Exception {
        //WHAT IF BLOG NOT FOUND
        try {
            Blog blog = blogRepository2.findById(blogId).get();
            Image image = new Image(description, dimensions, blog);
            blog.getImageList().add(image);
            blogRepository2.save(blog);
            return image;
        } catch (Exception e) {
            //BLOG NOT FOUND
           return new Image();
        }
    }

    public void deleteImage(Integer id) {
        //UNVERIFIED
//        Blog b = imageRepository2.findById(id).get().getBlog();
//        for (Image i : b.getImageList()) {
//            if (i.getId() == id)
//                b.getImageList().remove(i);
//        }
//        blogRepository2.save(b);

        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) throws Exception {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //CP-- TO DO AGAIN
        try{
            String[] scrarray = screenDimensions.split("X");
            Image image = imageRepository2.findById(id).get();

            String imageDimensions = image.getDimensions();
            String[] imgarray = imageDimensions.split("X");

            int scrl = Integer.parseInt(scrarray[0]);
            int scrb = Integer.parseInt(scrarray[1]);

            int imgl = Integer.parseInt(imgarray[0]);
            int imgb = Integer.parseInt(imgarray[1]);

            return no_Images(scrl, scrb, imgl, imgb);
        }
        catch (Exception e){
            return 0;
        }
    }

    private int no_Images(int scrl, int scrb, int imgl, int imgb) {
        int lenC = scrl / imgl;
        int lenB = scrb / imgb;
        return lenC * lenB;
    }
}
