package com.api.springMainApp.imagesRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.springMainApp.imageEntry.ImageEntry;

@Repository
public interface ImagesRepository extends CrudRepository<ImageEntry, String> {

}
