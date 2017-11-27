package vip.adog.service;

import org.springframework.stereotype.Service;
import vip.adog.model.Photo;
import vip.adog.model.PhotoDao;
import vip.adog.model.PhotoRepository;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {

    @Resource
    private PhotoDao photoDao;
    @Resource
    private PhotoRepository photoRepository;

    @Transient
    public void save(Photo photo){
        photoRepository.save(photo);
    }

    public Photo getPhoto(long id){
        return photoRepository.findOne(id);
    }

    public List<Photo> getPhotoAll(){
        Iterable<Photo> photoIterable = photoRepository.findAll();
        List<Photo> photoList = new ArrayList<>();
        photoIterable.forEach(photo -> photoList.add(photo));
        return photoList;
    }
}
